//Secondary OS password is the same but with letters reversed

package deadrecommender;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.mahout.cf.taste.common.TasteException;

public class DeadRecWeb extends HttpServlet {
	@SuppressWarnings("null")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<html>");
		pw.println("<head><title>Dead Recommender</title></title>");
		pw.println("<body>");
		//pw.println("<h1>");
		

		// Test servlet context

		ServletContext servletContext = getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		// pw.println("<br/>File system context path (in TestServlet): " +
		// contextPath);

		String subNamesResult = null;
		String[] names = null;
		String[] neigh = null;

		DeadRecommenderIO dio;

		try {

			dio = new DeadRecommenderIO(contextPath);

			names = new String[10];

			String commaSeparated = request.getParameter("names");

			String[] submittedNames = commaSeparated.split(",");

			subNamesResult = dio.submitNames(submittedNames);

			if (subNamesResult.length() > 0) {

				pw.println("Could not identify: " + subNamesResult
						+ ". They have been excluded. \n");
				pw.println("<br>\n");
				pw.println("<br>\n");

			} 
			
			pw.println("Famous people who have similar taste to you:");

			// if (subNamesResult != "") {

			dio.submitID(1);

			names = dio.getNames();
			neigh = dio.getNeighbours(1);

			// }

			pw.println("<UL>\n");

			for (String s : neigh) {

				if (s != null) {
					pw.println("<LI>" + s + "\n");
				}

				// pw.println("<LI>"+s+"\n");
			}

			pw.println("</UL>\n");

			pw.println(" \n");
			pw.println("So they would recommend to you:");

			pw.println("<UL>\n");
			
			for (String s : names) {
				pw.println("<LI>" + s + "\n");
			}

			// CSVReturn cobj;

			// cobj = new CSVReturn();

			// String[] outString;

			// outString=cobj.getRows();

			// pw.println(outString[1]);

			// pw.println("Jimbob");

			pw.println("</UL>\n");
			//pw.println("</h1>");

		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pw.println("</body></html>");
	}

}