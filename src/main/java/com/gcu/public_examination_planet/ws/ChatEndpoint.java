package com.gcu.public_examination_planet.ws;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gcu.public_examination_planet.config.GetHttpSessionConfig;
import com.gcu.public_examination_planet.domain.User;
import com.gcu.public_examination_planet.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/1 0:21
 **/
@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfig.class)
@Component
public class ChatEndpoint {

    private static final Map<String,Session> onlineUsers = new ConcurrentHashMap<>();

    private HttpSession httpSession;

    @Resource
    UserService userService;

    /**
     * 建立websocket连接后，被调用
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig){
        //将session进行保存
        onlineUsers.put("1",session);

        System.out.println("有人来了");
    }

    @OnMessage
    public void onMessage(String message){
        JSONObject messageObject = JSONUtil.parseObj(message);
        String content = (String)messageObject.get("content");
        System.out.println(content);
        User user = new User();
        user.setUserName("zkl");
        try {
            onlineUsers.get("1").getBasicRemote().sendText(JSONUtil.toJsonStr(user));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnClose
    public void OnClose(Session session){

    }
}
