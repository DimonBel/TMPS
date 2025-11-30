package org.example.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern: Subject that manages observers and notifies them of
 * changes.
 * This class maintains a list of observers and notifies them when budget events
 * occur.
 */
public class BudgetSubject {

    private final List<BudgetObserver> observers = new ArrayList<>();

    /**
     * Register an observer to receive notifications.
     */
    public void attach(BudgetObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("[BudgetSubject] Observer attached: " + observer.getClass().getSimpleName());
        }
    }

    /**
     * Unregister an observer.
     */
    public void detach(BudgetObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("[BudgetSubject] Observer detached: " + observer.getClass().getSimpleName());
        }
    }

    /**
     * Notify all registered observers of a budget event.
     */
    public void notifyObservers(BudgetEvent event, String message) {
        System.out.println("[BudgetSubject] Notifying " + observers.size() +
                " observer(s) about: " + event.getDescription());
        for (BudgetObserver observer : observers) {
            observer.update(event, message);
        }
    }

    /**
     * Get the number of registered observers.
     */
    public int getObserverCount() {
        return observers.size();
    }
}
