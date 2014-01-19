package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Helloworld extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4891339855104006045L;
	private String message;
	
	public void init() throws ServletException {
		message = "hello world";
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<h1>"+ message +"</h1>");
	}
}
