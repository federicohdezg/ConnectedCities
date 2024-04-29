# ConnectedCities
Java program which takes three arguments:
- first city name
- second city name
- third a path to a text file containing comma-separated pairs of connected cities, one pair per line
   (a pair represents a bidirectional connection: pair "A, B" implies city A is connected to city B,
   and city B is connected to city A)
- If the cities specified by the first two arguments are connected then the program prints CONNECTED.
   Otherwise, it prints NOT CONNECTED.

## Data Structure
I chose a simple Map using every City Name as key and storing all connected cities for each into a Set.
All the connected cities share the same Set to optimize memory.

Of course with large/huge cities CSV files this code will be out of memory.
But for that kind of scenarios a better datasource should be used, non-relational database, key-value store,
search engine like Lucene, even a relation database.

## Java version
Code is currently using Java 8 version.
It was created with Java 17, but not sure which Java version is required, then downgrade to Java 8.

## Maven
Project could ne build using Maven.
This jar `connected-cities-1.0.0-jar-with-dependencies.jar` is generated executing
```
mvn clean install
```

## Run application
To run the application use:

```
java -jar connected-cities-1.0.0-jar-with-dependencies.jar Cleveland "New York" connected.txt
```
