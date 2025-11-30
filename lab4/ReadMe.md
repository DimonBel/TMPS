# Topic: Behavioral Design Patterns (Lab 4)

## Author: Belih Dmitrii

## Objectives

1. Implement and demonstrate three behavioral patterns:
   - **Strategy** - for flexible budget validation
   - **Observer** - for budget event notifications
   - **Chain of Responsibility** - for budget approval workflow

## Previous Labs

- **Lab 1:** Version Control and Team Development
- **Lab 2:** Creational Patterns (Factory Method, Singleton, Builder)
- **Lab 3:** Structural Patterns (Facade, Adapter, Decorator)

## Main Tasks (Lab 4)

1. Implement Strategy pattern to allow different validation rules at runtime.
2. Implement Observer pattern to notify multiple observers of budget events.
3. Implement Chain of Responsibility for hierarchical budget approval workflow.

## Behavioral Patterns Implementation

This lab extends the existing budgeting service with three behavioral design patterns.

### 1. Strategy Pattern

**Purpose:** Define a family of validation algorithms, encapsulate each one, and make them interchangeable at runtime.

**Files / Classes:**

- `org.example.strategy.ValidationStrategy` — interface for validation strategies
- `org.example.strategy.BasicValidationStrategy` — basic validation (only checks total vs. income)
- `org.example.strategy.StrictValidationStrategy` — enforces conservative financial rules (min 15% savings, max 30% housing, max 10% entertainment)
- `org.example.strategy.FlexibleValidationStrategy` — provides suggestions but allows more freedom
- `org.example.strategy.BudgetValidator` — context class that uses strategies

**Use Case:** A user can choose their preferred validation level based on their financial expertise and risk tolerance.

**Benefits:**

- Validation logic is separated from budget creation logic
- Easy to add new validation strategies without modifying existing code
- Strategies can be changed at runtime based on user preferences

### 2. Observer Pattern

**Purpose:** Define a one-to-many dependency so that when a budget event occurs, all registered observers are notified automatically.

**Files / Classes:**

- `org.example.observer.BudgetObserver` — interface for observers
- `org.example.observer.BudgetEvent` — enum of budget events (CREATED, UPDATED, EXCEEDED, etc.)
- `org.example.observer.BudgetSubject` — maintains list of observers and notifies them
- `org.example.observer.EmailNotifier` — sends email notifications
- `org.example.observer.LoggingObserver` — logs events to console/file
- `org.example.observer.AlertObserver` — sends critical alerts via SMS/Push/Slack

**Use Case:** When a budget is created, exceeded, or updated, multiple systems (email, logging, alerts) need to be notified simultaneously.

**Benefits:**

- Loose coupling between budget system and notification mechanisms
- Easy to add new observers without modifying the subject
- Observers can be attached/detached dynamically

### 3. Chain of Responsibility Pattern

**Purpose:** Pass budget approval requests along a chain of handlers, where each handler decides to approve or escalate to the next level.

**Files / Classes:**

- `org.example.chain.ApprovalHandler` — abstract handler with approval limit
- `org.example.chain.BudgetRequest` — request object with amount, department, priority
- `org.example.chain.ApprovalResult` — result of approval decision
- `org.example.chain.BasicApprovalHandler` — Team Lead (up to $5,000)
- `org.example.chain.ManagerApprovalHandler` — Department Manager (up to $25,000)
- `org.example.chain.DirectorApprovalHandler` — Executive Director (up to $100,000)
- `org.example.chain.ApprovalChainBuilder` — factory to build predefined chains

**Use Case:** Budget requests are automatically routed through organizational hierarchy based on amount and priority.

**Benefits:**

- Decouples request sender from receivers
- Flexible approval workflows (standard, fast-track, custom)
- Easy to add or reorder approval levels

## Implementation (Previous Labs)

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

## Usage Example (Lab 4 - Behavioral Patterns)

All three behavioral patterns are demonstrated in `org.example.Main`:

```java
// Strategy Pattern - Switch validation strategies at runtime
BudgetValidator validator = new BudgetValidator(new BasicValidationStrategy());
List<String> issues = validator.validate(breakdown, totalIncome);
validator.setStrategy(new StrictValidationStrategy()); // Change strategy
issues = validator.validate(breakdown, totalIncome);

// Observer Pattern - Multiple observers notified of budget events
BudgetSubject budgetSystem = new BudgetSubject();
budgetSystem.attach(new EmailNotifier("user@example.com"));
budgetSystem.attach(new LoggingObserver("INFO"));
budgetSystem.attach(new AlertObserver("SMS"));
budgetSystem.notifyObservers(BudgetEvent.BUDGET_CREATED, "New budget created");

// Chain of Responsibility - Automatic approval routing
ApprovalHandler chain = ApprovalChainBuilder.buildStandardChain();
BudgetRequest request = BudgetRequest.builder()
    .requestId("REQ-001")
    .amount(15000.0)
    .department("Engineering")
    .priority(BudgetRequest.Priority.HIGH)
    .build();
ApprovalResult result = chain.handleRequest(request);
```

## How to Run

```bash
cd lab4
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## How to Extend

### Add New Patterns:

- **New validation strategy:** implement `ValidationStrategy` interface
- **New observer:** implement `BudgetObserver` interface and attach to `BudgetSubject`
- **New approval level:** extend `ApprovalHandler` and add to chain

### Extend Existing Patterns:

- Add a new external source: 1) Implement a client, 2) create an Adapter implementing `Transaction`, 3) register it in `TransactionProvider`.
- Add a new report feature: create a new `BudgetReportDecorator` and wrap it in `BudgetingFacade` based on a flag in `BudgetUserInput`.
- Add a new budget strategy: implement `BudgetPlan` and extend `ConcreteBudgetFactory`.

## Pattern Comparison Table

| Pattern                 | Type       | Purpose                  | Key Benefit                   |
| ----------------------- | ---------- | ------------------------ | ----------------------------- |
| Strategy                | Behavioral | Encapsulate algorithms   | Runtime algorithm switching   |
| Observer                | Behavioral | One-to-many notification | Loose coupling for events     |
| Chain of Responsibility | Behavioral | Pass request along chain | Flexible request handling     |
| Factory Method          | Creational | Object creation          | Flexible object instantiation |
| Singleton               | Creational | Single instance          | Global access point           |
| Builder                 | Creational | Complex construction     | Readable object creation      |
| Facade                  | Structural | Simplified interface     | Hide complexity               |
| Adapter                 | Structural | Interface conversion     | Integrate incompatible code   |
| Decorator               | Structural | Add responsibilities     | Dynamic feature addition      |

## Conclusions

### Behavioral Patterns (Lab 4)

- **Strategy** enables flexible validation rules without modifying core logic - perfect for user-configurable behaviors
- **Observer** decouples event producers from consumers - ideal for notification systems and event-driven architectures
- **Chain of Responsibility** simplifies complex approval workflows - handlers can be easily added, removed, or reordered

### Structural Patterns (Lab 3)

- Facade hides multi-step orchestration behind a single method, simplifying client code
- Adapter decouples external data from core logic, enabling painless integrations
- Decorator enables optional, composable features without modifying existing classes

### Creational Patterns (Lab 2)

- Factory Method provides flexible object creation without coupling to concrete classes
- Singleton ensures global access to shared configuration
- Builder simplifies construction of complex objects with many parameters

### Overall Project Benefits

By combining all three categories of design patterns, the budgeting service demonstrates:

- **Flexibility** - Easy to extend and modify behavior at any level
- **Maintainability** - Clear separation of concerns with well-defined responsibilities
- **Testability** - Each component can be tested independently
- **Scalability** - New features can be added without breaking existing code
- **Reusability** - Patterns can be applied to other domains with minimal modification
