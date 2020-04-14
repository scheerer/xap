package xmlobject;

/**
* Title:        XmlObject
* Description:  Implement the JAXP classes
* Copyright:    Copyright (c) 2001
* Company:      XAP (XML / LDAP Integration)
* @author      John Iles, Russel Scheerer, Raood Talarico
* @version 1.0
*/

import xmlobject.XmlContainerElement;
import xmlobject.XmlValueElement;
import iles.BasicArray;

public class XmlRootElement extends XmlContainerElement
{
    public static final int CLASS_ID    = 10;
    private String elementName;

    BasicArray valueElements;
    BasicArray containerElements;

    /**
    *  XmlRootElement()
    *  This is the only constructor for XmlRootElement. The constructor sets up
    *  the value and container element BasicArray objects, and the name of the
    *  root element.
    */

    public XmlRootElement( String name)
    {
	elementName         = name;
	classIdentifier     = CLASS_ID;
	valueElements       = new BasicArray(5);
	containerElements   = new BasicArray(5);
    } // end constructor

    /**
    *  getName()
    *       Return the name of the root element.
    *  #returns:    String
    */

    public String getName()
    {
	return elementName;
    } // end function getName()

    /**
     *  insertValueElement(String vElementName, String vElementValue)
     *      Insert a value element into the container element
     *  #parameter: vElementName
     *      String that sets the name of the value element to be inserted.
     *  #parameter: vElementValue
     *      String that sets the value of the value element to be inserted.
     *  #returns:   void
     */

    public void insertValueElement(String vElementName, String vElementValue)
    {
	int             index;
	XmlValueElement valueElement;
	for(index = 0; index < valueElements.objects.length; index++)
        {
	    if(valueElements.objects[index] != null)
	    {
		valueElement = (XmlValueElement)valueElements.objects[index];
		if( (vElementName == valueElement.getName()) &&
		(vElementValue == valueElement.getValue()) )
		{
		    return;
		} // end if
	    } // end if
	} // end for
	valueElement = new XmlValueElement(vElementName, vElementValue);
	valueElements.insert(valueElement);
    } // end function insertValueElement()

    /**
     *  removeValueElement(String vElementName, String vElementValue)
     *      Remove a value element specified by the name and value.
     *  #parameter: vElementName
     *      String specifying the name of the value element to be removed.
     *  #parameter: vElementValue
     *      String specifying the value of the value element to be removed.
     *  #returns:   XmlValueElement
     */

    public XmlValueElement removeValueElement(String vElementName, String vElementValue)
    {
	int             index;
	int             deletionPoint = 0;
	XmlValueElement valueElement;
	for(index = 0; index < valueElements.objects.length; index++)
	{
	    if(valueElements.objects[index] != null)
	    {
		valueElement = (XmlValueElement)valueElements.objects[index];
		if( (vElementName == valueElement.getName()) &&
    		(vElementValue == valueElement.getValue()) )
		{
		    deletionPoint = index;
		} // end if
	    } // end if
	} // end for

	if(deletionPoint == -1)
	{
		return null;
	} // end if

	return (XmlValueElement)valueElements.remove(deletionPoint);
    } // end function removeValueElement()

    /**
     *  getValueElements()
     *      Return the array of value elements in the container element.
     *  #returns:   XmlValueElement[]
     */

    public XmlValueElement[] getValueElements()
    {
	int index;
	int valueElementCount = 0;
	for(index = 0; index < valueElements.objects.length; index++)
	{
	    if(valueElements.objects[index] != null)
	    {
		valueElementCount++;
	    } // end if
	} // end for loop

	XmlValueElement[] temp = new XmlValueElement[valueElementCount];
	for(index = 0; index < temp.length; index++)
	{
	    temp[index] = (XmlValueElement)valueElements.objects[index];
	} // end for loop

	return temp;
    } // end Function getValueElements[]

    /**
     *  insertConatinerElement( String elementName,
     *                          String attributeName,
     *                          String attributeValue)
     *      Insert a container element into the current ContainerElement.
     *  #parameter: elementName
     *      String name for the new container element.
     *  #parameter: attributeName
     *      String attribute name for the new container element.
     *  #parameter: attributeValue
     *      String attribute value for the new container element.
     *  #returns:   void
     */

    public void insertContainerElement( String cElementName,
				        String attributeName,
				        String attributeValue)
    {
	int             index;
	XmlContainerElement containerElement;
	for(index = 0; index < containerElements.objects.length; index++)
	{
	    if(containerElements.objects[index] != null)
	    {
		containerElement = (XmlContainerElement)containerElements.objects[index];
		if(cElementName == containerElement.getName())
		{
		    XmlAttribute[] cAttributes = containerElement.getAttributes();
		    if( (cAttributes[containerElement.IDENTIFIER].getName() == attributeName) &&
		      (cAttributes[containerElement.IDENTIFIER].getValue() == attributeValue) )
		    {
			return;
		    } // end if
		} // end if
	    } // end if
	} // end for
	containerElement = new XmlContainerElement(cElementName, attributeName, attributeValue);
	containerElements.insert(containerElement);
    } // end function insertContainerElement()

    /**
     *  removeContainerElement( String elementName,
     *				String attributeName,
     *				String attributeValue)
     *      Remove a container element from the current ContainerElement.
     *  #parameter: elementName
     *      String element name to identify the value element to be removed.
     *  #parameter: attributeName
     *      String attribute name to authenticate the removal of a value element.
     *  #parameter: attributeValue
     *      String attribute value to authenticate the removal of a value element.
     *  #returns:   XmlContainerElement
     */

    public XmlContainerElement removeContainerElement(  String cElementName,
					      String attributeName,
					      String attributeValue)
    {
	int             index;
	int             deletionPoint = 0;
	XmlContainerElement containerElement;
	for(index = 0; index < containerElements.objects.length; index++)
	{
	    if(containerElements.objects[index] != null)
	    {
		containerElement = (XmlContainerElement)containerElements.objects[index];
		if(cElementName == containerElement.getName())
		{
		    XmlAttribute[] cAttributes = containerElement.getAttributes();
		    if( (cAttributes[containerElement.IDENTIFIER].getName() == attributeName) &&
		      (cAttributes[containerElement.IDENTIFIER].getValue() == attributeValue) )
		    {
			deletionPoint = index;
		    } // end if
		} // end if
	    } // end if
	} // end for

	if(deletionPoint == -1)
	{
	    return null;
	} // end if

	return (XmlContainerElement)containerElements.remove(deletionPoint);
    } // end function removeContainerElement()

    /**
     *  getContainerElements()
     *      Return the array of container elements in the container element.
     *  #returns:   XmlContainerElement[]
     */

    public XmlContainerElement[] getContainerElements()
    {
	int index;
	int containerElementCount = 0;
	for(index = 0; index < containerElements.objects.length; index++)
	{
	    if(containerElements.objects[index] != null)
	    {
		containerElementCount++;
	    } // end if
	} // end for loop

	XmlContainerElement[] temp = new XmlContainerElement[containerElementCount];
	for(index = 0; index < temp.length; index++)
	{
	    temp[index] = (XmlContainerElement)containerElements.objects[index];
	} // end for loop

	return temp;
    } // end Function getContainerElements[]

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
	display += ">";

	XmlValueElement[] showValueElements = getValueElements();
	for(index = 0; index < showValueElements.length; index++)
	{
	    display += "\n";
	    display += showValueElements[index].xmlToWindow();
	} // end for

	XmlContainerElement[] showContainerElements = getContainerElements();
	for(index = 0; index < showContainerElements.length; index++)
	{
	    display += "\n";
	    display += showContainerElements[index].xmlToWindow();
	} // end for

	display += "\n</" + elementName + ">";

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
	System.out.print(">");

	XmlValueElement[] showValueElements = getValueElements();
	for(index = 0; index < showValueElements.length; index++)
	{
	    System.out.print("\n");
	    showValueElements[index].showXml();
	} // end for

	XmlContainerElement[] showContainerElements = getContainerElements();
	for(index = 0; index < showContainerElements.length; index++)
	{
	    System.out.print("\n");
	    showContainerElements[index].showXml();
	} // end for

	System.out.print("\n</" + elementName + ">");
    } // end function showXml()

} // end class XmlRootElement