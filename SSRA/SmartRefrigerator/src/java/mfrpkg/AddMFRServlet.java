package mfrpkg;
import Model.Mfr;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Class name: AddMFRServlet
 * Description: This class gets the new manufacturer values and sends to DBHandler. 
 */
public class AddMFRServlet extends HttpServlet {

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
        String mname=request.getParameter("mname");  
        String maddr=request.getParameter("maddr");  
        String memail=request.getParameter("memail");  
        String mphno=request.getParameter("mphno");  
        
        /* Setting the Mfr object with Posted values from form*/
        Mfr e=new Mfr();  
        e.setmname(mname);  
        e.setmaddr(maddr);  
        e.setmemail(memail);  
        e.setmphno(mphno);  
        
        /*Insert values into Database*/
        int status=MfrDBHandler.AddMfr(e);
        /*If insertion is successful, status will have value greater than 0*/
        if(status>0){  
            out.print("<p>Record saved successfully!</p>");
            out.print("<p>Your MID Value is : "+e.getMIDFromDB()+"</p>");            
            request.getRequestDispatcher("ssra_home.html").include(request, response);  
        }else{  
            out.println("Sorry! unable to save record");  
        } 
        out.close();      
    }
}
