package com.g4m.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.g4m.util.GetIpUtil;
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
	public void send(HttpServletRequest request) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		// Read from request
        StringBuilder buffer = new StringBuilder();
        String ip = GetIpUtil.getIp(request);
        map.put("ip", ip);
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        if(buffer.toString()!=null && buffer.toString().length()>0) {
        	map.put("log", buffer.toString());
        	LogWebSocketHandle.sendInfo(JSON.toJSONString(map));
        }
	}
}	

