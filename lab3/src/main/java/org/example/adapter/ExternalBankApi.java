package org.example.adapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExternalBankApi {
    public List<BankRecord> fetchLatestTransactions(String accountId) {
        List<BankRecord> records = new ArrayList<>();
        records.add(new BankRecord(accountId, LocalDate.now().minusDays(2), -120.35, "food"));
        records.add(new BankRecord(accountId, LocalDate.now().minusDays(5), -780.00, "housing"));
        records.add(new BankRecord(accountId, LocalDate.now().minusDays(1), -65.20, "entertainment"));
        records.add(new BankRecord(accountId, LocalDate.now().minusDays(3), 2000.00, "income"));
        return records;
    }

    public static class BankRecord {
        private final String accountId;
        private final LocalDate date;
        private final double amount;
        private final String category;

        public BankRecord(String accountId, LocalDate date, double amount, String category) {
            this.accountId = accountId;
            this.date = date;
            this.amount = amount;
            this.category = category;
        }

        public String getAccountId() {
            return accountId;
        }

        public LocalDate getDate() {
            return date;
        }

        public double getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }
    }
}
