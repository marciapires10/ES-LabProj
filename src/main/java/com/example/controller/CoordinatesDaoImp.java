package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
public class CoordinatesDaoImp extends JdbcDaoSupport{

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

    @Override
    public List<Coordinates> getAllCoordinates() {
        String sql = "SELECT * FROM Coordinates";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Coordinates> result = new ArrayList<Coordinates>();
		for (Map<String, Object> row : rows) {
			Coordinates coord = new Coordinates((double)row.get("latitude"), (double)row.get("longitude"));
			result.add(coord);
		}

		return result;
    }

    @Override
    public void insertCoordinates(Coordinates coordinates) {
        String sql = "INSERT INTO Coordinates " + "(latitude, longitude) VALUES (?, ?)";
		getJdbcTemplate().update(sql, new Object[] { coordinates.getLatitude(), coordinates.getLongitude() });
    }
}