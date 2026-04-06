package logger;

public class ConsoleLogger implements LoggerStrategy {

    private static ConsoleLogger instance;

    private ConsoleLogger() {}

    public static ConsoleLogger getInstance() {
        if (instance == null) {
            instance = new ConsoleLogger();
        }
        return instance;
    }

    @Override
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}