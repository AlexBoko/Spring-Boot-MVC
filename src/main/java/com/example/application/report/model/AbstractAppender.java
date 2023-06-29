package com.example.application.report.model;

import org.apache.logging.log4j.core.AbstractLogEvent;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;

@Plugin(name = "CustomAppender", category = "Core", elementType = "appender", printObject = true)
class CustomAppender extends AbstractAppender {

    protected CustomAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    @PluginFactory
    public static CustomAppender createAppender(@PluginAttribute("name") String name,
                                                @PluginAttribute(value = "ignoreExceptions", defaultBoolean = true) boolean ignoreExceptions) {
        if (name == null) {
            LOGGER.error("No name provided for CustomAppender");
            return null;
        }
        Layout<? extends Serializable> layout = PatternLayout.newBuilder()
                .withPattern("%d [%t] %-5level %logger{36} - %msg%n")
                .build();
        return new CustomAppender(name, null, layout, ignoreExceptions);
    }

    @Override
    public void append(LogEvent event) {

    }
}
