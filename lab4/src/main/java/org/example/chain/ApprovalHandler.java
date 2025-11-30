package org.example.chain;

/**
 * Chain of Responsibility Pattern: Abstract handler for budget approval
 * requests.
 * Each handler decides whether to approve the request or pass it to the next
 * handler.
 */
public abstract class ApprovalHandler {

    protected ApprovalHandler nextHandler;
    protected final String handlerName;
    protected final double approvalLimit;

    public ApprovalHandler(String handlerName, double approvalLimit) {
        this.handlerName = handlerName;
        this.approvalLimit = approvalLimit;
    }

    /**
     * Set the next handler in the chain.
     */
    public ApprovalHandler setNext(ApprovalHandler handler) {
        this.nextHandler = handler;
        return handler;
    }

    /**
     * Handle the approval request.
     * If this handler can't approve it, pass to the next handler.
     */
    public final ApprovalResult handleRequest(BudgetRequest request) {
        System.out.println("\n[" + handlerName + "] Reviewing request: " + request.getRequestId());
        System.out.println("   Request amount: $" + String.format("%.2f", request.getAmount()));
        System.out.println("   My approval limit: $" + String.format("%.2f", approvalLimit));

        // Check if this handler can approve
        if (canApprove(request)) {
            return approve(request);
        } else if (nextHandler != null) {
            System.out.println("   ⬆️ Escalating to next level...");
            return nextHandler.handleRequest(request);
        } else {
            return reject(request, "No handler with sufficient authority found");
        }
    }

    /**
     * Determine if this handler can approve the request.
     */
    protected abstract boolean canApprove(BudgetRequest request);

    /**
     * Approve the request.
     */
    protected abstract ApprovalResult approve(BudgetRequest request);

    /**
     * Reject the request with a reason.
     */
    protected ApprovalResult reject(BudgetRequest request, String reason) {
        System.out.println("   ❌ REJECTED by " + handlerName);
        System.out.println("   Reason: " + reason);
        return new ApprovalResult(false, handlerName, reason);
    }

    public String getHandlerName() {
        return handlerName;
    }

    public double getApprovalLimit() {
        return approvalLimit;
    }
}
