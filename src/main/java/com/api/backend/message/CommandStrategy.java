package com.api.backend.message;

import com.api.backend.model.Command;

public interface CommandStrategy {
    String executeCommand(Command command);
}
