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
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Profile( "database" )
public class LocationDaoDBImpl implements LocationDao
{

    private final JdbcTemplate jdbcTemplate;

    public LocationDaoDBImpl( JdbcTemplate jdbcTemplate )
    {
        this.jdbcTemplate = jdbcTemplate;
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
