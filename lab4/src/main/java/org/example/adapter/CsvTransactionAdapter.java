package org.example.adapter;

public class CsvTransactionAdapter implements Transaction {
    private final CsvImporter.CsvRow row;

    public CsvTransactionAdapter(CsvImporter.CsvRow row) {
        this.row = row;
    }

    @Override
    public double amount() {
        return row.getAmount();
    }

    @Override
    public String category() {
        return row.getCategory();
    }

    @Override
    public String toString() {
        return "CsvTransaction{" +
                "category='" + row.getCategory() + '\'' +
                ", amount=" + row.getAmount() +
                '}';
    }
}
