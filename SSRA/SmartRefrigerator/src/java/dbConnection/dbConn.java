package dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
/* Class name: dbConn
 * Description: This class establishes database connection
 */
public class dbConn {
        /* Method name: getConnection
        * Description: This method establishes database connection
        */
        public static Connection getConnection(){  
        Connection con=null;  
        try{  
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssra", "root", "root@257");        
        }catch(Exception e){System.out.println(e);}  
        return con;  
    }
}
