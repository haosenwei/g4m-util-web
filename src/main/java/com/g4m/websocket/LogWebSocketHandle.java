package com.g4m.websocket;

import java.io.IOException;
import java.io.InputStream;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.g4m.controller.JsoupController;
import com.g4m.thread.TailLogThread;

@ServerEndpoint("/log")
public class LogWebSocketHandle {

	private final static Logger logger = LoggerFactory.getLogger(LogWebSocketHandle.class);
	
	private Process process;
	private String name ;
	private InputStream inputStream;

	/**
	 * 新的WebSocket请求开启
	 */
	@OnOpen
	public void onOpen(Session session) {
		try {
			// 执行tail -f命令
			process = Runtime.getRuntime().exec("tail -f /root/.jenkins/workspace/g4m-util-web/target/8080.log ");
			inputStream = process.getInputStream();
			
			// 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
			TailLogThread thread = new TailLogThread(inputStream, session);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@OnMessage
	public void onMessage(String message,Session session) throws IOException {
		logger.info(message);
	}


	/**
	 * WebSocket请求关闭
	 */
	@OnClose
	public void onClose() {
		try {
			if(inputStream != null)
				inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(process != null)
			process.destroy();
	}

	@OnError
	public void onError(Throwable thr) {
		thr.printStackTrace();
	}
}
