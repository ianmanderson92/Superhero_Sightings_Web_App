/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/5/22, 11:30 PM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: LocationDao
 * @Full_Class_Name: com.sg.superhero.dao.LocationDao
 * @File_Name: LocationDao.java
 */

package com.sg.superhero.dao;

import com.sg.superhero.dto.Location;

import java.util.List;

public interface LocationDao
{

    public Location addLocation( Location newLocation );
    public Location getLocationById( int id );
    public Location updateLocation( int id, Location updatedLocation );
    public boolean deleteLocationById( int id );
    public List<Location> getAllLocations();

}//End of LocationDao
