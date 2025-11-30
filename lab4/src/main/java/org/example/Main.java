package org.example;

import org.example.build.*;
import org.example.factory.*;
import org.example.facade.BudgetingFacade;
import org.example.model.BudgetBreakdown;
import org.example.model.BudgetFacadeResponse;
import org.example.model.BudgetUserInput;
import org.example.model.ExternalSource;
import org.example.singleton.BudgetConfig;
import org.example.strategy.*;
import org.example.observer.*;
import org.example.chain.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Budgeting Service Design Patterns Demo ===\n");
        System.out.println("This demo showcases Creational, Structural, and Behavioral Design Patterns\n");

        // 1. Factory Method
        System.out.println("1. Factory Method Pattern Demo:");
        System.out.println("Creating different budget plans using the factory...\n");

        BudgetFactory factory = new ConcreteBudgetFactory();

        BudgetPlan conservativeBudget = factory.create("conservative", 5000.0);
        System.out.println(conservativeBudget.allocate());

        System.out.println("\n----------------------------------------\n");

        BudgetPlan aggressiveBudget = factory.create("aggressive", 5000.0);
        System.out.println(aggressiveBudget.allocate());

        System.out.println("\n----------------------------------------\n");

        // 2. Singleton Pattern
        System.out.println("2. Singleton Pattern Demo:");
        System.out.println("Accessing global budget configuration...\n");

        BudgetConfig config1 = BudgetConfig.get();
        System.out.println("Default category limits:");
        config1.getAllLimits().forEach((category, limit) -> System.out.println(category + ": $" + limit));

        // Modify configuration
        config1.setLimit("food", 1500.0);
        config1.setLimit("entertainment", 750.0);

        System.out.println("\nModified category limits:");
        config1.getAllLimits().forEach((category, limit) -> System.out.println(category + ": $" + limit));

        // Verify singleton instance
        BudgetConfig config2 = BudgetConfig.get();
        System.out.println("\nAre config1 and config2 the same instance? " + (config1 == config2));

        System.out.println("\n----------------------------------------\n");

        // 3. Builder Pattern
        System.out.println("3. Builder Pattern Demo:");
        System.out.println("Creating complex budget results using the builder...\n");

        // Используем новую структуру Builder (Director + ConcreteBuilder)
        Builder builder = new ConcreteBudgetBuilder();
        BudgetDirector director = new BudgetDirector(builder);

        // Пример базового бюджета
        director.makeBasicBudget();
        BudgetResult basicBudget = builder.getResult();
        System.out.println("Basic Budget Result:");
        System.out.println(basicBudget);
        System.out.println("\nTotal budget: $" + basicBudget.getTotal());

        System.out.println("\n---\n");

        // Пример кастомного бюджета
        director.makeCustomBudget();
        BudgetResult customBudgetFromDirector = builder.getResult();
        System.out.println("Custom Budget Result:");
        System.out.println(customBudgetFromDirector);
        System.out.println("\nTotal budget: $" + customBudgetFromDirector.getTotal());

        System.out.println("\n----------------------------------------\n");

        // 4. Combined Demo
        System.out.println("4. Combined Demo - All Patterns Working Together:");
        System.out.println("Creating a budget plan and using configuration to build a result...\n");

        // Create a budget plan using factory
        BudgetPlan budgetPlan = factory.create("conservative", 5000.0);
        System.out.println("Budget Plan Created:");
        System.out.println(budgetPlan.allocate());

        // Get configuration
        BudgetConfig config = BudgetConfig.get();
        System.out.println("\nUsing Configuration Limits:");
        System.out.println("Food limit: $" + config.getLimit("food"));
        System.out.println("Housing limit: $" + config.getLimit("housing"));

        // Build a budget result respecting limits (manual builder steps)
        Builder manualBuilder = new ConcreteBudgetBuilder();
        manualBuilder.reset();
        manualBuilder.buildFood(Math.min(1500.0, config.getLimit("food")));
        manualBuilder.buildHousing(Math.min(2000.0, config.getLimit("housing")));
        manualBuilder.buildEntertainment(400.0);
        manualBuilder.buildSavings(1000.0);
        BudgetResult customBudget = manualBuilder.getResult();

        System.out.println("\nCustom Budget Result (respecting limits):");
        System.out.println(customBudget);
        System.out.println("Total: $" + customBudget.getTotal());

        System.out.println("\n----------------------------------------\n");

        // 5. Facade + Decorator + Adapter Demo
        System.out.println("5. Facade + Decorator + Adapter Demo:");
        System.out.println("Facade hides integration, validation, and reporting details...\n");

        BudgetingFacade facade = new BudgetingFacade(factory);
        BudgetUserInput input = BudgetUserInput.builder()
                .budgetType("aggressive")
                .monthlyIncome(6200.0)
                .addExternalSource(ExternalSource.BANK_API)
                .addExternalSource(ExternalSource.CSV_IMPORT)
                .addExternalSource(ExternalSource.FINANCIAL_SERVICE)
                .overageNotifications(true)
                .monthlyProjection(true)
                .colorizedCategories(true)
                .build();

        BudgetFacadeResponse response = facade.createBudget(input);
        System.out.println("Facade Generated Report:");
        System.out.println(response.getReport());

        System.out.println("\nRecommendations:");
        response.getRecommendations().forEach(rec -> System.out.println("- " + rec));

        System.out.println("\nAdapter Transactions (simulated external data):");
        response.getTransactions().forEach(tx -> System.out.println("  " + tx));

        System.out.println("\n========================================\n");
        System.out.println("=== BEHAVIORAL DESIGN PATTERNS (Lab 4) ===\n");
        System.out.println("========================================\n");

        // 6. Strategy Pattern Demo
        demonstrateStrategyPattern();

        System.out.println("\n========================================\n");

        // 7. Observer Pattern Demo
        demonstrateObserverPattern();

        System.out.println("\n========================================\n");

        // 8. Chain of Responsibility Pattern Demo
        demonstrateChainOfResponsibility();

        System.out.println("\n========================================");
        System.out.println("=== Demo Complete ===");
        System.out.println("All design patterns demonstrated successfully!");
    }

    /**
     * Demonstrates Strategy Pattern for budget validation.
     * Shows how different validation strategies can be applied at runtime.
     */
    private static void demonstrateStrategyPattern() {
        System.out.println("6. STRATEGY PATTERN Demo:");
        System.out.println("Different validation strategies for budget validation\n");

        // Create a sample budget breakdown using builder
        BudgetBreakdown breakdown = BudgetBreakdown.builder()
                .food(1200.0)
                .housing(2800.0)
                .entertainment(800.0)
                .savings(500.0)
                .build();
        double totalIncome = 5000.0;

        System.out.println("Budget to validate:");
        System.out.println("  Food: $" + breakdown.getFood());
        System.out.println("  Housing: $" + breakdown.getHousing());
        System.out.println("  Entertainment: $" + breakdown.getEntertainment());
        System.out.println("  Savings: $" + breakdown.getSavings());
        System.out.println("  Total Income: $" + totalIncome);

        // Create validator with Basic Strategy
        BudgetValidator validator = new BudgetValidator(new BasicValidationStrategy());

        System.out.println("\n--- Using " + validator.getCurrentStrategyName() + " ---");
        List<String> issues = validator.validate(breakdown, totalIncome);
        printValidationResults(issues);

        // Switch to Strict Strategy
        validator.setStrategy(new StrictValidationStrategy());
        System.out.println("\n--- Switching to " + validator.getCurrentStrategyName() + " ---");
        issues = validator.validate(breakdown, totalIncome);
        printValidationResults(issues);

        // Switch to Flexible Strategy
        validator.setStrategy(new FlexibleValidationStrategy());
        System.out.println("\n--- Switching to " + validator.getCurrentStrategyName() + " ---");
        issues = validator.validate(breakdown, totalIncome);
        printValidationResults(issues);
    }

    private static void printValidationResults(List<String> issues) {
        if (issues.isEmpty()) {
            System.out.println("✅ Budget is valid - no issues found");
        } else {
            System.out.println("⚠️ Validation issues found:");
            issues.forEach(issue -> System.out.println("   • " + issue));
        }
    }

    /**
     * Demonstrates Observer Pattern for budget notifications.
     * Shows how multiple observers can be notified of budget events.
     */
    private static void demonstrateObserverPattern() {
        System.out.println("7. OBSERVER PATTERN Demo:");
        System.out.println("Budget notification system with multiple observers\n");

        // Create subject
        BudgetSubject budgetSystem = new BudgetSubject();

        // Create and attach observers
        EmailNotifier emailNotifier = new EmailNotifier("user@example.com");
        LoggingObserver logger = new LoggingObserver("INFO");
        AlertObserver smsAlert = new AlertObserver("SMS");
        AlertObserver slackAlert = new AlertObserver("Slack");

        System.out.println("Attaching observers to budget system...\n");
        budgetSystem.attach(emailNotifier);
        budgetSystem.attach(logger);
        budgetSystem.attach(smsAlert);
        budgetSystem.attach(slackAlert);

        // Simulate budget events
        System.out.println("\n--- Simulating Budget Events ---\n");

        budgetSystem.notifyObservers(
                BudgetEvent.BUDGET_CREATED,
                "New monthly budget of $5,000 has been created for December 2025");

        System.out.println("\n---");

        budgetSystem.notifyObservers(
                BudgetEvent.BUDGET_EXCEEDED,
                "Food category spending has exceeded the limit by $150");

        System.out.println("\n---");

        budgetSystem.notifyObservers(
                BudgetEvent.SAVINGS_GOAL_REACHED,
                "Congratulations! You've reached your savings goal of $10,000");

        System.out.println("\n---");

        budgetSystem.notifyObservers(
                BudgetEvent.VALIDATION_FAILED,
                "Budget validation failed: Total spending exceeds income by $200");

        // Detach an observer
        System.out.println("\n\nDetaching email notifier...");
        budgetSystem.detach(emailNotifier);

        System.out.println("\nSending another notification (email won't receive it):\n");
        budgetSystem.notifyObservers(
                BudgetEvent.BUDGET_UPDATED,
                "Budget has been updated with new allocations");
    }

    /**
     * Demonstrates Chain of Responsibility Pattern for budget approvals.
     * Shows how approval requests are passed through a chain of handlers.
     */
    private static void demonstrateChainOfResponsibility() {
        System.out.println("8. CHAIN OF RESPONSIBILITY PATTERN Demo:");
        System.out.println("Budget approval workflow through organizational hierarchy\n");

        // Build the approval chain
        ApprovalHandler approvalChain = ApprovalChainBuilder.buildStandardChain();

        System.out.println("\n--- Processing Budget Approval Requests ---\n");

        // Request 1: Small budget - should be approved by Team Lead
        System.out.println("REQUEST #1: Small team budget");
        BudgetRequest request1 = BudgetRequest.builder()
                .requestId("REQ-001")
                .amount(3500.0)
                .department("Marketing")
                .requester("John Smith")
                .priority(BudgetRequest.Priority.MEDIUM)
                .justification("Q1 marketing campaign materials")
                .build();

        ApprovalResult result1 = approvalChain.handleRequest(request1);
        printApprovalResult(result1);

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Request 2: Medium budget - should escalate to Manager
        System.out.println("REQUEST #2: Department budget");
        BudgetRequest request2 = BudgetRequest.builder()
                .requestId("REQ-002")
                .amount(18000.0)
                .department("Engineering")
                .requester("Sarah Johnson")
                .priority(BudgetRequest.Priority.HIGH)
                .justification("New development tools and licenses")
                .build();

        ApprovalResult result2 = approvalChain.handleRequest(request2);
        printApprovalResult(result2);

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Request 3: Large budget - should escalate to Director
        System.out.println("REQUEST #3: Large infrastructure budget");
        BudgetRequest request3 = BudgetRequest.builder()
                .requestId("REQ-003")
                .amount(75000.0)
                .department("IT Infrastructure")
                .requester("Mike Chen")
                .priority(BudgetRequest.Priority.CRITICAL)
                .justification("Critical server infrastructure upgrade")
                .build();

        ApprovalResult result3 = approvalChain.handleRequest(request3);
        printApprovalResult(result3);

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Request 4: Extremely large budget - should be rejected
        System.out.println("REQUEST #4: Very large budget (exceeds all limits)");
        BudgetRequest request4 = BudgetRequest.builder()
                .requestId("REQ-004")
                .amount(150000.0)
                .department("R&D")
                .requester("Dr. Lisa Anderson")
                .priority(BudgetRequest.Priority.CRITICAL)
                .justification("New research lab setup")
                .build();

        ApprovalResult result4 = approvalChain.handleRequest(request4);
        printApprovalResult(result4);

        // Demonstrate Fast-Track Chain
        System.out.println("\n\n--- Fast-Track Approval Chain (Urgent Requests) ---\n");
        ApprovalHandler fastTrackChain = ApprovalChainBuilder.buildFastTrackChain();

        System.out.println("\nProcessing urgent request through fast-track chain:");
        BudgetRequest urgentRequest = BudgetRequest.builder()
                .requestId("REQ-URGENT")
                .amount(15000.0)
                .department("Sales")
                .requester("Tom Wilson")
                .priority(BudgetRequest.Priority.CRITICAL)
                .justification("Emergency customer acquisition campaign")
                .build();

        ApprovalResult urgentResult = fastTrackChain.handleRequest(urgentRequest);
        printApprovalResult(urgentResult);
    }

    private static void printApprovalResult(ApprovalResult result) {
        System.out.println("\n📋 Final Result:");
        System.out.println("   Status: " + (result.isApproved() ? "✅ APPROVED" : "❌ REJECTED"));
        System.out.println("   Decided by: " + result.getApprover());
        System.out.println("   Details: " + result.getMessage());
    }
}
