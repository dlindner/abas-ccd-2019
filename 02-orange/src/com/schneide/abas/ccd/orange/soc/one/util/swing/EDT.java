package com.schneide.abas.ccd.orange.soc.one.util.swing;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

/**
 * Helper class to access the Swing UI Thread (EDT) more easily.
 * It also helps with asserting proper EDT usage.
 */
public final class EDT {

    private EDT() {
        super();
    }

    /**
     * Checks if this call is <b>always</b> made from the EDT.
     * Same as only().
     */
    public static void always() {
        checkIsOnEDT();
    }

    /**
     * Indicates that this call can be made from the EDT or any other thread.
     * This assertion never fails, it's meant for documentation purposes.
     */
    public static void whatever() {
        // does nothing on purpose, only for documentation reasons
    }

    /**
     * Checks if this call is <b>only</b> made from the EDT.
     * Same as always().
     */
    public static void only() {
        checkIsOnEDT();
    }

    /**
     * Checks if this call is <b>never</b> made from the EDT.
     */
    public static void never() {
        if (EDT.isThis()) {
            EDT.reportEDTUsageFailure(" was called on EDT, though it shouldn't!"); //$NON-NLS-1$
        }
    }

    protected static void checkIsOnEDT() {
        if (!EDT.isThis()) {
            EDT.reportEDTUsageFailure(" was not called on EDT, though it should have been!"); //$NON-NLS-1$
        }
    }

    protected static void reportEDTUsageFailure(final String message) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        System.err.println(describe(ste[4]) + message + " (Came from " + describe(ste[5]) + ")"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private static String describe(final StackTraceElement stackElement) {
        return stackElement.getClassName() + "->" + stackElement.getMethodName(); //$NON-NLS-1$
    }

    public static boolean isThis() {
        return EventQueue.isDispatchThread();
    }

    public static void later(final Runnable actionOnEDT) {
        EventQueue.invokeLater(actionOnEDT);
    }

    public static void perform(final Runnable actionOnEDT) {
        if (!EDT.isThis()) {
            later(actionOnEDT);
            return;
        }
        actionOnEDT.run();
    }

    public static void performBlocking(final Runnable actionOnEDT) {
        if (!EDT.isThis()) {
            try {
                EventQueue.invokeAndWait(actionOnEDT);
            } catch (final InterruptedException e) {
                System.err.println("Could not wait on EDT action, interrupted. " + e); //$NON-NLS-1$
                Thread.currentThread().interrupt();
            } catch (final InvocationTargetException e) {
            	System.err.println("Exception while performing blocking EDT action."); //$NON-NLS-1$
                e.printStackTrace();
            }
            return;
        }
        actionOnEDT.run();
    }

    public static void waitUntilAllPendingEventsAreProcessed() {
        performBlocking(() -> {
            // does nothing intentionally
        });
    }

    public static <T> T query(final EDTQuery<T> query) {
        final EDTQueryResult<T> result = new EDTQueryResult<T>(query);
        performBlocking(result);
        return result.getResult();
    }

    private static class EDTQueryResult<TYPE> implements Runnable {

        private TYPE result;
        private final EDTQuery<TYPE> query;

        public EDTQueryResult(final EDTQuery<TYPE> query) {
            super();
            this.query = query;
            this.result = null;
        }

        @Override
        public void run() {
            this.result = this.query.perform();
        }

        public TYPE getResult() {
            return this.result;
        }
    }

    /**
     * Defines a query that should be performed on the EDT by EDT.performQuery()
     */
    @FunctionalInterface
    public interface EDTQuery<TYPE> {

        public abstract TYPE perform();
    }
}
