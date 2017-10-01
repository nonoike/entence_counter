package com.counter.java.processor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public abstract class Extractor {
    public abstract List<String> extract(Path inputFilePath) throws IOException;
}
