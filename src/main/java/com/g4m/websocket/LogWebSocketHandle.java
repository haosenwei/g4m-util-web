package com.g4m.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint(value="/loglog")
@Component
public class LogWebSocketHandle {

	private final static Logger logger = LoggerFactory.getLogger(LogWebSocketHandle.class);
	


	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<LogWebSocketHandle> webSocketSet = new CopyOnWriteArraySet<LogWebSocketHandle>();

	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法*/
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);     //加入set中
		addOnlineCount();           //在线数加1
		logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
		try {
			sendMessage("欢迎加入");
		} catch (IOException e) {
			logger.info("IO异常");
		}
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息*/
	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("来自客户端的消息:" + message);

		//群发消息
		for (LogWebSocketHandle item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
		//this.session.getAsyncRemote().sendText(message);
	}


	/**
	 * 群发自定义消息
	 * */
	public static void sendInfo(String message) throws IOException {
		for (LogWebSocketHandle item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		LogWebSocketHandle.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		LogWebSocketHandle.onlineCount--;
	}
}
