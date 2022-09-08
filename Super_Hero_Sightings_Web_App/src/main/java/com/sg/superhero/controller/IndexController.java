/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/8/22, 10:13 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: IndexController
 * @Full_Class_Name: com.sg.superhero.controller.IndexController
 * @File_Name: IndexController.java
 */

package com.sg.superhero.controller;

import com.sg.superhero.dto.Sighting;
import com.sg.superhero.service.SuperheroServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@Controller
public class IndexController
{

    @Autowired
    SuperheroServiceLayer serviceLayer;

    @RequestMapping( "/" )
    public String displayIndex( Model model )
    {
        List<Sighting> sightingList = serviceLayer.getTenMostRecentSightings();
        model.addAttribute( "sightings", sightingList );
        return "index.html";
    }
}
