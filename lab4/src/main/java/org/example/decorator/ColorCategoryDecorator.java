package org.example.decorator;

import org.example.model.BudgetBreakdown;

public class ColorCategoryDecorator extends BudgetReportDecorator {
    private final BudgetBreakdown breakdown;

    public ColorCategoryDecorator(BudgetReport delegate, BudgetBreakdown breakdown) {
        super(delegate);
        this.breakdown = breakdown;
    }

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder(delegate.render());
        builder.append("\n\nCategory Palette:\n");
        builder.append(colorLine("Food", breakdown.getFood(), "GREEN"));
        builder.append(colorLine("Housing", breakdown.getHousing(), "BLUE"));
        builder.append(colorLine("Entertainment", breakdown.getEntertainment(), "YELLOW"));
        builder.append(colorLine("Savings", breakdown.getSavings(), "PURPLE"));
        breakdown.getCustomCategories().forEach((name, amount) -> builder.append(colorLine(name, amount, "CYAN")));
        return builder.toString();
    }

    private String colorLine(String category, double value, String colorName) {
        return String.format("[%s - %s] $%.2f\n", category, colorName, value);
    }
}
