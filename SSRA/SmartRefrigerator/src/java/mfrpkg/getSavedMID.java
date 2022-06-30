package mfrpkg;
import Model.Mfr;
import dbConnection.dbConn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/* Class name: getSavedMID
 * Description: This class fetches the MID from database that was generated for the new manufacturer and displays it to him.
 */
public class getSavedMID {
    /* Method name: getSavedMIDFromDB
     * Description: This method searches for the inserted manufacturer values and gets his MID
     */
    public static String getSavedMIDFromDB(Mfr e){
        String mid = "";
        try{              
             Connection con=dbConn.getConnection();  
             PreparedStatement ps = con.prepareStatement("select mid from mis where mname='"+e.getmname()+"' and maddr='"+e.getmaddr()+"' and memail='"+e.getmemail()+"' and mphno='"+e.getmphno()+"'");
             ResultSet rs=ps.executeQuery();             
               if (rs.next()) 
               {  
                   mid = rs.getString(1);
                   return mid;
                }               
            }catch(Exception ex){ex.printStackTrace();}
        return mid;
    }
}
