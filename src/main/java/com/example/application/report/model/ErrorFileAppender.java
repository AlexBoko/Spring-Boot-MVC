package com.example.application.report.model;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;

public class ErrorFileAppender extends AppenderBase<ILoggingEvent> {

    private Layout<ILoggingEvent> layout;

    public ErrorFileAppender(Layout<ILoggingEvent> layout) {
        this.layout = layout;
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (isStarted() && event.getLevel().isGreaterOrEqual(Level.ERROR)) {
            String formattedMessage = layout.doLayout(event);
            // Обработка отформатированного сообщения лога
        }
    }

    public void setLayout(Layout<ILoggingEvent> layout) {
        this.layout = layout;
    }

    public Layout<ILoggingEvent> getLayout() {
        return layout;
    }
}
