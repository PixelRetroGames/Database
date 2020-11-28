package ciolty.action;

/**
 * View controller: takes data and transforms it in Output
 */
public class Output {
    private final int id;
    private final String message;

    public Output(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
