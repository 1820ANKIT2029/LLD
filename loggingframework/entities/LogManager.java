package loggingframework.entities;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import loggingframework.appender.LogAppender;

public class LogManager {
    private static volatile LogManager instance;
    private static final Object lock = new Object();

    private Logger rootLogger;
    private Map<String, Logger> loggers;
    private AsyncLogProcessor processor;

    public LogManager() {
        this.processor = new AsyncLogProcessor();
        this.loggers = new HashMap<>();
        this.rootLogger = new Logger("root", null);
    }

    public static LogManager getInstance() {
        if(instance == null) {
            synchronized (lock)  {
                if(instance == null){
                    instance = new LogManager();
                }
            }
        }

        return instance;
    }

    private Logger createLogger(String name) {
        if ("root".equalsIgnoreCase(name)) {
            return this.rootLogger;
        }

        int lastDot = name.lastIndexOf('.');
        String parentName;
        
        if (lastDot == -1) { parentName = "root"; } 
        else {
            parentName = name.substring(0, lastDot);
        }

        Logger parent = this.getLogger(parentName);
        return new Logger(name, parent);
    }

    public Logger getRootLogger() {
        return this.rootLogger;
    }
    public Logger getLogger(String logname) {
        if(this.loggers.get(logname) == null) {
            this.loggers.put(logname, this.createLogger(logname));
        }
        return this.loggers.get(logname);
    }

    public AsyncLogProcessor getProcessor() {return this.processor;}
    public void shutdown() {
        this.processor.stop();

        Set<LogAppender> allAppenders = new HashSet<>();
        for(Logger logger: this.loggers.values()) {
            for(LogAppender appender: logger.getAppenders()) {
                allAppenders.add(appender);
            }
        }

        for(LogAppender appender: allAppenders) {
            appender.close();
        }
    }
}