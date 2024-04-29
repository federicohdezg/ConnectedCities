package com.admarketplace.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineConverterTest {

    private final LineConverter converter = LineConverter.getInstance();

    @Test
    public void shouldConvert() {
        final List<String> lines = Arrays.asList("A,B", "B,C", "C,D", "D,E", "E,F", "G,H");

        final Map<String, Set<String>> actual = converter.convert(lines);

        assertNotNull(actual);
        assertEquals(8, actual.size());
        Set<String> set = new HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("D");
        set.add("E");
        set.add("F");
        assertEquals(set, actual.get("A"));
        assertEquals(set, actual.get("B"));
        assertEquals(set, actual.get("C"));
        assertEquals(set, actual.get("D"));
        assertEquals(set, actual.get("E"));
        assertEquals(set, actual.get("F"));
        set.clear();
        set.add("G");
        set.add("H");
        assertEquals(set, actual.get("G"));
        assertEquals(set, actual.get("H"));
    }

    @Test
    public void shouldConvertLineFormatError() {
        final List<String> lines = Collections.singletonList("No commas");

        final IllegalStateException actual = assertThrows(IllegalStateException.class, () -> converter.convert(lines));

        assertNotNull(actual);
        final String error = MessageFormat.format(LineConverter.LINE_FORMAT_ERROR, 1, lines.get(0));
        assertEquals(error, actual.getMessage());
    }
}
