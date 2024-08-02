package com.example.user.config;




import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;


@Component
@Configuration
public class CorsFilter implements Filter {

   
    public CorsFilter() {
        
    }

	@Override
	public void destroy() {
		
	}
	
		@Override
		public void init(FilterConfig filterConfig) throws ServletException{
			
		}
	
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		 	HttpServletResponse Response = (HttpServletResponse) response;
	        Response.setHeader("Access-Control-Allow-Origin", "*");
	        Response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	        Response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	        chain.doFilter(request, response);
		
		
	}

}

