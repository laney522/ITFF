package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * preHandle: DispatcherServlet이 handler 호출전
 * 
 * postHandle : handler가 DispatcherServlet 리턴 전
 * 
 * afterCompletion : view단 작업후
 *
 */
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.debug("==================== {} ====================", request.getRequestURI());   // 무조건 true
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
		
		log.debug("---------------------------------------------------------");
		log.debug("modelAndView = {}", modelAndView);
		log.debug("---------------------------------------------------------");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		log.debug("________________________ view ______________________");
		super.afterCompletion(request, response, handler, ex);
		log.debug("========================================== END ==========================================");
	}

	
}
