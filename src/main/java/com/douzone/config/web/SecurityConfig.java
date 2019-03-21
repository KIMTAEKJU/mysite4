package com.douzone.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.security.AuthInterceptor;
import com.douzone.security.AuthLoginInterceptor;
import com.douzone.security.AuthLogoutInterceptor;
import com.douzone.security.AuthUserHandlerMethodArgumentResolver;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebMvcConfigurerAdapter {

//	@Bean 
//	public SecurityController sc()
//	{
//		return new SecurityController();
//	}
	// Argument Resolver 

	public AuthUserHandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver()
	{
		return new AuthUserHandlerMethodArgumentResolver();
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add( authUserHandlerMethodArgumentResolver() );
	}

	
	
	// Interceptor
	
	@Bean
	public AuthLoginInterceptor authLoginInterceptor()
	{
		return new AuthLoginInterceptor();
	}
	
	@Bean
	public AuthLogoutInterceptor authLogoutInterceptor()
	{
		return new AuthLogoutInterceptor();
	}
	
	@Bean
	public AuthInterceptor authInterceptor()
	{
		return new AuthInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) 
	{
		
		registry
		.addInterceptor( authLoginInterceptor() )
		.addPathPatterns( "/user/auth" ); // 디폴트서블릿핸들러에 걸려서 작동을 잘안함 (뒤에 아무것도없어서)   javaConfig에서
		// 답은 xml로 설정하든지 or 컨트롤러에 매핑해서 메소드하나 만듬 껍데기로
		
		registry
		.addInterceptor( authLogoutInterceptor() )
		.addPathPatterns( "/user/logout" );
		
		registry
		.addInterceptor( authInterceptor() ) // 뒤에 핸들러가있음 (뒤에 보낼놈이있다?) 그래서 디폴트서블릿에 거릴지ㅏㅇㄶ음
		.addPathPatterns( "/**" )
		.excludePathPatterns( "/user/auth")
		.excludePathPatterns( "/user/logout")
		.excludePathPatterns( "/assets/*");
	}
}
