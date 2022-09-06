/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 3:01 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: OrganizationDaoDBImpl
 * @Full_Class_Name: com.sg.superhero.dao.OrganizationDaoDBImpl
 * @File_Name: OrganizationDaoDBImpl.java
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
public class OrganizationDaoDBImpl implements OrganizationDao
{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrganizationDaoDBImpl( JdbcTemplate jdbcTemplate )
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Organization addOrganization( Organization newOrganization )
    {
        final String sql = "INSERT INTO organization( name, description, address, email, phone )" +
            " VALUES(?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( ( Connection connection ) ->
        {
            PreparedStatement prepStatement = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS );

            prepStatement.setString( 1, newOrganization.getName() );
            prepStatement.setString( 2, newOrganization.getDescription() );
            prepStatement.setString( 3, newOrganization.getAddress() );
            prepStatement.setString( 4, newOrganization.getEmail() );
            prepStatement.setString( 5, newOrganization.getPhone() );
            return prepStatement;
        }, keyHolder );

        newOrganization.setId( keyHolder.getKey().intValue() );
        return newOrganization;
    }

    @Override
    public Organization getOrganizationById( int id )
    {
        final String sql = "SELECT id, name, description, address, email, phone " +
            "FROM organization WHERE id = ?;";

        return jdbcTemplate.queryForObject( sql, new OrganizationDaoDBImpl.OrganizationMapper(), id );
    }

    @Override
    public Organization updateOrganization( int id, Organization updatedOrganization )
    {
        final String sql = "UPDATE organization SET " +
            "name = ?, " +
            "description = ?, " +
            "address = ?, " +
            "email = ?, " +
            "phone = ? " +
            "WHERE id = ?;";

        jdbcTemplate.update( sql,
            updatedOrganization.getName(),
            updatedOrganization.getDescription(),
            updatedOrganization.getAddress(),
            updatedOrganization.getEmail(),
            updatedOrganization.getPhone(),
            id );

        //TODO: add check for success
        updatedOrganization.setId( id );
        return updatedOrganization;
    }

    @Override
    public boolean deleteOrganizationById( int id )
    {
        final String sql = "DELETE FROM organization WHERE id = ?;";
        return jdbcTemplate.update( sql, id ) > 0;
    }

    @Override
    public List<Organization> getAllOrganizations()
    {
        final String sql = "SELECT id, name, description, address, email, phone FROM organization;";
        return jdbcTemplate.query( sql, new OrganizationDaoDBImpl.OrganizationMapper() );
    }

    @Override
    public Boolean addSuperheroToOrganization( int superheroId, int organizationId )
    {
        return null;
    }

    @Override
    public Boolean removeSuperheroFromOrganization( int superheroId, int organizationId )
    {
        return null;
    }

    @Override
    public List<Superhero> getAllMembersByOrganizationId( int id )
    {
        return null;
    }

    private static final class OrganizationMapper implements RowMapper<Organization>
    {
        @Override
        public Organization mapRow( ResultSet resultSet, int index ) throws SQLException
        {
            Organization organizationObj = new Organization();
            organizationObj.setId( resultSet.getInt( "id" ) );
            organizationObj.setName( resultSet.getString( "name" ) );
            organizationObj.setDescription( resultSet.getString( "description" ) );
            organizationObj.setAddress( resultSet.getString( "address" ) );
            organizationObj.setEmail( resultSet.getString( "email" ) );
            organizationObj.setPhone( resultSet.getString( "phone" ) );
            return organizationObj;
        }
    }//End of OrganizationMapper

}//End of OrganizationDaoDBImpl
