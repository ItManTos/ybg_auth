package com.uplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@MapperScan("com.uplus.wei.**.dao")
@ServletComponentScan
@SpringBootApplication
public class WeixinApplication {
	/***
	 * demo参考https://gitee.com/binary/weixin-java-mp-demo-springboot
	 * 配置微信公众号中的接口地址：http://www.88ybg.com/wx/portal/xxxxx
	 * （注意xxxxx为对应公众号的appid值，www.88ybg.com要跟上面的一致，需要符合微信官方的要求）；
	 * 根据自己需要修改各个handler的实现，加入自己的业务逻辑。<br>
	 * 本地 <br>
	 * http://www.88ybg.com.tunnel.qydev.com/wx/portal/wxc6df6f48e697742c <br>
	 * 线上 <br>
	 * http://www.88ybg.com/wx/portal/wxc6df6f48e697742c
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(WeixinApplication.class, args);
	}

}
