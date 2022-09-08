
/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/8/22, 7:45 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: LocationController
 * @Full_Class_Name: com.sg.superhero.controller.LocationController
 * @File_Name: LocationController.java
 */

package com.sg.superhero.controller;

import com.sg.superhero.dto.Location;
import com.sg.superhero.service.SuperheroServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class LocationController
{

    @Autowired
    SuperheroServiceLayer serviceLayer;

    @Autowired
    RESTController restController;

    @GetMapping( "locations" )
    public String displayLocations( Model model )
    {
        List<Location> locationList = serviceLayer.getAllLocations();
        model.addAttribute( "locations", locationList );
        return "locations";
    }

    @PostMapping( "addLocation" )
    public String addLocation( HttpServletRequest request )
    {
        String name = request.getParameter( "name" );
        String description = request.getParameter( "description" );
        String address = request.getParameter( "address" );
        String latitude = request.getParameter( "latitude" );
        String longitude = request.getParameter( "longitude" );

        Location newLocation = new Location( name, description, address
            , new BigDecimal( latitude ), new BigDecimal( longitude ) );
        serviceLayer.addLocation( newLocation );

        return "redirect:/locations";
    }

    @GetMapping( "deleteLocation" )
    public String deleteLocation( HttpServletRequest request )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        serviceLayer.deleteLocationById( id );

        return "redirect:/locations";
    }

    @GetMapping( "editLocation" )
    public String editLocation( HttpServletRequest request, Model model )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        Location location = serviceLayer.getLocationById( id );

        model.addAttribute( "location", location );

        return "editLocation";
    }

    @PostMapping( "editLocation" )
    public String performEditLocation( HttpServletRequest request )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        Location location = serviceLayer.getLocationById( id );

        location.setName( request.getParameter( "name" ) );
        location.setDescription( request.getParameter( "description" ) );
        location.setAddress( request.getParameter( "address" ) );
        location.setLatitude( new BigDecimal( request.getParameter( "latitude" ) ) );
        location.setLongitude( new BigDecimal( request.getParameter( "longitude" ) ) );

        serviceLayer.updateLocation( id, location );

        return "redirect:/locations";
    }
}//End of LocationController
