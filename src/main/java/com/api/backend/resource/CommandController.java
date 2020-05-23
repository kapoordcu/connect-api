package com.api.backend.resource;

import com.api.backend.model.ClientCommand;
import com.api.backend.model.Greeting;
import com.api.backend.model.HelloMessage;
import com.api.backend.model.ServerReply;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class CommandController {

    @MessageMapping("/listen")
    @SendTo("/topic/communication")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
