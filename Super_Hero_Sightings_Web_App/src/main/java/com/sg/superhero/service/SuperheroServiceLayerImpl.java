/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/5/22, 11:41 PM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: SuperheroServiceLayerImpl
 * @Full_Class_Name: com.sg.superhero.service.SuperheroServiceLayerImpl
 * @File_Name: SuperheroServiceLayerImpl.java
 */

package com.sg.superhero.service;

import com.sg.superhero.dao.*;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SuperheroServiceLayerImpl implements SuperheroServiceLayer
{

    SuperheroDao superheroDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;

    @Autowired
    public SuperheroServiceLayerImpl( SuperheroDao superheroDao, LocationDao locationDao,
                                      OrganizationDao organizationDao, SightingDao sightingDao )
    {
        this.superheroDao = superheroDao;
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
    }


    //Superhero Methods
    //--------------------------------------------------------------------------------------------------------------------
    
    @Override
    public Superhero addSuperhero( Superhero newSuperhero )
    {
        //Validates new object
        Superhero superheroToAdd = new Superhero( newSuperhero.getName(), newSuperhero.getDescription(),
            newSuperhero.getSuperpower() );
        
        return superheroDao.addSuperhero( superheroToAdd );
    }

    @Override
    public Superhero getSuperheroById( int id )
    {
        //TODO: add validation for id
        return superheroDao.getSuperheroById( id );
    }

    @Override
    public Superhero updateSuperhero( int id, Superhero updatedSuperhero )
    {
        Superhero superheroToUpdate = new Superhero( updatedSuperhero.getName(), updatedSuperhero.getDescription(),
            updatedSuperhero.getSuperpower() );
        
        return superheroDao.updateSuperhero( id, superheroToUpdate );
    }

    @Override
    public boolean deleteSuperheroById( int id )
    {
        return superheroDao.deleteSuperheroById( id );
    }

    @Override
    public List<Superhero> getAllSuperheros()
    {
        return superheroDao.getAllSuperheros();
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperheroId( int superheroId )
    {
        return null;
    }


    //Organization Methods
    //--------------------------------------------------------------------------------------------------------------------
    
    @Override
    public Organization addOrganization( Organization newOrganization )
    {
        //Validates new object
        Organization orgToAdd = new Organization( newOrganization.getName(), newOrganization.getDescription(),
            newOrganization.getAddress(), newOrganization.getEmail(), newOrganization.getPhone() );

        return organizationDao.addOrganization( orgToAdd );
    }

    @Override
    public Organization getOrganizationById( int id )
    {
        return organizationDao.getOrganizationById( id );
    }

    @Override
    public Organization updateOrganization( int id, Organization updatedOrganization )
    {
        //Validates new object
        Organization orgToUpdate = new Organization( updatedOrganization.getName(), updatedOrganization.getDescription(),
            updatedOrganization.getAddress(), updatedOrganization.getEmail(), updatedOrganization.getPhone() );
        
        return organizationDao.updateOrganization( id, orgToUpdate );
    }

    @Override
    public boolean deleteOrganizationById( int id )
    {
        return organizationDao.deleteOrganizationById( id );
    }

    @Override
    public List<Organization> getAllOrganizations()
    {
        return organizationDao.getAllOrganizations();
    }

    @Override
    public Boolean addSuperheroToOrganization( int superheroId, int organizationId )
    {
        return organizationDao.addSuperheroToOrganization( superheroId, organizationId );
    }

    @Override
    public Boolean removeSuperheroFromOrganization( int superheroId, int organizationId )
    {
        return organizationDao.removeSuperheroFromOrganization( superheroId, organizationId );
    }

    @Override
    public List<Integer> getAllMembersByOrganizationId( int id )
    {
        return organizationDao.getAllMembersByOrganizationId( id );
    }


    //Location Methods
    //--------------------------------------------------------------------------------------------------------------------
    
    @Override
    public Location addLocation( Location newLocation )
    {
        //Validates new object
        Location locationToAdd = new Location( newLocation.getName(), newLocation.getDescription(),
            newLocation.getAddress(), newLocation.getLatitude(), newLocation.getLongitude() );
        
        return locationDao.addLocation( locationToAdd );
    }

    @Override
    public Location getLocationById( int id )
    {
        return locationDao.getLocationById( id );
    }

    @Override
    public Location updateLocation( int id, Location updatedLocation )
    {
        //Validates new object
        Location locationToAdd = new Location( updatedLocation.getName(), updatedLocation.getDescription(),
            updatedLocation.getAddress(), updatedLocation.getLatitude(), updatedLocation.getLongitude() );
        
        return locationDao.updateLocation( id, locationToAdd );
    }

    @Override
    public boolean deleteLocationById( int id )
    {
        return locationDao.deleteLocationById( id );
    }

    @Override
    public List<Location> getAllLocations()
    {
        return locationDao.getAllLocations();
    }

    
    //Sighting Methods
    //--------------------------------------------------------------------------------------------------------------------
    
    @Override
    public Sighting addSighting( Sighting newSighting )
    {
        //Validates new object
        Sighting sightingToAdd = new Sighting( newSighting.getHeroId(), newSighting.getLocationId(),
            newSighting.getDate() );

        return sightingDao.addSighting( sightingToAdd );
    }

    @Override
    public Sighting getSightingById( int id )
    {
        return sightingDao.getSightingById( id );
    }

    @Override
    public Sighting updateSighting( int id, Sighting updatedSighting )
    {
        //Validates new object
        Sighting sightingToUpdate = new Sighting( updatedSighting.getHeroId(), updatedSighting.getLocationId(),
            updatedSighting.getDate() );
        
        return sightingDao.updateSighting( id, sightingToUpdate );
    }

    @Override
    public boolean deleteSightingById( int id )
    {
        return sightingDao.deleteSightingById( id );
    }

    @Override
    public List<Sighting> getAllSightings()
    {
        return sightingDao.getAllSightings();
    }

    @Override
    public List<Sighting> getAllSightingsByDate( LocalDate date )
    {
        return sightingDao.getAllSightingsByDate( date );
    }

    @Override
    public List<Integer> getAllSightingLocationsBySuperheroId( int superheroId )
    {
        return sightingDao.getAllSightingLocationsBySuperheroId( superheroId );
    }

    @Override
    public List<Sighting> getAllSuperheroSightingsByLocationId( int locationId )
    {
        return sightingDao.getAllSuperheroSightingsByLocationId( locationId );
    }

}//End if SuperheroServiceLayerImpl
