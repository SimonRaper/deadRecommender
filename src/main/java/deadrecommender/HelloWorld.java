//Secondary OS password is the same but with letters reversed

package deadrecommender;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.mahout.cf.taste.common.TasteException;


public class HelloWorld extends HttpServlet{ 
  public void doGet(HttpServletRequest request, 
  HttpServletResponse response)
  throws ServletException,IOException{
  response.setContentType("text/html");
  PrintWriter pw = response.getWriter();
  
  
  pw.println("<html>");
  pw.println("<head><title>Hello World</title></title>");
  pw.println("<body>");
  pw.println("<h1>");
  pw.println("<UL>\n");
  
  //Test servlet context
  
  ServletContext servletContext = getServletContext();
  String contextPath = servletContext.getRealPath(File.separator);
  //pw.println("<br/>File system context path (in TestServlet): " + contextPath);
  
  
  String[] names = null;
  String[] neigh = null;
  
  
  DeadRecommenderIO dio;

  
  try {
	
	  dio = new DeadRecommenderIO(contextPath);

  
    names= new String[10];
    //String[] neigh= new String[200];
  
    //pw.println(dio.getUserID(request.getParameter("name")));
    
    String commaSeparated = request.getParameter("names");
    
    String [] submittedNames = commaSeparated.split(",");
    
    //String[] submittedNames = {request.getParameter("param1"), request.getParameter("param2"), request.getParameter("param3"), request.getParameter("param2")};
    
    pw.println("Famous people who liked the same people as you:");
    
    dio.submitNames(submittedNames);
     
    dio.submitID(1);
    names = dio.getNames();
    neigh=dio.getNeighbours(1);
  
    

    } catch (TasteException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    }
  
	for (String s : neigh)  {
		pw.println("<LI>"+s+"\n");
    }
	
	pw.println("</UL>\n");
	
	pw.println(" \n");
	pw.println("So they would recommend to you:");
	
	for (String s : names)  {
		pw.println("<LI>"+s+"\n");
    }

  //CSVReturn cobj;
  
  //cobj = new CSVReturn();
  
  //String[] outString;
  
  //outString=cobj.getRows();
  
  //pw.println(outString[1]);
  
  //pw.println("Jimbob");
  

  pw.println("</UL>\n");
  pw.println("</h1>");
  pw.println("</body></html>");
  }
}