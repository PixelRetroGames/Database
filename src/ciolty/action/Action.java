package ciolty.action;

/**
 * Interface used by user to create new behaviour
 */
public interface Action extends Actionable {
    /**
     * Called after setting the data, before execution.
     * Can be overriden for initializing data and returning error messages.
     * @return null if no error
     */
    String start();

    /**
     * The function that the ActionController will call at execution
     * after checking data.
     * @return message with the result of operation
     */
    String execute();
}
