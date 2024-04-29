package com.admarketplace.util;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    @Getter
    private static final FileReader instance = new FileReader();

    private FileReader() {
    }

    /**
     * Read lines of a text file and create a connected cities' data structure.
     * @param path - file path to read.
     * @return the List of lines read from file.
     * @throws IOException when we cannot read the file.
     */
    public List<String> readLines(final Path path) throws IOException {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.collect(Collectors.toList());
        }
    }
}
