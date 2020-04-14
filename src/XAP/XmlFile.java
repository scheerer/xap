package xap;

/**
 * Title:        XAP (XML / LDAP Integration)
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Ball State University CS498
 * @author John Iles, Russell Scheerer, Raood Talarico
 * @version 1.1
 */

import xmlobject.XmlObject;
import dtdobject.DtdObject;
import java.io.*;
import iles.BasicArray;

public class XmlFile
{

    /**
     *  writeXmlFile(XmlObject xml, String fileName)
     *    Given an XmlObject and a file name, this function records the contents
     *    of the XmlObject as a well formed XML file useable by outside applications.
     *
     *  #parameter:     xml
     *    	The virtual representation of the XML file.
     *  #parameter:     fileName
     *    	The name of the XML file to be created.
     *  #returns:       void
     */

    public static void writeXmlFile(XmlObject xml, String fileName)
    {
	try
	{
	    PrintWriter xmlFile = new PrintWriter(new FileWriter(fileName));
	    xmlFile.print(xml.xmlToWindow());
	    xmlFile.close();
	} // end try
	catch(IOException e)
	{
	    System.out.println(e);
	} // end catch
    } // end function writeXmlFile()

    /**
     *  readXmlFile(String fileName)
     *    Given an XmlObject and a file name, this function records the contents
     *    of the XmlObject as a well formed XML file useable by outside applications.
     *
     *  #parameter:     fileName
     *    	The name of the XML file to be loaded into an XmlObject.
     *  #returns:       XmlObject
     */

    public static XmlObject readXmlFile(String fileName)
    {
	try
	{
	    FileReader  xmlFile     = new FileReader(fileName);
	    int         input       = xmlFile.read();
	    String      text        = "";
	    BasicArray  parsedText  = new BasicArray(30);

	    while(input != -1)
	    {
		if( (input != '\n') && (input != 13) )
		{
		    text += (char)input;
		    if(input == '>')
		    {
			parsedText.insert(text);
			text = "";
		    } // end if
		} // end if
		input = xmlFile.read();
	    } // end while

	    int parsingIndex        = 0;
	    boolean done            = false;
	    String  rootElement     = "";
	    text = (String)parsedText.objects[1];
	    while( (!done) && (parsingIndex < text.length()) )
	    {
		switch( text.charAt(parsingIndex) )
		{
		    case '<':
			break;
		    case ' ':
		    case '>':
			done = true;
			break;
		    default:
			rootElement += text.charAt(parsingIndex);
		} // end switch
		parsingIndex++;
	    } // end while


	    int         counter     = 0;
	    int         index;
	    String      nextLine;
   	    XmlObject   xml = new XmlObject(rootElement);
	    xml.setXmlDeclarationVersion("1.0");
	    xml.setXmlDeclarationStandalone("yes");
	    for(index = 2; index < parsedText.objectCount() - 1; index++)
	    {
		text = (String)parsedText.objects[index];
		switch( text.charAt(0) )
		{
		    case '<':
			parsingIndex    = 0;
			done            = false;

			if( text.charAt(1) == '/')
			{
			    // Note that this wouldn't work in a situation where
			    // the elements are nested at any depth
			    xml.moveToRoot();
			    break;
			} // end if
			// Parse out element name
			parsingIndex            = 0;
			done                    = false;
			String  elementName     = "";
			while( (!done) && (parsingIndex < text.length()) )
			{
			    switch( text.charAt(parsingIndex) )
			    {
				case '<':
				    break;
				case ' ':
				case '>':
				    done = true;
				    break;
				default:
				    elementName += text.charAt(parsingIndex);
			    } // end switch
			    parsingIndex++;
			} // end while

			nextLine = (String)parsedText.objects[index + 1];
			if( nextLine.startsWith("<") )
			{
			    done                    = false;
			    String attributeName    = "";
			    while( (!done) && (parsingIndex < text.length()) )
			    {
				switch( text.charAt(parsingIndex) )
				{
				    case '<':
					break;
				    case ' ':
				    case '=':
				    case '>':
					done = true;
					break;
				    default:
					attributeName += text.charAt(parsingIndex);
				} // end switch
				parsingIndex++;
			    } // end while

			    done = false;
			    while( (!done) && (parsingIndex < text.length()) )
			    {
				if(text.charAt(parsingIndex) == '"')
				    done = true;
				parsingIndex++;
			    } // end while

			    done                    = false;
			    String attributeValue   = "";
			    while( (!done) && (parsingIndex < text.length()) )
			    {
				switch( text.charAt(parsingIndex) )
				{
				    case '"':
				    case '>':
					done = true;
					break;
				    default:
					attributeValue += text.charAt(parsingIndex);
				} // end switch
				parsingIndex++;
			    } // end while
			    xml.insertContainerElement(elementName, attributeName, attributeValue);
			    xml.moveToContainerElement(elementName, attributeName, attributeValue);
			} // end if
			else
			{
			    parsingIndex = 0;
			    String elementValue     = "";
			    nextLine = (String)parsedText.objects[index + 1];
			    done                    = false;
			    while( (!done) && (parsingIndex < nextLine.length()) )
			    {
				switch( nextLine.charAt(parsingIndex) )
				{
				    case '<':
					done = true;
					break;
				    default:
					elementValue += nextLine.charAt(parsingIndex);
				} // end switch
				parsingIndex++;
			    } // end while
			    xml.insertValueElement(elementName, elementValue);
			} // end else

//			Read in the rest of teh attributes

			break;
		    default:
		} // end switch
	    } // end for
	    return xml;
	} // end try
	catch(IOException e)
	{
	    System.out.println(e);
	} // end catch

	return null;
    } // end function readXmlFile()

    /**
     *  writeDtdFile(DtdObject dtd, String fileName)
     *    Given a DtdObject and a file name, this function records the contents
     *    of the DtdObject as a well formed DTD file useable by outside applications.
     *
     *  #parameter:     dtd
     *    	the virtual representation of the DTD file.
     *  #parameter:     fileName
     *    	the name of the DTD file to be created.
     *  #returns:       void
     */

    public static void writeDtdFile(DtdObject dtd, String fileName)
    {
	try
	{
	    PrintWriter dtdFile = new PrintWriter(new FileWriter(fileName));
	    dtdFile.print(dtd.dtdToText());
	    dtdFile.close();
	} // end try
	catch(IOException e)
	{
	    System.out.println(e);
	} // end catch
    } // end function writeDtdFile()
}
