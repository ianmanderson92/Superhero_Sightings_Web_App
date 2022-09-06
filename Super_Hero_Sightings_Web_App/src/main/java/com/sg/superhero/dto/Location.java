/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/5/22, 11:30 PM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: Location
 * @Full_Class_Name: com.sg.superhero.dto.Location
 * @File_Name: Location.java
 */

package com.sg.superhero.dto;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Location
{

    //Primary Key
    private int id;

    //Member Variables
    private String name;
    private String description;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Location()
    {
    }

    public Location( String name, String description, String address, BigDecimal latitude, BigDecimal longitude )
    {
        this.name = name;
        this.description = description;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public BigDecimal getLatitude()
    {
        return latitude;
    }

    public void setLatitude( BigDecimal latitude )
    {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude()
    {
        return longitude;
    }

    public void setLongitude( BigDecimal longitude )
    {
        this.longitude = longitude;
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
        Location location = (Location) o;
        return id == location.id && name.equals( location.name ) && Objects.equals( description,
            location.description ) && address.equals( location.address ) && latitude.equals(
            location.latitude ) && longitude.equals( location.longitude );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, description, address, latitude, longitude );
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

}//End of Location
