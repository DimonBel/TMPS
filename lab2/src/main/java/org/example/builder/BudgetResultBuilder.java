package org.example.builder;

import java.util.HashMap;
import java.util.Map;


public class BudgetResultBuilder {
    //new inner Builder of BudgetResult to avoid accessing private
    private BudgetResult.Builder builder;


    public BudgetResultBuilder() {
        builder = new BudgetResult.Builder();
    }


    public BudgetResultBuilder setFood(double amount) {
        builder.setFood(amount);
        return this;
    }


    public BudgetResultBuilder setHousing(double amount) {
        builder.setHousing(amount);
        return this;
    }


    public BudgetResultBuilder setEntertainment(double amount) {
        builder.setEntertainment(amount);
        return this;
    }


    public BudgetResultBuilder setSavings(double amount) {
        builder.setSavings(amount);
        return this;
    }

    //custom category
    public BudgetResultBuilder addCustomCategory(String category, double amount) {
        builder.addCustomCategory(category, amount);
        return this;
    }

    // Builds function
    public BudgetResult build() {
        return builder.build();
    }
}
