
/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/8/22, 9:35 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: SightingController
 * @Full_Class_Name: com.sg.superhero.controller.SightingController
 * @File_Name: SightingController.java
 */

package com.sg.superhero.controller;

import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.service.SuperheroServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
public class SightingController
{

    @Autowired
    SuperheroServiceLayer serviceLayer;


    @GetMapping( "sightings" )
    public String displaySightings( Model model )
    {
        List<Sighting> sightingList = serviceLayer.getAllSightings();
        model.addAttribute( "sightings", sightingList );
        return "sightings";
    }

    @PostMapping( "addSighting" )
    public String addSighting( HttpServletRequest request )
    {
        int heroId = Integer.parseInt( request.getParameter( "heroId" ) );
        int locationId = Integer.parseInt( request.getParameter( "locationId" ) );
        LocalDate date = LocalDate.parse( request.getParameter( "date" ) );

        Sighting newSighting = new Sighting( heroId, locationId, date );
        serviceLayer.addSighting( newSighting );

        return "redirect:/sightings";
    }

    @GetMapping( "deleteSighting" )
    public String deleteSighting( HttpServletRequest request )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        serviceLayer.deleteSightingById( id );

        return "redirect:/sightings";
    }

    @GetMapping( "editSighting" )
    public String editSighting( HttpServletRequest request, Model model )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        Sighting sighting = serviceLayer.getSightingById( id );

        model.addAttribute( "sighting", sighting );

        return "editSighting";
    }

    @PostMapping( "editSighting" )
    public String performEditSighting( HttpServletRequest request )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        Sighting sighting = serviceLayer.getSightingById( id );

        sighting.setHeroId( Integer.parseInt( request.getParameter( "heroId" ) ) );
        sighting.setLocationId( Integer.parseInt( request.getParameter( "locationId" ) ) );
        sighting.setDate( LocalDate.parse( request.getParameter( "date" ) ) );

        serviceLayer.updateSighting( id, sighting );

        return "redirect:/sightings";
    }
}//End of SightingController
