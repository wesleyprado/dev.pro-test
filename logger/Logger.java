import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void logMessage(String filename, String message, String level) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        String logEntry = "[" + timestamp + "] [" + level + "] " + message;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        logMessage("application.log", "User logged in", "INFO");
        logMessage("application.log", "Failed login attempt", "WARNING");
    }
}