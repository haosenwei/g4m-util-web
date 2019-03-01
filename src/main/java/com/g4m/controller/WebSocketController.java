package com.g4m.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.g4m.websocket.DanMuWebsocket;
import com.g4m.websocket.LogWebSocketHandle;

@Controller
public class WebSocketController {
	private final static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

	@Autowired
	DanMuWebsocket danMuWebsocket;
	@RequestMapping(value="/tolog")
	public String tolog() throws IOException {
		return "util/log";
	}	
	@RequestMapping(value="/todm")
	public String todm() throws IOException {
		return "util/dm";
	}	
	@RequestMapping(value="/send",method=RequestMethod.POST)
	@ResponseBody
	public void send(String msg,HttpServletRequest request) throws IOException {
		if(msg != null) {
			danMuWebsocket.sendInfo(msg);
		}

		ServletInputStream inputStream = request.getInputStream();
		byte[] bytes = new byte[0];
		bytes = new byte[inputStream.available()];
		inputStream.read(bytes);
		String str = new String(bytes);
		if(str != null) {
			danMuWebsocket.sendInfo(str);
		}

		Enumeration<String> y = request.getHeaderNames();
		while (y.hasMoreElements()) {
			String param = y.nextElement();
			String value = request.getHeader(param);
			System.out.println(param + "=" + value);
		}

		System.out.println("====================================");

		Enumeration<String> x = request.getParameterNames();
		while (x.hasMoreElements()) {
			String param = x.nextElement();
			String value = request.getParameter(param);
			System.out.println(param + "=" + value);
		}

	}
}	

