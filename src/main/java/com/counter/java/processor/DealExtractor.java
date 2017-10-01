package com.counter.java.processor;

import com.counter.java.util.FileUtil;
import com.counter.java.util.PropertiesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DealExtractor extends Extractor {
    private static final String DEAL_SENTENCE_START = PropertiesUtil.getValue("deal.sentence.start");

    public List<String> extract(Path inputFilePath) throws IOException {
        try (BufferedReader br = FileUtil.toBufferedReader(inputFilePath)) {
            String str;
            List<String> sentences = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                if (!str.startsWith(DEAL_SENTENCE_START)) {
                    continue;
                }

                StringBuffer deal = new StringBuffer();
                deal.append(str);
                while ((str = br.readLine()).length() != 0) {
                    deal.append(str);
                }
                sentences.add(deal.toString());
            }
            return sentences;
        }
    }
}
