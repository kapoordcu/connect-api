package com.api.backend.resource;

import com.api.backend.model.BroadcastMessage;
import com.api.backend.model.Command;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class CommandController {

    @MessageMapping("/listen")
    @SendTo("/topic/communication")
    public BroadcastMessage greeting(Command command) throws Exception {
        return new BroadcastMessage("Hello, " + HtmlUtils.htmlEscape(command.getName()) + "!");
    }
}
