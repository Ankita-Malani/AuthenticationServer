package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloForm extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5166386479094866796L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			response.setContentType("text/html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.print("Hello " + request.getParameter("firstname") + "" + request.getParameter("lastname"));	
	}
}
