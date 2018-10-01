package com.uplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

//import com.leftso.entity.Account;
//import com.leftso.repository.AccountRepository;

@ComponentScan("com.uplus")
@MapperScan("com.uplus.wei.**.dao")
@ServletComponentScan
@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
