package logger;

public interface Logger {
    void log(String message);
    void error(String message);

    void logArguments();

    void logRequirements();
}