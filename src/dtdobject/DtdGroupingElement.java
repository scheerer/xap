package dtdobject;

/**
 * Title:        XAP (XML / LDAP Integration)
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Ball State University CS498
 * @author John Iles, Russell Scheerer, Raood Talarico
 * @version 1.1
 */

import dtdobject.DtdObject;

public class DtdGroupingElement
{

    protected String    elementName;
    protected int       elementOccurrence;

    /**
     *  DtdGroupingElement()
     *    only constructor for DtdGroupingElement.
     *
     *  #parameter:  name
     *    	the name of the new subgroup to be inserted.
     *  #parameter:  occurrence
     *          MAY  -> the subgroup appears 0 to many times.
     *          MUST -> the subgroup appears at least once.
     */

    public DtdGroupingElement(String name, int occurrence)
    {
	elementName         = name;
	elementOccurrence   = occurrence;
    } // end Constructor

    /**
     *  setOccurrence()
     *    Set the occurrence rating of the DtdGroupingElement.
     *
     *  #parameter:  occurrence
     *          MAY  -> the subgroup appears 0 to many times.
     *          MUST -> the subgroup appears at least once.
     */

    public void setOccurrence(int occurrence)
    {
        elementOccurrence = occurrence;
    } // end function setOccurrence()

    /**
     *  getName()
     *    Return the name of the DtdGroupingElement.
     *
     *  #returns:   String
     */

    public String getName()
    {
        return elementName;
    } // end function getName

    /**
     *  getOccurrence()
     *    Return the occurrence rating of the DtdGroupingElement.
     *
     *  #returns:   int
     */

    public int getOccurrence()
    {
        return elementOccurrence;
    } // end Function getOccurrence()

    /**
     *  dtdToText()
     *    Convert a virtual DTD specfication given by this object into a String
     *    that can be printed in a window, file, or other such output gizmo.
     *  #returns:  String
     */

    public String dtdToText()
    {
	String display = "";
	display += elementName;
	if(elementOccurrence == DtdObject.MAY)
	{
	    display += "*";
	}
	else if(elementOccurrence == DtdObject.MUST)
	{
	    display += "+";
	} // end if

        return display;
    } // end function dtdToText()
}