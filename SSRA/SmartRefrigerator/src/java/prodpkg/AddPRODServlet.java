package prodpkg;
import Model.prod;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Class name: AddPRODServlet
 * Description: This class gets the new product values and sends to DBHandler. 
 */
public class AddPRODServlet extends HttpServlet {

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
        String cmbCNC=request.getParameter("cmbCNC");  
        String cmbcat=request.getParameter("cmbcat");  
        String cmbsubcat=request.getParameter("cmbsubcat");  
        
        /* Setting the prod object with Posted values from form*/
        prod e=new prod();  
        e.setcmbCNC(cmbCNC);  
        e.setcmbcat(cmbcat);  
        e.setcmbsubcat(cmbsubcat);  
         
        /*Insert values into Database*/
        int status=ProdDBHandler.AddProd(e);
        /*If insertion is successful, status will have value greater than 0*/
        if(status>0){  
            out.print("<p>Record saved successfully!</p>");  
            request.getRequestDispatcher("ssra_home.html").include(request, response);  
        }else{  
            out.println("Sorry! unable to save record");  
        }  
        out.close();  
    }
}
