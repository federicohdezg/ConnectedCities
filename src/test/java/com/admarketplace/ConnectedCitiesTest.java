package com.admarketplace;

import com.admarketplace.service.ConnectedCitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConnectedCitiesTest {

    private URL resource;

    @BeforeEach
    public void beforeEach() {
        resource = getClass().getClassLoader().getResource("connected.txt");
        assertNotNull(resource);
    }

    @Test
    public void shouldConnected() throws IOException {
        final String[] args = new String[]{"Houston", "Nashville", resource.getPath()};

        String actual = ConnectedCities.connected(args);

        assertEquals(ConnectedCitiesService.CONNECTED, actual);
    }

    @Test
    public void shouldConnectedFailed() {
        final String[] args = new String[]{"", "Nashville", resource.getPath()};

        final IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> ConnectedCities.connected(args));

        assertNotNull(actual);
    }
}
