
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

import java.time.LocalDate;
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
    public Organization updateOrganization( int id, Organization updatedOrganization );
    public boolean deleteOrganizationById( int id );
    public List<Organization> getAllOrganizations();
    public Boolean addSuperheroToOrganization( int superheroId, int organizationId );
    public Boolean removeSuperheroFromOrganization( int superheroId, int organizationId );
    public List<Integer> getAllMembersByOrganizationId( int id );

    //Location table Operations
    public Location addLocation( Location newLocation );
    public Location getLocationById( int id );
    public Location updateLocation( int id, Location updatedLocation );
    public boolean deleteLocationById( int id );
    public List<Location> getAllLocations();

    //Sighting table operations
    public Sighting addSighting( Sighting newSighting );
    public Sighting getSightingById( int id );
    public Sighting updateSighting( int id, Sighting updatedSighting );
    public boolean deleteSightingById( int id );
    public List<Sighting> getAllSightings();
    public List<Sighting> getAllSightingsByDate( LocalDate date );
    public List<Integer> getAllSightingLocationsBySuperheroId( int superheroId );
    public List<Sighting> getAllSuperheroSightingsByLocationId( int locationId );


}//End of SuperHeroServiceLayer
