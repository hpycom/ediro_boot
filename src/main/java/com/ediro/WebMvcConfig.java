package com.ediro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	 @Value("${bookimage.uploaded}")
	   private  String UPLOADED_FOLDER;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry
	      .addResourceHandler("/bookImages/**")
	      //.addResourceLocations("../upload/coverimage/");
	      .addResourceLocations("file:///"+UPLOADED_FOLDER);
	      //.addResourceLocations(UPLOADED_FOLDER);
	 }
}
