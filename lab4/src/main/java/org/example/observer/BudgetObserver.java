package org.example.observer;

/**
 * Observer Pattern: Interface for observers that receive budget notifications.
 */
public interface BudgetObserver {

    /**
     * Called when a budget event occurs.
     * 
     * @param event   The type of event
     * @param message Details about the event
     */
    void update(BudgetEvent event, String message);
}
