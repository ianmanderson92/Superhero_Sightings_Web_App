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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
@Profile( "database" )
public class SightingDaoDBImpl implements SightingDao
{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SightingDaoDBImpl( JdbcTemplate jdbcTemplate )
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sighting addSighting( Sighting newSighting )
    {
        final String sql = "INSERT INTO sighting( heroId, locationId, date )" +
            " VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( ( Connection connection ) ->
        {
            PreparedStatement prepStatement = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS );

            prepStatement.setInt( 1, newSighting.getHeroId() );
            prepStatement.setInt( 2, newSighting.getLocationId() );
            prepStatement.setDate( 3, Date.valueOf( newSighting.getDate() ) );
            return prepStatement;
        }, keyHolder );

        newSighting.setId( keyHolder.getKey().intValue() );
        return newSighting;
    }

    @Override
    public Sighting getSightingById( int id )
    {
        final String sql = "SELECT id, heroId, locationId, date " +
            "FROM sighting WHERE id = ?;";

        return jdbcTemplate.queryForObject( sql, new SightingDaoDBImpl.SightingMapper(), id );
    }

    @Override
    public Sighting updateSighting( int id, Sighting updatedSighting )
    {
        final String sql = "UPDATE sighting SET " +
            "heroId = ?, " +
            "locationId = ?, " +
            "date = ? " +
            "WHERE id = ?;";

        jdbcTemplate.update( sql,
            updatedSighting.getHeroId(),
            updatedSighting.getLocationId(),
            updatedSighting.getDate(),
            id );

        //TODO: add check for success
        updatedSighting.setId( id );
        return updatedSighting;
    }

    @Override
    public boolean deleteSightingById( int id )
    {
        final String sql = "DELETE FROM sighting WHERE id = ?;";
        return jdbcTemplate.update( sql, id ) > 0;
    }

    @Override
    public List<Sighting> getAllSightings()
    {
        final String sql = "SELECT id, heroId, locationId, date FROM sighting;";
        return jdbcTemplate.query( sql, new SightingDaoDBImpl.SightingMapper() );
    }

    @Override
    public List<Sighting> getAllSightingsByDate( LocalDate date )
    {
        final String sql = "SELECT id, heroId, locationId, date FROM sighting WHERE date = ?;";
        return jdbcTemplate.query( sql, new SightingDaoDBImpl.SightingMapper(), Date.valueOf( date ) );
    }

    @Override
    public List<Integer> getAllSightingLocationsBySuperheroId( int superheroId )
    {
        final String sql = "SELECT locationId FROM sighting WHERE heroId = ?;";
        return jdbcTemplate.queryForList( sql, Integer.class, superheroId );
    }

    @Override
    public List<Sighting> getAllSuperheroSightingsByLocationId( int locationId )
    {
        final String sql = "SELECT id, heroId, locationId, date FROM sighting WHERE locationId = ?;";
        return jdbcTemplate.query( sql, new SightingDaoDBImpl.SightingMapper(), locationId );
    }

    @Override
    public List<Sighting> getTenMostRecentSightings()
    {
        final String sql = "SELECT id, heroId, locationId, date FROM sighting ORDER BY date DESC LIMIT 10;";
        return jdbcTemplate.query( sql, new SightingDaoDBImpl.SightingMapper() );
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
            sightingObj.setDate( resultSet.getDate( "date" ).toLocalDate() );
            return sightingObj;
        }
    }//End of SightingMapper

}//End of SightingDaoDBImpl
