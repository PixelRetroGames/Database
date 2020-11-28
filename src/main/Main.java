package main;

import checker.Checkstyle;
import checker.Checker;
import ciolty.JSON.JsonConverter;
import ciolty.JSON.Writer;
import ciolty.VideoDBImplementation.JSON.JsonOutputConverter;
import ciolty.VideoDBImplementation.entities.VideoDBInput;
import ciolty.VideoDBImplementation.server.VideoDBServer;
import ciolty.server.Server;
import common.Constants;
import fileio.Input;
import fileio.InputLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implementation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String inputPath,
                              final String outputPath) throws IOException {
        InputLoader inputLoader = new InputLoader(inputPath);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(outputPath);

        //TODO add here the entry point to your implementation

        Server server = new VideoDBServer(new VideoDBInput(input));
        server.runAllActions();
        JsonConverter converter = new JsonOutputConverter();
        String output = converter.convert(server.getOutput());

        if (output.isEmpty()) {
            System.err.println("Output is null!");
            output = " ";
        }

        fileWriter.write(output);
        fileWriter.close();
    }
}
