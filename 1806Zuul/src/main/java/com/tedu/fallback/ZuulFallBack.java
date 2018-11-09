package com.tedu.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component //包扫描
public class ZuulFallBack implements ZuulFallbackProvider{

	@Override //获取路由
	public String getRoute() {
		
		return "1806provider-user";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders header=new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return header;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				String returnValue="tony11";
				return new ByteArrayInputStream(returnValue.getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException {
				
				return HttpStatus.BAD_REQUEST.getReasonPhrase();
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				
				return HttpStatus.BAD_REQUEST;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
