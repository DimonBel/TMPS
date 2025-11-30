package org.example.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Concrete Observer: Logs budget events to console (simulating log file).
 */
public class LoggingObserver implements BudgetObserver {

    private final String logLevel;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LoggingObserver(String logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void update(BudgetEvent event, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String severity = determineSeverity(event);

        System.out.println("\n📋 [LOGGING OBSERVER]");
        System.out.println("   [" + timestamp + "] [" + severity + "] [" + event.getDescription() + "]");
        System.out.println("   " + message);
        System.out.println("   Log Level: " + logLevel);
    }

    private String determineSeverity(BudgetEvent event) {
        return switch (event) {
            case BUDGET_EXCEEDED, VALIDATION_FAILED, APPROVAL_DENIED -> "ERROR";
            case APPROVAL_REQUESTED -> "WARN";
            case BUDGET_CREATED, BUDGET_UPDATED, APPROVAL_GRANTED -> "INFO";
            case SAVINGS_GOAL_REACHED -> "SUCCESS";
        };
    }

    public String getLogLevel() {
        return logLevel;
    }
}
