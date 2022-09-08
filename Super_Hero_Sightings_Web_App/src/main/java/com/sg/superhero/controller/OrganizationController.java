/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/8/22, 6:33 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: OrganizationController
 * @Full_Class_Name: com.sg.superhero.controller.OrganizationController
 * @File_Name: OrganizationController.java
 */

package com.sg.superhero.controller;

import com.sg.superhero.dto.Organization;
import com.sg.superhero.service.SuperheroServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrganizationController
{

    @Autowired
    SuperheroServiceLayer serviceLayer;

    @Autowired
    RESTController restController;

    @GetMapping( "organizations" )
    public String displayOrganizations( Model model )
    {
        List<Organization> organizationList = serviceLayer.getAllOrganizations();
        model.addAttribute( "organizations", organizationList );
        return "organizations";
    }

    @PostMapping( "addOrganization" )
    public String addOrganization( HttpServletRequest request )
    {
        String name = request.getParameter( "name" );
        String description = request.getParameter( "description" );
        String address = request.getParameter( "address" );
        String email = request.getParameter( "email" );
        String phone = request.getParameter( "phone" );

        Organization newOrganization = new Organization( name, description, address, email, phone );
        serviceLayer.addOrganization( newOrganization );

        return "redirect:/organizations";
    }

    @GetMapping( "deleteOrganization" )
    public String deleteOrganization( HttpServletRequest request )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        serviceLayer.deleteOrganizationById( id );

        return "redirect:/organizations";
    }

    @GetMapping( "editOrganization" )
    public String editOrganization( HttpServletRequest request, Model model )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        Organization organization = serviceLayer.getOrganizationById( id );

        model.addAttribute( "organization", organization );

        return "editOrganization";
    }

    @PostMapping( "editOrganization" )
    public String performEditOrganization( HttpServletRequest request )
    {
        int id = Integer.parseInt( request.getParameter( "id" ) );
        Organization organization = serviceLayer.getOrganizationById( id );

        organization.setName( request.getParameter( "name" ) );
        organization.setDescription( request.getParameter( "description" ) );
        organization.setAddress( request.getParameter( "address" ) );
        organization.setEmail( request.getParameter( "email" ) );
        organization.setPhone( request.getParameter( "phone" ) );

        serviceLayer.updateOrganization( id, organization );

        return "redirect:/organizations";
    }
}//End of OrganizationController
