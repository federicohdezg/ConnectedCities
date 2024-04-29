package com.admarketplace.util;

import com.admarketplace.validate.InputValidator;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class LineConverter {

    public static final int CITIES_PER_LINE = 2;
    public static final String CITY_DELIMITER = ",";
    public static final String LINE_FORMAT_ERROR = "CSV Connected cities file line ({0}) had wrong cities format: {1}";
    @Getter
    private static final LineConverter instance = new LineConverter();

    private LineConverter() {
    }

    /**
     * Convert lines to a connected cities' data structure.
     * @param lines - the lines to convert. Each line should have two City Names comma separated.
     * @return conected cities data structure.
     * @throws IllegalArgumentException if any city name is invalid (<code>null</code> or <code>empty</code>)
     * or any line has not two City Names comma separated.
     */
    public Map<String, Set<String>> convert(final List<String> lines) {
        final int size = lines.size();
        final Map<String, Set<String>> connectedCities = new HashMap<>(size);
        final AtomicInteger count = new AtomicInteger();
        lines.forEach(line -> {
            final String[] split = line.split(CITY_DELIMITER);
            count.getAndIncrement();
            if (split.length == CITIES_PER_LINE) {
                convertLine(connectedCities, split, size / 2);
            } else {
                final String error = MessageFormat.format(LINE_FORMAT_ERROR, count, line);
                throw new IllegalStateException(error);
            }
        });
        return connectedCities;
    }

    /**
     * Convert a line with two city names. Add a map entry for each city name.
     * Both map entries have the same <cde>Set</cde> of city names containing all the connected cities.
     * We use the same <cde>Set</cde> to optimize memory use.
     * @param connectedCities - data structure to find connected cities.
     * @param cities - array with the two city names to connect.
     * @param size - initial Set size trying to avoid rehashing.
     * @throws IllegalArgumentException if any city name is invalid (<code>null</code> or <code>empty</code>)
     */
    void convertLine(final Map<String, Set<String>> connectedCities, final String[] cities, final int size) {
        final InputValidator validator = InputValidator.getInstance();
        String firstCity = validator.validateFirstCity(cities[0]);
        Set<String> connected = connectedCities.get(firstCity);
        String secondCity = validator.validateSecondCity(cities[1]);
        if (connected == null) {
            connected = connectedCities.get(secondCity);
        }
        if (connected == null) {
            connected = new HashSet<>(size);
        }
        connected.add(firstCity);
        connected.add(secondCity);
        connectedCities.put(firstCity, connected);
        connectedCities.put(secondCity, connected);
    }
}
