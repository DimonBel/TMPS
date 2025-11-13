package org.example.adapter;

public class BankApiAdapter implements Transaction {
    private final ExternalBankApi.BankRecord record;

    public BankApiAdapter(ExternalBankApi.BankRecord record) {
        this.record = record;
    }

    @Override
    public double amount() {
        return record.getAmount();
    }

    @Override
    public String category() {
        return record.getCategory();
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "account='" + record.getAccountId() + '\'' +
                ", category='" + record.getCategory() + '\'' +
                ", amount=" + record.getAmount() +
                '}';
    }
}
