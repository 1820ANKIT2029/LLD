package loggingframework.formatter;

import java.time.format.DateTimeFormatter;
import loggingframework.entities.LogMessage;

public class JsonLogFormatter implements LogFormatter {
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        
   public String format(LogMessage logMessage) {
        String timestampStr = logMessage.getTimestamp().format(TIMESTAMP_FORMATTER);

        return String.format("{\"time\": %s, \"thread name\": [%s], \"level\": %s, \"logger name\": %s, \"message\": %s}%n",
            timestampStr,
            logMessage.getThreadName(),
            logMessage.getLevel().getValue(),
            logMessage.getLoggerName(),
            logMessage.getMessage()
        );
    }
}