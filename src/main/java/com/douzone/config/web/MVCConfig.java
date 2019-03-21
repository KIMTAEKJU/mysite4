package com.douzone.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
@EnableWebMvc
public class MVCConfig extends WebMvcConfigurerAdapter{ // 필요한것들만 
	
	// View Resolver
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver resolver = 
				new InternalResourceViewResolver();
		
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		
		return resolver;
	}

	
	//Falling Back On the DefaultServlet To Serve Resources
	// 디폴트서블릿 핸들러 작동
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) 
	{
		configurer.enable();
	}

	// Message Converters http://wonwoo.ml/index.php/post/889 참조
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter()
	{
		Jackson2ObjectMapperBuilder builder = 
				new Jackson2ObjectMapperBuilder();
//					.indentOutput(true)
//						.dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
//							.modulesToInstall(new ParameterNamesModule());
			
		MappingJackson2HttpMessageConverter converter = 
				new MappingJackson2HttpMessageConverter(builder.build());
		converter.setSupportedMediaTypes( Arrays.asList( new MediaType( "application", "json", Charset.forName("utf-8") ) ) );
		return converter;
	}
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter()
	{
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		// mediaType으로 값을 넣는건 string으로 발생할수있는 에러를 막기위해
		converter.setSupportedMediaTypes( Arrays.asList( new MediaType( "text", "html", Charset.forName("utf-8") ) ) );
		return converter;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) 
	{
		// 오버라이딩 메소드라서 빈을 여기다가 와이어링할수없음
		// 그래서 메소드를 만들어서 여기에 호출
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter()); // 안에 컨버터을 넣어줌 
		
	}

}
