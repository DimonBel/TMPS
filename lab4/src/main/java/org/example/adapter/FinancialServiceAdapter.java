package org.example.adapter;

public class FinancialServiceAdapter implements Transaction {
    private final FinancialServiceClient.ServiceEntry entry;

    public FinancialServiceAdapter(FinancialServiceClient.ServiceEntry entry) {
        this.entry = entry;
    }

    @Override
    public double amount() {
        return entry.getAmount();
    }

    @Override
    public String category() {
        return entry.getCategory();
    }

    @Override
    public String toString() {
        return "FinancialServiceTransaction{" +
                "period=" + entry.getPeriod() +
                ", category='" + entry.getCategory() + '\'' +
                ", amount=" + entry.getAmount() +
                '}';
    }
}
