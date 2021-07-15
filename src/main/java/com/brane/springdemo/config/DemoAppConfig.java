package com.brane.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


//THIS IS JAVA CONFIG CLASS.WE ARE USING PURE JAVA CONFIG, NO XML
@Configuration
//with this annotation we enable Spring MVC
@EnableWebMvc
//base package where to scan components for Controller class,Service class,Reporistory class...
@ComponentScan("com.brane.springdemo")
//Classpath is a parameter in the Java Virtual Machine or the Java compiler 
//that specifies the location of user-defined classes and packages. 
//The parameter may be set either on the command-line, or through an environment variable.
@PropertySource({ "classpath:persistence-mysql.properties" })
//with this interface WebMvcConfigurer we can make our custom configuration for SPRING MVC
//all methods are default methods, which are not implemented in this class
//Evrey class which have this annotation @EnableWebMvc can implement this interface WebMvcConfigurer
public class DemoAppConfig implements WebMvcConfigurer {

		
	//define a bean for ViewResolver, because we are using JSP pages
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		//set prefix
		viewResolver.setPrefix("/WEB-INF/view/");
		
		//set suffix
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	//Define bean for RestTemplate, this object(RestTemplate) is used to make client REST calls.
	//We can now easily do dependency injection if is necessary.
	//this code also loads .properties file from @PropertySource annotation.
	//Rest Template provides us executing http request methods, exposing REST API what we made.
	//REST TEMPLATE is using for creating application which using REST WEB SERVICES.
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	//Add resource handler for loading css, images, etc
	//OVERRIDE method from the WebMvcConfigurer interface
	//This method is like handler for static resources like:pictures, js, css...
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**")
  
          .addResourceLocations("/resources/"); 
    }
}









