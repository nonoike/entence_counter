package com.counter.java.processor;

import com.counter.java.util.FileUtil;
import com.counter.java.util.PropertiesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class CauseExtractor extends Extractor {
    private static final String CAUSE_SENTENCE_START = PropertiesUtil.getValue("cause.sentence.start");

    @Override
    public List<String> extract(Path inputFilePath) throws IOException {
        try (BufferedReader br = FileUtil.toBufferedReader(inputFilePath)) {
            return br.lines()
                    .filter(s -> s.contains(CAUSE_SENTENCE_START))
                    .collect(Collectors.toList());
        }
    }
}
