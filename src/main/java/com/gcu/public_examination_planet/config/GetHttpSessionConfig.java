package com.gcu.public_examination_planet.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/1 0:40
 **/
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);
        //获取HttpSession对象
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        //将httpSession对象保存起来
//        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }
}
