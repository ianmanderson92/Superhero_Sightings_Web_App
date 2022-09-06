/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 2:58 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: SightingDaoDBImpl
 * @Full_Class_Name: com.sg.superhero.dao.SightingDaoDBImpl
 * @File_Name: SightingDaoDBImpl.java
 */

package com.sg.superhero.dao;

import com.sg.superhero.dto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Profile( "database" )
public class SightingDaoDBImpl implements SightingDao
{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SightingDaoDBImpl( JdbcTemplate jdbcTemplate )
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final class SightingMapper implements RowMapper<Sighting>
    {
        @Override
        public Sighting mapRow( ResultSet resultSet, int index ) throws SQLException
        {
            Sighting sightingObj = new Sighting();
            sightingObj.setId( resultSet.getInt( "id" ) );
            sightingObj.setHeroId( resultSet.getInt( "heroId" ) );
            sightingObj.setLocationId( resultSet.getInt( "locationId" ) );
            sightingObj.setDate( resultSet.getDate( "date" ) );
            return sightingObj;
        }
    }//End of SightingMapper

}//End of SightingDaoDBImpl
