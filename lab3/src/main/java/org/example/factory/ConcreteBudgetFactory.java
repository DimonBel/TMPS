package org.example.factory;

//create specific budget plans.
public class ConcreteBudgetFactory extends BudgetFactory {

    @Override
    public BudgetPlan create(String type, double totalIncome) {
        switch (type.toLowerCase()) {
            case "conservative":
                return new ConservativeBudget(totalIncome);
            case "aggressive":
                return new AggressiveSavingsBudget(totalIncome);
            default:
                throw new IllegalArgumentException("Unknown budget type: " + type);
        }
    }
}
