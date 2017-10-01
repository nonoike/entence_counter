package com.counter.java;

import com.counter.java.domain.Counter;
import com.counter.java.processor.CauseExtractor;
import com.counter.java.processor.DealExtractor;
import com.counter.java.processor.Extractor;
import com.counter.java.util.FileUtil;
import com.counter.java.util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Executor {
    private static final String OUTPUT_DIR_PATH = PropertiesUtil.getValue("output.dir.path");
    private static final String READ_DIR_PATH = PropertiesUtil.getValue("read.dir.path");
    private static final String READ_FILE_EXTENSION = PropertiesUtil.getValue("read.file.extension");
    private static final String MERGE_FILE_NAME = PropertiesUtil.getValue("merge.file.name");
    private static final String CAUSE_FILE_NAME = PropertiesUtil.getValue("cause.file.name");
    private static final String DEAL_FILE_NAME = PropertiesUtil.getValue("deal.file.name");

    public static void main(String[] args) {
        try {
            Path mergedFilePath = outputMergeFile();
            Path causeFilePath = outputCauseFile(mergedFilePath);
            Path dealFilePath = outputDealFile(mergedFilePath);
        } catch (IOException e) {
            // TODO: consider the exception
            e.printStackTrace();
        }
    }

    private static Path outputMergeFile() throws IOException {
        Path readDir = Paths.get(READ_DIR_PATH);
        List<Path> paths = FileUtil.getFilteredFilePaths(readDir, READ_FILE_EXTENSION);

        Path outputFilePath = Paths.get(OUTPUT_DIR_PATH + File.separator + MERGE_FILE_NAME);
        FileUtil.merge(outputFilePath, paths);

        return outputFilePath;
    }

    private static Path outputCauseFile(Path inputFilePath) throws IOException {
        Extractor extractor = new CauseExtractor();
        List<String> sentences = extractor.extract(inputFilePath);
        Counter causeCounter = Counter.factory(sentences);

        Path outputFilePath = Paths.get(OUTPUT_DIR_PATH + File.separator + CAUSE_FILE_NAME);
        FileUtil.write(outputFilePath, causeCounter.toOutputFormatStrings());

        return outputFilePath;
    }

    private static Path outputDealFile(Path inputFilePath) throws IOException {
        Extractor extractor = new DealExtractor();
        List<String> sentences = extractor.extract(inputFilePath);
        Counter dealCounter = Counter.factory(sentences);

        Path outputFilePath = Paths.get(OUTPUT_DIR_PATH + File.separator + DEAL_FILE_NAME);
        FileUtil.write(outputFilePath, dealCounter.toOutputFormatStrings());

        return outputFilePath;
    }
}
