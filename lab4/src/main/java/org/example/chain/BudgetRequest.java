package org.example.chain;

/**
 * Represents a budget approval request with all necessary information.
 */
public class BudgetRequest {

    private final String requestId;
    private final double amount;
    private final String department;
    private final String requester;
    private final String justification;
    private final Priority priority;

    public enum Priority {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    private BudgetRequest(Builder builder) {
        this.requestId = builder.requestId;
        this.amount = builder.amount;
        this.department = builder.department;
        this.requester = builder.requester;
        this.justification = builder.justification;
        this.priority = builder.priority;
    }

    public String getRequestId() {
        return requestId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDepartment() {
        return department;
    }

    public String getRequester() {
        return requester;
    }

    public String getJustification() {
        return justification;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("BudgetRequest{id='%s', amount=$%.2f, dept='%s', requester='%s', priority=%s}",
                requestId, amount, department, requester, priority);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String requestId;
        private double amount;
        private String department;
        private String requester;
        private String justification;
        private Priority priority = Priority.MEDIUM;

        public Builder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder requester(String requester) {
            this.requester = requester;
            return this;
        }

        public Builder justification(String justification) {
            this.justification = justification;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public BudgetRequest build() {
            return new BudgetRequest(this);
        }
    }
}
