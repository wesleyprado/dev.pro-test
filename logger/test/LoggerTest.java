package test;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerTest {

    private static final String LOG_FILE = "test.log";

    @Test
    void testLogMessage() {
        String message = "Test log message";
        String level = "DEBUG";

        Logger.logMessage(LOG_FILE, message, level);

        assertTrue(doesLogFileContainMessage(message));
    }

    private boolean doesLogFileContainMessage(String expectedMessage) {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(expectedMessage)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Test
    void testLogFormat() {
        String message = "Format test";
        String level = "INFO";

        Logger.logMessage(LOG_FILE, message, level);

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String expectedLogEntry = "[" + timestamp + "] [" + level + "] " + message;

        assertTrue(doesLogFileContainMessage(expectedLogEntry));
    }
}
