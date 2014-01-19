package oauthProducer.tokenGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.codec.digest.DigestUtils;

import Utils.DBResourceHandler;



public class RequestTokenGenerator {

	public static RequestTokenGenerator instance = null;

	public static RequestTokenGenerator getInstance() {
		return instance == null ? instance = new RequestTokenGenerator()
				: instance;
	}

	public Map<String, Object> generateToken(String token, String key,
			String secret) {
		String newtoken = null;
		if (key != null && !key.isEmpty() && secret != null
				&& !secret.isEmpty()) {
			if (token != null && !token.isEmpty()) {
				if (AuthorizeToken.getInstance()
						.verifyToken(token, key, secret)) {
					newtoken = token;
					//TODO ask : on success what to return. i.e message or
					// tokenmap
				} else {
					String nonce = Integer.toString(((byte) System
							.currentTimeMillis()) ^ 0xDE);
					newtoken = DigestUtils.md5(nonce + key + secret).toString();
					insert(newtoken, key, secret);
				}
			} else {
				String nonce = Integer.toString(((byte) System
						.currentTimeMillis()) ^ 0xDE);
				newtoken = DigestUtils.md5(nonce + key + secret).toString();
				// String key = UUID.randomUUID().toString();
				// String secret = RandomStringUtils.random(8, true, true);
				insert(newtoken, key, secret);
			}

			Map<String, Object> tokenMap = new HashMap<String, Object>();
			tokenMap.put("token", newtoken);
			tokenMap.put("key", key);
			tokenMap.put("secret", secret);
			return tokenMap;
		} //TODO else do we have to generate token????
		return null;
	}

	public void insert(String token, String key, String secret) {
		Connection conn = null;
		try {
			conn = conn != null ? conn : DBResourceHandler.getConnection();
			PreparedStatement stmt = null;
			// ResultSet rs = null;
			String query = "insert into qpekatoken.usertokens(`token`, `key`, `secret`, `status`) value('"
					+ token + "','" + key + "','" + secret + "','active')";

			stmt = conn
					.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			/*
			 * rs = stmt.getGeneratedKeys(); while(rs != null && rs.next()) {
			 * usertoken = rs.getString(2); }
			 */

			DBResourceHandler.close(conn);
			DBResourceHandler.close(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) { RequestTokenGenerator r = new
	 * RequestTokenGenerator();
	 * System.out.println(r.generateToken("[B@43b09468", "1234556", "bghtyu"));
	 * }
	 */

}
