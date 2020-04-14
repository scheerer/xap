package dtdobject;

/**
 * Title:        XAP (XML / LDAP Integration)
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Ball State University CS498
 * @author John Iles, Russell Scheerer, Raood Talarico
 * @version 1.1
 */

import iles.BasicArray;

public class DtdObject
{
    public static final int     VALUE_ELEMENT       = 10;
    public static final int     CONTAINER_ELEMENT   = 12;
    public static final int     MUST                = 14;
    public static final int     MAY                 = 16;

    private BasicArray  elements;
    DtdElement          currentElement;

    /**
     *  DtdObject()
     *  This is the only constructor for DtdObject. The constructor sets up
     *  the BasicAray in which the elements are defined.
     */

    public DtdObject()
    {
	elements = new BasicArray(10);
	elements.setGrowthFactor(5);
    }

    /**
     *  insertElement(String name, int type)
     *    add an element into the virtual DtdObject.
     *
     *  #parameter:  name
     *    	the name of the new element to be inserted into the virtual DTD
     *    	representation.
     *  #parameter:  type
     *    	specifies the type of element being inserted as either a container
     *    	element or a value element. Use the constants CONTAINER_ELEMENT
     *    	and VALUE_ELEMENT defined in this class.
     *  #returns:  void
     */

    public void insertElement(String name, int type)
    {
	currentElement  = new DtdElement(name, type);

	// Check for existing element with same name.
	int index;
        for(index = 0; index < elements.objects.length; index++)
	{
	    if(elements.objects[index] != null)
	    {
		DtdElement temp = (DtdElement)elements.objects[index];
		if( temp.getName() == currentElement.getName() )
		{
		    return;
		} // end if
	    } // end if
	} // end for

	elements.insert(currentElement);
    }

    /**
     *  moveToElement(String name)
     *    move the current element to the element specified by name. This enables
     *    the addition of grouping objects into a node.
     *
     *  #parameter:  name
     *    	the name of the new element to be set as current.
     *  #returns:  void
     */

    public void moveToElement(String name)
    {
	DtdElement temp;
	int index;
	for(index = 0; index < elements.objects.length; index++)
	{
	    if(elements.objects[index] != null)
	    {
		temp = (DtdElement)elements.objects[index];
		if( temp.getName() == name )
		{
		    currentElement = temp;
		} // end if
	    } // end if
	} // end for
    } // end function moveToElement()

    /**
     *  addSubgroup(String subgroup, int occurrence)
     *    insert a subgroup to a container element. Each subgroup also has some
     *    occurrence specification given by the constants MAY and MUST which indicates
     *    if an object may appear 0 or more times or must appear at least once.
     *
     *  #parameter:  subgroup
     *    	the name of the new subgroup to be inserted.
     *  #parameter:  occurrence
     *          MAY  -> the subgroup appears 0 to many times.
     *          MUST -> the subgroup appears at least once.
     *  #returns:  void
     */

    public void addSubgroup(String subgroup, int occurrence)
    {
	currentElement.insertGroup(subgroup, occurrence);
    } // end function addSubgroup()

    /**
     *  dtdToText()
     *    convert a virtual DTD specfication given by this object into a String
     *    that can be printed in a window, file, or other such output gizmo.
     *  #returns:  String
     */

    public String dtdToText()
    {
	String display = "";
	int index;
	DtdElement[] showElements = getElements();
	for(index = 0; index < showElements.length; index++)
	{
	    display += showElements[index].dtdToText();
	    display += "\n";
	} // end for

        return display;
    } // end function dtdToText()

    /**
     *  getElements()
     *    convert the BasicArray of DTD elements into a simple String array for
     *    output purposes in the dtdToText() function. Note that getElements is
     *    a private function.
     *  #returns:  String
     */

    private DtdElement[] getElements()
    {
	int index;
	int elementCount = 0;
	for(index = 0; index < elements.objects.length; index++)
	{
	    if(elements.objects[index] != null)
	    {
		elementCount++;
	    } // end if
	} // end for loop

	DtdElement[] temp = new DtdElement[elementCount];
	for(index = 0; index < temp.length; index++)
	{
	    temp[index] = (DtdElement)elements.objects[index];
	} // end for loop

	return temp;
    }

}