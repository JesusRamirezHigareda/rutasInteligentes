package Models;
import MySql.MySqlConnection;
import java.util.Date;
import MySql.MySqlConnection;
import Exceptions.RecordNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import Enumerators.UbicationResults;
import java.sql.CallableStatement;
import java.util.ArrayList;

public class Location {
    private static PreparedStatement command; 
    private static ResultSet result;
    CallableStatement procedure;
    
    private int id;
    private String longitud;
    private String latitude;
    private Date date;

    public String getLongitud() {
        return longitud;
    }
    public String getLatitude() {
        return latitude;
    }
    public Date getDate() {
        return date;
    }
    public int getId() {
        return id;
    }

    public Location(int id, String longitud, String latitude, Date date) {
        this.id = id;
        this.longitud = longitud;
        this.latitude = latitude;
        this.date = date;
    }
    
    @Override 
    public String toString(){
        return this.id + " - " +  this.date + " - " + this.latitude + " - " + this.longitud;
    }
    
    
    
}
