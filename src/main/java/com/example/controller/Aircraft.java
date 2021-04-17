package com.example.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@JsonIgnoreProperties
@Entity
@Table(name = "Aircraft")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aircraftID;
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

    // JPA, not used directly so it's protected
    protected Aircraft() {}

    // public Aircraft(String icao24, int firstSeen, String estDepartureAirport, int lastSeen, String estArrivalAirport, 
    //                     String callsign, int estDepartureAirportHorizDistance, int estDepartureAirportVertDistance,
    //                     int estArrivalAirportHorizDistance, int estArrivalAirportVertDistance, int departureAirportCandidatesCount,
    //                     int arrivalAirportCandidatesCount){
    //                         this.icao24 = icao24;
    //                         this.firstSeen = firstSeen;
    //                         this.estDepartureAirport = estDepartureAirport;
    //                         this.lastSeen = lastSeen;
    //                         this.estArrivalAirport = estArrivalAirport;
    //                         this.callsign = callsign;
    //                         this.estDepartureAirportHorizDistance = estDepartureAirportHorizDistance;
    //                         this.estDepartureAirportVertDistance = estArrivalAirportVertDistance;
    //                         this.estArrivalAirportHorizDistance = estArrivalAirportHorizDistance;
    //                         this.estArrivalAirportVertDistance = estArrivalAirportVertDistance;
    //                         this.departureAirportCandidatesCount = departureAirportCandidatesCount;
    //                         this.arrivalAirportCandidatesCount = arrivalAirportCandidatesCount;
    //                     }

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

    public Long getAircraftID() {
        return aircraftID;
    }

    public void setAircraftID(Long aircraftID) {
        this.aircraftID = aircraftID;
    }

    
    
}
