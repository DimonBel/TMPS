package org.example.chain;

/**
 * Factory class to build the chain of approval handlers.
 * Provides predefined chains for different organizational structures.
 */
public class ApprovalChainBuilder {

    /**
     * Creates a standard approval chain: Team Lead -> Manager -> Director
     */
    public static ApprovalHandler buildStandardChain() {
        ApprovalHandler basic = new BasicApprovalHandler();
        ApprovalHandler manager = new ManagerApprovalHandler();
        ApprovalHandler director = new DirectorApprovalHandler();

        basic.setNext(manager);
        manager.setNext(director);

        System.out.println("📋 Standard approval chain created:");
        System.out.println("   1. " + basic.getHandlerName() + " (up to $"
                + String.format("%.0f", basic.getApprovalLimit()) + ")");
        System.out.println("   2. " + manager.getHandlerName() + " (up to $"
                + String.format("%.0f", manager.getApprovalLimit()) + ")");
        System.out.println("   3. " + director.getHandlerName() + " (up to $"
                + String.format("%.0f", director.getApprovalLimit()) + ")");

        return basic; // Return first handler in chain
    }

    /**
     * Creates a fast-track chain: Manager -> Director (skips basic level)
     * Useful for urgent or pre-approved departments
     */
    public static ApprovalHandler buildFastTrackChain() {
        ApprovalHandler manager = new ManagerApprovalHandler();
        ApprovalHandler director = new DirectorApprovalHandler();

        manager.setNext(director);

        System.out.println("⚡ Fast-track approval chain created:");
        System.out.println("   1. " + manager.getHandlerName() + " (up to $"
                + String.format("%.0f", manager.getApprovalLimit()) + ")");
        System.out.println("   2. " + director.getHandlerName() + " (up to $"
                + String.format("%.0f", director.getApprovalLimit()) + ")");

        return manager;
    }

    /**
     * Creates a single-handler chain for testing or special cases
     */
    public static ApprovalHandler buildSingleHandlerChain() {
        ApprovalHandler director = new DirectorApprovalHandler();

        System.out.println("👤 Single handler chain created:");
        System.out.println("   1. " + director.getHandlerName() + " (up to $"
                + String.format("%.0f", director.getApprovalLimit()) + ")");

        return director;
    }
}
