/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mugshotwebscraper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//imports to download mugshot
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alex
 */


public class HTMLParse {
    
    
    /**
     * Gets individual inmate information from the passed in link
     * @param link 
     * @param inmateObj 
     */
    public void getInmateInfo(String link, Inmate inmateObj) {
        String name = null;
        int bookingNumber = -1;
        int nameNumber = -1;
        String bookingDate = null;
        String offensesString = "Offenses String: ";
        
        try {
            Connection conn = Jsoup.connect(link);
            Document page = conn.get();
            
            //-----------------------------------------------------------------
            //gets NAME
            Element nameElement = page.select("td.table-label:contains(Name)").first();
            nameElement = nameElement.nextElementSibling();
            if (nameElement.text().equals("")) {
                name = "null";
            }
            else {
                name = nameElement.text();
            }
            //-----------------------------------------------------------------
            //gets BOOKING NUMBER
            Element bookingNumberElement = page.select
                    ("td.table-label:contains(Booking Number)").first();
            bookingNumberElement = bookingNumberElement.nextElementSibling();
            if (bookingNumberElement.text().equals("")) {
                bookingNumber = -1;
            }
            else {
                bookingNumber = Integer.parseInt(bookingNumberElement.text());
            }
            //-----------------------------------------------------------------
            //gets NAME NUMBER
            Element nameNumberElement = page.select
                    ("td.table-label:contains(Name Number)").first();
            nameNumberElement = nameNumberElement.nextElementSibling();
            if (nameNumberElement.text().equals("")) {
                nameNumber = -1;
            }
            else {
                nameNumber = Integer.parseInt(nameNumberElement.text());
            }
            //-----------------------------------------------------------------
            //gets DATE of booking
            Element bookingDateElement = page.select
                    ("td.table-label:contains(Booking Date)").first();
            bookingDateElement = bookingDateElement.nextElementSibling();
            if (bookingDateElement.text().equals("")) {
                bookingDate = "-1";
            }
            bookingDate = bookingDateElement.text();
            //-----------------------------------------------------------------
            //download image and safe with name = nameNumber
            Element img = page.select("#img-mugshot").first();
            String src = img.attr("src");
            String base64Data = src.replaceFirst("^data:image/[a-zA-Z]+;base64,", "");
            downloadImage(base64Data, Integer.toString(nameNumber));
            
            //-----------------------------------------------------------------
            //gets OFFENSES and times of the offense arrest
            Elements dtElements = page.select("dt");
            Elements ddElements = page.select("dd");
            offensesString = "Offenses String: ";
            String offense = null;
            String time = null;
            for (int i = 0; i < dtElements.size(); i++) {
                Element dtElement = dtElements.get(i);
                Element ddElement;
                Element timeElement;
                
                
                if (dtElement.text().contains("Offense")) {
                    ddElement = ddElements.get(i);
                    //System.out.println(ddElement.text());
                    offense = ddElement.text();
                }
                if (dtElement.text().contains("Date/Time")) {
                    timeElement = ddElements.get(i);
                    //System.out.println(timeElement.text());
                    time = timeElement.text();
                }
                //add offense with date to offensesString
                if (offense != null && time != null) {
                    offensesString += time + ", " + offense + " : ";
                    offense = null;
                    time = null;
                }
                
                
            }//FOR LOOP
        }//Try Block
        catch (IOException ex) {
            System.out.println( "IOException Caught: \n" + ex.getMessage() );
        }
        catch (NullPointerException ex) {
            System.out.println("Null pointer Exception Caught: \n" + ex.getMessage());
        }
        catch (NumberFormatException ex) {
            System.out.println("Number Format Exception Caught: \n" + ex.getMessage());
        }
        finally {
            /*
             * set variables to an inmate instance to transfer back to main then to Database Driver
            */
            inmateObj.setName(name);
            inmateObj.setBookingNumber(bookingNumber);
            inmateObj.setNameNumber(nameNumber);
            inmateObj.setBookingDate(bookingDate);
            inmateObj.setOffenses(offensesString);
            /*
            System.out.println(name);
            System.out.println(bookingNumber);
            System.out.println(nameNumber);
            System.out.println(bookingDate);
            System.out.println(offensesString + "\n\n\n");
            */
        }
    }
    
    private void downloadImage(String base64Data, String imgName) {
        try {
            System.out.println("Saving: " + imgName);
            byte[] imageData = Base64.getMimeDecoder().decode(base64Data);
            File outputFile = new File(imgName + ".jpg");
            FileOutputStream outputStream = new FileOutputStream("mugshotPictures/" + outputFile);
            outputStream.write(imageData);
            System.out.println("Image saved");
            outputStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("error FileNotFoundException caught");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("error writing the image");
            System.out.println(ex.getMessage());
        }
        System.out.println(base64Data);
    }
    
    
    
    
    /**
     * Passes in a linkedList to store all Inmate links from the main page so we can extract the 
     * data for each inmate.
     * 
     * @param inmateLinks 
     */
    public void getInmateLinksFromMainPage(LinkedList<String> inmateLinks) {
        Connection conn;
        try {
          // Connect to the main page and get the HTML
          conn = Jsoup.connect("https://danesheriff.com/Residents");
          Document mainPage = conn.get();

          // Get links for individual inmate pages
          Elements links = mainPage.select("a[href]");
          
          //for each href element link, reformat and store into our LinkedList
          for (Element link : links) {
            if (link.text().equals("Detail")){
                String linkToAdd = link.attr("href");
                linkToAdd = linkToAdd.replace("/Inmates/D", 
                            "https://danesheriff.com/Residents/d");
                inmateLinks.add(linkToAdd);
            }
            
          }//try block
          
        } //try
        catch (IOException ex) {
          System.out.println( ex.getMessage() );
        }
        //no need to close the connection, as it happens automatically
        
    }//getInmateLinksFromMainPage method
}//class
