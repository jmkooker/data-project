package tgf;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MainFrame extends JFrame {

    private static Read_file read = new Read_file();

    public static void initialize(){
        //Creates Gui, buttons, and entries.
        //Scroll bar does not work.
        JFrame mainPanel = new JFrame("Test");
        JButton b1 = new JButton("...");
        JButton b2 = new JButton("Get Data");
        JTextField t1=new JTextField("Click '...' to get file then 'Get Data' to retrieve file."); 
        JRadioButton r1=new JRadioButton("Person");    
        JRadioButton r2=new JRadioButton("Company");   
        JRadioButton r3=new JRadioButton("Color");    
        JRadioButton r4=new JRadioButton("Date");  
        
        //Creates bounds and adds everything to the mainPanel

        r1.setBounds(870,50,100,30);    
        r2.setBounds(870,80,100,30);
        r3.setBounds(870,110,100,30);
        r4.setBounds(870,140,100,30);    
        ButtonGroup bg=new ButtonGroup();    
        bg.add(r1);
        bg.add(r2);  
        bg.add(r3);
        bg.add(r4);
        mainPanel.add(r1);
        mainPanel.add(r2);
        mainPanel.add(r3);
        mainPanel.add(r4);
        r1.setSelected(true);
        JTextArea ta = new JTextArea();
        
        ta.setRows(4);
        ta.setColumns(38);
        JScrollPane sp = new JScrollPane(ta); 
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(ta);
        mainPanel.getContentPane().add(sp);
        sp.setBounds(900,300, 30,300);
        ta.setBounds(10,50, 850,700);
        b1.setBounds(820,10,35,30);  
        mainPanel.add(b1);
        b2.setBounds(870,10,100,30);  
        mainPanel.add(b2);
        t1.setBounds(10,10, 800,30);  
        mainPanel.add(t1); 
  
        
        //Gives buttons actions

        setUpButtonDirectory(b1, t1);
        getData(b2, t1,ta,r1,r2,r3,r4);



        //Creates Gui

        mainPanel.setName("Test");
        mainPanel.setSize(1000,800);
        mainPanel.setLayout(null); 
        mainPanel.setVisible(true);
        

  
 


    }

   
    //Creates the action for the "..." button which allows the user to pick a file to get data from.

    public static void setUpButtonDirectory(JButton button, JTextField field)
    {

        ActionListener buttonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();

            

                    int r = chooser.showSaveDialog(null);
 
                    // if the user selects a file
                    if (r == JFileChooser.APPROVE_OPTION)
                    {

                        String text = chooser.getSelectedFile().getAbsolutePath();
                        field.setText(text);
                    }
                    
                    
                
            }

        };
        button.addActionListener(buttonListener);
    }

    //Controls the "Get Data" button

    public static void getData(JButton button, JTextField field, JTextArea textarea, JRadioButton r1, JRadioButton r2,JRadioButton r3,JRadioButton r4)
    {

        ActionListener buttonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("getData Start");

                //Gets text from Jtextfield field.
                String text = field.getText();
                //Sets the type to be sorted
                String type = " ";
                if(r1.isSelected()){    
                    type = "Name";
                }    
                else if(r2.isSelected()){    
                    type = "Company";    
                }
                else if(r3.isSelected()){    
                    type = "Color";    
                }
                else{    
                    type = "Date";    
                }           
                //Checks if the file selected is a file    
                File f = new File(text);
                if(f.isFile()){

                
                //Reads the data in from the file by using read_file from Read_file.java.
                read.read_file(text);
                //Gets the Arraylist Person that was read in from Read_file.java.
                ArrayList<Person> People = read.getPeopleArray();

                //Sorts the array based on the sort "type".
                Person sort = new Person();
                People = sort.sortPeopleArray(type ,People);

                //Appends the text from the array to the textarea.
                for(Person x : People)
                {
                    textarea.append(x.getInformation());

                }
                //Runs toxml to create an xml file from the ArrayList<Person> People.
                try {
                    toxml(People, text);
                } catch (TransformerConfigurationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
            else{
                System.out.println("Pick a file that can be read.");
            }
                    
                
            }

        };
        button.addActionListener(buttonListener);
    }

    //Method to Create an XML file using DocumentBuilderFactory.
    public static void toxml(ArrayList<Person> People, String text) throws TransformerConfigurationException
    {


        try {
            //Creates DocumentBuilderFactory and DocumentBuilder.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            //Creates root element for the document.
            Element root = doc.createElement("People");
            doc.appendChild(root);

            //Creating the values to go into XML.
            String Name = "";
            String Company = "";
            String MyDate = "";
            String MyColor = "";
            int id = 1;
            //Loops through the ArrayList<Person> to extract all of the values that will go into XML.
            for(Person x : People)
            {
                Name = x.getName();
                Company = x.getCompany();
                MyDate = x.getDateString();
                MyColor = x.getColorString();
                //Adds elements to the XML with method createUser.
                //createUser uses a method called createUserElement.
                //Appends to root.
                root.appendChild(createUser(doc, Integer.toString(id) ,Name, Company, MyDate, MyColor));
                id++;

            }

            //Creates the XML file 
            //Transforms the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            text = text.substring(0,text.lastIndexOf("/"));

            StreamResult streamResult = new StreamResult(new File(text + "/test.xml"));

            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating XML File");

            
        } catch (ParserConfigurationException | TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }







    //Creates a user with all values to be written to XML.
    //Uses createUserElement to create individual elements for the XML.
    private static Node createUser(Document doc, String id, String Name, String Company, String Date, String Color) {

        Element user = doc.createElement("user");

        user.setAttribute("id", id);
        user.appendChild(createUserElement(doc, "Name", Name));
        user.appendChild(createUserElement(doc, "Company", Company));
        user.appendChild(createUserElement(doc, "Date", Date));
        user.appendChild(createUserElement(doc, "Color", Color));

        return user;
    }

    private static Node createUserElement(Document doc, String name, String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }









}













    



