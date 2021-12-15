package com.kh.spring.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ws")
@Slf4j
public class WebSocketController {

	@GetMapping("/websocket.do")
	public void websocket() {}
	 
	@GetMapping("/sockjs.do")
	public void sockjs() {}
	
	@GetMapping("/stomp.do")
	public void stomp() {}
	
}
