package ciolty.engine.JSON;

import java.io.FileWriter;
import java.io.IOException;

public final class Writer {
    private final FileWriter file;

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * Opens file from path
     * @param path
     */
    public void write(final String path) {
        try {
            file.write(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close file
     */
    public void close() {
        try {
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
