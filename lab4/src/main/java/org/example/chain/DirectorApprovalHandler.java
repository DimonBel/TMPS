package org.example.chain;

/**
 * Third level handler: Can approve large budget requests and critical
 * priorities.
 * Approval limit: $100,000
 */
public class DirectorApprovalHandler extends ApprovalHandler {

    private static final double DIRECTOR_LIMIT = 100000.0;

    public DirectorApprovalHandler() {
        super("Executive Director", DIRECTOR_LIMIT);
    }

    @Override
    protected boolean canApprove(BudgetRequest request) {
        // Director can approve large amounts and all priority levels
        return request.getAmount() <= approvalLimit;
    }

    @Override
    protected ApprovalResult approve(BudgetRequest request) {
        System.out.println("   ✅ APPROVED by " + handlerName);
        System.out.println("   Decision: Executive level approval granted");

        String justificationNote = request.getJustification() != null && !request.getJustification().isEmpty()
                ? " Justification: " + request.getJustification()
                : "";

        String message = String.format(
                "Approved by %s. Large budget request of $%.2f has been approved for %s department (Priority: %s).%s",
                handlerName, request.getAmount(), request.getDepartment(),
                request.getPriority(), justificationNote);

        return new ApprovalResult(true, handlerName, message);
    }

    @Override
    protected ApprovalResult reject(BudgetRequest request, String reason) {
        // Director can provide more detailed rejection reasons
        if (request.getAmount() > approvalLimit) {
            reason = String.format("Amount $%.2f exceeds executive authority limit of $%.2f. Board approval required.",
                    request.getAmount(), approvalLimit);
        }
        return super.reject(request, reason);
    }
}
