package org.example.chain;

/**
 * Second level handler: Can approve medium-sized budget requests.
 * Approval limit: $25,000
 */
public class ManagerApprovalHandler extends ApprovalHandler {

    private static final double MANAGER_LIMIT = 25000.0;

    public ManagerApprovalHandler() {
        super("Department Manager", MANAGER_LIMIT);
    }

    @Override
    protected boolean canApprove(BudgetRequest request) {
        // Manager can approve amounts within limit
        // Can also approve HIGH priority requests if within limit
        return request.getAmount() <= approvalLimit;
    }

    @Override
    protected ApprovalResult approve(BudgetRequest request) {
        System.out.println("   ✅ APPROVED by " + handlerName);
        System.out.println("   Decision: Request requires managerial approval but is within authority");

        String message = String.format(
                "Approved by %s. Budget request of $%.2f for %s department has been reviewed and approved. Priority: %s",
                handlerName, request.getAmount(), request.getDepartment(), request.getPriority());

        return new ApprovalResult(true, handlerName, message);
    }
}
