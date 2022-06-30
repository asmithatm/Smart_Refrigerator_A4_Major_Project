package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import client.TCPClient;
/**
 * Servlet implementation class shelflifeServlet
 */
public class shelflifeServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		PrintWriter out = res.getWriter();		
		out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'");
        out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Shelf Life Report</title>");
        out.println("<link rel='stylesheet' href='shelf.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Shelf Life Report: </h1>");
		
		String encmsg="v2.0#S#30#001#04#N*";
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
