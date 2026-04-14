package loggingframework.formatter;

import loggingframework.entities.LogMessage;

public interface LogFormatter {
    public String format(LogMessage logMessage);
}