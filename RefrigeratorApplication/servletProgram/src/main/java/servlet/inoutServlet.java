package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import client.TCPClient;
/**
 * Servlet implementation class inoutServlet
 */
public class inoutServlet extends HttpServlet {	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String itemrfid=request.getParameter("itemrfid");  
        String status=request.getParameter("status");
        
        String encmsg="v2.0#S#30#001#05#I#"+itemrfid+"&"+status+"*";       
        try {
			String serverMsg = TCPClient.client(encmsg);
	        out.println("<div class='items'>"+serverMsg+"</div>");
	        request.setAttribute("answer", serverMsg);
	        RequestDispatcher disp = request.getRequestDispatcher("RFIDInOut.html");
	        disp.include(request, response);
			out.println("</body>");
		} catch (Exception e) {
			e.printStackTrace();
		}       
	}
}
