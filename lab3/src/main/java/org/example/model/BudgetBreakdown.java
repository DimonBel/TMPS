package org.example.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class BudgetBreakdown {
    private final double food;
    private final double housing;
    private final double entertainment;
    private final double savings;
    private final Map<String, Double> customCategories;

    private BudgetBreakdown(Builder builder) {
        this.food = builder.food;
        this.housing = builder.housing;
        this.entertainment = builder.entertainment;
        this.savings = builder.savings;
        this.customCategories = Collections.unmodifiableMap(new HashMap<>(builder.customCategories));
    }

    public double getFood() {
        return food;
    }

    public double getHousing() {
        return housing;
    }

    public double getEntertainment() {
        return entertainment;
    }

    public double getSavings() {
        return savings;
    }

    public Map<String, Double> getCustomCategories() {
        return customCategories;
    }

    public double getTotal() {
        double total = food + housing + entertainment + savings;
        for (double value : customCategories.values()) {
            total += value;
        }
        return total;
    }

    public BudgetBreakdown withFood(double value) {
        return builder().from(this).food(value).build();
    }

    public BudgetBreakdown withHousing(double value) {
        return builder().from(this).housing(value).build();
    }

    public BudgetBreakdown withEntertainment(double value) {
        return builder().from(this).entertainment(value).build();
    }

    public BudgetBreakdown withSavings(double value) {
        return builder().from(this).savings(value).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return builder().from(this);
    }

    public static final class Builder {
        private double food;
        private double housing;
        private double entertainment;
        private double savings;
        private Map<String, Double> customCategories = new HashMap<>();

        public Builder food(double value) {
            this.food = value;
            return this;
        }

        public Builder housing(double value) {
            this.housing = value;
            return this;
        }

        public Builder entertainment(double value) {
            this.entertainment = value;
            return this;
        }

        public Builder savings(double value) {
            this.savings = value;
            return this;
        }

        public Builder customCategory(String name, double amount) {
            this.customCategories.put(name, amount);
            return this;
        }

        public Builder customCategories(Map<String, Double> categories) {
            this.customCategories.clear();
            this.customCategories.putAll(categories);
            return this;
        }

        private Builder from(BudgetBreakdown breakdown) {
            this.food = breakdown.food;
            this.housing = breakdown.housing;
            this.entertainment = breakdown.entertainment;
            this.savings = breakdown.savings;
            this.customCategories = new HashMap<>(breakdown.customCategories);
            return this;
        }

        public BudgetBreakdown build() {
            return new BudgetBreakdown(this);
        }
    }
}
