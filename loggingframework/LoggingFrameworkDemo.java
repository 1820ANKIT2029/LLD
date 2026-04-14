package loggingframework;

import loggingframework.entities.*;
import loggingframework.enums.*;
import loggingframework.appender.*;

public class LoggingFrameworkDemo {

    public static void main(String[] args) {
        // --- 1. Initial Configuration ---
        // Accessing the Singleton LogManager
        LogManager logManager = LogManager.getInstance();
        Logger rootLogger = logManager.getRootLogger();
        
        // Set global minimum level to INFO
        rootLogger.setLevel(LogLevel.INFO); 
        
        // Add a console appender to the root logger
        rootLogger.addAppender(new ConsoleLogAppender());

        System.out.println("--- Initial Logging Demo ---");
        
        Logger mainLogger = logManager.getLogger("com.example.Main");
        mainLogger.info("Application starting up.");
        
        // Below root level (INFO), so this should not print
        mainLogger.debug("This is a debug message, it should NOT appear."); 
        mainLogger.warn("This is a warning message.");

        // --- 2. Hierarchy and Additivity Demo ---
        System.out.println("\n--- Logger Hierarchy Demo ---");
        
        // dbLogger inherits level (INFO) and appenders from root because of hierarchy
        Logger dbLogger = logManager.getLogger("com.example.db");
        dbLogger.info("Database connection pool initializing.");

        // Create a specific logger and override its level
        Logger serviceLogger = logManager.getLogger("com.example.service.UserService");
        serviceLogger.setLevel(LogLevel.DEBUG); // More verbose for this specific service
        
        serviceLogger.info("User service starting.");
        serviceLogger.debug("This debug message SHOULD now appear for the service logger.");

        // --- 3. Dynamic Configuration Change ---
        System.out.println("\n--- Dynamic Configuration Demo ---");
        System.out.println("Changing root log level to DEBUG...");
        
        rootLogger.setLevel(LogLevel.DEBUG);
        
        // Now that root is DEBUG, mainLogger (which inherits from root) will show debug logs
        mainLogger.debug("This debug message should now be visible.");

        try {
            Thread.sleep(500);
            logManager.shutdown();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted during shutdown.");
            Thread.currentThread().interrupt(); // Restore interrupted status
        } catch (Exception e) {
            System.err.println("Caught exception during shutdown: " + e.getMessage());
        }
    }
}