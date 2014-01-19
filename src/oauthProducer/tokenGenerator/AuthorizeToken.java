package oauthProducer.tokenGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Utils.DBResourceHandler;


public class AuthorizeToken {
	
	public static AuthorizeToken instance = null;
	
	public static AuthorizeToken getInstance() {
		return instance==null ? instance = new AuthorizeToken() : instance;
	}

	/*public boolean verifyToken(String token, String key, String secret) {
		
		Connection conn = null;
		try {		
			conn = conn != null ? conn : DBResourceHandler.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String query = "select token from qpekatoken.usertokens where token='" + token + "' AND status='active'";
			
			stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.executeQuery();
			
			PreparedStatement stmt1 = null;
			if(rs.next()) {			
				return true;
			}			
			
			DBResourceHandler.close(conn);
			DBResourceHandler.close(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return false;
	}
	*/
	
	public boolean verifyToken(String token, String key, String secret) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = conn != null ? conn : DBResourceHandler.getConnection();
			
			ResultSet rs = null;
			String query = "select token from qpekatoken.usertokens where `token`='"
					+ token
					+ "' AND `key`='"
					+ key
					+ "' AND `secret`='"
					+ secret + "' AND `status`='active'";
			stmt = conn
					.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBResourceHandler.close(conn);
			DBResourceHandler.close(stmt);
		}
		return false;
	}

	
	/*public static void main(String[] args) {
		AuthorizeToken.getInstance().verifyToken("[B@7ecd2c3c",
				"5f9eb11b-1426-4a4b-8fe8-ddcc890dcb54", "D5knzayR", (long) 1);
	}*/

}
