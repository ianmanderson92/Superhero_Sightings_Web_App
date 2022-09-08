/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 4:58 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: SuperheroController
 * @Full_Class_Name: com.sg.superhero.controller.SuperheroController
 * @File_Name: SuperheroController.java
 */

package com.sg.superhero.controller;

import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Superhero;
import com.sg.superhero.service.SuperheroServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/superhero")
public class SuperheroController
{
    @Autowired
    private final SuperheroServiceLayer service;

    public SuperheroController( SuperheroServiceLayer service )
    {
        this.service = service;
    }


    //Superhero Methods
    //--------------------------------------------------------------------------------------------
    @PostMapping( "/addSuperhero" )
    public Superhero addSuperhero( @RequestBody Superhero newSuperhero )
    {
        Superhero addedSuperHero = service.addSuperhero( newSuperhero );
        return addedSuperHero;
    }

    @GetMapping( "/getSuperhero/{superheroId}" )
    public ResponseEntity<Superhero> getSuperheroById ( @PathVariable int superheroId )
    {
        Superhero foundSuperhero = service.getSuperheroById( superheroId );
        if ( foundSuperhero == null )
        {
            return new ResponseEntity( null, HttpStatus.NOT_FOUND );
        }
        List<Integer> sightingLocationIds = service.getAllSightingLocationsBySuperheroId( foundSuperhero.getId() );
        List<Location> locationList = new ArrayList<>();
        for ( Integer locationId : sightingLocationIds )
        {
            locationList.add( service.getLocationById( locationId ) );
        }
        foundSuperhero.setSightingLocations( locationList );
        foundSuperhero.setMemberOrganizations( service.getAllOrganizationsBySuperheroId( foundSuperhero.getId() ) );

        return ResponseEntity.ok( foundSuperhero );
    }

    @PutMapping( "/updateSuperhero/{superheroId}" )
    public ResponseEntity<Superhero> updateSuperheroById( @PathVariable int superheroId
        , @RequestBody Superhero updatedSuperhero )
    {
        Superhero foundSuperhero = service.getSuperheroById( superheroId );
        if ( foundSuperhero == null )
        {
            return new ResponseEntity( null, HttpStatus.NOT_FOUND );
        }

        updatedSuperhero = service.updateSuperhero( superheroId, updatedSuperhero );
        return ResponseEntity.ok( updatedSuperhero );
    }

    @DeleteMapping( "/deleteSuperhero/{superheroId}" )
    public ResponseEntity<String> deleteSuperheroById( @PathVariable int superheroId )
    {
        Superhero foundSuperhero = service.getSuperheroById( superheroId );
        if ( foundSuperhero == null )
        {
            return new ResponseEntity( "Superhero not found.", HttpStatus.NOT_FOUND );
        }

        boolean isSuccessful = service.deleteSuperheroById( superheroId );
        if( isSuccessful )
        {
            return ResponseEntity.ok( foundSuperhero.getName() + " deleted successfully." );
        }
        else
        {
            return new ResponseEntity( "Error occured while deleting Superhero.", HttpStatus.CONFLICT );
        }
    }

    @GetMapping( "/getAllSuperheros" )
    public List<Superhero> getAllSuperheros()
    {
        return service.getAllSuperheros();
    }

    @GetMapping( "/getOrgsBySuperhero/{superheroId}" )
    public List<Organization> getAllOrgsBySuperheroId( @PathVariable int superheroId )
    {
        //TODO: finish
        return null;
    }

    //Organization methods
    //-----------------------------------------------------------------------------------------------------

    @PostMapping( "/addOrganization" )
    public Organization addOrganization( @RequestBody Organization newOrganization )
    {
        //TODO: add validation
        return service.addOrganization( newOrganization );
    }
    
    @GetMapping( "/getOrganization/{organizationId}" )
    public ResponseEntity<Organization> getOrganizationById( @PathVariable int organizationId )
    {
        Organization foundOrganization = service.getOrganizationById( organizationId );
        if ( foundOrganization == null )
        {
            return new ResponseEntity( null, HttpStatus.NOT_FOUND );
        }
        List<Integer> heroIdSet = service.getAllMembersByOrganizationId( foundOrganization.getId() );
        List<Superhero> membersSet = new ArrayList<>();
        for ( Integer heroId : heroIdSet )
        {
            membersSet.add( service.getSuperheroById( heroId ) );
        }
        foundOrganization.setMembersOfOrganization( membersSet );
        return ResponseEntity.ok( foundOrganization );
    }

    @PutMapping( "/updateOrganization/{organizationId}" )
    public ResponseEntity<Organization> updateOrganizationById( @PathVariable int organizationId
        , @RequestBody Organization updatedOrganization )
    {
        Organization foundOrganization = service.getOrganizationById( organizationId );
        if ( foundOrganization == null )
        {
            return new ResponseEntity( null, HttpStatus.NOT_FOUND );
        }
        updatedOrganization = service.updateOrganization( organizationId, updatedOrganization );
        return ResponseEntity.ok( updatedOrganization );
    }

    @DeleteMapping( "/deleteOrganization/{organizationId}" )
    public ResponseEntity<String> deleteOrganizationById( @PathVariable int organizationId )
    {
        Organization foundOrganization = service.getOrganizationById( organizationId );
        if ( foundOrganization == null )
        {
            return new ResponseEntity( "Organization not found.", HttpStatus.NOT_FOUND );
        }

        boolean isSuccessful = service.deleteOrganizationById( organizationId );
        if( isSuccessful )
        {
            return ResponseEntity.ok( foundOrganization.getName() + " deleted successfully." );
        }
        else
        {
            return new ResponseEntity( "Error occured while deleting Organization.", HttpStatus.CONFLICT );
        }
    }

    @GetMapping( "/getAllOrganizations" )
    public List<Organization> getAllOrganizations()
    {
        return service.getAllOrganizations();
    }

    @PostMapping( "/addSuperheroToOrg/{superheroId}/{organizationId}" )
    public ResponseEntity<String> addSuperheroToOrganization( @PathVariable int superheroId, @PathVariable int organizationId )
    {
        Superhero foundSuperhero = service.getSuperheroById( superheroId );
        if ( foundSuperhero == null )
        {
            //TODO: fix error not triggering properly
            return new ResponseEntity<String>( "Superhero not found in DB.", HttpStatus.NOT_FOUND );
        }

        Organization foundOrg = service.getOrganizationById( organizationId );
        if( foundOrg == null )
        {
            return new ResponseEntity<String>( "Organization not found in DB.", HttpStatus.NOT_FOUND );
        }

        boolean isSuccessful = service.addSuperheroToOrganization( superheroId, organizationId );
        if ( isSuccessful )
        {
            return new ResponseEntity<String>( foundSuperhero.getName() + " was added to the " +
                foundOrg.getName() + " Organization.", HttpStatus.OK );
        }
        return new ResponseEntity<String>( "Error occured adding to Bridge table despite both " +
            "objects being found.", HttpStatus.CONFLICT );
    }

    @DeleteMapping( "/removeSuperheroFromOrg/{superheroId}/{organizationId}" )
    public ResponseEntity<String> removeSuperheroFromOrganization( @PathVariable int superheroId, @PathVariable int organizationId )
    {
        Superhero foundSuperhero = service.getSuperheroById( superheroId );
        if ( foundSuperhero == null )
        {
            //TODO: fix error not triggering properly
            return new ResponseEntity<String>( "Superhero not found in DB.", HttpStatus.NOT_FOUND );
        }

        Organization foundOrg = service.getOrganizationById( organizationId );
        if( foundOrg == null )
        {
            return new ResponseEntity<String>( "Organization not found in DB.", HttpStatus.NOT_FOUND );
        }

        boolean isSuccessful = service.removeSuperheroFromOrganization( superheroId, organizationId );
        if ( isSuccessful )
        {
            return new ResponseEntity<String>( foundSuperhero.getName() + " was removed from the " +
                foundOrg.getName() + " Organization.", HttpStatus.OK );
        }
        return new ResponseEntity<String>( "Error occured removing from Bridge table despite both " +
            "objects being found.", HttpStatus.CONFLICT );
    }

    //TODO: possibly switch implementations for  second half of Project.
    /*
    @GetMapping( "/getAllOrgMembers/{organizationId}" )
    public List<Superhero> getAllOrgMembers( @PathVariable int organizationId )
    {
        List<Integer> returnedHeroIds = service.getAllMembersByOrganizationId( organizationId );
        List<Superhero> returnedSuperheroSet = new ArrayList<Superhero>();
        for ( Integer superheroId : returnedHeroIds )
        {
            returnedSuperheroSet.add( service.getSuperheroById( superheroId ) );
        }
        return returnedSuperheroSet;
    }
    */

    @GetMapping( "/getAllOrgMembers/{organizationId}" )
    public Map<String, List<Superhero>> getAllOrgMembers( @PathVariable int organizationId )
    {
        List<Integer> returnedHeroIds = service.getAllMembersByOrganizationId( organizationId );
        List<Superhero> returnedSuperheroSet = new ArrayList<Superhero>();
        for ( Integer superheroId : returnedHeroIds )
        {
            returnedSuperheroSet.add( service.getSuperheroById( superheroId ) );
        }
        Map<String, List<Superhero>> responseMap = new HashMap<>();
        responseMap.put( "Superheros from Organization: " + service.getOrganizationById( organizationId ).getName(), returnedSuperheroSet );
        return responseMap;
    }

    //Locatiop Methods
    //--------------------------------------------------------------------------------------------------------------------

    @PostMapping( "/addLocation" )
    public Location addLocation( @RequestBody Location newLocation )
    {
        return service.addLocation( newLocation );
    }

    @GetMapping( "/getLocation/{locationId}" )
    public ResponseEntity<Location> getLocationById( @PathVariable int locationId )
    {
        Location foundLocation = service.getLocationById( locationId );
        if ( foundLocation == null )
        {
            return new ResponseEntity( null, HttpStatus.NOT_FOUND );
        }
        return ResponseEntity.ok( foundLocation );
    }

    @PutMapping( "/updateLocation/{locationId}" )
    public ResponseEntity<Location> updateLocationById( @PathVariable int locationId
        , @RequestBody Location updatedLocation )
    {
        Location foundLocation = service.getLocationById( locationId );
        if ( foundLocation == null )
        {
            return new ResponseEntity( null, HttpStatus.NOT_FOUND );
        }
        updatedLocation = service.updateLocation( locationId, updatedLocation );
        return ResponseEntity.ok( updatedLocation );
    }

    @DeleteMapping( "/deleteLocation/{locationId}" )
    public ResponseEntity<String> deleteLocationById( @PathVariable int locationId )
    {
        Location foundLocation = service.getLocationById( locationId );
        if ( foundLocation == null )
        {
            return new ResponseEntity( "Location not found.", HttpStatus.NOT_FOUND );
        }

        boolean isSuccessful = service.deleteLocationById( locationId );
        if( isSuccessful )
        {
            return ResponseEntity.ok( foundLocation.getName() + " deleted successfully." );
        }
        else
        {
            return new ResponseEntity( "Error occured while deleting Location.", HttpStatus.CONFLICT );
        }
    }

    @GetMapping( "/getAllLocations" )
    public List<Location> getAllLocations()
    {
        return service.getAllLocations();
    }

    //Sighting Functions
    //-----------------------------------------------------------------------------------------------------------------

    @PostMapping( "/addSighting" )
    public Sighting addSighting( @RequestBody Sighting newSighting )
    {
        Sighting returnedSighting = service.addSighting( newSighting );
        returnedSighting.setSuperheroName( service.getSuperheroById( returnedSighting.getHeroId() ).getName() );
        returnedSighting.setLocationName( service.getLocationById( returnedSighting.getLocationId() ).getName() );
        return returnedSighting;
    }

    @GetMapping( "/getSighting/{sightingId}" )
    public ResponseEntity<Sighting> getSightingById( @PathVariable int sightingId )
    {
        Sighting foundSighting = service.getSightingById( sightingId );
        if ( foundSighting == null )
        {
            return new ResponseEntity( null, HttpStatus.NOT_FOUND );
        }
        return ResponseEntity.ok( foundSighting );
    }

    @PutMapping( "/updateSighting/{sightingId}" )
    public ResponseEntity<Sighting> updateSightingById( @PathVariable int sightingId
        , @RequestBody Sighting updatedSighting )
    {
        Sighting foundSighting = service.getSightingById( sightingId );
        if ( foundSighting == null )
        {
            return new ResponseEntity( null, HttpStatus.NOT_FOUND );
        }
        updatedSighting = service.updateSighting( sightingId, updatedSighting );
        return ResponseEntity.ok( updatedSighting );
    }

    @DeleteMapping( "/deleteSighting/{sightingId}" )
    public ResponseEntity<String> deleteSightingById( @PathVariable int sightingId )
    {
        Sighting foundSighting = service.getSightingById( sightingId );
        if ( foundSighting == null )
        {
            return new ResponseEntity( "Sighting not found.", HttpStatus.NOT_FOUND );
        }

        boolean isSuccessful = service.deleteSightingById( sightingId );
        if( isSuccessful )
        {
            return ResponseEntity.ok( "Sighting " + foundSighting.getId() + " deleted successfully." );
        }
        else
        {
            return new ResponseEntity( "Error occured while deleting Sighting.", HttpStatus.CONFLICT );
        }
    }

    @GetMapping( "/getAllSightings" )
    public List<Sighting> getAllSightings()
    {
        return service.getAllSightings();
    }

    @GetMapping( "/getSightingsByDate/{sightingDate}" )
    public List<Sighting> getSightingsByDate( @PathVariable LocalDate sightingDate )
    {
        List<Sighting> returnedSightingSet = service.getAllSightingsByDate( sightingDate );
        for ( Sighting sighting : returnedSightingSet )
        {
            sighting.setSuperheroName( service.getSuperheroById( sighting.getHeroId() ).getName() );
            sighting.setLocationName( service.getLocationById( sighting.getLocationId() ).getName() );
        }
        return returnedSightingSet;
    }

    @GetMapping( "/getSightingLocationsByHero/{superheroId}" )
    public List<Location> getSightingLocationsByHero( @PathVariable int superheroId )
    {
        List<Integer> returnedLocationIds = service.getAllSightingLocationsBySuperheroId( superheroId );
        List<Location> returnedLocationSet = new ArrayList<Location>();
        for ( Integer locationId : returnedLocationIds )
        {
            returnedLocationSet.add( service.getLocationById( locationId ) );
        }
        return returnedLocationSet;
    }

    @GetMapping( "/getAllSightingsByLocation/{locationId}" )
    public List<Sighting> getAllSightingsByLocation( @PathVariable int locationId )
    {
        List<Sighting> returnedSightingSet = service.getAllSuperheroSightingsByLocationId( locationId );
        for ( Sighting sighting : returnedSightingSet )
        {
            sighting.setSuperheroName( service.getSuperheroById( sighting.getHeroId() ).getName() );
            sighting.setLocationName( service.getLocationById( sighting.getLocationId() ).getName() );
        }
        return returnedSightingSet;
    }
    
}//END of SuperheroController
