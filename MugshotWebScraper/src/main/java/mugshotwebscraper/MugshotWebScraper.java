
package mugshotwebscraper;

import java.util.LinkedList;
import java.util.Scanner;




/**
 * Main Driver of the program. Uses a Jsoup object called HTMLParse to scrape inmate
 * data from Dane county sheriff website. Uses SQLite to store the information with the database
 * driver object.
 * @author Alex
 */
public class MugshotWebScraper {
    //main instance object will be used for moving code from main to other methods.
    static MugshotWebScraper mainInstance = new MugshotWebScraper();
    static DatabaseDriver db = new DatabaseDriver();
    static HTMLParse scraper = new HTMLParse();
    static Inmate inmateObj = null;
    static LinkedList <String>inmateLinks = new LinkedList<>();
    
    public static void main(String[] args) {
        mainInstance.updateDatabase();
        
    }
    
    public void updateDatabase() {
        db.connect();
        db.clearInmateTable();
        scraper.getInmateLinksFromMainPage(inmateLinks);
        for (int i = 0; i < inmateLinks.size(); i++) { 
            inmateObj = new Inmate();
            System.out.println(inmateLinks.get(i));
            
            //get inmate info by scraping website and put it into an inmate object
            scraper.getInmateInfo(inmateLinks.get(i), inmateObj);
            
            //check if inmateObj has blank info. IF it does, we don't enter it
            if (inmateObj.getNameNumber() != -1 &&
                    inmateObj.getBookingNumber() != -1 &&
                    !inmateObj.getBookingDate().equals("-1") &&
                    inmateObj.getBookingDate() != null){
                
                //if inmate doesn't exist, insert into legacy database
                if (!db.checkIfInmateExists(inmateObj.getNameNumber()) ){

                    db.insertInmateLegacy(inmateObj.getName(),
                                        inmateObj.getBookingNumber(),
                                        inmateObj.getNameNumber(),
                                        inmateObj.getBookingDate(), 
                                        inmateObj.getOffenses());
                }
                //insert inmate into temp database always
                db.insertInmate(inmateObj.getName(),
                                        inmateObj.getBookingNumber(),
                                        inmateObj.getNameNumber(),
                                        inmateObj.getBookingDate(), 
                                        inmateObj.getOffenses());
            }//if loop to check for inmateObj blank info
            
            
            //if the inmate info has any null info, or was assigned -1 for having no info, we skipped
            //over entering into the database and print out that they have blank info
            else {
                System.out.println(inmateObj.getName() + " has blank info, was not entered into database!" );
            }
            inmateObj = null;
            
            System.out.printf("\n %.0f%% \n" , ((i * 100.0f) / inmateLinks.size()));
            
        }
        
        db.printAllInfo();
        db.disconnect();
    }
}
