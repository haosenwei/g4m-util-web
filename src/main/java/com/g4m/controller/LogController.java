package com.g4m.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {
	private final static Logger logger = LoggerFactory.getLogger(LogController.class);

	@RequestMapping(value="/tolog")
	public String getJsoup() throws IOException {
		return "log";
	}	

}
