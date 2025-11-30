package org.example.chain;

/**
 * First level handler: Can approve small budget requests.
 * Approval limit: $5,000
 */
public class BasicApprovalHandler extends ApprovalHandler {

    private static final double BASIC_LIMIT = 5000.0;

    public BasicApprovalHandler() {
        super("Team Lead", BASIC_LIMIT);
    }

    @Override
    protected boolean canApprove(BudgetRequest request) {
        // Basic handler can approve if amount is within limit and priority is not
        // critical
        return request.getAmount() <= approvalLimit &&
                request.getPriority() != BudgetRequest.Priority.CRITICAL;
    }

    @Override
    protected ApprovalResult approve(BudgetRequest request) {
        System.out.println("   ✅ APPROVED by " + handlerName);
        System.out.println("   Decision: Request is within basic approval threshold");

        String message = String.format(
                "Approved by %s. Amount $%.2f is within the $%.2f limit for %s priority requests.",
                handlerName, request.getAmount(), approvalLimit, request.getPriority());

        return new ApprovalResult(true, handlerName, message);
    }
}
