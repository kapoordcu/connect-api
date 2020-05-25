package com.api.backend.message;

import com.api.backend.model.Command;

public class UnknownCommandStrategy  implements CommandStrategy {
    @Override
    public String executeCommand(Command command) {
        return null;
    }
}
