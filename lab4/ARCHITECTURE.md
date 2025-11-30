# Design Patterns Architecture - Lab 4

## Package Structure

```
org.example
│
├── Main.java                          # Main demo application
│
├── strategy/                          # STRATEGY PATTERN (Behavioral)
│   ├── ValidationStrategy.java       # Strategy interface
│   ├── BudgetValidator.java          # Context
│   ├── BasicValidationStrategy.java  # Concrete strategy 1
│   ├── StrictValidationStrategy.java # Concrete strategy 2
│   └── FlexibleValidationStrategy.java # Concrete strategy 3
│
├── observer/                          # OBSERVER PATTERN (Behavioral)
│   ├── BudgetObserver.java           # Observer interface
│   ├── BudgetSubject.java            # Subject
│   ├── BudgetEvent.java              # Event types enum
│   ├── EmailNotifier.java            # Concrete observer 1
│   ├── LoggingObserver.java          # Concrete observer 2
│   └── AlertObserver.java            # Concrete observer 3
│
├── chain/                             # CHAIN OF RESPONSIBILITY (Behavioral)
│   ├── ApprovalHandler.java          # Abstract handler
│   ├── BudgetRequest.java            # Request object
│   ├── ApprovalResult.java           # Result object
│   ├── BasicApprovalHandler.java     # Handler 1 (Team Lead)
│   ├── ManagerApprovalHandler.java   # Handler 2 (Manager)
│   ├── DirectorApprovalHandler.java  # Handler 3 (Director)
│   └── ApprovalChainBuilder.java     # Chain builder
│
├── factory/                           # FACTORY METHOD (Creational)
│   ├── BudgetFactory.java            # Factory interface
│   ├── ConcreteBudgetFactory.java    # Factory implementation
│   ├── BudgetPlan.java               # Product interface
│   ├── ConservativeBudget.java       # Concrete product 1
│   └── AggressiveSavingsBudget.java  # Concrete product 2
│
├── singleton/                         # SINGLETON (Creational)
│   └── BudgetConfig.java             # Singleton configuration
│
├── build/                             # BUILDER (Creational)
│   ├── Builder.java                  # Builder interface
│   ├── ConcreteBudgetBuilder.java    # Builder implementation
│   ├── BudgetDirector.java           # Director
│   └── BudgetResult.java             # Product
│
├── facade/                            # FACADE (Structural)
│   ├── BudgetingFacade.java          # Facade
│   ├── BudgetCalculationService.java # Subsystem 1
│   ├── BudgetConstraintValidator.java # Subsystem 2
│   └── BudgetRecommendationEngine.java # Subsystem 3
│
├── adapter/                           # ADAPTER (Structural)
│   ├── Transaction.java              # Target interface
│   ├── TransactionProvider.java      # Adapter manager
│   ├── ExternalBankApi.java          # Adaptee 1
│   ├── BankApiAdapter.java           # Adapter 1
│   ├── CsvImporter.java              # Adaptee 2
│   ├── CsvTransactionAdapter.java    # Adapter 2
│   ├── FinancialServiceClient.java   # Adaptee 3
│   └── FinancialServiceAdapter.java  # Adapter 3
│
├── decorator/                         # DECORATOR (Structural)
│   ├── BudgetReport.java             # Component interface
│   ├── PlainBudgetReport.java        # Concrete component
│   ├── BudgetReportDecorator.java    # Decorator abstract
│   ├── OverageAlertDecorator.java    # Concrete decorator 1
│   ├── MonthlyProjectionDecorator.java # Concrete decorator 2
│   └── ColorCategoryDecorator.java   # Concrete decorator 3
│
└── model/                             # Domain models
    ├── BudgetBreakdown.java
    ├── BudgetUserInput.java
    ├── BudgetFacadeResponse.java
    ├── ConstraintOutcome.java
    └── ExternalSource.java
```

## Pattern Interaction Flow

### Budget Creation Flow (Using All Patterns)

```
User Input
    ↓
[FACADE] BudgetingFacade.createBudget()
    ↓
    ├──→ [FACTORY] Create BudgetPlan
    ↓
    ├──→ [BUILDER] Build BudgetResult
    ↓
    ├──→ [SINGLETON] Get BudgetConfig
    ↓
    ├──→ [ADAPTER] Collect external transactions
    ↓
    ├──→ [DECORATOR] Wrap report with features
    ↓
    ├──→ [STRATEGY] Validate with chosen strategy
    ↓
    ├──→ [OBSERVER] Notify all observers
    ↓
    └──→ [CHAIN] Process approval request
         ↓
Response to User
```

## Pattern Categories Summary

### Creational (3 patterns)

1. **Factory Method** - Flexible budget plan creation
2. **Singleton** - Global configuration access
3. **Builder** - Complex object construction

### Structural (3 patterns)

4. **Facade** - Simplified interface to complex subsystems
5. **Adapter** - External data integration
6. **Decorator** - Dynamic feature addition to reports

### Behavioral (3 patterns)

7. **Strategy** - Interchangeable validation algorithms
8. **Observer** - Event notification system
9. **Chain of Responsibility** - Approval workflow

## Dependencies Between Patterns

```
Facade
 ├── uses Factory (to create budgets)
 ├── uses Builder (to construct results)
 ├── uses Singleton (for configuration)
 ├── uses Adapter (for external data)
 └── uses Decorator (for reports)

Main (Demo)
 ├── uses Factory
 ├── uses Singleton
 ├── uses Builder
 ├── uses Facade (which uses all structural patterns)
 ├── uses Strategy
 ├── uses Observer
 └── uses Chain
```

## Key Design Principles Demonstrated

1. **Single Responsibility** - Each class has one clear purpose
2. **Open/Closed** - Open for extension, closed for modification
3. **Liskov Substitution** - Subtypes can replace base types
4. **Interface Segregation** - Small, focused interfaces
5. **Dependency Inversion** - Depend on abstractions, not concretions

---

**This architecture demonstrates enterprise-level design pattern usage in a real-world budgeting application.**
