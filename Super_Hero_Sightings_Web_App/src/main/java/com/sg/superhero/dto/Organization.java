/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/6/22, 2:05 AM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: Organization
 * @Full_Class_Name: com.sg.superhero.dto.Organization
 * @File_Name: Organization.java
 */

package com.sg.superhero.dto;

import com.sg.superhero.dao.InputValidationException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization
{
    //Primary Key
    private int id;

    //Member Variables
    private String name;
    private String description;
    private String address;
    private String email;
    private String phone;

    //Database field constants
    private final int NAME_MAX_LENGTH = 45;
    private final int DESCRIPTION_MAX_LENGTH = 450;
    private final int ADDRESS_MAX_LENGTH = 255;
    private final int EMAIL_MAX_LENGTH = 255;
    private final int PHONE_MAX_LENGTH = 25;

    //Optional Member Variables
    private List<Superhero> membersOfOrganization;

    public Organization()
    {
    }

    public Organization( String name, String description, String address, String email, String phone )
    {
        this.name = name;
        this.description = description;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }

    public List<Superhero> getMembersOfOrganization()
    {
        return membersOfOrganization;
    }

    public void setMembersOfOrganization( List<Superhero> membersOfOrganization )
    {
        this.membersOfOrganization = membersOfOrganization;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        Organization that = (Organization) o;
        return id == that.id && name.equals( that.name ) && Objects.equals( description,
            that.description ) && address.equals( that.address ) && email.equals( that.email ) && phone.equals(
            that.phone );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, name, description, address, email, phone );
    }

    //Validation function
    private void validateOrganizationMembers() throws Exception
    {
        List<String> errorMessages = new ArrayList<>();

        //Name
        if ( !hasContent( this.name ) )
        {
            errorMessages.add( "Name is required." );
        }
        if ( this.name.length() > NAME_MAX_LENGTH )
        {
            errorMessages.add( "Input Name is too long. Max length: " + NAME_MAX_LENGTH );
        }

        //Description
        //Uncomment following block if description becomes a required field.
        /*if ( !hasContent( this.description ) )
        {
            errorMessages.add( "Name is required." );
        }*/
        if ( this.description.length() > DESCRIPTION_MAX_LENGTH )
        {
            errorMessages.add( "Input description is too long. Max length: " + DESCRIPTION_MAX_LENGTH);
        }

        //Address
        if ( !hasContent( this.address ) )
        {
            errorMessages.add( "Address is required." );
        }
        if ( this.address.length() > ADDRESS_MAX_LENGTH )
        {
            errorMessages.add( "Input address is too long. Max length: " + ADDRESS_MAX_LENGTH );
        }

        //Email
        if ( !hasContent( this.email ) )
        {
            errorMessages.add( "Email is required." );
        }
        if (this.email.length() > EMAIL_MAX_LENGTH )
        {
            errorMessages.add( "Input email is too long. Max length: " + EMAIL_MAX_LENGTH );
        }

        //Phone
        if ( !hasContent( this.phone ) )
        {
            errorMessages.add( "Phone number is required." );
        }
        if (this.phone.length() > PHONE_MAX_LENGTH )
        {
            errorMessages.add( "Input phone is too long. Max length: " + PHONE_MAX_LENGTH );
        }

        if ( !errorMessages.isEmpty() )
        {
            Exception validationEX = new Exception();
            for ( String error : errorMessages )
            {
                validationEX.addSuppressed( new InputValidationException( error ) );
            }
            throw validationEX;
        }
    }

    //Helper methods for validation function

    /**
     Method code adapted from:
     Author: Hirondelle Systems
     http://www.javapractices.com/topic/TopicAction.do?Id=209#:~:text=In%20the%20Java%20programming%20language,the%20user%20about%20the%20issues

     * @param field object to be checked
     * @param errorMsg error message to throw
     * @param errors error message array to append
     * @return true is null
     */
    private boolean ensureNotNull( Object field, String errorMsg, List<String> errors )
    {
        boolean result = true;
        if (field == null)
        {
            errors.add( errorMsg );
            result = false;
        }
        return result;
    }

    /**
     Method code adapted from:
     Author: Hirondelle Systems
     http://www.javapractices.com/topic/TopicAction.do?Id=209#:~:text=In%20the%20Java%20programming%20language,the%20user%20about%20the%20issues

     * @param stringToCheck String to validate
     * @return true if not null or empty
     */
    private boolean hasContent( String stringToCheck )
    {
        return ( stringToCheck != null && stringToCheck.trim().length() > 0 );
    }

    
}//End of Organization
