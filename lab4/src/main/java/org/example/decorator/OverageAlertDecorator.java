package org.example.decorator;

import org.example.adapter.Transaction;
import org.example.singleton.BudgetConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OverageAlertDecorator extends BudgetReportDecorator {
    private final List<Transaction> transactions;
    private final BudgetConfig config;

    public OverageAlertDecorator(BudgetReport delegate,
            List<Transaction> transactions,
            BudgetConfig config) {
        super(delegate);
        this.transactions = transactions;
        this.config = config;
    }

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder(delegate.render());
        List<String> alerts = detectOverages();
        if (!alerts.isEmpty()) {
            builder.append("\n\nAlerts:\n");
            alerts.forEach(alert -> builder.append("- ").append(alert).append('\n'));
        }
        return builder.toString();
    }

    private List<String> detectOverages() {
        Map<String, Double> limits = config.getAllLimits();
        List<String> alerts = new ArrayList<>();
        limits.forEach((category, limit) -> {
            double spending = transactions.stream()
                    .filter(tx -> tx.category().equalsIgnoreCase(category))
                    .mapToDouble(Transaction::amount)
                    .filter(amount -> amount < 0)
                    .map(Math::abs)
                    .sum();
            if (spending > limit) {
                alerts.add(String.format("%s spending %.2f exceeds limit %.2f", category, spending, limit));
            }
        });
        return alerts;
    }
}
