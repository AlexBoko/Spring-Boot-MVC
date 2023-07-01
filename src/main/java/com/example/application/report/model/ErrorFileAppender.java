package com.example.application.report.model;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;

public class ErrorFileAppender extends FileAppender<ILoggingEvent> {

    private Level minLogLevel = Level.ERROR;

    private String logFilePath;

    @Override
    public void start() {
        if (logFilePath != null && !logFilePath.isEmpty()) {
            setFile(logFilePath);
        }
        super.start();
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (eventObject.getLevel().isGreaterOrEqual(minLogLevel)) {
            super.append(eventObject);
        }
    }

    public void setMinLogLevel(Level minLogLevel) {
        this.minLogLevel = minLogLevel;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public Level getMinLogLevel() {
        return minLogLevel;
    }
}
