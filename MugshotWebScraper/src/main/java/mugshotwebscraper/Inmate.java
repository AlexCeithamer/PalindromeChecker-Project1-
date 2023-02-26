/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mugshotwebscraper;



/**
 *
 * @author Alex
 */
public class Inmate {
    private String name;
    private int bookingNumber;
    private int nameNumber;
    private String bookingDate;
    private String offenses;
    
    
    public String getName(){
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getBookingNumber(){
        return bookingNumber;
    }
    
    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    
    public void setNameNumber(int nameNumber) {
        this.nameNumber = nameNumber;
    }
    
    public int getNameNumber() {
        return nameNumber;
    }
    
    public void setBookingDate(String date) {
        this.bookingDate = date;
    }
    
    public String getBookingDate() {
        return bookingDate;
    }
    
    public void setOffenses(String offenses) {
        this.offenses = offenses;
    }
    
    public String getOffenses() {
        return offenses;
    }
    
    
}
