package hello;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Aircraft {
    private String icao24;
    private int firstSeen;
    private String estDepartureAirport;
    private int lastSeen;
    private String estArrivalAirport;
    private String callsign;
    private int estDepartureAirportHorizDistance;
    private int estDepartureAirportVertDistance;
    private int estArrivalAirportHorizDistance;
    private int estArrivalAirportVertDistance;
    private int departureAirportCandidatesCount;
    private int arrivalAirportCandidatesCount;

    public String getIcao24() {
        return icao24;
    }
    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }
    public int getFirstSeen() {
        return firstSeen;
    }
    public void setFirstSeen(int firstSeen) {
        this.firstSeen = firstSeen;
    }
    public String getEstDepartureAirport() {
        return estDepartureAirport;
    }
    public void setEstDepartureAirport(String estDepartureAirport) {
        this.estDepartureAirport = estDepartureAirport;
    }
    public int getLastSeen() {
        return lastSeen;
    }
    public void setLastSeen(int lastSeen) {
        this.lastSeen = lastSeen;
    }
    public String getEstArrivalAirport() {
        return estArrivalAirport;
    }
    public void setEstArrivalAirport(String estArrivalAirport) {
        this.estArrivalAirport = estArrivalAirport;
    }
    public String getCallsign() {
        return callsign;
    }
    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }
    public int getEstDepartureAirportHorizDistance() {
        return estDepartureAirportHorizDistance;
    }
    public void setEstDepartureAirportHorizDistance(int estDepartureAirportHorizDistance) {
        this.estDepartureAirportHorizDistance = estDepartureAirportHorizDistance;
    }
    public int getEstDepartureAirportVertDistance() {
        return estDepartureAirportVertDistance;
    }
    public void setEstDepartureAirportVertDistance(int estDepartureAirportVertDistance) {
        this.estDepartureAirportVertDistance = estDepartureAirportVertDistance;
    }
    public int getEstArrivalAirportHorizDistance() {
        return estArrivalAirportHorizDistance;
    }
    public void setEstArrivalAirportHorizDistance(int estArrivalAirportHorizDistance) {
        this.estArrivalAirportHorizDistance = estArrivalAirportHorizDistance;
    }
    public int getEstArrivalAirportVertDistance() {
        return estArrivalAirportVertDistance;
    }
    public void setEstArrivalAirportVertDistance(int estArrivalAirportVertDistance) {
        this.estArrivalAirportVertDistance = estArrivalAirportVertDistance;
    }
    public int getDepartureAirportCandidatesCount() {
        return departureAirportCandidatesCount;
    }
    public void setDepartureAirportCandidatesCount(int departureAirportCandidatesCount) {
        this.departureAirportCandidatesCount = departureAirportCandidatesCount;
    }
    public int getArrivalAirportCandidatesCount() {
        return arrivalAirportCandidatesCount;
    }
    public void setArrivalAirportCandidatesCount(int arrivalAirportCandidatesCount) {
        this.arrivalAirportCandidatesCount = arrivalAirportCandidatesCount;
    }

    @JsonIgnore
    public String getAircraft()
    {
        return "I am an aircraft";
    }
    @Override
    public String toString() {
        return "Aircraft [arrivalAirportCandidatesCount=" + arrivalAirportCandidatesCount + ", callsign=" + callsign
                + ", departureAirportCandidatesCount=" + departureAirportCandidatesCount + ", estArrivalAirport="
                + estArrivalAirport + ", estArrivalAirportHorizDistance=" + estArrivalAirportHorizDistance
                + ", estArrivalAirportVertDistance=" + estArrivalAirportVertDistance + ", estDepartureAirport="
                + estDepartureAirport + ", estDepartureAirportHorizDistance=" + estDepartureAirportHorizDistance
                + ", estDepartureAirportVertDistance=" + estDepartureAirportVertDistance + ", firstSeen=" + firstSeen
                + ", icao24=" + icao24 + ", lastSeen=" + lastSeen + "]";
    }

    
    
}
