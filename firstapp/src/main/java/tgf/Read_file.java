package tgf;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner; // Import the Scanner class to read text files


import tgf.Person.compareName;
import tgf.Person.compareCompany;
import tgf.Person.compareDate;
import tgf.Person.compareColor;

import java.awt.*;
import java.awt.Color;
import java.lang.reflect.Field;

public class Read_file
{

    //Create all values that will be used in the class.
    private ArrayList<Person> People = new ArrayList<Person>();
    private int number_of_values; 
    private Date MyDate;
    private String Name;
    private String Company;
    private Color MyColor;
    String[]split_variable = {"test","test"};





    public void read_file(String text)
    {
        //Clears the People ArrayList if button is hit again.
        //Creates the Scanner object
        People = new ArrayList<Person>();
        String next_line;
        String split_variable[] = {" ", " "};
        File myObj = new File(text);
        Scanner sc;
        try {
            sc = new Scanner(myObj);
            //Loops through file skipping empty lines.
            while(sc.hasNext())
            {
                next_line = sc.nextLine();
                if(!next_line.isEmpty())
                {   
                    //Gets rid of ":" delimiter and gets rid of white space.
                    split_variable = next_line.split(":");
                    split_variable[0] = split_variable[0].trim();
                    split_variable[1] = split_variable[1].trim();
                    System.out.println(split_variable[0].trim() + " : " + split_variable[1].trim());

                    //Converts extracts String values for Month, Day, and Year then converts to Date type.
                    //Sets the MyDate value.
                    if( split_variable[0].equals("Date"))
                    {
                        String Month = split_variable[1].substring(0,2);
                        String Day = split_variable[1].substring(3,5);
                        String Year = "20" + split_variable[1].substring(6);
                        String date_to_convert = Year +  Month  + Day;
                        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                        MyDate = originalFormat.parse(date_to_convert);

            
                    }
                    //Sets the Name value.
                    else if(split_variable[0].equals("Name"))
                    {
                        
                        Name = split_variable[1];
                    }
                    //Sets the Company value.
                    else if(split_variable[0].equals("Company"))
                    {
                        Company = split_variable[1];
                    }
                    //Convets indigo to blue and violet to magenta since they do not exist in Color class.
                    //Converts String Color value to Color value.
                    //Sets the MyColor value.
                    else if(split_variable[0].equals("Color"))
                    {
                        if(split_variable[1].equals("indigo"))
                        {
                            split_variable[1] = "blue";
                        }
                        if(split_variable[1].equals("violet"))
                        {
                            split_variable[1] = "magenta";
                        }                   
                        Field field = Class.forName("java.awt.Color").getField(split_variable[1]);
                        MyColor = (Color)field.get(null);
    
                    }
    

                }
               else if(split_variable[0].equals("Color"))
               {
                    //Adds Person to ArrayList<Person> People
                    
                    People.add(new Person(Name, Company, MyColor, MyDate));
                    split_variable[0] = " ";
                    split_variable[0] = " ";
                    System.out.println("Person Added");
    
                }
    
            }

            

        } catch (FileNotFoundException | ParseException | NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }






    }


    public ArrayList<Person> getPeopleArray()
    {   
        //Returns ArrayList<Person> People
        return People;
    }







}

    













