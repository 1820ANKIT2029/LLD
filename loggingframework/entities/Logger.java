package loggingframework.entities;

import java.util.List;
import java.util.ArrayList;

import loggingframework.enums.LogLevel;
import loggingframework.appender.LogAppender;

public class Logger {
    private Logger parent;
    private List<LogAppender> appenders;
    private String name;
    private LogLevel level;
    private boolean additivity;

    public Logger( String name, Logger parent) {
        this.parent = parent;
        this.name = name;
        this.additivity = true;
        this.appenders = new ArrayList<>();
    }

    public void addAppender(LogAppender appender) {this.appenders.add(appender);}
    public void setAdditivity(boolean additivity) {this.additivity = additivity;}
    public void setLevel(LogLevel level) {this.level = level;}
    public List<LogAppender> getAppenders() {return this.appenders;}
    public LogLevel getEffectiveLevel() {
        Logger logger = this;
        while(logger != null) {
            LogLevel curLevel = logger.level;
            if(curLevel != null) {
                return curLevel;
            }

            logger = logger.parent;
        }

        return LogLevel.DEBUG;
    }
    public void log(LogLevel level, String message) {
        if(level.isGreaterOrEqual(this.getEffectiveLevel())) {
            LogMessage logMessage = new LogMessage(level, message, this.name);
            this.callAppenders(logMessage);
        }
    }
    private void callAppenders(LogMessage logMessage) {
        if(this.appenders != null) {
            LogManager.getInstance().getProcessor().process(logMessage, this.appenders);
        } 

        if(this.additivity && this.parent != null) {
            this.parent.callAppenders(logMessage);
        }
    }
    public void debug(String message) {this.log(LogLevel.DEBUG, message);}
    public void error(String message) {this.log(LogLevel.ERROR, message);}
    public void fatal(String message) {this.log(LogLevel.FATAL, message);}
    public void info(String message) {this.log(LogLevel.INFO, message);}
    public void warn(String message) {this.log(LogLevel.WARN, message);}
}