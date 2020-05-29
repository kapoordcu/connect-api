package com.api.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:graph.properties")
public class GraphConfigProperties {
    @Value("${graph.error:ERROR}")
    private String errorLabel;

    @Value("${graph.added:ADDED}")
    private String addedLabel;

    @Value("${graph.removed:REMOVED}")
    private String removedLabel;

    public String getErrorLabel() {
        return errorLabel;
    }

    public String getAddedLabel() {
        return addedLabel;
    }

    public String getRemovedLabel() {
        return removedLabel;
    }
}
