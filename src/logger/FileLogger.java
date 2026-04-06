package logger;

import java.io.FileWriter;

public class FileLogger implements LoggerStrategy {

    private static FileLogger instance;

    private FileLogger() {}

    public static FileLogger getInstance() {
        if (instance == null) {
            instance = new FileLogger();
        }
        return instance;
    }

    @Override
    public void log(String message) {
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            fw.write(message + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}