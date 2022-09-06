/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 3:02 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: LocationDaoDBImpl
 * @Full_Class_Name: com.sg.superhero.dao.LocationDaoDBImpl
 * @File_Name: LocationDaoDBImpl.java
 */

package com.sg.superhero.dao;

import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
@Profile( "database" )
public class LocationDaoDBImpl implements LocationDao
{

    private final JdbcTemplate jdbcTemplate;

    public LocationDaoDBImpl( JdbcTemplate jdbcTemplate )
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Location addLocation( Location newLocation )
    {
        final String sql = "INSERT INTO location( name, description, address, latitude, longitude )" +
            " VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( ( Connection connection ) ->
        {
            PreparedStatement prepStatement = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS );

            prepStatement.setString( 1, newLocation.getName() );
            prepStatement.setString( 2, newLocation.getDescription() );
            prepStatement.setString( 3, newLocation.getAddress() );
            prepStatement.setBigDecimal( 4, newLocation.getLatitude() );
            prepStatement.setBigDecimal( 5, newLocation.getLongitude() );
            return prepStatement;
        }, keyHolder );

        newLocation.setId( keyHolder.getKey().intValue() );
        return newLocation;
    }

    @Override
    public Location getLocationById( int id )
    {
        final String sql = "SELECT id, name, description, address, latitude, longitude " +
            "FROM location WHERE id = ?;";

        return jdbcTemplate.queryForObject( sql, new LocationDaoDBImpl.LocationMapper(), id );
    }

    @Override
    public Location updateLocation( int id, Location updatedLocation )
    {
        final String sql = "UPDATE location SET " +
            "name = ?, " +
            "description = ?, " +
            "address = ?, " +
            "latitude = ?, " +
            "longitude = ? " +
            "WHERE id = ?;";

        jdbcTemplate.update( sql,
            updatedLocation.getName(),
            updatedLocation.getDescription(),
            updatedLocation.getAddress(),
            updatedLocation.getLatitude(),
            updatedLocation.getLongitude(),
            id );

        //TODO: add check for success
        updatedLocation.setId( id );
        return updatedLocation;
    }

    @Override
    public boolean deleteLocationById( int id )
    {
        final String sql = "DELETE FROM location WHERE id = ?;";
        return jdbcTemplate.update( sql, id ) > 0;
    }

    @Override
    public List<Location> getAllLocations()
    {
        final String sql = "SELECT id, name, description, address, latitude, longitude FROM location;";
        return jdbcTemplate.query( sql, new LocationDaoDBImpl.LocationMapper() );
    }

    private static final class LocationMapper implements RowMapper<Location>
    {
        @Override
        public Location mapRow( ResultSet resultSet, int index ) throws SQLException
        {
            Location locationObj = new Location();
            locationObj.setId( resultSet.getInt( "id" ) );
            locationObj.setName( resultSet.getString( "name" ) );
            locationObj.setDescription( resultSet.getString( "description" ) );
            locationObj.setAddress( resultSet.getString( "address" ) );
            locationObj.setLatitude( resultSet.getBigDecimal( "latitude" ) );
            locationObj.setLongitude( resultSet.getBigDecimal( "longitude" ) );
            return locationObj;
        }
    }//End of LocationMapper

}//End of LocationDaoDBImpl
