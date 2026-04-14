package loggingframework.entities;

import java.time.LocalDateTime;

import loggingframework.enums.LogLevel;

public class LogMessage {
    private LogLevel level;
    private String message;
    private String threadName;
    private LocalDateTime timestamp;
    private String loggerName;

    public LogMessage(LogLevel level, String message, String loggerName) {
        this.level = level;
        this.message = message;
        this.loggerName = loggerName;
        this.timestamp = LocalDateTime.now();
        this.threadName = Thread.currentThread().getName();
    }

    public LogLevel getLevel() {return this.level;}
    public String getMessage() {return this.message;}
    public String getThreadName() {return this.threadName;}
    public String getLoggerName() {return this.loggerName;}
    public LocalDateTime getTimestamp() {return this.timestamp;}
}