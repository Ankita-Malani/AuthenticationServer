package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oauthProducer.tokenGenerator.RequestTokenGenerator;

import com.google.gson.Gson;

public class RequestTokenService extends HttpServlet {

	private static final long serialVersionUID = -2953344403783707315L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String requestToken = request.getParameter("token");
		String key = request.getParameter("key");
		String secret = request.getParameter("secret");

		Map<String, Object> tokenMap = null;

		tokenMap = RequestTokenGenerator.getInstance().generateToken(
				requestToken, key, secret);

		/*
		 * redirecting to jsp page through this commented code is causing jasper
		 * Exception
		 
		  if(tokenMap != null && !tokenMap.isEmpty()) {
		  request.setAttribute("generatedToken", new Gson().toJson(tokenMap));
		  }
		 
		  RequestDispatcher reqDispatcher = request.getRequestDispatcher("GeneratedRequestToken.jsp");
		   try {
		  	reqDispatcher.forward(request, response); 
		  } catch (ServletException e) { 
		  		e.printStackTrace(); 
		  } catch (IOException e) {
		  		e.printStackTrace();
		  }
		 */
		
		if(tokenMap != null && !tokenMap.isEmpty()) {
			out.println("<html><body><input type='text' value='"+tokenMap.get("key")+"' name='key' readonly /><br/>" +
					"<input type='text' value='"+tokenMap.get("token")+"' name='token' readonly /><br/>" +
							"<input type='text' value='"+tokenMap.get("secret")+"' name='secret' readonly /> </body></html>");
		}
		
		
	}
}
