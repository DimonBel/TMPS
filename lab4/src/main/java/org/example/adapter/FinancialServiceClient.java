package org.example.adapter;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class FinancialServiceClient {
    public List<ServiceEntry> fetchSpendingForecast(String userId) {
        List<ServiceEntry> entries = new ArrayList<>();
        entries.add(new ServiceEntry(YearMonth.now(), "entertainment", -150.0));
        entries.add(new ServiceEntry(YearMonth.now().plusMonths(1), "food", -250.0));
        entries.add(new ServiceEntry(YearMonth.now(), "health", -80.0));
        return entries;
    }

    public static class ServiceEntry {
        private final YearMonth period;
        private final String category;
        private final double amount;

        public ServiceEntry(YearMonth period, String category, double amount) {
            this.period = period;
            this.category = category;
            this.amount = amount;
        }

        public YearMonth getPeriod() {
            return period;
        }

        public String getCategory() {
            return category;
        }

        public double getAmount() {
            return amount;
        }
    }
}
