package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		HttpSession session = request.getSession();
//		Member loginMember = (Member) session.getAttribute("loginMember");
//		
//		if(loginMember == null) {
//			// 메시지 처리
//			FlashMap flashMap = new FlashMap();
//			flashMap.put("msg", "로그인후 이용할 수 있습니다.");
//			FlashMapManager manager = RequestContextUtils.getFlashMapManager(request);
//			manager.saveOutputFlashMap(flashMap, request, response);
//			
//			// 로그인 성공시 redirect 처리
//			// http://localhost:9090/member/memberDetail.do?tel=123&id=abc
//			String url =  request.getRequestURL().toString();   // http://localhost:9090/member/memberDetail.do
//			String queryString = request.getQueryString();   // tel=123&id=abc
//			log.debug("redirect = {}", url);
//			log.debug("queryString = {}", queryString);
//			if(queryString != null) 
//				url += "?" + queryString;
//			session.setAttribute("redirect", url);
//			
//			
//			response.sendRedirect(request.getContextPath() + "/member/memberLogin.do");
//			return false;
//		}
//		
//		return super.preHandle(request, response, handler);
//	}
	
}
