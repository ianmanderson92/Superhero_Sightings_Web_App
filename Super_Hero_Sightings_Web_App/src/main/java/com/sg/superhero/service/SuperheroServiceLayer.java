
/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 1:21 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: SuperheroServiceLayer
 * @Full_Class_Name: com.sg.superhero.service.SuperheroServiceLayer
 * @File_Name: SuperheroServiceLayer.java
 */

package com.sg.superhero.service;

import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Superhero;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface SuperheroServiceLayer
{
    //Superhero table Operations
    public Superhero addSuperhero( Superhero newSuperhero );
    public Superhero getSuperheroById( int id );
    public Superhero updateSuperhero( int id, Superhero updatedSuperhero );
    public boolean deleteSuperheroById( int id );
    public List<Superhero> getAllSuperheros();
    public List<Organization> getAllOrganizationsBySuperheroId( int superheroId );

    //Organization table operations
    public Organization addOrganization( Organization newOrganization );
    public Organization getOrganizationById( int id );
    public Organization updateOrganizationById( int id );
    public Organization deleteOrganizationById( int id );
    public List<Organization> getAllOrganizations();
    public Boolean addSuperheroToOrganization( int superheroId, int organizationId );
    public Boolean removeSuperheroFromOrganization( int superheroId, int organizationId );
    public List<Superhero> getAllMembersByOrganizationId( int id );

    //Location table Operations
    public Location addLocation( String name, String description, String address, BigDecimal latitude, BigDecimal longitude );
    public Location getLocationById( int id );
    public Location updateLocationById( int id );
    public Location deleteLocationById( int id );
    public List<Location> getAllLocations();

    //Sighting table operations
    public Sighting addSighting( int superheroId, int locationId, Date date );
    public Sighting getSightingById( int id );
    public Sighting updateSightingById( int id );
    public Sighting deleteSightingById( int id );
    public List<Sighting> getAllSightings();
    public List<Sighting> getAllSightingsByDate( Date date );
    public List<Location> getAllSightingLocationsBySuperheroId( int superheroId );
    public List<Superhero> getAllSuperheroSightingsByLocationId( int locationId );


}//End of SuperHeroServiceLayer
