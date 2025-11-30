package org.example.chain;

/**
 * Result of a budget approval request processing.
 */
public class ApprovalResult {

    private final boolean approved;
    private final String approver;
    private final String message;

    public ApprovalResult(boolean approved, String approver, String message) {
        this.approved = approved;
        this.approver = approver;
        this.message = message;
    }

    public boolean isApproved() {
        return approved;
    }

    public String getApprover() {
        return approver;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("ApprovalResult{approved=%s, approver='%s', message='%s'}",
                approved, approver, message);
    }
}
