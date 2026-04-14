package loggingframework.appender;

import loggingframework.entities.LogMessage;

public class ConsoleLogAppender extends LogAppender {
    public ConsoleLogAppender() {
        super();
    }

    public void append(LogMessage message) {
        System.out.print(this.formatter.format(message));
    }

    public void close() {};
}