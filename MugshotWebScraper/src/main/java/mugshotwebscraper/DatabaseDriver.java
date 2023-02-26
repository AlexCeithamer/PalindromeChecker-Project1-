
package mugshotwebscraper;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement; 
import java.sql.ResultSet;  
import java.sql.SQLException; 
/**
 *
 * @author Alex
 */
public class DatabaseDriver {
    static Connection connection = null;
    static ResultSet resultSet = null;
    static Statement statement = null;
    /** database identifier */
    static String url = "jdbc:sqlite:inmateDatabase.db";
    
    /**
     * Establishes a connection to the database and creates the
     * class-wide statement object
     */
    public void connect() {        
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }// connect method
    
    
    
    /**
     * Uses the existing statement object to execute the SQL query
     * built out of the three given query-parts.
     * 
     * @param  columns    the list of columns in the SELECT clause
     * @param  tables     the list of tables in the FROM clause
     * @param  conditions the rest of the query after the WHERE keyword
     */
    private void executeQuery( String columns, 
                                      String tables,
                                      String conditions ) {
        //SELECT ____  FROM ______  WHERE ______
        String sql = "SELECT " + columns + " FROM " + tables;
        if (!conditions.equals("")) {
            sql += " WHERE " + conditions;
        }
        try {
            resultSet = statement.executeQuery(sql);
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }// executeQuery method
    
    
    /**
     * Disconnects from the database
     */
    public void disconnect() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }// disconnect method
    
    public void printAllInfo() {
        //connect();
        try {
            executeQuery( 
                    "*",
                    "Inmate",
                    "" );
            while ( resultSet.next() ) {  
                System.out.format( "%-15s    %-15s    %-15s    %-15s    %-15s\n",
                    resultSet.getString( "Name" ),
                    resultSet.getString( "BookingNumber" ),
                    resultSet.getString( "NameNumber" ),
                    resultSet.getString( "BookingDate" ),
                    resultSet.getString("Offenses") );
            } 
        }
        catch(SQLException ex) {
            System.out.println( ex.getMessage() );
        }
        //disconnect();
    }//printAllInfo()
    
    
    
    /**
     * Inserts a new record into the inmate table
     * @param name
     * @param bookingNumber
     * @param nameNumber
     * @param date
     * @param offenses 
     */
    public void insertInmate(String name, int bookingNumber, int nameNumber, String date, 
                        String offenses) {
        //connect();
        try {
            //statement.executeUpdate("CREATE TABLE inmate (Name TEXT, BookingNumber INTEGER, NameNumber INTEGER, Date DATE, Offenses TEXT)");
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO inmate " +
                        "(Name, BookingNumber, NameNumber, BookingDate, Offenses) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, bookingNumber);
            pstmt.setInt(3, nameNumber);
            pstmt.setString(4, date);
            pstmt.setString(5, offenses);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println( ex.getMessage() );
        }
        //disconnect();
    }//insertInmate
    public void insertInmateLegacy(String name, int bookingNumber, int nameNumber, 
                                String date, String offenses) {
        //connect();
        try {
            
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO inmateLegacy " +
                        "(Name, BookingNumber, NameNumber, BookingDate, Offenses) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, bookingNumber);
            pstmt.setInt(3, nameNumber);
            pstmt.setString(4, date);
            pstmt.setString(5, offenses);
            pstmt.executeUpdate();
        } 
        catch(SQLException | NumberFormatException ex) {
            System.out.println( ex.getMessage() );
        }
        //disconnect();
    }
    
    public boolean checkIfInmateExists(int nameNumber) {
        //connect();
        
        try {
            executeQuery( 
                    "Name",
                    "InmateLegacy",
                    "nameNumber = ".concat(Integer.toString(nameNumber)) );
            while ( resultSet.next() ) {  
                System.out.format( "%s is already entered!\n",
                    resultSet.getString( "Name" ) );
                return true;
            } 
        }
        catch(SQLException | NumberFormatException ex) {
            System.out.println( ex.getMessage() );
        }
        //disconnect();
        return false;
    }
    
    public void clearInmateTable() {
        //connect();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM inmate");
            System.out.println("Table cleared successfully");
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //disconnect();
    }
    
    
}
