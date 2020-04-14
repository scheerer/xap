package xmlobject;

/**
* Title:        XmlObject
* Description:  Implement the JAXP classes
* Copyright:    Copyright (c) 2001
* Company:      XAP (XML / LDAP Integration)
* @author      John Iles, Russel Scheerer, Raood Talarico
* @version 1.0
*/

import xmlobject.XmlAttribute;
import iles.BasicArray;

public class XmlElement
{
    protected int         classIdentifier;
    protected BasicArray  attributes;

    /**
     *  XmlElement()
     *  This is the only constructor for XmlElement. The constructor sets up
     *  the BasicArray of attributes.
     */

    public XmlElement()
    {
	attributes    = new BasicArray(2);
    } // end constructor

    /**
     *  getClassIdentifier()
     *      Return the class identifier constant for the object.
     *  #returns:   int
     */

    public int getClassIdentifier()
    {
	return classIdentifier;
    } // end function getClassIdentifier()

    /**
     *  insertAttribute( String attributeName, String attriubteValue)
     *      Insert an attribute into this element
     *  #parameter: attributeName
     *      String attribute name to insert.
     *  #parameter: attributeValue
     *      String attribute value to insert.
     *  #returns:   void
     */

    public void insertAttribute(String attributeName, String attributeValue)
    {
	int           index;
	XmlAttribute  attribute;
	for(index = 0; index < attributes.objects.length; index++)
	{
	    if(attributes.objects[index] != null)
	    {
		attribute = (XmlAttribute)attributes.objects[index];
		if(attributeName == attribute.getName())
		{
		    return;
		} // end if
	    } // end if
	} // end for
	attribute = new XmlAttribute(attributeName, attributeValue);
	attributes.insert(attribute);
    } // end function insertAttribute()

    /**
     *  removeAttribute(String attributeName, String attributeValue)
     *      Remove an attribute.
     *  #parameter: attributeName
     *      String attribute name to authenticate the removal of the attribute
     *  #parameter: attributeValue
     *      String attribute value to authenticate the removal of the attribute.
     *  #returns:   void
     */

    public XmlAttribute removeAttribute(String attributeName)
    {
	int           index;
	int           deletionPoint = 0;
	XmlAttribute  attribute;
	for(index = 0; index < attributes.objects.length; index++)
	{
	    if(attributes.objects[index] != null)
	    {
		attribute = (XmlAttribute)attributes.objects[index];
		if(attributeName == attribute.getName())
		{
		    deletionPoint = index;
		} // end if
	    } // end if
	} // end for

	if(deletionPoint == -1)
	{
	return null;
	} // end if

	return (XmlAttribute)attributes.remove(deletionPoint);
    } // end function removeAttribute()

    /**
     *  getAttributes()
     *      Returns an array of XmlAttribute.
     *      the current container element.
     *  #returns:   XmlAttribute[]
     */

    public XmlAttribute[] getAttributes()
    {
	int index;
	int attributeCount = 0;
	for(index = 0; index < attributes.objects.length; index++)
	{
	    if(attributes.objects[index] != null)
	    {
		attributeCount++;
	    } // end if
	} // end for loop

	XmlAttribute[] temp = new XmlAttribute[attributeCount];
	for(index = 0; index < temp.length; index++)
	{
	    temp[index] = (XmlAttribute)attributes.objects[index];
	} // end for loop

	return temp;
    } // end Function getAttributes[]

}