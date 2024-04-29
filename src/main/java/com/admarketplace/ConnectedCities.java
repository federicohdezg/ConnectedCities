package com.admarketplace;

import com.admarketplace.model.InputArguments;
import com.admarketplace.service.ConnectedCitiesService;
import com.admarketplace.validate.InputValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Connected Cities application, which takes three arguments:
 * <ul>
 *     <li>First city name</li>
 *     <li>Second city name</li>
 *     <li>Third a path to a text file containing comma-separated pairs of connected cities, one pair per line
 * (a pair represents a bi-directional connection: pair "A, B" implies city A is connected to city B,
 * and city B is connected to city A)</li>
 * </ul>
 * If the cities specified by the first two arguments are connected then the program prints CONNECTED.
 * Otherwise, it prints NOT CONNECTED.
 */
public class ConnectedCities {

    private static final Logger logger = LogManager.getLogger(ConnectedCities.class);

    public static void main(final String[] args) {
        try {
            logger.info(connected(args));
        } catch (Exception e) {
            logger.error("An error:", e);
        }
    }

    static String connected(String[] args) throws IOException {
        final InputValidator validator = InputValidator.getInstance();
        final InputArguments params = validator.validateArguments(args);
        final ConnectedCitiesService service = ConnectedCitiesService.getInstance();
        return service.connectedCities(params);
    }
}
