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

import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Superhero;
import com.sg.superhero.service.SuperheroServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        //System.out.println(addedSuperHero.getName() + " was added with id: " + addedSuperHero.getId()
        //+ " successfully" );
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
        /*
        else if ( superheroId != updatedSuperhero.getId() )
        {
            return new ResponseEntity( null, HttpStatus.BAD_REQUEST );
        }
        */
        else
        {
            updatedSuperhero = service.updateSuperhero( superheroId, updatedSuperhero );
        }
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
}//END of SuperheroController
