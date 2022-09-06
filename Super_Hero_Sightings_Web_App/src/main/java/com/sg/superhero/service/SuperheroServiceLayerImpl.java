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

import java.util.Date;
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
        //TODO: add validation

        Superhero superheroToAdd = new Superhero( newSuperhero.getName(), newSuperhero.getDescription(),
            newSuperhero.getSuperpower() );

        superheroDao.addSuperhero( superheroToAdd );
        return superheroToAdd;
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
        //TODO: validate updatedSuperhero
        return superheroDao.updateSuperhero( id, updatedSuperhero );
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
        //TODO: add validation
        return organizationDao.addOrganization( newOrganization );
    }

    @Override
    public Organization getOrganizationById( int id )
    {
        return organizationDao.getOrganizationById( id );
    }

    @Override
    public Organization updateOrganization( int id, Organization updatedOrganization )
    {
        //TODO: validate updatedOrganization
        return organizationDao.updateOrganization( id, updatedOrganization );
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
        return locationDao.addLocation( newLocation );
    }

    @Override
    public Location getLocationById( int id )
    {
        return locationDao.getLocationById( id );
    }

    @Override
    public Location updateLocation( int id, Location updatedLocation )
    {
        return locationDao.updateLocation( id, updatedLocation );
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
        return sightingDao.addSighting( newSighting );
    }

    @Override
    public Sighting getSightingById( int id )
    {
        return sightingDao.getSightingById( id );
    }

    @Override
    public Sighting updateSighting( int id, Sighting updatedSighting )
    {
        return sightingDao.updateSighting( id, updatedSighting );
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
    public List<Sighting> getAllSightingsByDate( Date date )
    {
        return null;
    }

    @Override
    public List<Location> getAllSightingLocationsBySuperheroId( int superheroId )
    {
        return null;
    }

    @Override
    public List<Superhero> getAllSuperheroSightingsByLocationId( int locationId )
    {
        return null;
    }


}//End if SuperheroServiceLayerImpl
