package com.example.application.report.model;

import org.apache.logging.log4j.core.AbstractAppender;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;

@Plugin(name = "ErrorFileAppender", category = "Core", elementType = Appender.ELEMENT_TYPE, printObject = true)
public class ErrorFileAppender extends AbstractAppender {

    protected ErrorFileAppender(String name, Layout<? extends Serializable> layout) {
        super(name, null, layout, false);
    }

    @PluginFactory
    public static ErrorFileAppender createAppender(@PluginAttribute("name") String name) {
        if (name == null) {
            LOGGER.error("No name provided for ErrorFileAppender");
            return null;
        }
        Layout<? extends Serializable> layout = PatternLayout.newBuilder()
                .withPattern("%d [%t] %-5level %logger{36} - %msg%n")
                .build();
        return new ErrorFileAppender(name, layout);
    }

    @Override
    public void append(LogEvent event) {
        if (event.getLevel().isMoreSpecificThan(org.apache.logging.log4j.Level.ERROR)) {
            super.append(event);
        }
    }
}
