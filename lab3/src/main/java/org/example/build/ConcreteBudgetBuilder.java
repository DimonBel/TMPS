package org.example.build;


public class ConcreteBudgetBuilder implements Builder {
    private BudgetResult result;

    public ConcreteBudgetBuilder() {
        reset();
    }

    @Override
    public void reset() {
        result = new BudgetResult();
    }

    @Override
    public void buildFood(double amount) {
        result.setFood(amount);
    }

    @Override
    public void buildHousing(double amount) {
        result.setHousing(amount);
    }

    @Override
    public void buildEntertainment(double amount) {
        result.setEntertainment(amount);
    }

    @Override
    public void buildSavings(double amount) {
        result.setSavings(amount);
    }

    @Override
    public void addCustomCategory(String name, double amount) {
        result.addCustomCategory(name, amount);
    }

    @Override
    public BudgetResult getResult() {
        return result;
    }
}
