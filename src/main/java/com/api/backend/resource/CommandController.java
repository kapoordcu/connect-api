package com.api.backend.resource;

import com.api.backend.model.BroadcastMessage;
import com.api.backend.model.Command;
import com.api.backend.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class CommandController {
    @Value("${ws.topic:/topic}")
    private String wsTopic;

    @Value("${ws.reply:/reply}")
    private String wsReply;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private CommandService commandService;

    @MessageMapping("/listen")
    public void processMessageFromClient(@Payload Command command, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        headerAccessor.setSessionId(sessionId);
        messagingTemplate.convertAndSend(wsTopic + wsReply,
                new BroadcastMessage(commandService.sendMessageToClient(command)));
    }

}
