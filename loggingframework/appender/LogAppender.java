package loggingframework.appender;

import loggingframework.entities.LogMessage;
import loggingframework.formatter.LogFormatter;
import loggingframework.formatter.SimpleTextLogFormatter;
import loggingframework.formatter.JsonLogFormatter;

public abstract class LogAppender {
    protected LogFormatter formatter;

    public LogAppender() {
        this.formatter = new JsonLogFormatter();
    }

    public LogFormatter getFormatter() {return this.formatter;}
    public void setFormatter(LogFormatter formatter) {this.formatter = formatter;}
    public abstract void append(LogMessage message);
    public abstract void close();
}