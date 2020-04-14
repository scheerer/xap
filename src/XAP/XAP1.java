package xap;

import ErrorCode;
import JndiInterface;
import Message;
import xmlobject.XmlObject;
import dtdobject.DtdObject;

import javax.swing.event.*;
import java.beans.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.border.*;

/**
 * Title:        XAP (XML / LDAP Integration)
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Ball State University CS498
 * @author John Iles, Russell Scheerer, Raood Talarico
 * @version 1.1b
 */

public class XAP1 extends JFrame
{
    XmlObject xml;
    DtdObject dtd;
    JndiInterface j = new JndiInterface();
    String xmlFileName;
  //Designing GUI
  ButtonGroup ImportExport = new ButtonGroup();
  boolean named;
  String name;
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  ButtonGroup view = new ButtonGroup();
  String[] select ={"Import LDAP into XML", "Export XML into LDAP"};
  BorderLayout borderLayout2 = new BorderLayout();
    JPanel jPanel7 = new JPanel();
    JPanel exportpane = new JPanel();
    JPanel queryPanel = new JPanel();
    JPanel updatePanel = new JPanel();
    JPanel savePanel = new JPanel();
    JPanel importpane = new JPanel();
    CardLayout card = new CardLayout();
    CardLayout cardSAVE = new CardLayout();
    JPanel cardpane = new JPanel();
    JPanel viewPanel = new JPanel();
    FlowLayout flowLayout2 = new FlowLayout();
    FlowLayout flowLayout1 = new FlowLayout();
    JButton saveXML = new JButton();
    JTextArea xapDisplay = new JTextArea();
    JRadioButton Export = new JRadioButton();
    JPanel radioPanel = new JPanel();
    JLabel queryLabel = new JLabel();
    JPanel panelsPanel = new JPanel();
    JPanel viewRadio = new JPanel();
    JRadioButton Import = new JRadioButton();
    JButton updateLDAP = new JButton();
    JPanel browse = new JPanel();
    JPanel cardPANE = new JPanel();
    JRadioButton viewXML = new JRadioButton();
    JPanel jPanel4 = new JPanel();
    JRadioButton viewDTD = new JRadioButton();
    BorderLayout borderLayout9 = new BorderLayout();
    BorderLayout borderLayout7 = new BorderLayout();
    JButton queryButton = new JButton();
    BorderLayout borderLayout4 = new BorderLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    BorderLayout borderLayout1 = new BorderLayout();
    JScrollPane jScrollPane1 = new JScrollPane(xapDisplay);
    JPanel workpane = new JPanel();
    JTextField qyeryText = new JTextField();
    JPanel setDirPAnel = new JPanel();
    JTextField baseText = new JTextField();
    JLabel baseDN = new JLabel();
    JLabel SetDirLabel = new JLabel();
    JTextField dirText = new JTextField();
    JPanel jPanel6 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel1 = new JPanel();
    BorderLayout borderLayout6 = new BorderLayout();
    BorderLayout borderLayout5 = new BorderLayout();
    BorderLayout borderLayout12 = new BorderLayout();
    BorderLayout borderLayout13 = new BorderLayout();
    JPanel browsefile = new JPanel();
    JPasswordField password = new JPasswordField();
    FlowLayout flowLayout4 = new FlowLayout();
    BorderLayout borderLayout8 = new BorderLayout();
    JLabel borwseLabel = new JLabel();
    JTextField browseXML = new JTextField();
    JButton browseButton1 = new JButton();
    JPanel jPanel8 = new JPanel();
    JTextField userName = new JTextField();
    JPanel viewdtdpanel = new JPanel();
    JButton saveDTD = new JButton();
    FlowLayout flowLayout3 = new FlowLayout();
    JPanel jPanel3 = new JPanel();
    FlowLayout flowLayout5 = new FlowLayout();

//  JTextArea viewText = new JTextArea()
//  JScrollPane jScrollPane1 = new JScrollPane(viewText);
  public XAP1()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
/*
 *  jbInit() Method is designed to set up all the property for GUI
 */
  private void jbInit() throws Exception
  {
    this.setTitle("XAP XML/LDAP Integrator");
//    addWindowListener(new BasicWindowMonitor());
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.getContentPane().setLayout(borderLayout2);

    this.setDefaultCloseOperation(3);
        exportpane.setLayout(borderLayout9);
        exportpane.setMinimumSize(new Dimension(30, 50));
        exportpane.setPreferredSize(new Dimension(200, 54));
        queryPanel.setLayout(borderLayout4);
        queryPanel.setMinimumSize(new Dimension(358, 74));
        queryPanel.setPreferredSize(new Dimension(358, 30));
        importpane.setMinimumSize(new Dimension(30, 130));
        importpane.setPreferredSize(new Dimension(200, 130));
        importpane.setLayout(borderLayout13);
        cardpane.setMinimumSize(new Dimension(30, 100));
        cardpane.setPreferredSize(new Dimension(200, 200));
        cardpane.setLayout(card);
        viewPanel.setLayout(borderLayout1);
        saveXML.setToolTipText("");
        saveXML.setMnemonic('S');
        saveXML.setText("SAVE XML");
        saveXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
        saveXML_actionPerformed(e);
        }
        });
        xapDisplay.setNextFocusableComponent(saveXML);
        xapDisplay.setBorder(null);
        xapDisplay.setMinimumSize(new Dimension(20, 17));
        xapDisplay.setEditable(false);
        Export.setSelected(true);
        Export.setText("Export LDAP into XML");
        Export.setMnemonic('E');
        Export.setHorizontalAlignment(SwingConstants.LEADING);
        Export.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(ActionEvent e){
         Export_actionPerformed(e);
        }
        });
        radioPanel.setBorder(BorderFactory.createEtchedBorder());
        radioPanel.setMinimumSize(new Dimension(50, 35));
        radioPanel.setPreferredSize(new Dimension(50, 35));
        queryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        queryLabel.setText("Please Inquire the LDAP Directory to create an XML representaion");
        panelsPanel.setLayout(borderLayout7);
        panelsPanel.setMinimumSize(new Dimension(100, 65));
        panelsPanel.setPreferredSize(new Dimension(100, 107));
        viewRadio.setLayout(flowLayout1);
        viewRadio.setMinimumSize(new Dimension(175, 20));
        viewRadio.setOpaque(false);
        viewRadio.setPreferredSize(new Dimension(171, 30));
        Import.setText("Import XML into LDAP");
        Import.setMnemonic('I');
        Import.setHorizontalAlignment(SwingConstants.LEADING);
        Import.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e)
        {
            Import_actionPerformed(e);
        }
        });
        updateLDAP.setText("Update LDAP");
        updateLDAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateLDAP_actionPerformed(e);
            }
        });
        browse.setPreferredSize(new Dimension(298, 110));
        browse.setLayout(borderLayout8);
        cardPANE.setLayout(cardSAVE);
        cardPANE.setBorder(BorderFactory.createEtchedBorder());
        viewXML.setMnemonic('X');
        viewXML.setNextFocusableComponent(viewDTD);
        viewXML.setText("View XML");
        viewXML.setToolTipText("");
        jPanel4.setMinimumSize(new Dimension(194, 20));
        jPanel4.setPreferredSize(new Dimension(100, 30));
        jPanel4.setLayout(flowLayout2);
        viewDTD.setNextFocusableComponent(xapDisplay);
        viewDTD.setText("View DTD");
        viewDTD.setMnemonic('D');
        queryButton.setMinimumSize(new Dimension(79, 22));
        queryButton.setPreferredSize(new Dimension(79, 22));
        queryButton.setActionCommand("QUERY");
        queryButton.setMnemonic('Q');
        queryButton.setText("QUERY!");
        queryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
            queryButton_actionPerformed(e);
          }
        });
        jScrollPane1.setAutoscrolls(true);
//        jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
        jScrollPane1.setPreferredSize(new Dimension(300, 200));
        workpane.setLayout(borderLayout3);
        qyeryText.setMinimumSize(new Dimension(4, 20));
        qyeryText.setNextFocusableComponent(queryButton);
        qyeryText.setPreferredSize(new Dimension(200, 21));
        qyeryText.setToolTipText("LDAP query: i.e. \"sn=smith\"");
        setDirPAnel.setLayout(borderLayout5);
        setDirPAnel.setPreferredSize(new Dimension(650, 100));
        baseText.setPreferredSize(new Dimension(140, 21));
        baseText.setToolTipText("BASEDN: i.e. \"o=UNC-CH, c=US\"");
        baseText.setText("");
        baseDN.setText("BASEDN = ");
        SetDirLabel.setHorizontalAlignment(SwingConstants.CENTER);
        SetDirLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        SetDirLabel.setText("Please enter the LDAP Directory address");
        dirText.setNextFocusableComponent(baseText);
        dirText.setPreferredSize(new Dimension(300, 21));
        dirText.setToolTipText("Set LDAP Directory: i.e. \"ldap://directory.unc.edu:389\"");
        jPanel2.setLayout(borderLayout6);
        jPanel7.setLayout(borderLayout12);
        flowLayout2.setHgap(0);
        flowLayout2.setVgap(0);
        flowLayout1.setHgap(0);
        flowLayout1.setVgap(0);
        browsefile.setMinimumSize(new Dimension(296, 30));
        browsefile.setPreferredSize(new Dimension(498, 30));
        browsefile.setLayout(flowLayout4);
        borwseLabel.setMaximumSize(new Dimension(215, 30));
        borwseLabel.setMinimumSize(new Dimension(215, 30));
        borwseLabel.setPreferredSize(new Dimension(215, 30));
        borwseLabel.setToolTipText("");
        borwseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        borwseLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        borwseLabel.setText("Please browse for the XML fileyou need");
        browseXML.setPreferredSize(new Dimension(200, 21));
        browseXML.setToolTipText("Type file name");
        browseButton1.setMinimumSize(new Dimension(77, 21));
        browseButton1.setPreferredSize(new Dimension(83, 21));
        browseButton1.setToolTipText("Open an XML file from the disk");
        browseButton1.setText("Browse");
        browseButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browseButton1_actionPerformed(e);
            }
        });
        userName.setPreferredSize(new Dimension(69, 21));
        userName.setToolTipText("Enter the LDAP username here");
        userName.setText("User Name");
        jPanel8.setMinimumSize(new Dimension(96, 20));
        jPanel8.setPreferredSize(new Dimension(298, 30));
        password.setPreferredSize(new Dimension(68, 21));
        password.setToolTipText("Enter the password here");
        saveDTD.setText("Save DTD");
        saveDTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveDTD_actionPerformed(e);
            }
        });
        viewdtdpanel.setLayout(flowLayout3);
        jPanel3.setPreferredSize(new Dimension(300, 400));
        jPanel3.setLayout(flowLayout5);
        viewPanel.setPreferredSize(new Dimension(300, 241));
        flowLayout5.setHgap(0);
        flowLayout5.setVgap(0);
        this.getContentPane().add(jPanel7, BorderLayout.NORTH);
        jPanel7.add(setDirPAnel, BorderLayout.CENTER);
        setDirPAnel.add(jPanel2, BorderLayout.NORTH);
        jPanel2.add(jPanel6, BorderLayout.CENTER);
        jPanel6.add(dirText, null);
        jPanel2.add(SetDirLabel, BorderLayout.NORTH);
        setDirPAnel.add(jPanel1, BorderLayout.CENTER);
        jPanel1.add(baseDN, null);
        jPanel1.add(baseText, null);
        jPanel7.add(workpane, BorderLayout.SOUTH);
        workpane.add(viewPanel, BorderLayout.CENTER);
        viewPanel.add(cardPANE, BorderLayout.SOUTH);
        cardPANE.add(savePanel, "savePanel");
        savePanel.add(saveXML, null);
        cardPANE.add(updatePanel, "updatePanel");
        updatePanel.add(updateLDAP, null);
        cardPANE.add(viewdtdpanel, "viewdtdpanel");
        viewdtdpanel.add(saveDTD, null);
        viewPanel.add(jPanel3, BorderLayout.NORTH);
        jPanel3.add(jScrollPane1, null);
        jScrollPane1.getViewport().add(xapDisplay, null);
        workpane.add(panelsPanel, BorderLayout.NORTH);
        panelsPanel.add(cardpane, BorderLayout.CENTER);
        cardpane.add(exportpane, "exportpane");
        exportpane.add(queryPanel, BorderLayout.CENTER);
        queryPanel.add(queryLabel, BorderLayout.NORTH);
        queryPanel.add(jPanel4, BorderLayout.CENTER);
        jPanel4.add(qyeryText, null);
        jPanel4.add(queryButton, null);
        exportpane.add(viewRadio, BorderLayout.SOUTH);
        viewRadio.add(viewXML, null);
        viewRadio.add(viewDTD, null);
        cardpane.add(importpane, "importpane");
        importpane.add(browse, BorderLayout.CENTER);
        browse.add(borwseLabel, BorderLayout.CENTER);
        browse.add(jPanel8, BorderLayout.SOUTH);
        jPanel8.add(browseXML, null);
        jPanel8.add(browseButton1, null);
        importpane.add(browsefile, BorderLayout.SOUTH);
        browsefile.add(userName, null);
        browsefile.add(password, null);
        panelsPanel.add(radioPanel, BorderLayout.NORTH);
        radioPanel.add(Export, null);
        radioPanel.add(Import, null);
        viewPanel.add(jScrollPane1, BorderLayout.NORTH);
        ImportExport.add(Import);
        ImportExport.add(Export);
        view.add(viewDTD);
        view.add(viewXML);

  }
/*
 *  queryButton_actionPerformed(). get invoked after the query button
 *  getpressed. it connect with JndiInterface and XML Object, to
 *  show the XML or the DTD represintation in the Text Area
 */
  void queryButton_actionPerformed(ActionEvent e)
  {

    Message m, n;

    m = j.setDirectory(dirText.getText(), baseText.getText());
    dtd = j.getDtd();
    if(m.getErrorCode() != ErrorCode.VALID_EXECUTION)
    {
	JOptionPane.showMessageDialog(XAP1.this, m.getErrorMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
	return;
    }


    n = j.selectQuery(qyeryText.getText());
//    System.out.println(n.getErrorMessage());
// This is where the MSG get invoked
    if(n.getErrorCode() != ErrorCode.VALID_EXECUTION)
    {
        JOptionPane.showMessageDialog(XAP1.this, n.getErrorMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
	return;
    }
        viewXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewXML_actionPerformed(e);
            }
        });

	viewDTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewDTD_actionPerformed(e);
            }
        });

  }
/*
 *  Export_actionPerformed(). get invoked after the radio button export
 *  getslelected. it use a card lay out where it shows the panel that it use
 *
 */
  void Export_actionPerformed(ActionEvent e)
  {
    if (Export.isSelected())
      card.show(cardpane, "exportpane");
      cardSAVE.show(cardPANE, "savePanel");
  }

  /*
 *  Import_actionPerformed(). get invoked after the radio button import
 *  getslelected. it use a card lay out where it shows the panel that it use
 */
  void Import_actionPerformed(ActionEvent e)
  {
  if (Import.isSelected())
      card.show(cardpane, "importpane");
      cardSAVE.show(cardPANE, "updatePanel");
  }
/*
 *  saveXML_actionPerformed(). get invoked after the Save button get pressed
 *  it save the XML file
 */

  void saveXML_actionPerformed(ActionEvent e)
  {
    JFileChooser chooser = new JFileChooser();
    int option =  chooser.showSaveDialog(XAP1.this);
    if (option == JFileChooser.APPROVE_OPTION)
    {
       String filename = chooser.getSelectedFile().getAbsolutePath();
       XmlFile.writeXmlFile(xml, filename);
       XAP1.this.named = true;

    }
  }

/*
 *  viewDTD_actionPerformed(). get invoked after the radio button view DTD
 *  get selected. it use a card lay out where it shows the save type that it use
 *  as well as it shows the DTD on the text area
 */

    void viewDTD_actionPerformed(ActionEvent e)
    {
	if(viewDTD.isSelected())
	{
	    cardSAVE.show(cardPANE, "viewdtdpanel");
	    xapDisplay.setText(dtd.dtdToText());
	}
    }
/*
 *  viewXML_actionPerformed(). get invoked after the radio button view XML
 *  get selected. it use a card lay out where it shows the save type that it use
 *  as well as it shows the XML on the text area
 */
    void viewXML_actionPerformed(ActionEvent e)
    {
	if(viewXML.isSelected())
	{
	    cardSAVE.show(cardPANE, "savePanel");
	    xml = j.getXml();
	    xapDisplay.setText(xml.xmlToWindow());
	}
    }

/*
 *  saveDTD_actionPerformed(). get invoked after the Save button get pressed
 *  it save the DTD file
 */
    void saveDTD_actionPerformed(ActionEvent e)
    {
	JFileChooser chooser = new JFileChooser();
	int option =  chooser.showSaveDialog(XAP1.this);
	JLabel statusbar = new JLabel("Output of your selection will go here");
	if (option == JFileChooser.APPROVE_OPTION)
	{
	   String filename = chooser.getSelectedFile().getAbsolutePath();
	   XmlFile.writeDtdFile(dtd, filename);
	   XAP1.this.named = true;
	}
    }
/*
 *  updateLdap_actionPerformed(). get invoked after the Update button get pressed
 *  it Update the LDAP directory with the choosen XML file
 */
    void updateLDAP_actionPerformed(ActionEvent e)
    {
	char[] pass = password.getPassword();
	String stringPassword = "";
	for(int i = 0; i < pass.length; i++)
	{
	    stringPassword += pass[i];
	}

	Message error = j.updateQuery(xmlFileName, userName.getText(), stringPassword);
        if(error.getErrorCode() != ErrorCode.VALID_EXECUTION)
        {
	    JOptionPane.showMessageDialog(XAP1.this, error.getErrorMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
	    return;
        }

    }
/*
 *  browseButton1_actionPerformed(). get invoked after the Browse button get pressed
 *  it allowe the user to select the XML file needed
 */
    void browseButton1_actionPerformed(ActionEvent e)
    {
	JFileChooser chooser = new JFileChooser();
	int option =  chooser.showOpenDialog(XAP1.this);
	JLabel statusbar = new JLabel("Output of your selection will go here");
	if (option == JFileChooser.APPROVE_OPTION)
	{
	   xmlFileName = chooser.getSelectedFile().getAbsolutePath();
	   XAP1.this.named = true;

	}
    }

}
