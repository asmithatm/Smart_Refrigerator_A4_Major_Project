package tagpkg;
import dbConnection.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Class name: FindTIDServlet
 * Description: This class finds the TypeID of the product that the manufacturer is manufacturing and displays it to him. 
 */
public class FindTIDServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
        PrintWriter out=response.getWriter();
        String Mmid=request.getParameter("Mmid");
        String mCNC=request.getParameter("mCNC");
        String mcat=request.getParameter("mcat");  
        String msubcat=request.getParameter("msubcat");
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FIND TID Servlet</title>");
            out.println("<meta charset='UTF-8'");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='stylesheet' href='newcss.css'>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class = 'form_wrapper'>");
            out.println("<form method='post' action='AddTAGServlet'>");
            
        /*Checks if the MID entered by the manufacturer and returns the MID if found else asks to enter correctly. */   
        try{
            Connection con=dbConn.getConnection();
            PreparedStatement ps = con.prepareStatement("select mid from mis where mid='"+Mmid+"' ");
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                out.println("<label class = 'form-label'>MID:</label><input type='text' class = 'form-items' name='Mmid' value='"+rs.getString(1)+"'/>");     
            }
            else if (!rs.next()){
                out.println("<label class = 'form-label'>MID:</label><input type='text' class = 'form-items' name='Mmid' value='Enter Correct MID VALUE'/>");                
            }
            out.println("<div class='gap'></div>");
            con.close();
         }catch(Exception ex){
             ex.printStackTrace();
         }         
            
        /*Fetches the TID for the product details and displays*/
         try{
                Connection con=dbConn.getConnection();
                PreparedStatement ps = con.prepareStatement("select * from pds where cmbCNC='"+mCNC+"' and cmbcat='"+mcat+"' and cmbsubcat='"+msubcat+"'");
                ResultSet rs=ps.executeQuery();
                while(rs.next()){  
                    out.print("<h1 class = 'form-head'>Your TID is : "+rs.getString(5)+"</h1>");
                    String Mtid = rs.getString(5);
                    out.println("<label class = 'form-label'>TID:</label><input type='text' class = 'form-items' name='Mtid' value='"+Mtid+"'/>");
                }
                out.println("<div class='gap'></div>");
                con.close();
         }catch(Exception ex){
             ex.printStackTrace();
         }
            
            /*Takes in input of other Manufacturing Details*/
            out.println("<h1 class = 'form-head'>Manufacturing Details</h1>");
            out.println("<div class='gap'></div>");
            out.println("<label class = 'form-label'>Product Name:</label><input type='text' class = 'form-items' name='prodname'/>");
            out.println("<div class='gap'></div>");
            out.println("<label class = 'form-label'>Date of Manufacturing:</label><input type='date' class = 'form-items' name='mfgdate'/>");  
            out.println("<div class='gap'></div>");
            out.println("<label class = 'form-label'>Batch Number:</label><input type='text' class = 'form-items' name='bno'/>");
            out.println("<div class='gap'></div>");
            out.println("<label class = 'form-label'>Batch Quantity:</label><input type='text' class = 'form-items' name='bqty'/>");
            out.println("<div class='gap'></div>");
            out.println("<label class = 'form-label'>Date of Expiry:</label><input type='date' class = 'form-items' name='expdate'/>");
            out.println("<div class='gap'></div>");
            out.println("<label class = 'form-label'>Brand:</label><input type='text' class = 'form-items' name='brand'/>");
            out.println("<div class='gap'></div>");
            out.println("<input type='submit' class = 'form-sub' value='Generate RFID Tags'/>");
            out.println("<div class='gap'></div>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
    }
}
