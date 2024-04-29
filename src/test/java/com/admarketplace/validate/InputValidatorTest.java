package com.admarketplace.validate;

import com.admarketplace.model.InputArguments;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.net.URL;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputValidatorTest {

    private final InputValidator validator = InputValidator.getInstance();

    @Test
    public void shouldValidateArgumentsNull() {
        final IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> validator.validateArguments(null));

        assertNotNull(actual);
        assertEquals(InputValidator.NO_ARGUMENTS_ERROR, actual.getMessage());
    }

    @ParameterizedTest
    @MethodSource("wrongArgumentsNumber")
    public void shouldValidateArgumentsWrongArgumentsNumber(final String[] args) {
        final IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> validator.validateArguments(args));

        assertNotNull(actual);
        assertEquals(InputValidator.WRONG_ARGUMENTS_NUMBER_ERROR, actual.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void shouldValidateArgumentsFirstCity(final String first) {
        final String[] args = new String[]{first, null, null};
        final IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> validator.validateArguments(args));

        assertNotNull(actual);
        assertEquals(InputValidator.FIRST_CITY_ERROR, actual.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void shouldValidateArgumentsSecondCity(final String second) {
        final String[] args = new String[]{"First", second, null};
        final IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> validator.validateArguments(args));

        assertNotNull(actual);
        assertEquals(InputValidator.SECOND_CITY_ERROR, actual.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void shouldValidateArgumentsFileMissing(final String filePath) {
        final String[] args = new String[]{"First", "Second", filePath};
        final IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> validator.validateArguments(args));

        assertNotNull(actual);
        assertEquals(InputValidator.FILE_MISSING_ERROR, actual.getMessage());
    }

    @Test
    public void shouldValidateArgumentsFileDoesNotExist() {
        final String[] args = new String[]{"First", "Second", "file.txt"};
        final IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> validator.validateArguments(args));

        assertNotNull(actual);
        assertTrue(actual.getMessage().startsWith(InputValidator.FILE_NOT_EXIST_ERROR));
    }

    @Test
    public void shouldValidateArguments() {
        final URL resource = getClass().getClassLoader().getResource("connected.txt");
        assertNotNull(resource);
        final String[] args = new String[]{"First", "Second", resource.getPath()};
        final InputArguments actual = validator.validateArguments(args);

        assertNotNull(actual);
        assertEquals(args[0], actual.getFirstCityName());
        assertEquals(args[1], actual.getSecondCityName());
        assertEquals(Paths.get(args[2]), actual.getPath());
    }

    private static Stream<Arguments> wrongArgumentsNumber() {
        return Stream.of(
                Arguments.of((Object) new String[0]),
                Arguments.of((Object) new String[]{"One"}),
                Arguments.of((Object) new String[]{"One", "Two"}),
                Arguments.of((Object) new String[]{"One", "Two", "Three", "Four"})
        );
    }
}
