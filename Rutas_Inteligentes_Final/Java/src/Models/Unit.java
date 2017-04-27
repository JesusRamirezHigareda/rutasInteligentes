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

public class Unit {
    private static PreparedStatement command; 
    private static ResultSet result;
    CallableStatement procedure;
    
    private String id;
    private String descritpion;
    private String route;
    private Location locations;

    public String getId() {
        return id;
    }
    public String getDescritpion() {
        return descritpion;
    }
    public String getRoute() {
        return route;
    }
    
    public Unit(String id) throws RecordNotFoundException{
        try{
            String query = "SELECT uni_id, uni_description, rou_description FROM unit, route WHERE uni_route_id = rou_id AND uni_id =  ?";
            command = MySqlConnection.getConnection().prepareStatement(query);
            command.setString(1,id);
            
            result = command.executeQuery();
            result.first();
            if(result.getRow() > 0){
                this.id = result.getString("uni_id");
                this.descritpion = result.getString("uni_description");
                this.route = result.getString("rou_description");
            }
            else{
                throw new RecordNotFoundException(this.getClass().getName(),String.valueOf(id));
            }
            }
            catch(SQLException ex){
            }
    }
    public UbicationResults add_ubication(String longitud, String latitud,String nowDate){
        int result = 0;
        String call = "call sp_new_location(?,?,?,?,?)";
        ///(acount_id,concept_id,ammount,out result)
        try{
            procedure = MySqlConnection.getConnection().prepareCall(call);
            //parameters
            procedure.setString(1,this.id);
            procedure.setString(2,latitud);
            procedure.setString(3,longitud);
            procedure.setString(4,nowDate);
            //out parameters
            procedure.registerOutParameter(5,java.sql.Types.INTEGER);
            //ejecute
            procedure.executeUpdate();
            //recived out param
            result = procedure.getInt(5);
            //uptdate balance attribute
            
        }
        catch(SQLException ex){         
        }
        return UbicationResults.values()[result];
    }
    
    public ArrayList<Location> getLocations(){
        ArrayList<Location> list = new ArrayList<Location>();
        String query = "SELECT loc_id, loc_datetime, loc_latitude, loc_longitude"
                +   "FROM unit join locations on loc_unit_id = uni_id where uni_id = ?";
        try{
            command = MySqlConnection.getConnection().prepareStatement(query);
            command.setString(1,this.id);
            result = command.executeQuery();

            while(result.next()){
                int locId = result.getInt("loc_id");
                Date date =  result.getDate("loc_datetime");
                String longitude = result.getString("loc_longitude");
                String latitude = result.getString("loc_latitude");
                
                list.add(new Location(locId,longitude,latitude,date));
            }
        }
        catch(SQLException ex){
        }
        return list;
    }
}
