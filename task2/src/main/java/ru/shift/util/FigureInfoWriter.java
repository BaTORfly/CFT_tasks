package ru.shift.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.parameters.CmdParameters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FigureInfoWriter {

    private static final Logger log = LogManager.getLogger(FigureInfoWriter.class);
    private static final String OUTPUT_FILE_DIR = "task2/src/main/resources";
    private final boolean consoleMode;
    private final boolean fileMode;

    public FigureInfoWriter(CmdParameters cmdParameters) {
        this.consoleMode = cmdParameters.consoleMode();
        this.fileMode = cmdParameters.fileMode();
    }

    public void write(String figureInfo) throws IOException {
        if (consoleMode) {
            printToConsole(figureInfo);
            log.trace("Console mode is active: figure information printed to console.");
        } else if (fileMode) {
            writeToFile(figureInfo);
            log.trace("File mode is active: figure information written to file.");
        }
    }

    private void printToConsole(String figureInfo) {
        System.out.println(figureInfo);
    }

    private void writeToFile(String figureInfo) throws IOException {
        File resourcesDir = new File(OUTPUT_FILE_DIR);
        if (!resourcesDir.exists()) {
            boolean dirCreated = resourcesDir.mkdirs();
            if (!dirCreated) {
                log.error("Error: Unable to create resources directory.");
                return;
            } else {
                log.info("Resources directory created successfully.");
            }
        }
        File outputFile = new File(resourcesDir, "figureInfo.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(figureInfo);
            log.info("Figure information has been successfully written to " + outputFile.getAbsolutePath());
        } catch (IOException ex) {
            throw new IOException("Error writing figure information to " + outputFile.getAbsolutePath(), ex);
        }
    }

}
