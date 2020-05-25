package com.api.backend.resource;

import com.api.backend.model.BroadcastMessage;
import com.api.backend.model.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class CommandController {
    @Value("${ws.topic}")
    private String wsTopic;

    @Value("${ws.reply}")
    private String wsReply;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/listen")
    public void processMessageFromClient(@Payload Command command, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        headerAccessor.setSessionId(sessionId);
        messagingTemplate.convertAndSend(wsTopic + wsReply, new BroadcastMessage("Hello, " + HtmlUtils.htmlEscape(command.getName()) + "!"));
    }
}
