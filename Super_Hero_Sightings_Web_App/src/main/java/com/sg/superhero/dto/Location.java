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

import com.sg.superhero.dao.InputValidationException;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    //Database Validation Constants
    private final int NAME_MAX_LENGTH = 45;
    private final int DESCRIPTION_MAX_LENGTH = 450;
    private final int ADDRESS_MAX_LENGTH = 450;

    //Optional Member Variables
    private List<Superhero> superherosSightedAtLocation;

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

        try
        {
            validateLocationMembers();
        } catch ( Exception e )
        {
            throw new RuntimeException( e );
        }
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


    //Helper methods for validation function
    private void validateLocationMembers() throws Exception
    {
        List<String> errorMessages = new ArrayList<>();

        //Name
        if ( !hasContent( this.name ) )
        {
            errorMessages.add( "Name is required." );
        }
        if ( this.name.length() > NAME_MAX_LENGTH )
        {
            errorMessages.add( "Input Name is too long. Max length: " + NAME_MAX_LENGTH );
        }

        //Description
        if ( this.description.length() > DESCRIPTION_MAX_LENGTH )
        {
            errorMessages.add( "Input Description is too long. Max length: " + DESCRIPTION_MAX_LENGTH );
        }

        //Address
        if ( !hasContent( this.address ) )
        {
            errorMessages.add( "Address is required." );
        }
        if ( this.address.length() > ADDRESS_MAX_LENGTH )
        {
            errorMessages.add( "Input Address is too long. Max length: " + ADDRESS_MAX_LENGTH );
        }

        //Lat. and Long.
        ensureNotNull( this.latitude, "Latitude coordinate is required.", errorMessages );
        if ( this.latitude.compareTo( BigDecimal.valueOf( 90 ) ) <= 0
            && this.latitude.compareTo( BigDecimal.valueOf( -90 ) ) >= 0 )
        {
            errorMessages.add( "Latitude coordinate out of valid range [-90,90]." );
        }

        ensureNotNull( this.longitude, "Longitude coordinate is required.", errorMessages );
        if ( this.longitude.compareTo( BigDecimal.valueOf( 180 ) ) <= 0
            && this.longitude.compareTo( BigDecimal.valueOf( -180 ) ) >= 0 )
        {
            errorMessages.add( "Longitude coordinate out of valid range [-180,180]." );
        }

        if ( !errorMessages.isEmpty() )
        {
            Exception validationEX = new Exception();
            for ( String error : errorMessages )
            {
                validationEX.addSuppressed( new InputValidationException( error ) );
            }
            throw validationEX;
        }
    }

    //Helper methods for validation function

    /**
     Method code adapted from:
     Author: Hirondelle Systems
     http://www.javapractices.com/topic/TopicAction.do?Id=209#:~:text=In%20the%20Java%20programming%20language,the%20user%20about%20the%20issues

     * @param field object to be checked
     * @param errorMsg error message to throw
     * @param errors error message array to append
     * @return true is null
     */
    private boolean ensureNotNull( Object field, String errorMsg, List<String> errors )
    {
        boolean result = true;
        if (field == null)
        {
            errors.add( errorMsg );
            result = false;
        }
        return result;
    }

    /**
     Method code adapted from:
     Author: Hirondelle Systems
     http://www.javapractices.com/topic/TopicAction.do?Id=209#:~:text=In%20the%20Java%20programming%20language,the%20user%20about%20the%20issues

     * @param stringToCheck String to validate
     * @return true if not null or empty
     */
    private boolean hasContent( String stringToCheck )
    {
        return ( stringToCheck != null && stringToCheck.trim().length() > 0 );
    }


}//End of Location
