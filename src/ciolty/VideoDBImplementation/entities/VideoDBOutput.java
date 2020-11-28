package ciolty.VideoDBImplementation.entities;

import ciolty.action.Output;

public final class VideoDBOutput implements Output {
    private final int id;
    private final String message;

    public VideoDBOutput(final int id, final String message) {
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
