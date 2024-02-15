package com.gcu.public_examination_planet.ws;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gcu.public_examination_planet.dto.SendMessage;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/1 0:21
 **/
@ServerEndpoint(value = "/chat/{liveId}/{userId}/{userName}")
@Component
public class ChatEndpoint {

    private static final Map<Integer,Map<Integer,Session>> onlineUsers = new ConcurrentHashMap<>();

    private Integer liveId;

    private Integer userId;

    private String userName;

    /**
     * 建立websocket连接后，被调用
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("liveId") Integer liveId, @PathParam("userId") Integer userId, @PathParam("userName") String userName,Session session){
        this.liveId =liveId;
        this.userId = userId;
        this.userName = userName;
        Map<Integer, Session> room = onlineUsers.get(liveId);
        if (room != null && room.size() > 0){
            room.put(userId,session);
        }else {
            onlineUsers.put(liveId,new ConcurrentHashMap<>());
            Map<Integer, Session> newRoom = onlineUsers.get(liveId);
            newRoom.put(userId,session);
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setMessageType("system");
        sendMessage.setUserName(userName);
        sendMessage.setOnlineAmount(onlineUsers.get(liveId).size());
        broadcast(liveId,sendMessage);
    }

    @OnMessage
    public void onMessage(String message){
        JSONObject messageObject = JSONUtil.parseObj(message);
        Integer liveId = Integer.parseInt((String) messageObject.get("liveId"));
        String userName = (String)messageObject.get("userName");
        String msgContent = (String)messageObject.get("msgContent");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setMessageType("user");
        sendMessage.setUserName(userName);
        sendMessage.setMessageContent(msgContent);
        broadcast(liveId,sendMessage);
    }

    public void broadcast(Integer liveId, SendMessage sendMessage){
        Map<Integer, Session> room = onlineUsers.get(liveId);
        Collection<Session> sessions = room.values();
        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()){
            try {
                iterator.next().getBasicRemote().sendText(JSONUtil.toJsonStr(sendMessage));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @OnClose
    public void OnClose(Session session){
        Map<Integer, Session> room = onlineUsers.get(liveId);
        room.remove(userId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setMessageType("close");
        sendMessage.setOnlineAmount(room.size());
        broadcast(liveId,sendMessage);
    }
}
