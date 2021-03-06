package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import client.TCPClient;
/**
 * Servlet implementation class closeServerServlet
 */
public class closeServerServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Server Closed</title>");
        out.println("<meta charset='UTF-8'");
        out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link rel='stylesheet' href='displayAll.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("");
		
		String encmsg="v2.0#D#30#001#06#N*";
		try {
			String serverMsg = TCPClient.client(encmsg);
	        out.println("<div class='items'>"+serverMsg+"</div>");
	        out.println("<h1>Refrigerator Closed!<br> You can no longer perform operations on the Refrigerator Server</h1>");
	        out.println("<h2>Please Close this Window</h2>");
			out.println("</body>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
