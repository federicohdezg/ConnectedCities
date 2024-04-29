package com.admarketplace.service;

import com.admarketplace.model.InputArguments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectedCitiesServiceTest {

    private final ConnectedCitiesService service = ConnectedCitiesService.getInstance();
    private URL resource;

    @BeforeEach
    public void beforeEach() {
        resource = getClass().getClassLoader().getResource("connected.txt");
        assertNotNull(resource);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Cleveland, Cleveland", "Cleveland,Chicago", "Cleveland,New York", "Cleveland,Los Angeles", "Cleveland,Phoenix",
            "Chicago,Cleveland", "Chicago,Chicago", "Chicago,New York", "Chicago,Los Angeles", "Chicago, Phoenix",
            "New York,Cleveland ", "New York,Chicago", "New York,New York", "New York, Los Angeles", "New York,Phoenix",
            "Los Angeles,Cleveland", "Los Angeles,Chicago", "Los Angeles,New York", "Los Angeles,Los Angeles", "Los Angeles,Phoenix",
            "Phoenix,Cleveland", "Phoenix, Chicago", "Phoenix,New York ", "Phoenix,Los Angeles", "Phoenix,Phoenix"
    })
    public void shouldConnectedCitiesConnected(final String first, final String second) throws IOException {
        InputArguments input = new InputArguments(first, second, Paths.get(resource.getPath()));

        final String actual = service.connectedCities(input);

        assertEquals(ConnectedCitiesService.CONNECTED, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Cleveland,Houston", "Cleveland,Denver", "Nashville,New York", "Columbus,Los Angeles", "Kansas City,Phoenix",
            "Chicago,Omaha", "Charlotte,New York", "Tampa,Los Angeles", "Seattle,Columbus", "First, Second"
    })
    public void shouldConnectedCitiesNotConnected(final String first, final String second) throws IOException {
        InputArguments input = new InputArguments(first, second, Paths.get(resource.getPath()));

        final String actual = service.connectedCities(input);

        assertEquals(ConnectedCitiesService.NOT_CONNECTED, actual);
    }
}
