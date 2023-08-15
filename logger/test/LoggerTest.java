package test;

import org.junit.Assert;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoggingStepDefinitions {

    private String logFileName;
    private String message;
    private String logLevel;
    private String logContent;

    @Given("^the application log file \"([^\"]*)\" exists$")
    public void theApplicationLogFileExists(String fileName) {
        this.logFileName = fileName;
    }

    @Given("^the application log file \"([^\"]*)\" does not exist$")
    public void theApplicationLogFileDoesNotExist(String fileName) {
        this.logFileName = fileName;

        // Delete the file if it already exists
        File logFile = new File(logFileName);
        if (logFile.exists()) {
            logFile.delete();
        }
    }

    @When("^a message \"([^\"]*)\" with level \"([^\"]*)\" is logged$")
    public void aMessageWithLevelIsLogged(String message, String level) {
        this.message = message;
        this.logLevel = level;

        logMessage(logFileName, message, logLevel);

        try (BufferedReader reader = new BufferedReader(new FileReader(logFileName))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            logContent = content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("^the log file \"([^\"]*)\" should contain \"([^\"]*)\"$")
    public void theLogFileShouldContain(String fileName, String expectedLogEntry) {
        Assert.assertTrue(logContent.contains(expectedLogEntry));
    }

    @Then("^the system should display an error message$")
    public void theSystemShouldDisplayAnErrorMessage() {
        boolean errorOccurred = false;
        try {
            logMessage(logFileName, message, logLevel);
        } catch (Exception e) {
            errorOccurred = true;
        }
        Assert.assertTrue("Error should have occurred", errorOccurred);
    }