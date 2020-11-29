package ciolty.action;

public interface Actionable {
    /**
     * The function that the ActionController will call at execution
     * @return message with the result of operation
     */
    String run();
}
