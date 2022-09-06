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


import java.sql.Date;
import java.util.Objects;

public class Sighting
{
    //Primary Key
    private int id;

    //Foreign Keys
    private int heroId; //This name is not "superhero" so that is matches foreign key declaration in DB.
    private int locationId;

    private Date date;

    public Sighting()
    {
    }

    public Sighting( int heroId, int locationId, Date date )
    {
        this.heroId = heroId;
        this.locationId = locationId;
        this.date = date;
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

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
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


    
}//End of Sighting
