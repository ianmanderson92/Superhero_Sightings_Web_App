package com.sg.superhero.controller;

import com.sg.superhero.service.SuperheroServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
