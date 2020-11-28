package ciolty.database;

public interface Filter {
    /**
     * @param object
     * @return true if object matches filter pattern
     */
    boolean isValid(Object object);
}
