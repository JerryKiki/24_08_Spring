package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//요청을 처리하기 전에 인터셉트해서 필요한 검사를 수행 후 원래 경로로 보냄

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {

	@Autowired
	private Rq rq;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

		rq.initBeforeActionInterceptor();

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}