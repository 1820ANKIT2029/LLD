package loggingframework.enums;

public enum LogLevel {
    DEBUG("DEBUG", 1),
    INFO("INFO", 2),
    WARN("WARN", 3),
    ERROR("ERROR", 4),
    FATAL("FATAL", 5);

    private String value;
    private Integer level;

    private LogLevel(String value, Integer level) {
        this.value = value;
        this.level = level;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isGreaterOrEqual(LogLevel other) {
        return this.level >= other.level;
    }
}