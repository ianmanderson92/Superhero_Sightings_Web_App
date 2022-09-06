/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 1:38 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: Superhero
 * @Full_Class_Name: com.sg.superhero.dto.Superhero
 * @File_Name: Superhero.java
 */

package com.sg.superhero.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Superhero
{

    //Primary Key
    private  int id;

    private String name;
    private String description;
    private String superpower;

    //Optional Data members
    private List<Location> sightingLocations;
    private List<Organization> memberOrganizations;

    public Superhero()
    {
    }

    public Superhero( String name, String description, String superpower )
    {
        this.name = name;
        this.description = description;
        this.superpower = superpower;
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

    public String getSuperpower()
    {
        return superpower;
    }

    public void setSuperpower( String superpower )
    {
        this.superpower = superpower;
    }

    public List<Location> getSightingLocations()
    {
        return sightingLocations;
    }

    public void setSightingLocations( List<Location> sightingLocations )
    {
        this.sightingLocations = sightingLocations;
    }

    public List<Organization> getMemberOrganizations()
    {
        return memberOrganizations;
    }

    public void setMemberOrganizations( List<Organization> memberOrganizations )
    {
        this.memberOrganizations = memberOrganizations;
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
        Superhero superhero = (Superhero) o;
        return id == superhero.id && name.equals( superhero.name ) && Objects.equals( description,
            superhero.description ) && Objects.equals( superpower, superhero.superpower );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, description, superpower );
    }

}//End of Superhero
