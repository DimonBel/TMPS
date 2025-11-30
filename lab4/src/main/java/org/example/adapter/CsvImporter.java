package org.example.adapter;

import java.util.ArrayList;
import java.util.List;

public class CsvImporter {
    public List<CsvRow> parse(String filePath) {
        List<CsvRow> rows = new ArrayList<>();
        rows.add(new CsvRow("food", -90.0));
        rows.add(new CsvRow("transport", -40.0));
        rows.add(new CsvRow("savings", 300.0));
        return rows;
    }

    public static class CsvRow {
        private final String category;
        private final double amount;

        public CsvRow(String category, double amount) {
            this.category = category;
            this.amount = amount;
        }

        public String getCategory() {
            return category;
        }

        public double getAmount() {
            return amount;
        }
    }
}
