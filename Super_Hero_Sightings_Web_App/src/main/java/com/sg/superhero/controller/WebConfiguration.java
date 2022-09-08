/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/8/22, 11:16 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: WebConfiguration
 * @Full_Class_Name: com.sg.superhero.controller.WebConfiguration
 * @File_Name: WebConfiguration.java
 */

package com.sg.superhero.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer
{

    @Override
    public void addViewControllers( ViewControllerRegistry registry )
    {
        registry.addRedirectViewController( "/", "index.html" );
    }
}
