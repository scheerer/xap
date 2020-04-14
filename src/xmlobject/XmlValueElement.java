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
import xmlobject.XmlElement;
import xmlobject.XmlAttribute;
import iles.BasicArray;

public class XmlValueElement extends XmlElement
{
    public static final int CLASS_ID = 4;
    private String elementName;
    private String elementValue;

   /**
    *  XmlValueElement( String name, String value)
    *  This is the primary constructor for XmlValueElement. The constructor sets
    *  up the attribute BasicArray objects, and the name and value.
    */

    public XmlValueElement(String name, String value)
    {
	elementName     = name;
	elementValue    = value;
	classIdentifier = CLASS_ID;
	attributes      = new BasicArray(2);
    } // end constructor

    /**
     *  getName()
     *       Return the name of the value element.
     *  #returns:    String
     */

    public String getName()
    {
	return elementName;
    } // end Function getName()

    /**
     *  getValue()
     *       Return the value of the value element.
     *  #returns:    String
     */

    public String getValue()
    {
	return elementValue;
    } // end Function getValue

    /**
     *  xmlToWindow()
     *    Convert a virtual XML specfication given by this object into a String
     *    that can be printed in a window, file, or other such output gizmo.
     *  #returns:  String
     */

    public String xmlToWindow()
    {
	String display = "";

	display += "<" + elementName;

	XmlAttribute[] showAttributes = getAttributes();
	int index;
	for(index = 0; index < showAttributes.length; index++)
	{
	    display += " ";
	    display += showAttributes[index].xmlToWindow();
	} // end for
	display += ">" + elementValue + "</" + elementName + ">";

	return display;
    } // end function xmlToWindow()

    /**
     *  showXml()
     *    Print a virtual XML specfication given by this object into a String
     *    that can be printed in a window, file, or other such output gizmo.
     *  #returns:  String
     */

    void showXml()
    {
	int index;
	System.out.print("<" + elementName);

	XmlAttribute[] showAttributes = getAttributes();
	for(index = 0; index < showAttributes.length; index++)
	{
	    System.out.print(" ");
	    showAttributes[index].showXml();
	} // end for
	System.out.print(">" + elementValue + "</" + elementName + ">");
    } // end function showXml()

} // end class XmlValueElement