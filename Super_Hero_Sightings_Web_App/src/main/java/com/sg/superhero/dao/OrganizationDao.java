/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/5/22, 11:30 PM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: OrganizationDao
 * @Full_Class_Name: com.sg.superhero.dao.OrganizationDao
 * @File_Name: OrganizationDao.java
 */

package com.sg.superhero.dao;

import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Superhero;

import java.util.List;

public interface OrganizationDao
{

    public Organization addOrganization( Organization newOrganization );
    public Organization getOrganizationById( int id );
    public Organization updateOrganization( int id, Organization updatedOrganization );
    public boolean deleteOrganizationById( int id );
    public List<Organization> getAllOrganizations();
    public Boolean addSuperheroToOrganization( int superheroId, int organizationId );
    public Boolean removeSuperheroFromOrganization( int superheroId, int organizationId );
    public List<Superhero> getAllMembersByOrganizationId( int id );
    
}//End of OrganizationDao
