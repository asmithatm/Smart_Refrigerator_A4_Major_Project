package prodpkg;
import Model.prod;
import dbConnection.dbConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

/* Class name: ProdDBHandler
 * Description: This class handles database operations
 */
public class ProdDBHandler {
    /* Method name: rand
     * Description: This method generates random integers for TID
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
        String pvar = "p"+stvar;
        return pvar;
    }
    
    /* Method name: checktid
     * Description: This method checks if random integers generated for TID is unique or not.  If not, generates new one.
     */ 
    public static String checktid(){
        String pvar=rand();        
        try{               
            Connection con=dbConn.getConnection();  
             PreparedStatement ps = con.prepareStatement("select tid from pds where tid='"+pvar+"'");
             ResultSet rs=ps.executeQuery();            
               if (rs.next()) 
               {  
                    String newpvar="";
                    newpvar=checktid();  
                }              
            }catch(Exception ex){ex.printStackTrace();}
        return pvar;
    }
    
    /* Method name: AddProd
     * Description: This method inserts the values of new product into the database.
     */   
    public static int AddProd(prod e){  
        int status=0;  
        try{  
            Connection con=dbConn.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "insert into pds(cmbCNC,cmbcat,cmbsubcat, tid) values (?,?,?,?)");  
            ps.setString(1,e.getcmbCNC());  
            ps.setString(2,e.getcmbcat());  
            ps.setString(3,e.getcmbsubcat());  
            ps.setString(4,e.gettid());  
            status=ps.executeUpdate();              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}            
        return status;  
    }  
      
}
