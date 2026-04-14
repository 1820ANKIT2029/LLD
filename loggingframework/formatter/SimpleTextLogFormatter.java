package loggingframework.formatter;

import java.time.format.DateTimeFormatter;
import loggingframework.entities.LogMessage;

public class SimpleTextLogFormatter implements LogFormatter {
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public String format(LogMessage logMessage) {
        String timestampStr = logMessage.getTimestamp().format(TIMESTAMP_FORMATTER);

        return String.format("%s [%s] %s - %s: %s%n",
            timestampStr,
            logMessage.getThreadName(),
            logMessage.getLevel().getValue(),
            logMessage.getLoggerName(),
            logMessage.getMessage()
        );
    }
}