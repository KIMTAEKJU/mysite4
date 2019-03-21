package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MVCConfig;
import com.douzone.config.web.MessagesConfig;
import com.douzone.config.web.SecurityConfig;

/*
 * WebConfig == spring-servlet.xml 
 * 메세지 컨버터, 뷰 리절브 다 여기서 설정
 */

@Configuration
//@EnableWebMvc // == <mvc:annotation-driven/>
@ComponentScan(value = { "com.douzone.mysite.controller", "com.douzone.mysite.exception" } )
@Import(value = {SecurityConfig.class, MVCConfig.class, MessagesConfig.class, FileUploadConfig.class})
public class WebConfig {
	
}
