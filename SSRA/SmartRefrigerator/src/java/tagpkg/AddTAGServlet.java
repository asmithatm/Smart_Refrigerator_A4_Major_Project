package tagpkg;
import Model.tag;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Class name: AddTAGServlet
 * Description: This class gets the manufacturing details of the product being manufactured and sends to DBHandler. 
 */
public class AddTAGServlet extends HttpServlet {

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
        String Mtid=request.getParameter("Mtid");
        String prodname=request.getParameter("prodname"); 
        String mfgdate=request.getParameter("mfgdate");
        String bno=request.getParameter("bno");
        int bqty=Integer.parseInt(request.getParameter("bqty"));  
        String expdate=request.getParameter("expdate");
        String brand=request.getParameter("brand");
         
        /* Setting the tag object with Posted values from form*/
        tag e=new tag();  
        e.setMmid(Mmid);
        e.setMtid(Mtid);
        e.setprodname(prodname);  
        e.setmfgdate(mfgdate);  
        e.setbno(bno);
        e.setbqty(bqty); 
        e.setexpdate(expdate);
        e.setbrand(brand);
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add Tag Servlet</title>");
            out.println("<meta charset='UTF-8'");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='stylesheet' href='newcss.css'>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class = 'form_wrapper'>");
         
        /*Insert values into Database*/    
        int status=TagDBHandler.AddTag(e);
        /*If insertion is successful, status will have value greater than 0 and values are further posted to ShowRFID Servlet to fetch generated RFID tags */
        if(status>0){  
            out.print("<h1 class = 'form-head'>Record saved successfully!</h1>");
            
            out.println("<form method='post' action='ShowRfidServlet'>");
            out.println("<table>");
            out.println("<tr><td></td><td><input  type='hidden' name='Mmid' value='"+e.getMmid()+"'/></td></tr>");
            out.println("<tr><td></td><td><input  type='hidden' name='Mtid' value='"+e.getMtid()+"'/></td></tr>");
            out.println("<tr><td></td><td><input type='hidden' name='prodname' value='"+e.getprodname()+"'/></td></tr>");
            out.println("<tr><td></td><td><input type='hidden' name='mfgdate' value='"+e.getmfgdate()+"'/></td></tr>");  
            out.println("<tr><td></td><td><input type='hidden' name='bno' value='"+e.getbno()+"'/></td></tr>");
            out.println("<tr><td></td><td><input type='hidden' name='bqty' value='"+e.getbqty()+"'/></td></tr>");
            out.println("<tr><td></td><td><input type='hidden' name='expdate' value='"+e.getexpdate()+"'/></td></tr");
            out.println("<tr><td></td><td><input type='hidden' name='brand' value='"+e.getbrand()+"'/></td></tr>");
            out.println("<input type='submit' class = 'form-sub' value='Show RFID Tags'/>");
            out.println("</table>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        }else{  
            out.println("Sorry! unable to save record");  
        }            
        out.close();  
    }
}
