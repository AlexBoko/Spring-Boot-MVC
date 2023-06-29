package com.example.application.report.config;

import com.example.application.report.model.ErrorFileAppender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @Value("${logging.file.name}")
    private String logFileName;

    @Bean
    public ErrorFileAppender errorFileAppender() {
        return ErrorFileAppender.createAppender(logFileName);
    }

    @Bean
    public org.apache.logging.log4j.core.LoggerContext loggerContext(ErrorFileAppender errorFileAppender) {
        org.apache.logging.log4j.core.LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        context.getConfiguration().addAppender(errorFileAppender);
        context.getRootLogger().addAppender(errorFileAppender, null, null);
        context.updateLoggers();
        return context;
    }
