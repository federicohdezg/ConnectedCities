package com.admarketplace.service;

import com.admarketplace.model.InputArguments;
import com.admarketplace.util.FileReader;
import com.admarketplace.util.LineConverter;
import lombok.Getter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Connected Cities Service class to identify if First City name and Second City Name are conected.
 */
public class ConnectedCitiesService {

    public static final String CONNECTED = "CONNECTED";
    public static final String NOT_CONNECTED = "NOT CONNECTED";
    public static final String INPUT_PARAMS_NULL_ERROR = "Input params could not be null";
    @Getter
    private static final ConnectedCitiesService instance = new ConnectedCitiesService();

    private ConnectedCitiesService() {
    }

    /**
     * Identifies if First City name and Second City Name are connected or not.
     * @param params - The input parameters to use.
     * @return <code>CONNECTED</code> when First City name and Second City Name are connected, NOT CONNECTED otherwise.
     * @throws IllegalStateException when params is <code>null</code>.
     * @throws IOException when cannot read the connected cities file.
     */
    public String connectedCities(final InputArguments params) throws IOException {
        if (equalsCities(params)) {
            return CONNECTED; // Don't read file if they are the same cities
        }
        final FileReader reader = FileReader.getInstance();
        final List<String> lines = reader.readLines(params.getPath());
        final LineConverter converter = LineConverter.getInstance();
        return connectedCities(converter.convert(lines), params);
    }

    String connectedCities(final Map<String, Set<String>> connectedCities, final InputArguments params) {
        Set<String> connected = connectedCities.get(params.getFirstCityName());
        if (connected != null && connected.contains(params.getSecondCityName())) {
            return CONNECTED;
        } else {
            connected = connectedCities.get(params.getSecondCityName());
            if (connected != null && connected.contains(params.getFirstCityName())) {
                return CONNECTED;
            }
        }
        return NOT_CONNECTED;
    }

    /**
     * Indicates if First City name and Second City Name are equals.
     * @param params - The input parameters to use.
     * @return <code>true</code> when First City name is equal to Second City Name.
     * @throws IllegalStateException when params is <code>null</code>.
     */
    boolean equalsCities(final InputArguments params) {
        if (params == null) {
            throw new IllegalStateException(INPUT_PARAMS_NULL_ERROR);
        }
        return params.equalsCities();
    }
}
