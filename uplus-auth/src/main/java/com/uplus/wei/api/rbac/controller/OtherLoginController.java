package com.uplus.wei.api.rbac.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.uplus.wei.util.RedisClient;
import com.uplus.wei.util.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/login/")
public class OtherLoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OtherLoginController.class);

	/**
	 * 确保域名是自己发起的
	 *
	 * @param request
	 * @return
	 */
	public static boolean getAccessControlAllowOrigin(HttpServletRequest request) {
		String localName = request.getServerName();
		// 确保 名称是自己域名发起的

//		if (localName.contains("www.88ybg.com") || localName.contains("127.0.0.1")) {
//			return true;
//		}
//		return false;
		return true;
	}

	@Autowired
	private DefaultKaptcha defaultKaptcha;
	@Value("${oauth2.baseAuthUrl}")
	private String oauthBaseUrl;
	@Value("${oauth2.clientId}")
	private String oauthClientId;

	@Value("${oauth2.appSecret}")
	private String oauthSecret;
	@Autowired
	private TokenStore redisTokenStore;

	@RequestMapping(value = { "code" }, method = { RequestMethod.GET })
	public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		LOGGER.info("请求验证码");
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// 生产验证码字符串并保存到redis中
			String createText = defaultKaptcha.createText();
			RedisClient.set("logincode" + httpServletRequest.getRemoteAddr(), createText, 60);
			// 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = defaultKaptcha.createImage(createText);
			ImageIO.write(challenge, "jpg", jpegOutputStream);
		} catch (IllegalArgumentException e) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		// 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		httpServletResponse.setHeader("Cache-Control", "no-store");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}

	@ApiOperation(value = "登入")
	@PostMapping("loginIn")
	public Result login(HttpServletRequest request,
                        @ApiParam(name = "username", value = "账号", required = true) @RequestParam("username") String username,
                        @ApiParam(name = "password", value = "密码", required = true) @RequestParam("password") String password,
                        @ApiParam(name = "code", value = "验证码", required = true) @RequestParam("code") String code) {
		String redisCode = RedisClient.get("logincode" + request.getRemoteAddr());
		if (redisCode == null) {
			return Result.error("验证码不正确");
		}
		Map<String, Object> resultMap = new LinkedHashMap<>(4);
		Map<String, Object> formMap = new LinkedHashMap<>(5);
		if (!getAccessControlAllowOrigin(request)) {
			return Result.error("非法请求");
		}

		if (username != null && username.trim().length() > 0) {
			formMap.put("username", username);
			formMap.put("password", password);
			formMap.put("grant_type", "password");
			// spingboot2.0 不能穿头部验证了 传入clientId和密钥
			formMap.put("client_id", oauthClientId);
			formMap.put("client_secret", oauthSecret);
			LOGGER.info(oauthBaseUrl + "/oauth/token");
			String result = HttpRequest.post(oauthBaseUrl + "/oauth/token").form(formMap)
					// .basicAuth(oauthClientId, oauthSecret) springboot2.0 不能这么传
					.execute().body();
			LOGGER.info(result);
			JSONObject json = JSONObject.parseObject(result);
			try {
				resultMap.put("access_token", json.get("access_token").toString());
				resultMap.put("refresh_token", json.get("refresh_token").toString());
				resultMap.put("expires_in", json.get("expires_in").toString());
				resultMap.put("user", redisTokenStore.readAuthentication(json.get("access_token").toString()));

				return Result.successWithData(resultMap);
			} catch (Exception e) {
				e.printStackTrace();
				if (json.get("error_description") != null
						&& json.get("error_description").toString().trim().length() > 0) {
					return Result.error(json.get("error_description").toString());
				}

				return Result.error("账号密码错误");
			}

		}

		return Result.error("账号密码错误");

	}

	@ApiOperation(value = "登出")
	@GetMapping("loginOut")
	public Result loginOut(HttpServletRequest request,
                           @ApiParam(name = "access_token", value = "用户凭证", required = true) @RequestParam("access_token") String token) {

		if (!getAccessControlAllowOrigin(request)) {
			return Result.error("非法请求");
		}

		if (token != null) {
			OAuth2AccessToken accessToken = redisTokenStore.readAccessToken(token);
			redisTokenStore.removeAccessToken(accessToken);
		} else {
			return Result.error("非法token");
		}

		return Result.success();

	}

	@ApiOperation(value = "刷新凭证")
	@PostMapping("/refresh_token")
	public Result refresh_token(HttpServletRequest request,
                                @ApiParam(name = "refresh_token", value = "刷新凭证token", required = true) @RequestParam("refresh_token") String refresh_token) {
		Map<String, String> resultMap = new LinkedHashMap<>(3);
		if (!getAccessControlAllowOrigin(request)) {
			return Result.error("非法请求");
		}
		Map<String, Object> formMap = new LinkedHashMap<>(2);
		formMap.put("grant_type", "refresh_token");
		formMap.put("refresh_token", refresh_token);

		// spingboot2.0 不能穿头部验证了 传入clientId和密钥
		formMap.put("client_id", oauthClientId);
		formMap.put("client_secret", oauthSecret);
		String result = HttpRequest.post(oauthBaseUrl + "/oauth/token").form(formMap).

		// basicAuth(oauthClientId, oauthSecret).
				execute().body();

		JSONObject json = JSONObject.parseObject(result);
		try {

			resultMap.put("access_token", json.get("access_token").toString());
			resultMap.put("refresh_token", json.get("refresh_token").toString());
			resultMap.put("expires_in", json.get("expires_in").toString());
			return Result.successWithData(resultMap);
		} catch (Exception e) {

			return Result.error("refresh_token 过期");
		}

	}
}
