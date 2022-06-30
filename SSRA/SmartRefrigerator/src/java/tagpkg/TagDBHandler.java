package tagpkg;
import Model.tag;
import dbConnection.dbConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

/* Class name: TagDBHandler
 * Description: This class handles database operations
 */
public class TagDBHandler {
    /* Method name: rand
     * Description: This method generates random integers for RFID Tag
     */
     public static String rand(){
        int min = 0;
        int max = 99999;
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
        String rvar = stvar;
        return rvar;
    }
    
    /* Method name: checkid
     * Description: This method checks if random integers generated for ID is unique or not.  If not, generates new one.
     */     
    public static String checkid(){
        String pvar=rand();
        try{   
            Connection con=dbConn.getConnection();  
             PreparedStatement ps = con.prepareStatement("SELECT RIGHT(rf,5) FROM ssra.tag where RIGHT(rf,5)='"+pvar+"'");
             ResultSet rs=ps.executeQuery();
             
               if (rs.next()) 
               {  
                    String newpvar="";
                    newpvar=checkid();
                    return newpvar;
                }               
            }catch(Exception ex){ex.printStackTrace();}
        return pvar;
    }
    /* Method name: AddTag
     * Description: This method inserts the values of new tags into the database.
     */
    public static int AddTag(tag e){  
        int status=0;  
        try{  
            Connection con=dbConn.getConnection();
            int i, qty = e.getbqty();
            for(i=1;i<=qty;i++){
            PreparedStatement ps=con.prepareStatement(  
                         "insert into tag(Mmid,Mtid,prodname,mfgdate,bno,bqty,expdate,brand,rf,rfid) values (?,?,?,?,?,?,?,?,?,?)");  
            ps.setString(1,e.getMmid());  
            ps.setString(2,e.getMtid());  
            ps.setString(3,e.getprodname());
            ps.setString(4,e.getmfgdate());  
            ps.setString(5,e.getbno());
            ps.setInt(6,e.getbqty());
            ps.setString(7,e.getexpdate());
            ps.setString(8,e.getbrand());
            ps.setString(9,e.getrf());
            ps.setString(10,e.getrfid());
            status=ps.executeUpdate();  
            }  
            con.close();  
        }catch(Exception ex){
            ex.printStackTrace();
        }  
        return status;  
    }  
}
