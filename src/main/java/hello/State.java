package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class State      // Mudar isto tudo para private
{
    public String icao24;
    public String callsign;
    public String origin_country;
    public int time_position;
    public int last_contact;
    public double longitude;
    public double latitude;
    public double baro_altitude;
    public boolean on_ground;
    public double velocity;
    public double true_track;
    public double vertical_rate;
    public int[] sensors;
    public double geo_altitude;
    public String squawk;
    public boolean spi;
    public int position_source;
    @Override
    public String toString() {
        return "State [baro_altitude=" + baro_altitude + ", callsign=" + callsign + ", geo_altitude=" + geo_altitude
                + ", icao24=" + icao24 + ", last_contact=" + last_contact + ", latitude=" + latitude + ", longitude="
                + longitude + ", on_ground=" + on_ground + ", origin_country=" + origin_country + ", position_source="
                + position_source + ", sensors=" + Arrays.toString(sensors) + ", spi=" + spi + ", squawk=" + squawk
                + ", time_position=" + time_position + ", true_track=" + true_track + ", velocity=" + velocity
                + ", vertical_rate=" + vertical_rate + "]";
    }

    

}
