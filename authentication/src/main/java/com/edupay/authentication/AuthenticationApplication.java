package com.edupay.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




@SpringBootApplication
@ComponentScan({"com.edupay"})
@EntityScan({"com.edupay.authentication.data.model"})
@EnableCaching(proxyTargetClass = true)
public class AuthenticationApplication {
	
@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(); 
}
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
