/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/5/22, 11:30 PM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: SightingDao
 * @Full_Class_Name: com.sg.superhero.dao.SightingDao
 * @File_Name: SightingDao.java
 */

package com.sg.superhero.dao;

import com.sg.superhero.dto.Sighting;

import java.time.LocalDate;
import java.util.List;

public interface SightingDao
{
    public Sighting addSighting( Sighting newSighting );
    public Sighting getSightingById( int id );
    public Sighting updateSighting( int id, Sighting updatedSighting );
    public boolean deleteSightingById( int id );
    public List<Sighting> getAllSightings();
    public List<Sighting> getAllSightingsByDate( LocalDate date );
    public List<Integer> getAllSightingLocationsBySuperheroId( int superheroId );
    public List<Sighting> getAllSuperheroSightingsByLocationId( int locationId );
    public List<Sighting> getTenMostRecentSightings();
    
}//End of SightingDao
