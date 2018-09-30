package com.g4m;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class G4mUtilWebApplication extends SpringBootServletInitializer {
	
	private static Logger logger = LogManager.getLogger(G4mUtilWebApplication.class.getName());

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(G4mUtilWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(G4mUtilWebApplication.class, args);
		logger.info("启动成功");
	}
}
