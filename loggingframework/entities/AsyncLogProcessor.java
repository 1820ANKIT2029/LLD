package loggingframework.entities;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import loggingframework.appender.LogAppender;

public class AsyncLogProcessor {
    private ExecutorService executor;
    private boolean shutdownFlag;
    
    public AsyncLogProcessor() {
        this.executor = Executors.newFixedThreadPool(1);
        this.shutdownFlag = false;
    }

    public void process(LogMessage message, List<LogAppender> appenders) {
        if(this.shutdownFlag) {
            System.out.println("Logger is shut down. Cannot process log message.");
            return;
        }

        this.executor.submit(()-> {
            for(LogAppender appender: appenders) {
                appender.append(message);
            }
        });
    }

    public void stop() {this.executor.shutdown(); this.shutdownFlag = true;}
}