package org.example.model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public final class BudgetUserInput {
    private final String budgetType;
    private final double monthlyIncome;
    private final Set<ExternalSource> externalSources;
    private final boolean overageNotifications;
    private final boolean monthlyProjection;
    private final boolean colorizedCategories;

    private BudgetUserInput(Builder builder) {
        this.budgetType = Objects.requireNonNull(builder.budgetType, "budgetType");
        this.monthlyIncome = builder.monthlyIncome;
        this.externalSources = Collections.unmodifiableSet(EnumSet.copyOf(builder.externalSources));
        this.overageNotifications = builder.overageNotifications;
        this.monthlyProjection = builder.monthlyProjection;
        this.colorizedCategories = builder.colorizedCategories;
    }

    public String getBudgetType() {
        return budgetType;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public Set<ExternalSource> getExternalSources() {
        return externalSources;
    }

    public boolean isOverageNotifications() {
        return overageNotifications;
    }

    public boolean isMonthlyProjection() {
        return monthlyProjection;
    }

    public boolean isColorizedCategories() {
        return colorizedCategories;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String budgetType = "conservative";
        private double monthlyIncome = 5000.0;
        private Set<ExternalSource> externalSources = EnumSet.noneOf(ExternalSource.class);
        private boolean overageNotifications;
        private boolean monthlyProjection;
        private boolean colorizedCategories;

        public Builder budgetType(String type) {
            this.budgetType = type;
            return this;
        }

        public Builder monthlyIncome(double income) {
            this.monthlyIncome = income;
            return this;
        }

        public Builder addExternalSource(ExternalSource source) {
            this.externalSources.add(source);
            return this;
        }

        public Builder externalSources(Set<ExternalSource> sources) {
            this.externalSources = EnumSet.copyOf(sources);
            return this;
        }

        public Builder overageNotifications(boolean enabled) {
            this.overageNotifications = enabled;
            return this;
        }

        public Builder monthlyProjection(boolean enabled) {
            this.monthlyProjection = enabled;
            return this;
        }

        public Builder colorizedCategories(boolean enabled) {
            this.colorizedCategories = enabled;
            return this;
        }

        public BudgetUserInput build() {
            return new BudgetUserInput(this);
        }
    }
}
