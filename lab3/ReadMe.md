# Topic: 3 Structural Design Patterns — Facade, Adapter, Decorator

## Author: Belih Dmitrii

## Objectives

1. Implement and demonstrate three structural patterns:
   - Facade
   - Adapter
   - Decorator

## Main tasks

1. Provide a single entry-point that hides budget creation pipeline complexity (Facade).
2. Integrate external data sources without changing core domain classes (Adapter).
3. Add optional features to budget reporting at runtime (Decorator).

## Implementation (lab3)

This lab uses Java and builds on the existing budgeting domain. The following structural patterns are implemented.

- Facade

  - Purpose: expose one simple method that orchestrates the entire budgeting flow: factory → calculation → constraints → recommendations → report.
  - Files / classes:
    - `org.example.facade.BudgetingFacade` — single entry point: `createBudget(BudgetUserInput)`.
    - `org.example.facade.BudgetCalculationService` — computes initial plan using `BudgetFactory`.
    - `org.example.facade.BudgetConstraintValidator` — applies category limits from `BudgetConfig`.
    - `org.example.facade.BudgetRecommendationEngine` — generates recommendations based on plan vs. transactions.
  - Description: clients pass `BudgetUserInput` and receive `BudgetFacadeResponse` (includes `BudgetResult`, `BudgetBreakdown`, recommendations, rendered report, and unified transactions).

- Adapter

  - Purpose: integrate external data sources and present them via a common `Transaction` interface without changing facade or domain logic.
  - Files / classes:
    - `org.example.adapter.Transaction` — target interface used in the app.
    - `org.example.adapter.ExternalBankApi` + `org.example.adapter.BankApiAdapter`.
    - `org.example.adapter.CsvImporter` + `org.example.adapter.CsvTransactionAdapter`.
    - `org.example.adapter.FinancialServiceClient` + `org.example.adapter.FinancialServiceAdapter`.
    - `org.example.adapter.TransactionProvider` — composes adapters and returns a `List<Transaction>` based on selected sources.
  - Description: each external client returns its own model; an adapter converts it to `Transaction`. New sources can be added by creating a client + adapter and registering it in `TransactionProvider`.

- Decorator

  - Purpose: add optional reporting features without modifying the base report class.
  - Files / classes:
    - `org.example.decorator.BudgetReport` — reporting interface.
    - `org.example.decorator.PlainBudgetReport` — base report (renders `BudgetResult`).
    - `org.example.decorator.BudgetReportDecorator` — abstract decorator.
    - `org.example.decorator.OverageAlertDecorator` — adds alerts for spending over limits.
    - `org.example.decorator.MonthlyProjectionDecorator` — appends simple monthly/annual projections.
    - `org.example.decorator.ColorCategoryDecorator` — annotates categories with color hints.
  - Description: decorators wrap `BudgetReport` dynamically based on user options selected in `BudgetUserInput`.

## Supporting model and factory updates

- `org.example.model.BudgetBreakdown` — immutable breakdown object used across services.
- `org.example.model.BudgetUserInput` — facade input options (budget type, income, selected external sources, toggles for decorators).
- `org.example.model.BudgetFacadeResponse` — result bundle from facade (result, breakdown, recommendations, report, transactions).
- `org.example.model.ConstraintOutcome` — constraint application result.
- `org.example.model.ExternalSource` — flags for which adapters to use.
- `org.example.factory.BudgetFactory` — now accepts income: `create(String type, double totalIncome)`.
- `org.example.factory.ConservativeBudget`, `org.example.factory.AggressiveSavingsBudget` — expose `getBreakdown()` for downstream steps.

## Usage example (in this project)

- `org.example.Main` demonstrates all three structural patterns working together via the Facade. Example snippet:

```java
BudgetFactory factory = new ConcreteBudgetFactory();
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
System.out.println(response.getReport());
response.getRecommendations().forEach(r -> System.out.println("- " + r));
```

## How to extend

- Add a new external source:
  1) Implement a client, 2) create an Adapter implementing `Transaction`, 3) register it in `TransactionProvider`.
- Add a new report feature: create a new `BudgetReportDecorator` and wrap it in `BudgetingFacade` based on a flag in `BudgetUserInput`.
- Add a new budget strategy: implement `BudgetPlan` and extend `ConcreteBudgetFactory`.

## Conclusions

- Facade hides multi-step orchestration behind a single method, simplifying client code.
- Adapter decouples external data from core logic, enabling painless integrations.
- Decorator enables optional, composable features without modifying existing classes.
