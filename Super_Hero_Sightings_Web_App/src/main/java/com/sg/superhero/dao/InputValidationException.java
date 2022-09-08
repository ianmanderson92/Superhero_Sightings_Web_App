/*
 * @Author: Ian Anderson
 * @Mailto: ianmanderson92@gmail.com
 * @Modified: 9/7/22, 9:44 PM
 * All Rights Reserved.
 *
 * @Project: Super_Hero_Sightings_Web_App
 * @Class_Name: InputValidationException
 * @Full_Class_Name: com.sg.superhero.dao.InputValidationException
 * @File_Name: InputValidationException.java
 */

package com.sg.superhero.dao;

public class InputValidationException extends Exception
{

    public InputValidationException(String message)
    {
        super(message);
    }

    public InputValidationException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
