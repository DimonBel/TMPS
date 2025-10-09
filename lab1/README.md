# Topic: _SOLID Principles_

## Author: _Belih Dmitrii_

## Objectives:

1. Study and understand the The SOLID principles

## Main tasks:

1. Write 3 letters from SOLID

## ImplementationFor this Lab I decided to use Java, and Implement the S/I/D letters from SOLID Principles:

1. S — Single Responsibility Principle

   `A class should have only one reason to change.`

   GitVersionControl class has only handles Git-related operations (commit, push, pull).
   It does not deal with how the team works or coordinates — just version control.

DevelopmentTeam class is responsible only for the team’s actions (committing, pushing, pulling).
It does not know or care about how Git actually does these actions.

2. I — Interface Segregation Principle

   `Clients should not be forced to depend on interfaces they do not use.`

An interface IVersionControl with only three methods:
commit(), push(), and pull().

3. D — Dependency Inversion Principle

   `High-level modules should not depend on low-level modules; both should depend on abstractions.`

**private IVersionControl versionControl;**

Then in Main, you inject the dependency:

**DevelopmentTeam team = new DevelopmentTeam(new GitVersionControl());**

So if you later create another implementation, for example:

**class SVNVersionControl implements IVersionControl { ... }**

The class DevelopmentTeam (high-level module) depends on the interface IVersionControl (abstraction), not on GitVersionControl (concrete class).

## Conclusions

In this lab, I explored and implemented three core SOLID principles — Single Responsibility, Interface Segregation, and Dependency Inversion — using Java. Through this exercise, I learned how applying these principles helps create clean, modular, and maintainable code.

By following the Single Responsibility Principle, each class was given one clear purpose, reducing complexity and making future changes easier. The Interface Segregation Principle ensured that classes depend only on what they actually use, leading to more flexible and reusable components. Finally, the Dependency Inversion Principle demonstrated how high-level classes can rely on abstractions instead of concrete implementations, improving extensibility and testability.

Overall, this lab showed the importance of designing software that is easy to modify, extend, and understand. Implementing SOLID principles leads to better-structured code and prepares projects for scalability and long-term maintenance.
