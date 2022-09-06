/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 2:05 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: Organization
 * @Full_Class_Name: com.sg.superhero.dto.Organization
 * @File_Name: Organization.java
 */

package com.sg.superhero.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Organization
{
    //Primary Key
    private int id;

    //Member Variables
    private String name;
    private String description;
    private String address;
    private String email;
    private String phone;

    public Organization()
    {
    }

    public Organization( String name, String description, String address, String email, String phone )
    {
        this.name = name;
        this.description = description;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        Organization that = (Organization) o;
        return id == that.id && name.equals( that.name ) && Objects.equals( description,
            that.description ) && address.equals( that.address ) && email.equals( that.email ) && phone.equals(
            that.phone );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, description, address, email, phone );
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
    
}//End of Organization
