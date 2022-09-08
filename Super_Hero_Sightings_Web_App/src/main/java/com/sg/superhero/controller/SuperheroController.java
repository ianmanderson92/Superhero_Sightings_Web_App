/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/8/22, 4:32 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: SuperheroController
 * @Full_Class_Name: com.sg.superhero.controller.SuperheroController
 * @File_Name: SuperheroController.java
 */

package com.sg.superhero.controller;

import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.dao.SuperheroDao;
import com.sg.superhero.dto.Superhero;
import com.sg.superhero.service.SuperheroServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SuperheroController
{

    @Autowired
    SuperheroDao superheroDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SuperheroServiceLayer serviceLayer;

    @Autowired
    RESTController restController;

    @GetMapping("superheros")
    public String displaySuperheros( Model model )
    {
        List<Superhero> superheroList = serviceLayer.getAllSuperheros();
        model.addAttribute( "superheros", superheroList );
        return "superheros";
    }

    @PostMapping( "addSuperhero" )
    public String addSuperhero ( HttpServletRequest request )
    {
        String name = request.getParameter( "name" );
        String description = request.getParameter( "description" );
        String superpower = request.getParameter( "superpower" );

        Superhero newSuperhero = new Superhero( name, description, superpower );
        serviceLayer.addSuperhero( newSuperhero );

        return "redirect:/superheros";
    }

    @GetMapping("deleteSuperhero")
    public String deleteSuperhero( HttpServletRequest request )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        serviceLayer.deleteSuperheroById( id );

        return "redirect:/superheros";
    }

    @GetMapping( "editSuperhero" )
    public String editSuperhero(HttpServletRequest request, Model model)
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        Superhero superhero = serviceLayer.getSuperheroById( id );

        model.addAttribute( "superhero", superhero );

        return "editSuperhero";
    }

    @PostMapping( "editSuperhero" )
    public String performEditTeacher( HttpServletRequest request )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        Superhero superhero = serviceLayer.getSuperheroById( id );

        superhero.setName( request.getParameter( "name" ) );
        superhero.setDescription( request.getParameter( "description" ) );
        superhero.setSuperpower( request.getParameter( "superpower" ) );

        serviceLayer.updateSuperhero( id, superhero );

        return "redirect:/superheros";
    }
}//End of SuperheroController
