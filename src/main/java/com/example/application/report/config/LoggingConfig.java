package com.example.application.report.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @Value("${logging.file.name}")
    private String logFileName;

    @Bean
    public FileAppender fileAppender() {
        RollingFileAppender fileAppender = new RollingFileAppender();
        fileAppender.setName("FILE");
        fileAppender.setFile(logFileName);

        SizeBasedTriggeringPolicy triggeringPolicy = new SizeBasedTriggeringPolicy();
        triggeringPolicy.setMaxFileSize(FileSize.valueOf("10MB"));
        fileAppender.setTriggeringPolicy(triggeringPolicy);

        TimeBasedRollingPolicy rollingPolicy = new TimeBasedRollingPolicy();
        rollingPolicy.setFileNamePattern(logFileName + ".%d{yyyy-MM-dd}.gz");
        rollingPolicy.setMaxHistory(7);
        fileAppender.setRollingPolicy(rollingPolicy);

        LayoutWrappingEncoder<ILoggingEvent> encoder = new LayoutWrappingEncoder<>();
        encoder.setLayout(createLayout());
        fileAppender.setEncoder(encoder);

        fileAppender.start();

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger("ROOT").addAppender(fileAppender);

        return fileAppender;
    }

    private Layout<ILoggingEvent> createLayout() {


        return null;
    }
}