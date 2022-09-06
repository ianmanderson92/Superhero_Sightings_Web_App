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

import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.dao.SuperheroDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        return null;
    }

    @Override
    public Superhero updateSuperheroById( int id )
    {
        return null;
    }

    @Override
    public Superhero deleteSuperheroById( int id )
    {
        return null;
    }

    @Override
    public List<Superhero> getAllSuperheros()
    {
        return null;
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperheroId( int superheroId )
    {
        return null;
    }

    @Override
    public Organization addOrganization( String name, String description, String address, String email, String phone )
    {
        return null;
    }

    @Override
    public Organization getOrganizationById( int id )
    {
        return null;
    }

    @Override
    public Organization updateOrganizationById( int id )
    {
        return null;
    }

    @Override
    public Organization deleteOrganizationById( int id )
    {
        return null;
    }

    @Override
    public List<Organization> getAllOrganizations()
    {
        return null;
    }

    @Override
    public Boolean addSuperheroToOrganization( int superheroId, int organizationId )
    {
        return null;
    }

    @Override
    public Boolean removeSuperheroFromOrganization( int superheroId, int organizationId )
    {
        return null;
    }

    @Override
    public List<Superhero> getAllMembersByOrganizationId( int id )
    {
        return null;
    }

    @Override
    public Location addLocation( String name, String description, String address, BigDecimal latitude,
                                 BigDecimal longitude )
    {
        return null;
    }

    @Override
    public Location getLocationById( int id )
    {
        return null;
    }

    @Override
    public Location updateLocationById( int id )
    {
        return null;
    }

    @Override
    public Location deleteLocationById( int id )
    {
        return null;
    }

    @Override
    public List<Location> getAllLocations()
    {
        return null;
    }

    @Override
    public Sighting addSighting( int superheroId, int locationId, Date date )
    {
        return null;
    }

    @Override
    public Sighting getSightingById( int id )
    {
        return null;
    }

    @Override
    public Sighting updateSightingById( int id )
    {
        return null;
    }

    @Override
    public Sighting deleteSightingById( int id )
    {
        return null;
    }

    @Override
    public List<Sighting> getAllSightings()
    {
        return null;
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
}
