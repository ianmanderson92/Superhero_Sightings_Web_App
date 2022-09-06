/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 2:46 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: SuperheroDaoDBImpl
 * @Full_Class_Name: com.sg.superhero.dao.SuperheroDaoDBImpl
 * @File_Name: SuperheroDaoDBImpl.java
 */

package com.sg.superhero.dao;

import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
@Profile( "database" )
public class SuperheroDaoDBImpl implements SuperheroDao
{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SuperheroDaoDBImpl( JdbcTemplate jdbcTemplate )
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Superhero addSuperhero( Superhero superhero )
    {
        final String sql = "INSERT INTO superhero( name, description, superpower )" +
            " VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( ( Connection connection ) ->
        {
            PreparedStatement prepStatement = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS );

            prepStatement.setString( 1, superhero.getName() );
            prepStatement.setString( 2, superhero.getDescription() );
            prepStatement.setString( 3, superhero.getSuperpower() );
            return prepStatement;
        }, keyHolder );

        superhero.setId( keyHolder.getKey().intValue() );
        return superhero;
    }

    @Override
    public Superhero getSuperheroById( int id )
    {
        return null;
    }

    @Override
    public Superhero updateSuperhero( Superhero superhero )
    {
        return null;
    }

    @Override
    public Superhero deleteSuperheroById( int id )
    {
        return null;
    }

    @Override
    public List<Superhero> getAllSuperheros()
    {
        return null;
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperheroId( int superheroId )
    {
        return null;
    }

    private static final class SuperheroMapper implements RowMapper<Superhero>
    {
        @Override
        public Superhero mapRow( ResultSet resultSet, int index ) throws SQLException
        {
            Superhero superheroObj = new Superhero();
            superheroObj.setId( resultSet.getInt( "id" ) );
            superheroObj.setName( resultSet.getString( "name" ) );
            superheroObj.setDescription( resultSet.getString( "description" ) );
            superheroObj.setSuperpower( resultSet.getString( "superpower" ) );
            return superheroObj;
        }
    }//End of SuperheroMapper

}//End of SuperheroDaoDBImpl
