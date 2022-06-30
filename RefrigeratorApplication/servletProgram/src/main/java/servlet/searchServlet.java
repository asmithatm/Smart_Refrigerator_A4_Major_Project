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
 * Servlet implementation class searchServlet
 */
public class searchServlet extends HttpServlet {	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter(); 
		String category = "", subCategory = "";		
        String cat=request.getParameter("cat");  
        String subCat=request.getParameter("subCat");  
        String brand=request.getParameter("brand");  
        String item=request.getParameter("item");       
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'");
        out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Search an Item</title>");
        out.println("<link rel='stylesheet' href='search.css'>");
        out.println("</head>");
        out.println("<body>");
        
        switch(cat)
			{
				case "Solid" : 		category = "S";
									switch(subCat) 
									{
										case "Medicines"  			 :	subCategory = "02";
																		break;
										case "Fruits and Vegetables" : 	subCategory = "03";
																		break;
										case "Bakery Items"  		 :	subCategory = "04";
																		break;
										case "Meat"  				 :	subCategory = "05";
																		break;
										case "Cereals"  			 :	subCategory = "06";
																		break;
									}
									break;
				case "Liquid" : 	category = "L";
									switch(subCat) 
									{
										case "Drinks"  			  :	subCategory = "02";
																	break;
										case "Milk Products"  	  :	subCategory = "03";
																	break;
										case "Medicinal Syrups"   :	subCategory = "04";
																	break;
									}	
									break;
				case "Semi Solid" : category = "M";
									switch(subCat) 
									{
										case "Milk Products"  :	subCategory = "02";
																break;
										case "Bakery Items"   :	subCategory = "03";
																break;
									}
									break;
			}

        String encmsg="v2.0#S#30#001#03#I#"+category+"&"+subCategory+"&"+brand+"&"+item+"*";      
        try {
			String serverMsg = TCPClient.client(encmsg);
	        out.println("<div class='items'>"+serverMsg+"</div>");
	        request.setAttribute("answer", serverMsg);
	        RequestDispatcher disp = request.getRequestDispatcher("search.html");
	        disp.include(request, response);
			out.println("</body>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
