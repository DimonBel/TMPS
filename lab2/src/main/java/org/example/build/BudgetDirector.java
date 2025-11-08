package org.example.build;


public class BudgetDirector {
    private Builder builder;

    public BudgetDirector(Builder builder) {
        this.builder = builder;
    }

    public void changeBuilder(Builder builder) {
        this.builder = builder;
    }

    public void makeBasicBudget() {
        builder.reset();
        builder.buildFood(300);
        builder.buildHousing(700);
        builder.buildEntertainment(150);
        builder.buildSavings(200);
    }

    public void makeCustomBudget() {
        builder.reset();
        builder.buildFood(250);
        builder.buildHousing(800);
        builder.buildSavings(300);
        builder.addCustomCategory("Health", 100);
        builder.addCustomCategory("Transport", 120);
    }
}
