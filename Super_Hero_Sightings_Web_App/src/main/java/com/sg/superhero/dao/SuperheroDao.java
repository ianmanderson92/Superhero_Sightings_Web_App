/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/5/22, 11:30 PM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: SuperheroDao
 * @Full_Class_Name: com.sg.superhero.dao.SuperheroDao
 * @File_Name: SuperheroDao.java
 */

package com.sg.superhero.dao;

import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Superhero;

import java.util.List;

public interface SuperheroDao
{

    public Superhero addSuperhero( Superhero superhero );
    public Superhero getSuperheroById( int id );
    public Superhero updateSuperhero( int id, Superhero superhero );
    public boolean deleteSuperheroById( int id );
    public List<Superhero> getAllSuperheros();

    //TODO: consider different implementation
    public List<Organization> getAllOrganizationsBySuperheroId( int superheroId );
}//End of SuperheroDao
