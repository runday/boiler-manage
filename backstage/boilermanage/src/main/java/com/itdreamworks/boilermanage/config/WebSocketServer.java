package com.itdreamworks.boilermanage.config;


import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{userId}", configurator = WebSocketConfiguration.class)
@Component
public class WebSocketServer {

    //当前登陆的用户map
    private static Map<String, String> loginUserMap = new ConcurrentHashMap<String, String>();

    //连接
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        if(loginUserMap.containsKey(userId)){
            this.sendMsg(session,"false");
        }else{
            loginUserMap.put(userId,userId);
            this.sendMsg(session,"true");
        }
    }

    //关闭
    @OnClose
    public void onClose(Session session,@PathParam("userId") String userId) {
        loginUserMap.remove(userId);
    }

    //异常
    @OnError
    public void onError(Session session,Throwable throwable) {}

    //统一的发送消息方法
    public synchronized void sendMsg(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
