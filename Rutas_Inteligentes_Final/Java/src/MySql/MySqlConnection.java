package MySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySqlConnection {
    
    private static final String SERVER = "localhost";
    private static final String DATABASE = "rutasinteligentes";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection = null;
    
    public static Connection getConnection(){
        if(connection == null){
            try{
                String connectionString = "jdbc:mysql://" + SERVER + "/"+ DATABASE + "?user="+ USERNAME;
                
                if(PASSWORD != null) connectionString += "&password" + PASSWORD;
                
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                
                connection = DriverManager.getConnection(connectionString);
            }
            catch(ClassNotFoundException ex){
                System.out.println(ex.getMessage());
            }
            catch(InstantiationException ex){
                System.out.println(ex.getMessage());
            }
            catch(IllegalAccessException ex){
                System.out.println(ex.getMessage());
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return connection;
    }
}
