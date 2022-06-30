package mfrpkg;
import Model.Mfr;
import dbConnection.dbConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

/* Class name: MfrDBHandler
 * Description: This class handles database operations
 */
public class MfrDBHandler {
    /* Method name: rand
     * Description: This method generates random integers for MID
     */
    public static String rand(){
        int min = 0;
        int max = 999;
        Random random = new Random();
        int var = min + random.nextInt(max - min + 1);        
        String stvar = Integer.toString(var);
        String stmax = Integer.toString(max);
        int ln=stvar.length(); 
        int ln2=stmax.length();        
        if(ln!=ln2){
        for(int i=ln;i<ln2;i++){
            stvar=min+stvar;
          }
        }      
        String mvar = "M"+stvar;
        return mvar;
    }
    
    /* Method name: checkmid
     * Description: This method checks if random integers generated for MID is unique or not.  If not, generates new one.
     */    
    public static String checkmid(){
        String mvar=rand();        
        try{  
             Connection con=dbConn.getConnection();  
             PreparedStatement ps = con.prepareStatement("select mid from mis where mid='"+mvar+"'");
             ResultSet rs=ps.executeQuery();             
               if (rs.next()) 
               {  
                    String newmvar="";
                    newmvar=checkmid();
                    return newmvar;
                }               
            }catch(Exception ex){
                ex.printStackTrace();
            }
        return mvar;
    }    
     /* Method name: AddMfr
     * Description: This method inserts the values of new manuufacturer into the database.
     */
    public static int AddMfr(Mfr e){  
        int status=0;  
        try{  
            Connection con=dbConn.getConnection();
            PreparedStatement ps=con.prepareStatement(  
                         "insert into mis(mname,maddr,memail,mphno, mid) values (?,?,?,?,?)");  
            ps.setString(1,e.getmname());  
            ps.setString(2,e.getmaddr());  
            ps.setString(3,e.getmemail());  
            ps.setString(4,e.getmphno());  
            ps.setString(5,e.getmid());  
            status=ps.executeUpdate();  
            String midDB = getSavedMID.getSavedMIDFromDB(e);
            e.setMIDFromDB(midDB);
            con.close();  
        }catch(Exception ex){
            ex.printStackTrace();
        }            
        return status;  
    }
}
