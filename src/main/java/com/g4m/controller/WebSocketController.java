package com.g4m.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {
	private final static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

	@RequestMapping(value="/tolog")
	public String tolog() throws IOException {
		return "util/log";
	}	
	@RequestMapping(value="/todm")
	public String todm() throws IOException {
		return "util/dm";
	}	

}
