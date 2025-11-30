package org.example.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Concrete Observer: Simulates sending email notifications.
 */
public class EmailNotifier implements BudgetObserver {

    private final String emailAddress;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public EmailNotifier(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void update(BudgetEvent event, String message) {
        String timestamp = LocalDateTime.now().format(formatter);

        System.out.println("\n📧 [EMAIL NOTIFICATION]");
        System.out.println("   To: " + emailAddress);
        System.out.println("   Subject: " + event.getDescription());
        System.out.println("   Time: " + timestamp);
        System.out.println("   Message: " + message);
        System.out.println("   Status: Email sent successfully");
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
