package org.example.facade;

import org.example.adapter.Transaction;
import org.example.adapter.TransactionProvider;
import org.example.build.Builder;
import org.example.build.BudgetResult;
import org.example.build.ConcreteBudgetBuilder;
import org.example.decorator.BudgetReport;
import org.example.decorator.ColorCategoryDecorator;
import org.example.decorator.MonthlyProjectionDecorator;
import org.example.decorator.OverageAlertDecorator;
import org.example.decorator.PlainBudgetReport;
import org.example.factory.BudgetFactory;
import org.example.model.BudgetBreakdown;
import org.example.model.BudgetFacadeResponse;
import org.example.model.BudgetUserInput;
import org.example.model.ConstraintOutcome;
import org.example.singleton.BudgetConfig;

import java.util.List;

public class BudgetingFacade {
    private final BudgetCalculationService calculationService;
    private final BudgetConstraintValidator constraintValidator;
    private final BudgetRecommendationEngine recommendationEngine;
    private final TransactionProvider transactionProvider;

    public BudgetingFacade(BudgetFactory budgetFactory) {
        this.calculationService = new BudgetCalculationService(budgetFactory);
        this.constraintValidator = new BudgetConstraintValidator(BudgetConfig.get());
        this.recommendationEngine = new BudgetRecommendationEngine();
        this.transactionProvider = new TransactionProvider();
    }

    public BudgetFacadeResponse createBudget(BudgetUserInput input) {
        BudgetBreakdown initialBreakdown = calculationService.calculate(input);
        ConstraintOutcome constraintOutcome = constraintValidator.applyConstraints(initialBreakdown);
        BudgetBreakdown constrainedBreakdown = constraintOutcome.getBreakdown();

        List<Transaction> transactions = transactionProvider.collect(input);

        Builder builder = new ConcreteBudgetBuilder();
        builder.reset();
        builder.buildFood(constrainedBreakdown.getFood());
        builder.buildHousing(constrainedBreakdown.getHousing());
        builder.buildEntertainment(constrainedBreakdown.getEntertainment());
        builder.buildSavings(constrainedBreakdown.getSavings());
        constrainedBreakdown.getCustomCategories().forEach(builder::addCustomCategory);
        BudgetResult result = builder.getResult();

        BudgetReport report = new PlainBudgetReport(result);
        if (input.isOverageNotifications()) {
            report = new OverageAlertDecorator(report, transactions, BudgetConfig.get());
        }
        if (input.isMonthlyProjection()) {
            report = new MonthlyProjectionDecorator(report, constrainedBreakdown);
        }
        if (input.isColorizedCategories()) {
            report = new ColorCategoryDecorator(report, constrainedBreakdown);
        }

        List<String> recommendations = recommendationEngine.generate(constrainedBreakdown, transactions,
                constraintOutcome.getNotes());

        return new BudgetFacadeResponse(result,
                constrainedBreakdown,
                constraintOutcome.getNotes(),
                recommendations,
                report.render(),
                transactions);
    }
}