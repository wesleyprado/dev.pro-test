Feature: Logging Functionality

# Happy flow
  Scenario: Logging an INFO message
    Given the application log file "test.log" exists
    When a message "User logged in" with level "INFO" is logged
    Then the log file "test.log" should contain "[INFO] User logged in"

  Scenario: Logging a WARNING message
    Given the application log file "test.log" exists
    When a message "Failed login attempt" with level "WARNING" is logged
    Then the log file "test.log" should contain "[WARNING] Failed login attempt"

# Bad flow
  Scenario: Logging to a non-existent file
    Given the application log file "nonexistent.log" does not exist
    When a message "User logged in" with level "INFO" is logged
    Then the system should display an error message

  Scenario: Logging with invalid log level
    Given the application log file "test.log" exists
    When a message "Invalid log level" with level "INVALID" is logged
    Then the system should display an error message
