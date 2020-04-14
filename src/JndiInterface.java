

/**
 * Title:        XAP (XML / LDAP Integration)
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Ball State University CS498
 * @author John Iles, Russell Scheerer, Raood Talarico
 * @version 1.1b
 */

import java.util.Hashtable;
import java.util.Enumeration;
import javax.naming.*;
import javax.naming.directory.*;
import java.util.Vector;
import java.io.*;
import Message;
import ErrorCode;
import dtdobject.DtdObject;
import xmlobject.XmlObject;
import xap.XmlFile;

public class JndiInterface
{
    /**
     *   This is the only constructor for JndiInterface.
     *   The only function is for it to setup the Hashtable for making a
     *   connection to te LDAP server.
     */
    public JndiInterface()
    {
	/* Create the Hashtable that contains information on how to access LDAP directories? */
	environment = new Hashtable(5, 0.75f);
    }

    /**
     *
     *    this makes a connection to the specified LDAP server.
     *    When the connection is made, the LDAP schema is obtained and
     *    stored within dtd ( a dtdObject ) after calling MakeDTD().
     *
     *  <code>@param</code>  directory
     *    	is the address for the LDAP server that is to be connected to.
     *    	example: "ldap://directory.unc.edu:389"  the port must be specified
     *    	within the address.
     *  <code>@param</code>  dn
     *    	is the basedn that is to be used when accessing/searching the LDAP
     *    	server.
     *    	example: "o=UNC-CH, c=US"  When specifying multiple conditions as in the
     *      	example, there MUST be a space after the comma.  Also, the
     *      	conditions must be listed in lowest heirarchial order first. ie:
     *      	o=UNC-CH   is lower in the heirarchy than     c=US.
     *  <code>@return</code>  Message
     */

    public Message setDirectory(String directory, String dn)
    {
	Message m;
	ldap_directory = directory;
	if(directory.equals(""))
	{
	    m = new Message(ErrorCode.BAD_LOCATION, "You have not entered a Location.");
	    return m;
	}
	if(dn.equals(""))
	{
	    m = new Message(ErrorCode.BAD_DOMAIN, "You have not entered a BASEDN.");
	    return m;
	}
	dtd = new DtdObject();
	try
	{
	    basedn = dn;
	    environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	    environment.put(Context.PROVIDER_URL, directory);

	    context = new InitialDirContext(environment);
	    Message dtdMessage = MakeDTD();
	    if(dtdMessage.getErrorCode() == ErrorCode.INTERNAL_ERROR)
	    {
		m = new Message(ErrorCode.INTERNAL_ERROR, dtdMessage.getErrorMessage());
		return m;
	    }

	}
	catch(NamingException n)
	{
	    /*Start parsing the Exception and create a Message to send back to caller*/

	    String error = n.toString();
	    int i = error.indexOf(": ");
	    String error_exception = error.substring(0,i);

	    if(error_exception.equalsIgnoreCase("javax.naming.NamingException [Root exception is java.net.MalformedURLException"))
	    {
	        m = new Message(ErrorCode.BAD_LOCATION, "The LDAP directory is incorrect.");
	    }
	    else if(error_exception.equalsIgnoreCase("javax.naming.NameNotFoundException"))
	    {
		m = new Message(ErrorCode.BAD_DOMAIN, "The base DN is invalid.");
	    }
	    else if(error_exception.equalsIgnoreCase("javax.naming.CommunicationException"))
	    {
	        m = new Message(ErrorCode.BAD_LOCATION_OR_DOMAIN, "The specified directory is incorrect.");
	    }
	    else
	    {
		m = new Message(ErrorCode.UNKNOWN_ERROR, "Error Unknown.");
	    }
	    return m;
	}
	m = new Message();
	return m;
    }

    /**
     *	This method is to be called when searching the LDAP directory for some specific
     *	attribute.  As the entries are found they are inserted into the private member
     *	xml (an XmlObject).  If any exceptions arise they are thorwn and parsed out into
     *	a Message.
     *   <code>@param</code> search_string
     *	search_string is the value that is passed to the LDAP search so that the entries can
     *	be retrieved.  The form is "sn=smith" or "sn=smith*" or "sn=*mit*" etc.  The wildcards
     *	are used to represent any characters before or after that point respectively.  Only one
     *	attribute can be searched for at a time.
     *   <code>@return</code> Message
     */
    public Message selectQuery(String search_string)
    {
	Message m;
	xml = new XmlObject("LDAP_Root");
	SearchControls constraints = new SearchControls();
	constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
	try
	{
	    NamingEnumeration results = context.search(basedn, search_string, constraints);
	    xml.setXmlDeclarationVersion("1.0");
	    xml.setXmlDeclarationStandalone("yes");
	    xml.setXmlDoctypeSystem("ldap.dtd");
	    int tempcount = 0;
	    /*Retrieve all entries*/
	    while(results != null && results.hasMore())
	    {
		tempcount ++;
		if(tempcount >= 500)
		{
		    m = new Message(ErrorCode.TOO_LARGE, "The result was too large, returning only 499 results. (Try narrowing your search)");
		    return m;
		}

		SearchResult si = (SearchResult)results.next();

		/*Get its name and store it into xml*/

		xml.insertContainerElement("Person", "Name", si.getName());
		xml.moveToContainerElement("Person", "Name", si.getName());

		Attributes attrs = si.getAttributes();
		if(attrs != null)
		{
		    /* Get each attribute */
		    for(NamingEnumeration ae = attrs.getAll(); ae.hasMoreElements();)
		    {
			Attribute attr = (Attribute)ae.next();
			String attrId = attr.getID();

			/* Get each attribute value */
			int count = 0;
			for(Enumeration vals = attr.getAll(); vals.hasMoreElements();)
			{
			    String temp = (String)vals.nextElement();
			    xml.insertValueElement(attrId, temp);
			    //System.out.println(attrId + ": " + vals.nextElement());
			}
		    }
		}
		xml.moveToRoot();
	    }



	}
	catch(Exception e)
	{
	    /*Start parsing the Exception and create a Message to send back to caller*/

	    String err = e.toString();
	    int i = err.indexOf(": ");
	    if(i == -1)
		i = err.length();
	    String exception = err.substring(0,i);

	    if(exception.equalsIgnoreCase("java.lang.NullPointerException"))
	    {
		m = new Message(ErrorCode.NO_CONNECTION, "There has been no connection made to the LDAP server.");
	    }
	    else if(exception.equalsIgnoreCase("javax.naming.directory.InvalidSearchFilterException"))
	    {
		m = new Message(ErrorCode.BAD_SEARCH_FILTER, "The search filter is incorrect.");
	    }
	    else if(exception.equalsIgnoreCase("javax.naming.NameNotFoundException"))
	    {
		m = new Message(ErrorCode.BAD_DOMAIN, "The base DN is not valid.");
	    }
	    else
	    {
		m = new Message(ErrorCode.UNKNOWN_ERROR, "Error Unknown.");
	    }
	    return m;
	}
	m = new Message();
	return m;
    }

    /**
     *  COULD NOT BE TESTED!!!!!!  MAY NOT WORK CORRECTLY!!!!!!
     *	updateQuery is to be used when an XML file is to be inserted into the previously
     *	specified LDAP server.
     *   <code>@param</code> xmlName
     *	xmlName is the file name of the xml file that is to inserted into the LDAP server.
     *   <code>@param</code> managerName
     *  managerName is the manager's user name for the LDAP server.
     *   <code>@param</code> managerPassword
     *  mangerPassword is the manager's password for the LDAP server.
     *   <code>@return</code> Message
     */

    public Message updateQuery(String xmlName, String managerName, String managerPassword)
    {
	XmlObject updateXml = XmlFile.readXmlFile(xmlName);
	Message error = new Message();

	try
	{
	    environment.clear();
	    environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	    environment.put(Context.PROVIDER_URL, ldap_directory);
	    environment.put(Context.SECURITY_AUTHENTICATION, "simple");

	    environment.put(Context.SECURITY_PRINCIPAL, managerName);

	    environment.put(Context.SECURITY_CREDENTIALS, managerPassword);
	    SearchControls ctls = new SearchControls();
	    ctls.setSearchScope(SearchControls.OBJECT_SCOPE);
	    ctls.setReturningAttributes(new String[0]);

	    context = new InitialDirContext(environment);
	    NamingEnumeration searchResults;

	    /* construct the list of modifications to make */
	    Vector allMods = new Vector();
	    String[][] containerElements = updateXml.listContainerElements();
	    //String[][] containerElements = xml.listContainerElements();
	    for(int index = 0; index < containerElements.length; index++)
	    {
	        updateXml.moveToContainerElement(containerElements[index][0], containerElements[index][1], containerElements[index][2]);
		String[][] valueElements = updateXml.listValueElements();

		String checkBase = containerElements[index][2] + ", " + basedn;
		for(int valueIndex = 0; valueIndex < valueElements.length; valueIndex++)
		{
		    Attribute mod = new BasicAttribute(valueElements[valueIndex][0], valueElements[valueIndex][1]);
		    ModificationItem modItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod);
		    searchResults = context.search(checkBase, "objectclass=" + 	valueElements[valueIndex][0], ctls);

		    /* if item already in LDAP go ahead and add */
		    if(searchResults != null && searchResults.hasMoreElements())
		    {
			allMods.add(modItem);
		    }

		    /* if item is not in LDAP, create a new item, store in LDAP, then make changes */
		    else
		    {
			Attribute objClasses = new BasicAttribute("objectclass");
			objClasses.add("top");
			objClasses.add("person");
			objClasses.add("organizationalPerson");
			objClasses.add("inetOrgPerson");

			Attribute cn = new BasicAttribute("cn", containerElements[index][2].substring(3));

			Attributes entry = new BasicAttributes();
			entry.put(objClasses);
			entry.put(cn);
			context.createSubcontext(checkBase, entry);
			mod = new BasicAttribute(valueElements[valueIndex][0], valueElements[valueIndex][1]);
			modItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod);
			allMods.add(modItem);
		    }
		}

		/* create a ModificationItem array and copy the Vector into it */
		ModificationItem[] list = new ModificationItem[allMods.size()];
		for(int i = 0; i < list.length; i++)
		{
		    list[i] = (ModificationItem)allMods.get(i);
		}
		/* make the change */
		context.modifyAttributes(basedn, list);
	    }

	}
	catch(Exception e)
	{
	    error = new Message(ErrorCode.INTERNAL_ERROR, "Problem during Update: " + e.getMessage());
	    return error;
	}
	error = new Message(ErrorCode.VALID_EXECUTION, "Valid Execution");
	return error;
    }

    /**
     *	<code>@return</code> a DtdObject
     */
    public DtdObject getDtd()
    {
	return dtd;
    }

    /**
     *	<code>@return</code> an XmlObject
     */
    public XmlObject getXml()
    {
	return xml;
    }

    /**
     *	MakeDTD is a private method that retrieves the LDAP schema from the specified
     *	context and finds all AttibuteDefinitions and all ClassDefinitions.  It stores each
     *	into its own Vector respectively.  Then GetAllAttributes is called for each vector
     *	so that the objects can be stored within dtd (a DtdObject).
     */
    private Message MakeDTD()
    {
	Vector attributeSchemaList, classSchemaList;
	attributeSchemaList = new Vector();
	classSchemaList = new Vector();
	try
	{
	    //get LDAP schema tree
	    schema = context.getSchema("");

	    DirContext attrdef = (DirContext)schema.lookup("AttributeDefinition");
	    DirContext classdef = (DirContext)schema.lookup("ClassDefinition");

	    NamingEnumeration attrNE = attrdef.listBindings("");

	    /*Add all Attributes to the attrdef Vector*/
	    while( attrNE.hasMore() )
	    {
		Binding attrbinding = (Binding)attrNE.next();
		String attrbind = attrbinding.toString();
		int i = attrbind.indexOf(": ");
		if(i == -1)
		    i = attrbind.length();
		String attrID = attrbind.substring(0,i);
		attributeSchemaList.add(attrID);
	    }

	    NamingEnumeration classNE = classdef.listBindings("");

	    /*Add all Classes to the classdef Vector*/
	    while( classNE.hasMore() )
	    {
		Binding classbinding = (Binding)classNE.next();
		String classbind = classbinding.toString();
		int i = classbind.indexOf(": ");
		if(i == -1)
		    i = classbind.length();
		String classID = classbind.substring(0,i);
		classSchemaList.add(classID);
	    }
	    Message valueMessage, containerMessage;
	    valueMessage = GetObjectAttributes(attributeSchemaList, DtdObject.VALUE_ELEMENT);

	    if(valueMessage.getErrorCode() == ErrorCode.INTERNAL_ERROR)
		throw new NamingException(valueMessage.getErrorMessage());

	    containerMessage = GetObjectAttributes(classSchemaList, DtdObject.CONTAINER_ELEMENT);

	    if(containerMessage.getErrorCode() == ErrorCode.INTERNAL_ERROR)
		throw new NamingException(containerMessage.getErrorMessage());
	}
	catch(NamingException n)
	{
	    Message error = new Message(ErrorCode.INTERNAL_ERROR, n.getExplanation());
	    return error;
	}
	return new Message();
    }

    /**
     *	GetObjectAttributes decides whether or not the vector of Objects is of containers or
     *	values.  If it is of Values then dtd.setName is called sending DtdObject.VALUE so that
     *	the DTD represents the Attributes as Elements containing just plain data.  If it is of
     *	containers then dtd.setName is called but this time it sends DtdObject.CONTAINER.
     *	After this then AddDtdElement is called.
     *   <code>@param</code> Vector list
     *	list is just a Vector that contains a list of LDAP Attributes or LDAP Classes.
     *	the form should be of Strings.
     *   <code>@param</code> String type
     *	type is used to differentiate between VALUES or CONTAINERS.
     *   <code>@return</code> Message
     */
    private Message GetObjectAttributes(Vector list, int type)
    {
        try
        {
	    int i;
	    Message addDtd;
	    DirContext tempSchema;
	    String objectName;


	    if(type == DtdObject.VALUE_ELEMENT)
	    {
		for(i = 0; i < list.size(); i++)
		{
		    objectName = (String)list.get(i);

		    dtd.insertElement(objectName, DtdObject.VALUE_ELEMENT);
		    dtd.moveToElement(objectName);
		    //   //System.out.println("Values: " + objectName);
		    objectName = "AttributeDefinition/" + objectName;
		    tempSchema = (DirContext)schema.lookup(objectName);
		    addDtd = AddDtdElement(tempSchema, type);
		    if(addDtd.getErrorCode() == ErrorCode.INTERNAL_ERROR)
			throw new Exception(addDtd.getErrorMessage());
		}
	    }

	    if(type == DtdObject.CONTAINER_ELEMENT)
	    {
		for(i = 0; i < list.size(); i++)
		{
		    objectName = (String)list.get(i);

		    dtd.insertElement(objectName, DtdObject.CONTAINER_ELEMENT);
		    dtd.moveToElement(objectName);
		    //   //System.out.println("CONTAINER:  " + objectName);

		    objectName = "ClassDefinition/" + objectName;
		    tempSchema = (DirContext)schema.lookup(objectName);
		    addDtd = AddDtdElement(tempSchema, type);
		    if(addDtd.getErrorCode() == ErrorCode.INTERNAL_ERROR)
			throw new Exception(addDtd.getErrorMessage());
		}
	    }
	}
	catch(Exception e)
	{
	    Message error = new Message(ErrorCode.INTERNAL_ERROR, e.getMessage());
	    return error;
	}
	return new Message();
    }

    /**
     *	AddDtdElement is used to add the "MUST" and "MAY" clauses in the LDAP Class
     *	Definitions to dtd (a DtdObject).  It simply just parses all attributes in the DirContext
     *	and finds out whether it is a "MUST" or "MAY" type.
     *   <code>@param</code> DirContext item
     *	item is a DirContext that containsthe result of a DirContext.lookup() command.
     *   <code>@return</code> Message
     */
    private Message AddDtdElement(DirContext item, int type)
    {
	try
	{
	    Attributes attrs = item.getAttributes("");

	    for(NamingEnumeration all = attrs.getAll(); all.hasMore();)
	    {
		Attribute attr = (Attribute)all.next();
		String ID = attr.getID();

		if(ID.equals("MAY"))
		{
		    for(NamingEnumeration values = attr.getAll(); values.hasMore();)
		    {
			String value = (String)values.next();
			//   //System.out.println("MAY:  " + value);
			dtd.addSubgroup(value, DtdObject.MAY);
		    }
		}
		if(ID.equals("MUST"))
		{
		    for(NamingEnumeration values = attr.getAll(); values.hasMore();)
		    {
			String value = (String)values.next();
			//   //System.out.println("MUST:  " + value);
			dtd.addSubgroup(value, DtdObject.MUST);
		    }
		}
	    }
	}
	catch(Exception e)
	{
	    Message error = new Message(ErrorCode.INTERNAL_ERROR, e.getMessage());
	    return error;
	}
	return new Message();
    }

    private DirContext schema;
    private XmlObject xml;
    private DtdObject dtd;
    private String ldap_directory;
    private String basedn;
    private Hashtable environment;
    private DirContext context;
}
