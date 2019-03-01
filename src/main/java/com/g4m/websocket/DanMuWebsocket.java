package com.g4m.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

////每个客户端连接成功的时候在后台都会创建一个相应的MyWebsocket类
//@ServerEndpoint("/danmu")
//@Component
//public class DanMuWebsocket {
//
//	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
//	private static CopyOnWriteArraySet<DanMuWebsocket> websocketPools=new CopyOnWriteArraySet<DanMuWebsocket>();
//
//	//与某个客户端的连接会话，需要通过它来给客户端发送数据
//	private Session session;
//
//	/**
//	 * 连接建立成功调用的方法
//	 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
//	 */
//	@OnOpen
//	public void onOpen(Session session)
//	{
//		this.session=session;
//		websocketPools.add(this);
//	}
//
//	@OnClose
//	public void onClose()
//	{
//		websocketPools.remove(this);
//	}
//
//	@OnMessage
//	public void onMessage(String message,Session session)
//	{
//		for(DanMuWebsocket item:websocketPools)
//		{
//			try {
//				item.send(message);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void sendMsg(String a ) {
//		for(DanMuWebsocket item:websocketPools)
//		{
//			try {
//				item.send(a);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private void send(String message) throws IOException{
//		this.session.getAsyncRemote().sendText(message);
//		//this.session.getBasicRemote().sendText(message);
//
//	}
//
//
//	@OnError
//	public void onError(Session session, Throwable error)
//	{
//		System.out.println("发生错误");
//		error.printStackTrace();
//	}
//}


@ServerEndpoint(value = "/danmu")
@Component
public class DanMuWebsocket {
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<DanMuWebsocket> webSocketSet = new CopyOnWriteArraySet<DanMuWebsocket>();

	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法*/
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);     //加入set中
		addOnlineCount();           //在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
		try {
			sendMessage("aaa");
		} catch (IOException e) {
			System.out.println("IO异常");
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);  //从set中删除
		subOnlineCount();           //在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息*/
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("来自客户端的消息:" + message);

		//群发消息
		for (DanMuWebsocket item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发生错误时调用*/
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}


	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
		//this.session.getAsyncRemote().sendText(message);
	}


	/**
	 * 群发自定义消息
	 * */
	public static void sendInfo(String message) throws IOException {
		for (DanMuWebsocket item : webSocketSet) {
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
		DanMuWebsocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		DanMuWebsocket.onlineCount--;
	}
}
