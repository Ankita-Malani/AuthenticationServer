package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oauthProducer.tokenGenerator.AuthorizeToken;
import oauthProducer.tokenGenerator.RequestTokenGenerator;


import com.google.gson.Gson;


public class AuthorizeTokenService extends HttpServlet {

	private static final long serialVersionUID = -288515550346140706L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");

		String token = request.getParameter("token");
		String key = request.getParameter("key");
		String secret = request.getParameter("secret");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (token != null && !token.isEmpty()) {
			if (AuthorizeToken.getInstance().verifyToken(
					token, key, secret)) {
				try {
					response.sendRedirect("http://198.100.180.45");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				out.println(new Gson().toJson(RequestTokenGenerator.getInstance().generateToken(token, key,secret)));
			}
		} else {
			out.println(new Gson().toJson(RequestTokenGenerator.getInstance().generateToken(token, key, secret)));
		}
	}
}
