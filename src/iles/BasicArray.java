package iles;

/**
* Title:        XAP (XML / LDAP Integration)
* Description:
* Copyright:    Copyright (c) 2001
* Company:      Ball State University CS498
* @author John Iles, Russell Scheerer, Raood Talarico
* @version 1.1b
*/

public class BasicArray
{
    public  Object[]   objects;
    private int       growthFactor;

    /**
    *    BasicArray(int size)
    *        Primary constructor for BasicArray. Takes size and sets as growth
    *        factor.
    *    #parameter: size
    *        Integer initial size of the array.
    */

    public BasicArray(int size)
    {
	objects = new Object[size];
	growthFactor = size;
    } // end constructor

    /**
     *  setGrowthFactor(int size)
     *      Sets the growth factor for an overflwing BasicArray.
     *  #parameter: size
     *          Integer size of the growth factor.
     *  #returns:   void
     */

    public void setGrowthFactor(int size)
    {
	growthFactor = size;
    } // end function setGrowthFactor()

    /**
     *  objectCount()
     *      Returns the number of non-null objects in the array.
     *  #returns:   int
     */

    public int objectCount()
    {
	int count = 0;
	int index;
	for(index = 0; index < objects.length; index++)
	{
	    if(objects[index] != null)
	    {
		count++;
	    } // end if
	} // end for
	return count;
    } // end Function objectCount()

    /**
     *  growArray(int size)
     *      Grow the aray by an integer amount size.
     *  #parameter: size
     *          Integer size of the growingness.
     *  #returns:   void
     */

    public void growArray(int size)
    {
	Object[] replace = new Object[objects.length + size];
	for( int i = 0; i < objects.length; i++)
	{
	    replace[i] = objects[i];
	}
	objects = new Object[replace.length];
	objects = replace;
    } // end Function growArray()

    /**
     *  remove(int index)
     *      Remove the object at the specified index.
     *  #parameter: index
     *          Integer index into the object array.
     *  #returns:   Object
     */

    public Object remove(int index)
    {
	if(index >= objects.length)
	{
	    return null;
	}

	Object temp = objects[index];

	Object[] replace = new Object[objects.length];
	for(int i = 0; i < objects.length - 1; i++)
	{
	    if(i < index)
	    {
		replace[i] = objects[i];
	    }
	    else
	    {
		replace[i] = objects[i + 1];
	    }
	}

	objects = new Object[replace.length];
	objects = replace;
	return temp;
    } // end Function remove(Object)

    /**
     *  insert(Object newObject)
     *      Insert an object to the end of the array
     *  #parameter: newObject
     *          New object to insert
     *  #returns:   void
     */

    public void insert(Object newObject)
    {
	int index = 0;
	while( (index < objects.length) && (objects[index] != null) )
	{
	    index++;
	} // end for

	if(index == objects.length)
	{
	    growArray(growthFactor);
	} // end if

	objects[index] = newObject;
    } // end Function insert()

} // end class BasicArray