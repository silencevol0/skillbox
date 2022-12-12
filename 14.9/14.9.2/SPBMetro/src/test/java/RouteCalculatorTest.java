import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {

    private static StationIndex stationIndex;
    List<Station> routeWithOutConnections;
    List<Station> routeToOneConnection;
    List<Station> routeToTwoConnection;
    private static final String DATA_FILE = "src/test/resources/map.json";



    @Override
    protected void setUp() throws Exception {
        RouteCalculator calculator = getRouteCalculator();
        routeWithOutConnections = calculator.getShortestRoute(stationIndex.getStation("Московская"), stationIndex.getStation("Петровщина"));
        routeToOneConnection = calculator.getShortestRoute(stationIndex.getStation("Московская"), stationIndex.getStation("Кунцевщина"));
        routeToTwoConnection = calculator.getShortestRoute(stationIndex.getStation("Московская"), stationIndex.getStation("Станция4"));
    }

    public void testCalculateDurationWithoutConnections() {
        double actual = RouteCalculator.calculateDuration(routeWithOutConnections);
        double correct = 25.0;
        assertEquals(actual,correct);
    }

    public void testCalculateDurationWithOneConnections() {
        double actual = RouteCalculator.calculateDuration(routeToOneConnection);
        double correct = 31.0;
        assertEquals(actual,correct);
    }

    public void testCalculateDurationWithTwoConnections() {
        double actual = RouteCalculator.calculateDuration(routeToTwoConnection);
        printRoute(routeToTwoConnection);
        double correct = 39.5;
        assertEquals(actual,correct);
    }

    private static RouteCalculator getRouteCalculator() {
        createStationIndex();
        return new RouteCalculator(stationIndex);
    }

    private static void createStationIndex() {
        stationIndex = new StationIndex();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                int lineNumber = ((Long) itemObject.get("line")).intValue();
                String stationName = (String) itemObject.get("station");

                Station station = stationIndex.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            stationIndex.addConnection(connectionStations);
        });
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject);
            Line line = stationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    private static void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(),
                    (String) lineJsonObject.get("name")
            );
            stationIndex.addLine(line);
        });
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    private static void printRoute(List<Station> route) {
        Station previousStation = null;
        for (Station station : route) {
            if (previousStation != null) {
                Line prevLine = previousStation.getLine();
                Line nextLine = station.getLine();
                if (!prevLine.equals(nextLine)) {
                    System.out.println("\tПереход на станцию " +
                            station.getName() + " (" + nextLine.getName() + " линия)");
                }
            }
            System.out.println("\t" + station.getName());
            previousStation = station;
        }
    }
}


