/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/5/22, 11:30 PM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: Sighting
 * @Full_Class_Name: com.sg.superhero.dto.Sighting
 * @File_Name: Sighting.java
 */

package com.sg.superhero.dto;


import com.sg.superhero.dao.InputValidationException;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;

//TODO:add annotations?
/*@Entity
@Table(name = "sighting" )*/
public class Sighting
{
    //Primary Key
    private int id;

    //Foreign Keys
    //TODO: annotation
    //@Column ( name = "heroId" )
    private int heroId; //This name is not "superhero" so that is matches foreign key declaration in DB.
    private int locationId;


    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate date;
    //private LocalDateTime date;

    //Optional Member Variables
    private String superheroName;
    private String locationName;

    public Sighting()
    {
    }

    public Sighting( int heroId, int locationId, LocalDate date )
    {
        this.heroId = heroId;
        this.locationId = locationId;
        this.date = date;

        try
        {
            validateSightingMembers();
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

    public int getHeroId()
    {
        return heroId;
    }

    public void setHeroId( int heroId )
    {
        this.heroId = heroId;
    }

    public int getLocationId()
    {
        return locationId;
    }

    public void setLocationId( int locationId )
    {
        this.locationId = locationId;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate( LocalDate date )
    {
        this.date = date;
    }

    public String getSuperheroName()
    {
        return superheroName;
    }

    public void setSuperheroName( String superheroName )
    {
        this.superheroName = superheroName;
    }

    public String getLocationName()
    {
        return locationName;
    }

    public void setLocationName( String locationName )
    {
        this.locationName = locationName;
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
        Sighting sighting = (Sighting) o;
        return id == sighting.id && heroId == sighting.heroId && locationId == sighting.locationId && date.equals(
            sighting.date );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, heroId, locationId, date );
    }


    //Validation function
    private void validateSightingMembers() throws Exception
    {
        List<String> errorMessages = new ArrayList<>();

        //HeroId
        ensureNotNull( this.heroId, "SuperheroId required", errorMessages );

        ensureNotNull( this.locationId, "LocationId is required.", errorMessages );

        //Date
        if( ensureNotNull( this.date, "Date is required", errorMessages ) )
        {
            LocalDate START_OF_DATE_RANGE = LocalDate.of( 1900, 1, 1 );
            LocalDate END_OF_DATE_RANGE = LocalDate.now();
            END_OF_DATE_RANGE = END_OF_DATE_RANGE.plusDays( 1 ); //Set end of range to Day after Today.
            if ( this.date.isBefore( START_OF_DATE_RANGE ) || this.date.isAfter( END_OF_DATE_RANGE ) )
            {
                errorMessages.add( "Date is out of acceptable Range [ " + START_OF_DATE_RANGE + ", "
                    + END_OF_DATE_RANGE + " ]"  );
            }
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
    
}//End of Sighting
