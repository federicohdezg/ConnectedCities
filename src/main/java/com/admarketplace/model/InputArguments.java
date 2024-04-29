package com.admarketplace.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.nio.file.Path;

/**
 * Input Arguments data structure.
 */
@Getter
@ToString
@RequiredArgsConstructor
public class InputArguments {

    final String firstCityName;
    final String secondCityName;
    final Path path;

    /**
     * Indicates when First City name is equal to Second City Name.
     * @return <code>true</code> when First City name is equal to Second City Name, <code>false</code> otherwise.
     */
    public boolean equalsCities() {
        return firstCityName != null && firstCityName.equalsIgnoreCase(secondCityName);
    }
}
