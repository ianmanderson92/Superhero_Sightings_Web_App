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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

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
