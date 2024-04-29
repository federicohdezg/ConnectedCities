package com.admarketplace.validate;

import com.admarketplace.model.InputArguments;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to validate input parameters.
 */
public class InputValidator {

    public static final String FILE_NOT_EXIST_ERROR = "Text Connected cities file doesn't exist: ";
    public static final String FILE_MISSING_ERROR = "Text Connected cities file missing";
    public static final String FIRST_CITY_ERROR = "First City name missing";
    public static final String NO_ARGUMENTS_ERROR = "No Application parameters";
    public static final String SECOND_CITY_ERROR = "Second City name missing";
    public static final String WRONG_ARGUMENTS_NUMBER_ERROR = "Wrong number of Application parameters. Expected 3 arguments";
    @Getter
    private static final InputValidator instance = new InputValidator();

    private InputValidator() {
    }

    /**
     * Validate application arguments.
     * @param args - the arguments to validate.
     * @return a valid input argument object.
     * @throws IllegalStateException when arguments are invalid.
     */
    public InputArguments validateArguments(final String[] args) {
        if (args == null) {
            throw new IllegalArgumentException(NO_ARGUMENTS_ERROR);
        }
        if (args.length != 3) {
            throw new IllegalArgumentException(WRONG_ARGUMENTS_NUMBER_ERROR);
        }
        final String firstCity = validateFirstCity(args[0]);
        final String secondCity = validateSecondCity(args[1]);
        if (StringUtils.isEmpty(args[2])) {
            throw new IllegalArgumentException(FILE_MISSING_ERROR);
        }
        final Path path = Paths.get(args[2]);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(FILE_NOT_EXIST_ERROR + path);
        }
        return new InputArguments(firstCity, secondCity, path);
    }

    /**
     * Validate First City name.
     * @param name - name to validate.
     * @return valida city name, all leading and trailing space removed.
     * @throws IllegalStateException when name is <code>null</code> or <code>empty</code>.
     */
    public String validateFirstCity(String name) {
        return validateName(name, FIRST_CITY_ERROR);
    }

    /**
     * Validate Second City name.
     * @param name - name to validate.
     * @return valida city name, all leading and trailing space removed.
     * @throws IllegalStateException when name is <code>null</code> or <code>empty</code>.
     */
    public String validateSecondCity(String name) {
        return validateName(name, SECOND_CITY_ERROR);
    }

    private String validateName(String name, final String errorMessage) {
        if (name != null) {
            name = name.trim();
        }
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException(errorMessage);
        }
        return name;
    }
}
