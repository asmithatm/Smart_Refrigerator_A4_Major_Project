package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import client.TCPClient;
/**
 * Servlet implementation class displayAllServlet
 */
public class displayAllServlet extends HttpServlet {	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Display All Items</title>");
        out.println("<meta charset='UTF-8'");
        out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' href='displayAll.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Items in the Refrigerator are : </h1>");		
		String encmsg="v2.0#S#30#001#01#N*";
		try {
			String serverMsg = TCPClient.client(encmsg);
	        out.println("<div class='items'>"+serverMsg+"</div>");
	        out.println("<div><a href='http://localhost:9090/servletProgram/'><img alt='Home' src='homeBtncircle.png' width='70' height='70'> </a></div>");
			out.println("</body>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
