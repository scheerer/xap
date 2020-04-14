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
import iles.BasicArray;

public class DtdElement
{
    protected String        elementName;
    protected int           elementType;
    protected BasicArray    elementGroup;

    /**
     *  DtdElement()
     *  This is the only constructor for DtdElement. The constructor sets up
     *  the BasicAray in which the elements are defined, sets the name of the
     *  DTD element, and specify the type of the DTD element.
     *
     *  #parameter: name
     *          The name of the element to be created in the DTD virtual
     *          representation
     *  #parameter: type
     *          The kind of DTD element being created. Takes the constants
     *          (CONTAINER_ELEMENT | VALUE_ELEMENT) as defined in DtdObject.
     */

    public DtdElement(String name, int type)
    {
	elementName     = name;
	elementType     = type;
	elementGroup    = new BasicArray(5);
    } // end Constructor

    /**
     *  getName()
     *    Returns the name of the DTD element.
     *  #returns:  String
     */

    public String getName()
    {
        return elementName;
    } // end Function getName()

    /**
     *  getType()
     *    Returns the type of the DtdElement, which is either a CONTAINER_ELEMENT
     *    or a VALUE_ELEMENT.
     *  #returns:  int
     */

    public int getType()
    {
	return elementType;
    } // end function getType()

    /**
     *  insertGroup(String name, int occurrence)
     *    Inserts a subgroup into a container element. Each subgroup also has some
     *    occurrence specification given by the constants MAY and MUST which indicates
     *    if an object may appear 0 or more times or must appear at least once.
     *
     *  #parameter:  name
     *    	the name of the new subgroup to be inserted.
     *  #parameter:  occurrence
     *          MAY  -> the subgroup appears 0 to many times.
     *          MUST -> the subgroup appears at least once.
     *  #returns:  void
     */

    public void insertGroup(String name, int occurrence)
    {
	int                 index;
	DtdGroupingElement  element;
	for(index = 0; index < elementGroup.objects.length; index++)
	{
	    if(elementGroup.objects[index] != null)
	    {
		element = (DtdGroupingElement)elementGroup.objects[index];
		if(name == element.getName())
		{
		    return;
		} // end if
	    } // end if
	} // end for
	element = new DtdGroupingElement(name, occurrence);
	elementGroup.insert(element);
    } // end Function insertGroup()

    /**
     *  getGroupingElements()
     *    Returns an array of DtdGroupingElements for analysis by another object.
     *  #returns:  DtdGroupingElement[]
     */

    public DtdGroupingElement[] getGroupingElements()
    {
	int index;
	int groupingElementCount = 0;
	for(index = 0; index < elementGroup.objects.length; index++)
	{
	    if(elementGroup.objects[index] != null)
	    {
		groupingElementCount++;
	    } // end if
	} // end for loop

	DtdGroupingElement[] temp = new DtdGroupingElement[groupingElementCount];
	for(index = 0; index < temp.length; index++)
	{
	    temp[index] = (DtdGroupingElement)elementGroup.objects[index];
	} // end for loop

	return temp;
    } // end Function getValueElements[]

    /**
     *  dtdToText()
     *    Convert a virtual DTD specfication given by this object into a String
     *    that can be printed in a window, file, or other such output gizmo.
     *  #returns:  String
     */

    public String dtdToText()
    {
	String display = "<!ELEMENT " + elementName + " ";
	if(elementType == DtdObject.VALUE_ELEMENT)
	{
	    display += "(#PCDATA)>";
	}
	else if(elementType == DtdObject.CONTAINER_ELEMENT)
	{
	    display += "(";
	    int index;
	    DtdGroupingElement[] showGroupingElements = getGroupingElements();
	    for(index = 0; index < showGroupingElements.length; index++)
	    {
		display += showGroupingElements[index].dtdToText();
		if(index != (showGroupingElements.length - 1) )
		{
		    display += ",";
		}
	    } // end for
	    display += ")>";
	} // end if

        return display;
    } // end function dtdToText()

}