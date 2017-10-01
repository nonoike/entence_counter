package com.counter.java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileUtil {
    private FileUtil() {
    }

    public static BufferedReader toBufferedReader(Path inputFilePath) throws IOException {
        return Files.newBufferedReader(inputFilePath, StandardCharsets.UTF_8);
    }

    public static List<Path> getFilteredFilePaths(Path inputDirPath, String selectExtension) throws IOException {
        Predicate<Path> isSelectExtension = p -> p.toString().endsWith(selectExtension);

        return Files.list(inputDirPath)
                .filter(isSelectExtension)
                .collect(Collectors.toList());
    }

    public static void merge(Path outputFilePath, List<Path> readFilePaths) throws IOException {
        for (Path readFilePath : readFilePaths) {
            write(outputFilePath, Files.readAllLines(readFilePath));
        }
    }

    public static void write(Path outputFilePath, List<String> lines) throws IOException {
        Files.write(outputFilePath, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
