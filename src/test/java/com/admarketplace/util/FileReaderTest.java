package com.admarketplace.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileReaderTest {

    private final FileReader reader = FileReader.getInstance();

    @Test
    public void shouldReadLines() throws IOException {
        final URL resource = getClass().getClassLoader().getResource("connected.txt");
        assertNotNull(resource);
        final Path path = Paths.get(resource.getPath());

        final List<String> actual = reader.readLines(path);

        assertNotNull(actual);
        assertEquals(16, actual.size());
    }

    @Test
    public void shouldReadLinesException() {
        final Path path = Paths.get("connected.txt");

        final IOException actual = assertThrows(IOException.class, () -> reader.readLines(path));

        assertNotNull(actual);
    }
}
