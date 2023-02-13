package tgf;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.awt.*;
import java.util.Comparator;

public class Person {
    
    
    final private String name;
    final private String company;
    final private Color MyColor;
    final private Date MyDate;

    //Creation of the Person object.
    public Person(String name, String company, Color MyColor, Date MyDate) 
    {
        this.name = name;
        this.company = company;
        this.MyColor = MyColor;
        this.MyDate = MyDate;
    }






    
    //getName returns Name for Person
    public String getName()
    {
        return this.name;
    }



    //getCompany returns Company for Person
    public String getCompany()
    {
        return this.company;
    }





    //getColor returns Color for Person
    public Color getColor()
    {
        return this.MyColor;
    }

    //getColorString returns String Color for Person.
    public String getColorString()
    {
        int theColor = this.MyColor.getRGB();
        String colorName = " ";
 

        if(theColor == Color.red.getRGB())
        {
            colorName = "red";
        }
        else if(theColor == Color.black.getRGB())
        {
            colorName = "black";
        }
        else if(theColor == Color.yellow.getRGB())
        {
            colorName = "yellow";
        }
        else if(theColor == Color.pink.getRGB())
        {
            colorName = "pink";
        }
        else if(theColor == Color.orange.getRGB())
        {
            colorName = "orange";
        }
        else if(theColor == Color.magenta.getRGB())
        {
            colorName = "magenta";
        }
        else if(theColor == Color.blue.getRGB())
        {
            colorName = "blue";
        }
        else if(theColor == Color.green.getRGB())
        {
            colorName = "green";
        }
        else if(theColor == Color.white.getRGB())
        {
            colorName = "white";
        }
        else if(theColor == Color.cyan.getRGB())
        {
            colorName = "cyan";
        }
        else if(theColor == Color.darkGray.getRGB())
        {
            colorName = "darkGray";
        }
        else if(theColor == Color.gray.getRGB())
        {
            colorName = "gray";
        }
        else if(theColor == Color.lightGray.getRGB())
        {
            colorName = "lightGray";
        }




        return colorName;
    }

    //converts Color type to String Color name.
    public String colortoString(Color x)
    {
        int theColor = x.getRGB();
        String colorName = " ";
 

        if(theColor == Color.red.getRGB())
        {
            colorName = "red";
        }
        else if(theColor == Color.black.getRGB())
        {
            colorName = "black";
        }
        else if(theColor == Color.yellow.getRGB())
        {
            colorName = "yellow";
        }
        else if(theColor == Color.pink.getRGB())
        {
            colorName = "pink";
        }
        else if(theColor == Color.orange.getRGB())
        {
            colorName = "orange";
        }
        else if(theColor == Color.magenta.getRGB())
        {
            colorName = "magenta";
        }
        else if(theColor == Color.blue.getRGB())
        {
            colorName = "blue";
        }
        else if(theColor == Color.green.getRGB())
        {
            colorName = "green";
        }
        else if(theColor == Color.white.getRGB())
        {
            colorName = "white";
        }
        else if(theColor == Color.cyan.getRGB())
        {
            colorName = "cyan";
        }
        else if(theColor == Color.darkGray.getRGB())
        {
            colorName = "darkGray";
        }
        else if(theColor == Color.gray.getRGB())
        {
            colorName = "gray";
        }
        else if(theColor == Color.lightGray.getRGB())
        {
            colorName = "lightGray";
        }




        return colorName;
    }


    //Returns the Date for person.
    public Date getDate()
    {
        
        return this.MyDate;
    }

    //getDateString returns the String Date in yyyy/MM/dd format.
    public String getDateString()
    {   
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(this.MyDate);
    }



    //Returns all information do be returned to textarea.
    public String getInformation()
    {
        System.out.println("Get Information");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(this.MyDate);
        System.out.println(formatter.format(this.MyDate)) ;
        String Information = 
        this.name + "\n" +  
        this.company + "\n" + 
        colortoString(this.MyColor) + "\n" + 
        formatter.format(this.MyDate) + "\n\n"  ;
        return Information;

    }




    //Compares name for person.
    public class compareName implements Comparator<Person>
    {
    public int compare(Person o1, Person o2) 
    {
        return o1.getName().compareTo(o2.getName());
    }
    }
    //Compares Company for person.
    public class compareCompany implements Comparator<Person>
    {
    public int compare(Person o1, Person o2) 
    {
        return o1.getCompany().compareTo(o2.getCompany());
    }
    }    
     //Compares Color for person.
    public class compareColor implements Comparator<Person>
    {
    public int compare(Person o1, Person o2) 
    {
        //String color1 = Integer.toString(o1.getColor().getRGB());
        //String color2 = Integer.toString(o2.getColor().getRGB());
        //return color1.compareTo(color2);

        return o1.getColorString().compareTo(o2.getColorString());
    }
    } 
    
    //Compares Date for person.
    public class compareDate implements Comparator<Person>
    {
    public int compare(Person o1, Person o2) 
    {
        return o1.getDate().compareTo(o2.getDate());
    }
    } 
    
    //Sorts an ArrayList<Person> by the type selected.
    public ArrayList<Person> sortPeopleArray(String type, ArrayList<Person> People)

    {   System.out.println("getPeopleArray run");
        if(type == "Name")
        {
            Collections.sort(People,  new compareName());
        }
        else if(type == "Company")
        {
            Collections.sort(People,  new compareCompany());
        }
        else if(type == "Date")
        {
            Collections.sort(People,  new compareDate());
        }
        else
        {
            Collections.sort(People,  new compareColor());           
        }




        return People;
    }



    
}
