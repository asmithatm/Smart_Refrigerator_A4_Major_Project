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

/* Class name: ShowRfidServlet
 * Description: This class fetches the RFID tags that were generated for the products inserted and displays them to the manufacturer.
 */
public class ShowRfidServlet extends HttpServlet {

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
        
        String mid=request.getParameter("Mmid");
        String tid=request.getParameter("Mtid");
        String prodname=request.getParameter("prodname");  
        String mfgdate=request.getParameter("mfgdate");
        String bno=request.getParameter("bno");
        int bqty=Integer.parseInt(request.getParameter("bqty"));  
        String expdate=request.getParameter("expdate");
        String brand=request.getParameter("brand");

        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Display RFID Tags</title>");
            out.println("<meta charset='UTF-8'");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='stylesheet' href='newcss.css'>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class = 'form_wrapper'>");
            
        /*fetches the RFID tags from database and displays them*/
        try{
            Connection con=dbConn.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from tag where Mmid='"+mid+"'and Mtid='"+tid+"' and prodname='"+prodname+"' and mfgdate='"+mfgdate+"' and bno='"+bno+"' and bqty='"+bqty+"' and expdate='"+expdate+"' and brand='"+brand+"'");
            ResultSet rs=ps.executeQuery();
        
            while(rs.next()){  
                out.print("<h1 class = 'form-head'>RFID: "+rs.getString(11)+"</h1>");
            }  
            con.close();
            out.println("</div>");
            out.println("</body>");
         }catch(Exception ex){ex.printStackTrace();}            
    }
}
