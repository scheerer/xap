package xmlobject;

/**
* Title:        XmlObject
* Description:  Implement the JAXP classes
* Copyright:    Copyright (c) 2001
* Company:      XAP (XML / LDAP Integration)
* @author      John Iles, Russel Scheerer, Raood Talarico
* @version 1.0
*/

import java.io.*;

public class XmlAttribute
{
    public static final int CLASS_ID = 6;
    private String name;
    private String value;

   /**
    *  XmlAttribute( String n, String v)
    *  This is the primary constructor for XmlAttribute. The constructor sets
    *  up the attribute name and value.
    */

    public XmlAttribute(String n, String v)
    {
	name  = n;
	value = v;
    }

    /**
     *  setValue()
     *       Set the value of the attribute
     *  #parameter: v
     *       String used for setting the value.
     *  #returns:    void
     */

    public void setValue(String v)
    {
	value = v;
    }

    /**
     *  getName()
     *       Return the name of the attribute.
     *  #returns:    String
     */

    public String getName()
    {
	return name;
    }

    /**
     *  getValue()
     *       Return the value of the attribute.
     *  #returns:    String
     */

    public String getValue()
    {
	return value;
    }

    /**
     *  xmlToWindow()
     *    Convert a virtual XML specfication given by this object into a String
     *    that can be printed in a window, file, or other such output gizmo.
     *  #returns:  String
     */


    public String xmlToWindow()
    {
	String display = "";

	display += name + "=\"" + value + "\"";

	return display;
    } // end function xmlToWindow()

    /**
     *  showXml()
     *    Print a virtual XML specfication given by this object into a String
     *    that can be printed in a window, file, or other such output gizmo.
     *  #returns:  String
     */

    public void showXml()
    {
	System.out.print(name + "=\"" + value + "\"");
    } // end function showXml()

}