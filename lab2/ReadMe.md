# Topic: 3 Design Patterns — Factory, Singleton, Builder

## Author: Belih Dmitrii

## Objectives

1. Implement and demonstrate three design patterns:
   - Factory Method
   - Singleton
   - Builder

## Main tasks

1. Design a factory to create different budget plans.
2. Provide a global configuration using the Singleton pattern.
3. Implement a Builder to construct complex budget result objects.

## Implementation

This lab uses Java. The `lab2` module implements the three design patterns listed above.

- Factory Method

  - Purpose: centralize creation of different types of objects (here — different budget plans).
  - Files / classes: `org.example.factory.BudgetFactory`, `org.example.factory.ConcreteBudgetFactory`,
    `org.example.factory.BudgetPlan`, `org.example.factory.ConservativeBudget`, `org.example.factory.AggressiveSavingsBudget`.
  - Description: `ConcreteBudgetFactory` returns the appropriate `BudgetPlan` implementation based on a key.
    Each `BudgetPlan` exposes an `allocate()` method that produces a textual representation of the plan.

- Singleton

  - Purpose: provide a single global object to hold category limit configuration.
  - Files / classes: `org.example.singleton.BudgetConfig`.
  - Description: the class stores a map of category limits (`food`, `housing`, `entertainment`, etc.),
    exposes `get()` to retrieve the single instance, and provides `getLimit()`, `setLimit()` and `getAllLimits()`.

- Builder

  - Purpose: build complex `BudgetResult` objects step-by-step.
  - Files / classes: `org.example.builder.BudgetResult`, `org.example.builder.BudgetResult.Builder`, `org.example.builder.BudgetResultBuilder`.
  - Description: `BudgetResult` holds the final allocation (fields: `food`, `housing`, `entertainment`, `savings`, `customCategories`).
    The inner `public static class Builder` allows `new BudgetResult.Builder()` and chained calls, then `build()` returns the constructed `BudgetResult`.
    There is also an external `BudgetResultBuilder` that delegates to the inner Builder for API compatibility.

## Usage example (in the project)

- `org.example.Main` demonstrates all three patterns:
  1. Creating budget plans via the factory (`ConcreteBudgetFactory`).
  2. Reading and modifying global configuration via `BudgetConfig.get()`.
  3. Building `BudgetResult` via `new BudgetResult.Builder()` and via `BudgetResultBuilder`.

Snippet (from `Main`):

```java
BudgetFactory factory = new ConcreteBudgetFactory();
BudgetConfig config = BudgetConfig.get();

BudgetResult budget = new BudgetResult.Builder()
        .setFood(1200.0)
        .setHousing(1800.0)
        .setEntertainment(600.0)
        .setSavings(1500.0)
        .addCustomCategory("Transportation", 400.0)
        .build();
```

- The Builder is implemented as `public static class Builder` inside `BudgetResult` — this is a common and convenient approach: callers can use `new BudgetResult.Builder()` while the inner Builder still has access to the private fields of `BudgetResult` via the instance it constructs.
- The project also includes an external helper `BudgetResultBuilder` that delegates to the inner Builder for an alternative API.

## Conclusions

This lab implements three fundamental design patterns and demonstrates their cooperation:

- Factory Method encapsulates creation of different budget plans and makes the system extensible.
- Singleton provides a single, global configuration for category limits.
- Builder simplifies construction of complex budget results and makes calling code concise and safe.

Using these patterns improves readability, extensibility, and maintainability of the code.
