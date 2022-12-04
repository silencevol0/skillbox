import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.sql.Timestamp;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Flight> planesLeavingInTheNextTwoHours = findPlanesLeavingInTheNextTwoHours(airport);

    }
    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List<Flight> correctDeparture = new ArrayList<>();
        List<Terminal> terminals = airport.getTerminals();
        Date twoHourForward = getCalendarTwoHoursAgo();
        terminals.forEach(terminal -> {
            List<Flight> flights = terminal.getFlights();
            flights.forEach(flight -> {
                if (flight.getDate().before(twoHourForward) && flight.getDate().after(new Date()) && flight.getType() == Flight.Type.DEPARTURE) {
                    correctDeparture.add(flight);
                }
            });
        });
        return correctDeparture;
    }

    public static Date getCalendarTwoHoursAgo() {
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.HOUR, 2);
        return cal.getTime();
    }

}