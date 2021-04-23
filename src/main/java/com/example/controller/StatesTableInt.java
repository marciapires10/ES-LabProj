import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
public class StatesTableInt extends JdbcDaoSupport{

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

    @Override
    public List<Coordinates> getAllCoordinates() {
        String sql = "SELECT * FROM State";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<State> result = new ArrayList<State>();
		for (Map<String, Object> row : rows) {
			State state = new State();
            state.
			result.add(coord);
		}

		return result;
    }

    @Override
    public void insertCoordinates(State state) {
        String sql = "INSERT INTO coordinates " + "(latitude, longitude) VALUES (?, ?)";
		getJdbcTemplate().update(sql, new Object[] { coordinates.getLatitude(), coordinates.getLongitude() });
    }

    private void createState(Map<String, Object> row)
    {
        try{state.icao24 = object.get(0).toString();} catch(Exception e){};
        try{state.callsign = object.get(1).toString();} catch(Exception e){};
        try{state.origin_country = object.get(2).toString();}catch(Exception e){};
        try{state.time_position = (int)object.get(3);}catch(ClassCastException e){state.time_position =  (int)(double)object.get(3);}catch(Exception e){};
        try{state.last_contact = (int)object.get(4);}catch(ClassCastException e){state.last_contact = (int)(double)object.get(4);}catch(Exception e){};
        try{state.longitude = (double)object.get(5);}catch(ClassCastException e){state.longitude = (double)(int)object.get(5);}catch(Exception e){};
        try{state.latitude = (double)object.get(6);}catch(ClassCastException e){state.latitude = (double)(int)object.get(6);}catch(Exception e){};;
        try{state.baro_altitude = (double)object.get(7);}catch(ClassCastException e){state.baro_altitude = (double)(int)object.get(7);}catch(Exception e){};
        try{state.on_ground = (boolean)object.get(8);}catch(Exception e){};
        try{state.velocity = (double)object.get(9);}catch(ClassCastException e){state.velocity = (double)(int)object.get(9);}catch(Exception e){};;
        try{state.true_track = (double)object.get(10);}catch(ClassCastException e){state.true_track = (double)(int)object.get(10);}catch(Exception e){};;
        try{state.vertical_rate = (double)object.get(11);}catch(ClassCastException e){state.vertical_rate = (double)(int)object.get(11);}catch(Exception e){};
        try{state.sensors = (int[])object.get(12);}catch(Exception e){}
        try{state.geo_altitude = (double)object.get(13);}catch(ClassCastException e){state.geo_altitude = (double)(int)object.get(13);}catch(Exception e){};
        try{state.squawk = object.get(14).toString().toString();}catch(Exception e){};
        try{state.spi = (boolean)object.get(15);}catch(Exception e){};
        try{state.position_source = (int)object.get(16);}catch(ClassCastException e){state.position_source = (int)(double)object.get(16);}catch(Exception e){};
    }
}