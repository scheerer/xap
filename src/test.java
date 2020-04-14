package test;

/**
 * Title:        XAP (XML / LDAP Integration)
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Ball State University CS498
 * @author John Iles, Russell Scheerer, Raood Talarico
 * @version 1.0
 */

import dtdobject.DtdObject;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.naming.*;
import javax.naming.directory.*;
import java.io.*;
import xap.XAP1;
import JndiInterface;
import Message;
import ErrorCode;



public class test {

  public test() {

  }
  public static void main(String[] args) {
      XAP1 XAP11 = new XAP1();
    XAP11.show();
    XAP11.pack();
    XAP11.repaint();
    test test1 = new test();
  /*
    JndiInterface j = new JndiInterface();


    Message m, n;
//    m = j.setDirectory("ldap://siu-ds.siu.edu:389", "o=SIU, c=US");
    m = j.setDirectory("ldap://directory.unc.edu:389", "ou=AIS, o=UNC-CH, c=US");
    System.out.println(m.getErrorMessage());

    n = j.selectQuery("sn=smith");
    //System.out.println(n.getErrorMessage());
*/
  }
}