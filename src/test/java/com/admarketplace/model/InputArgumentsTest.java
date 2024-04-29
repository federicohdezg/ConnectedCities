package com.admarketplace.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputArgumentsTest {

    @Test
    public void shouldEqualsCities() {
        final String city = "New York";
        final InputArguments object = new InputArguments(city, city, null);

        assertTrue(object.equalsCities());
    }

    @Test
    public void shouldEqualsCitiesNull() {
        final String city = "New York";
        InputArguments object = new InputArguments(city, null, null);
        assertFalse(object.equalsCities());

        object = new InputArguments(null, city, null);
        assertFalse(object.equalsCities());
    }

    @Test
    public void shouldNotEqualsCities() {
        final InputArguments object = new InputArguments("New York", "Jersey City", null);

        assertFalse(object.equalsCities());
    }
}
