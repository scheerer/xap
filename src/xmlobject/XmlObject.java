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
import xmlobject.XmlRootElement;
import xmlobject.XmlAttribute;
import xmlobject.XmlContainerElement;
import xmlobject.XmlValueElement;

public class XmlObject
{
    public static final int VERSION       = 0;
    public static final int STANDALONE    = 1;
    public static final int ENCODING      = 2;
    public static final int ROOT_ELEMENT  = 0;
    public static final int DOCTYPE       = 1;
    public static final int DTD_NAME      = 2;
    public static final int DTD_URI       = 3;

    private String[]            xmlDeclaration;
    private String[]            xmlDoctype;
    private XmlRootElement      xmlRootElement;
    private XmlContainerElement currentElement;

    /**
     *  XmlObject()
     *  This is the only constructor for XmlObject. The constructor sets up
     *  the XML declaration, the root element, and DOCTYPE.
     */

    public XmlObject(String rootElement)
    {
	xmlDeclaration  = new String[3];
	xmlDoctype      = new String[4];

	int index;
	for(index = 0; index < 4; index++)
	{
	    if(index < 3)
	    {
		xmlDeclaration[index] = "";
	    } // end if
	    xmlDoctype[index] = "";
	} // end for

	setXmlRootElement(rootElement);
	currentElement = xmlRootElement;

    } // end constructor

    /**
     *  setXmlDeclarationVersion(String version)
     *      Sets the version parameter within the XML declaration.
     *  #parameter: version
     *      String representation specifying the version of XML used in this XML
     *      document.
     *  #returns:   void
     */

    public void setXmlDeclarationVersion(String version)
    {
        xmlDeclaration[VERSION] = version;
    } // end function setXmlDeclarationVersion()

    /**
     *  setXmlDeclarationEncoding(String encoding)
     *      Sets the encoding scheme within the XML declaration.
     *  #parameter: encoding
     *      String representation specifying the encoding scheme used in this XML
     *      document.
     *  #returns:   void
     */

    public void setXmlDeclarationEncoding(String encoding)
    {
        xmlDeclaration[ENCODING] = encoding;
    } // end function setXmlDeclarationEncoding()

    /**
     *  setXmlDeclarationStandalone(String standalone)
     *      Sets the standalone parameter within the XML declaration.
     *  #parameter: standalone
     *      String representation specifying the independence of the XML document
     *      from some DTD object.
     *  #returns:   void
     */

    public void setXmlDeclarationStandalone(String standalone)
    {
        xmlDeclaration[STANDALONE] = standalone;
    } // end function setXmlDecalarationStandalone()

    /**
     *  setXmlDoctypePublic(String dtdName, String dtdURI)
     *      Sets the DOCTYPE for a PUBLIC DTD file.
     *  #parameter: dtdName
     *      String representation of the name given to the public DTD file.
     *  #parameter: dtdURI
     *      String giving the path to the DTD file (aka. file name).
     *  #returns:   void
     */

    public void setXmlDoctypePublic(    String dtdName,
				        String dtdURI)
    {
	xmlDoctype[DOCTYPE]       = "PUBLIC";
	xmlDoctype[DTD_NAME]      = dtdName;
	xmlDoctype[DTD_URI]       = dtdURI;
    } // end function setXmlDoctypePublic

    /**
     *  setXmlDoctypeSystem(String dtdURI)
     *      Sets the DOCTYPE for a PUBLIC DTD file.
     *  #parameter: dtdURI
     *      String giving the path to the DTD file (aka. file name).
     *  #returns:   void
     */

    public void setXmlDoctypeSystem(  String dtdURI)
    {
	xmlDoctype[DOCTYPE]       = "SYSTEM";
	xmlDoctype[DTD_NAME]      = "";
	xmlDoctype[DTD_URI]       = dtdURI;
    } // end function setXmlDoctypePublic

    /**
     *  setXmlRootElement(String rootElement)
     *      Sets the root element name for the XML file.
     *  #parameter: rootElement
     *      String name for the root element.
     *  #returns:   void
     */

    public void setXmlRootElement(String rootElement)
    {
	xmlDoctype[ROOT_ELEMENT]  = rootElement;
	xmlRootElement            = new XmlRootElement(rootElement);
    } // end function setXmlRootElement

    /**
     *  insertValueElement(String elementName, String elementValue)
     *      Insert a value element into the current ContainerElement.
     *  #parameter: elementName
     *      String name for the new value element.
     *  #parameter: elementValue
     *      String value for the new value element.
     *  #returns:   void
     */

    public void insertValueElement(String elementName, String elementValue)
    {
	currentElement.insertValueElement(elementName, elementValue);
    } // end function insertValueElement()

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

    public void insertContainerElement( String elementName,
					String attributeName,
					String attributeValue)
    {
	currentElement.insertContainerElement(  elementName,
					        attributeName,
					        attributeValue);
    } // end function insertContainerElement()

    /**
     *  insertAttribute( String attributeName, String attriubteValue)
     *      Insert an attribute into the current ContainerElement.
     *  #parameter: attributeName
     *      String attribute name for the new container element.
     *  #parameter: attributeValue
     *      String attribute value for the new container element.
     *  #returns:   void
     */

    public void insertAttribute(String attributeName, String attributeValue)
    {
        currentElement.insertAttribute(attributeName, attributeValue);
    } // end function insertAttribute()

    /**
     *  removeValueElement(String elementName, String elementValue)
     *      remove a value element from the current ContainerElement.
     *  #parameter: elementName
     *      String element name to identify the value element to be removed.
     *  #parameter: elementValue
     *      String element value to authenticate the removal of a value element.
     *  #returns:   void
     */

    public void removeValueElement(String elementName, String elementValue)
    {
        currentElement.removeValueElement(elementName, elementValue);
    } // end function removeValueElement()

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
     *  #returns:   void
     */

    public void removeContainerElement( String elementName,
					String attributeName,
					String attributeValue)
    {
        currentElement.removeContainerElement(  elementName,
						attributeName,
						attributeValue);
    } // end function removeContainerElement()

    /**
     *  removeAttribute(String attributeName, String attributeValue)
     *      Remove an attribute current ContainerElement.
     *  #parameter: attributeName
     *      String attribute name to authenticate the removal of a value element.
     *  #parameter: attributeValue
     *      String attribute value to authenticate the removal of a value element.
     *  #returns:   void
     */

    public void removeAttribute(String attributeName)
    {
        currentElement.removeAttribute(attributeName);
    } // end function removeAttribute()

    /**
     *  moveToRoot()
     *      Move the current container element to the root element.
     *  #returns:   void
     */

    public void moveToRoot()
    {
        currentElement = xmlRootElement;
    } // end function moveToRoot()

    /**
     *  moveToContainerElement( String elementName,
     *				String attributeName,
     *				String attributeValue)
     *      Move to a container element from the current ContainerElement.
     *  #parameter: elementName
     *      String element name to identify the value element to be moved to.
     *  #parameter: attributeName
     *      String attribute name to authenticate the move.
     *  #parameter: attributeValue
     *      String attribute value to authenticate the move.
     *  #returns:   void
     */

    public void moveToContainerElement( String elementName,
					String attributeName,
					String attributeValue)
    {
	// Ensure element is in the list.
	XmlContainerElement[] elements = currentElement.getContainerElements();
	int index     = 0;
	int moveIndex = -1;
	while( (index < elements.length) && (moveIndex < 0) )
	{
	    if(elements[index].getName() == elementName)
	    {
		XmlAttribute[] attributes = elements[index].getAttributes();
		if( (attributes[currentElement.IDENTIFIER].getName() == attributeName) &&
		    (attributes[currentElement.IDENTIFIER].getValue() == attributeValue) )
		{
		    moveIndex = index;
		} // end if
	    } // end if
	    index++;
	} // end while

	if(moveIndex < 0)
	{
	    return;
	} // end if

	// Move to new element.
	currentElement = elements[moveIndex];
    } // end function moveToContainerElement()

    /**
     *  listValueElements()
     *      Return a 2 column matrix of all the value elements contained within
     *      the current container element.
     *  #returns:   String[][]
     */

    public String[][] listValueElements()
    {
        XmlValueElement[] elements = currentElement.getValueElements();
        int index;
	String[][] valueElements = new String[elements.length][2];

	for(index = 0; index < elements.length; index++)
	{
	    valueElements[index][0] = elements[index].getName();
	    valueElements[index][1] = elements[index].getValue();
	}

	return valueElements;
    } // end function listValueElements()

    /**
     *  listContainerElements()
     *      Return a 3 column matrix of all the container elements contained within
     *      the current container element.
     *  #returns:   String[][]
     */

    public String[][] listContainerElements()
    {
	XmlContainerElement[] elements = currentElement.getContainerElements();
	int index;
	String[][] containerElements = new String[elements.length][3];

	for(index = 0; index < elements.length; index++)
	{
	    containerElements[index][0] = elements[index].getName();
	    containerElements[index][1] = elements[index].getIdentifierName();
	    containerElements[index][2] = elements[index].getIdentifierValue();
	    //System.out.println(containerElements[index][0] + " " + containerElements[index][1] + "=\"" + containerElements[index][2] + "\"");
	}

	return containerElements;
    } // end function listContainerElements()

    /**
     *  listAttributes()
     *      Return a 2 column matrix of all the attributes contained within
     *      the current container element.
     *  #returns:   String[][]
     */

    public String[][] listAttributes()
    {
        XmlAttribute[] attributes = currentElement.getAttributes();
        int index;
	String[][] attributesList = new String[attributes.length][2];

	for(index = 0; index < attributes.length; index++)
	{
	    attributesList[index][0] = attributes[index].getName();
	    attributesList[index][1] = attributes[index].getValue();
	}

	return attributesList;
    } // end function listAttributes()

    /**
     *  xmlToWindow()
     *    Convert a virtual XML specfication given by this object into a String
     *    that can be printed in a window, file, or other such output gizmo.
     *  #returns:  String
     */

    public String xmlToWindow()
    {
	String display = "";
	display += "<?xml version=\"" + xmlDeclaration[VERSION] + "\"";
	if(xmlDeclaration[STANDALONE] != "")
	{
	    display += " standalone=\"" + xmlDeclaration[STANDALONE] + "\"";
	} // end if
	if(xmlDeclaration[ENCODING] != "")
	{
	    display += " encoding=\"" + xmlDeclaration[ENCODING] + "\"";
	} // end if
	display += "?>\n";

	if(xmlDeclaration[STANDALONE] == "no")
	{
	    display += "<!DOCTYPE <" + xmlDoctype[ROOT_ELEMENT] + ">";
	    display += " " + xmlDoctype[DOCTYPE];
	    if(xmlDoctype[DOCTYPE] == "SYSTEM")
	    {
		display += " \"" + xmlDoctype[DTD_URI] + "\">";
	    } // end if
	    else if(xmlDoctype[DOCTYPE] == "PUBLIC")
	    {
		display += " \"" + xmlDoctype[DTD_NAME] + "\"";
		display += " \"" + xmlDoctype[DTD_URI] + "\">";
	    } // end if
	} // end if
	display += "\n";
	display += xmlRootElement.xmlToWindow();
	return display;
    } // end function xmlToWindow

    /**
     *  showXml()
     *    Print a virtual XML specfication given by this object into a String
     *    that can be printed in a window, file, or other such output gizmo.
     *  #returns:  String
     */

    public void showXml()
    {
	System.out.print("<?xml version=\"" + xmlDeclaration[VERSION] + "\"");
	if(xmlDeclaration[STANDALONE] != "")
	{
	    System.out.print(" standalone=\"" + xmlDeclaration[STANDALONE] + "\"");
	} // end if
	if(xmlDeclaration[ENCODING] != "")
	{
	    System.out.print(" encoding=\"" + xmlDeclaration[ENCODING] + "\"");
	} // end if
	System.out.print("?>\n");

	if(xmlDeclaration[STANDALONE] == "no")
	{
	    System.out.print("<!DOCTYPE <" + xmlDoctype[ROOT_ELEMENT] + ">");
	    System.out.print(" " + xmlDoctype[DOCTYPE]);
	    if(xmlDoctype[DOCTYPE] == "SYSTEM")
	    {
		System.out.print(" \"" + xmlDoctype[DTD_URI] + "\">");
	    } // end if
	    else if(xmlDoctype[DOCTYPE] == "PUBLIC")
	    {
		System.out.print(" \"" + xmlDoctype[DTD_NAME] + "\"");
	        System.out.print(" \"" + xmlDoctype[DTD_URI] + "\">");
	    } // end if
        } // end if
	System.out.print("\n");
	xmlRootElement.showXml();
    } // end function showXml()

} // end class XmlObject
