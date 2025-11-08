package org.example.build;

public interface Builder {
    void reset();
    void buildFood(double amount);
    void buildHousing(double amount);
    void buildEntertainment(double amount);
    void buildSavings(double amount);
    void addCustomCategory(String name, double amount);
    BudgetResult getResult();
}