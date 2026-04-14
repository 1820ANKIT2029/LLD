package loggingframework.appender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import loggingframework.entities.LogMessage;

public class FileLogAppender extends LogAppender {
    private BufferedWriter writer;
    private final Lock lock = new ReentrantLock();

    public FileLogAppender(String filePath) {
        try {
            this.writer = new BufferedWriter(new FileWriter(filePath, true));
        } catch (IOException e) {
            System.out.println("Failed to create writer for file logs, exception: " + e.getMessage());
            this.writer = null;
        }
    }

    @Override
    public void append(LogMessage logMessage) {
        lock.lock();
        try {
            if (writer != null) {
                writer.write(formatter.format(logMessage));
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("Failed to write logs to file, exception: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void close() {
        lock.lock();
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close logs file, exception: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}