package org.example.observer;

/**
 * Concrete Observer: Sends alerts for critical budget events.
 */
public class AlertObserver implements BudgetObserver {

    private final String alertChannel;
    private int alertCount = 0;

    public AlertObserver(String alertChannel) {
        this.alertChannel = alertChannel;
    }

    @Override
    public void update(BudgetEvent event, String message) {
        // Only alert on critical events
        if (isCriticalEvent(event)) {
            alertCount++;
            System.out.println("\n🚨 [ALERT SYSTEM - " + alertChannel.toUpperCase() + "]");
            System.out.println("   CRITICAL EVENT #" + alertCount + ": " + event.getDescription());
            System.out.println("   " + message);
            System.out.println("   Action: Immediate attention required!");

            // Simulate different alert channels
            switch (alertChannel.toLowerCase()) {
                case "sms" -> System.out.println("   📱 SMS alert sent to mobile device");
                case "push" -> System.out.println("   📲 Push notification sent to app");
                case "slack" -> System.out.println("   💬 Message posted to Slack channel");
                default -> System.out.println("   🔔 Alert sent via " + alertChannel);
            }
        }
    }

    private boolean isCriticalEvent(BudgetEvent event) {
        return event == BudgetEvent.BUDGET_EXCEEDED ||
                event == BudgetEvent.VALIDATION_FAILED ||
                event == BudgetEvent.APPROVAL_DENIED;
    }

    public int getAlertCount() {
        return alertCount;
    }

    public String getAlertChannel() {
        return alertChannel;
    }
}
