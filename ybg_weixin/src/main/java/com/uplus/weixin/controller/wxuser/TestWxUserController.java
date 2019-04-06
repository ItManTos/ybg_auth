package com.uplus.weixin.controller.wxuser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uplus.wei.api.weixin.entity.WxUser;
import com.uplus.wei.api.weixin.entity.WxUserTag;
import com.uplus.wei.api.weixin.service.WxUserService;
import com.uplus.wei.api.weixin.service.WxUserTagService;
import com.uplus.wei.util.Result;
import com.uplus.weixin.config.WxMpConfig;

import cn.hutool.core.util.StrUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

@RestController
@RequestMapping("/api/")
public class TestWxUserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	WxUserService wxUserService;
	@Autowired
	WxUserTagService wxUserTagService;

//127.0.0.1:8091/uplus_weixin/api/userList?appid=wxbed172448f475c85
	@GetMapping("userList")
	public Result getUserList(String appid) {
		sync(appid, null);
//		try {
//			sync(appid);
//		} catch (WxErrorException e) {
//			e.printStackTrace();
//		}
		return Result.success();
	}

	/***
	 * 拉取这个公众号的userid
	 *
	 * @param appid
	 * @param NextOpenid
	 * @throws WxErrorException
	 */
	private void sync(String appid) throws WxErrorException {
		WxMpService mpService = WxMpConfig.getMpServices().get(appid);
		List<String> openidListIn100 = new ArrayList<>();
		openidListIn100.add("oux9Ws--GbukOrr4Umri3XTOu0Z0");
		List<WxMpUser> WxMpUserlist = mpService.getUserService().userInfoList(openidListIn100);
		logger.info("getSubscribe=" + WxMpUserlist.get(0).getSubscribe());
		WxMpUser u = WxMpUserlist.get(0);

		WxUser entity = new WxUser();
		BeanUtils.copyProperties(u, entity);
		entity.setNickname(u.getNickname());
		entity.setAppId(appid);
		entity.setSubscribe(u.getSubscribe() ? 1 : 0);
		entity.setSubscribeTime(new BigDecimal(u.getSubscribeTime()));
		QueryWrapper<WxUser> wrapper = new QueryWrapper<WxUser>();
		wrapper.eq(WxUser.OPEN_ID, u.getOpenId());
		wrapper.eq(WxUser.APP_ID, appid);
		WxUser dbuser = wxUserService.getOne(wrapper);
		if (dbuser == null) {
			wxUserService.save(entity);
		} else {
			wxUserService.update(entity, wrapper);
		}

	}

	/***
	 * 拉取这个公众号的userid
	 *
	 * @param appid
	 * @param NextOpenid
	 */
	private void sync(String appid, String NextOpenid) {
		WxMpService mpService = WxMpConfig.getMpServices().get(appid);
		try {
			WxMpUserList list = mpService.getUserService().userList(NextOpenid);
			List<String> openIdlist = list.getOpenids();
			logger.info("总数" + list.getCount());

			for (int i = 0; i < openIdlist.size();) {// 大概一万个记录
				logger.info("索引：" + i);
				int maxindex = i + 100 > openIdlist.size() ? openIdlist.size() : i + 100;
				List<String> openidListIn100 = new ArrayList<>();
				for (int j = i; j < maxindex; j++) {
					openidListIn100.add(openIdlist.get(j));
				}
				List<WxMpUser> WxMpUserlist = mpService.getUserService().userInfoList(openidListIn100);
				for (WxMpUser wxMpUser : WxMpUserlist) {
					SyncThreadPool.syncUser.submit(new Runnable() {
						@Override
						public void run() {
							WxUser entity = new WxUser();
							BeanUtils.copyProperties(wxMpUser, entity);
							entity.setAppId(appid);
							entity.setSubscribe(wxMpUser.getSubscribe() ? 1 : 0);
							QueryWrapper<WxUser> wrapper = new QueryWrapper<WxUser>();
							wrapper.eq(WxUser.OPEN_ID, wxMpUser.getOpenId());
							wrapper.eq(WxUser.APP_ID, appid);
							WxUser dbuser = wxUserService.getOne(wrapper);
							Long[] tagsId = wxMpUser.getTagIds();
							if (tagsId != null && tagsId.length > 0) {
								for (Long tagId : tagsId) {
									if (tagId != null) {
										// 这里不会删除已经取消的打标签，业务需要的话自己写代码删除 呵呵
										WxUserTag wxUsertag = new WxUserTag();
										wxUsertag.setAppId(appid);

										wxUsertag.setCreateTime(LocalDateTime.now());
										wxUsertag.setModifiedTime(LocalDateTime.now());
										wxUsertag.setOpenId(wxMpUser.getOpenId());
										wxUsertag.setTagId(tagId);
										wxUsertag.setTagName("");// 自己写吧
										wxUserTagService.save(wxUsertag);
									}
								}
							}

							if (dbuser == null) {
								wxUserService.save(entity);
							} else {
								wxUserService.update(entity, wrapper);
							}

						}
					});
					logger.info(wxMpUser.toString());
				}
				i += 100;
			}
			String nextOpenid = list.getNextOpenid();
			logger.info("下一批的微信ID是" + nextOpenid);
			if (StrUtil.isNotBlank(nextOpenid)) {
				// 递归直到同步完成
				sync(appid, nextOpenid);
			}

		} catch (WxErrorException e) {
			e.printStackTrace();
			logger.error("错误", e);
		}
	}
}
