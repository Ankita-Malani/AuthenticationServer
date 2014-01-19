package test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oauthconsumer.com.qpeka.oauth.QpekaOauthAsServerApi;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import Utils.BCrypt;



public class TestOAuth extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2895733336074519058L;
	private static final String PROTECTED_RESOURCE_URI = "http://www.qpeka.com/QPEKA/v0.2/user/login:(userid, tokenid, firstname, lastname, middlename, gender)";
	
	public void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setContentType("text/html");
		/*String nonce = BCrypt.hashpw(
				Integer.toString(((byte) System.currentTimeMillis()) ^ 0xDE),
				BCrypt.gensalt());
*/
		OAuthService service = new ServiceBuilder().provider(QpekaOauthAsServerApi.class)
				.apiKey("ggfdg").apiSecret("gfdgfdg").callback("").build();

		System.out.println("3");
		// Token requestToken = service.getRequestToken();
		Token requestToken = service.getRequestToken();

		System.out.println("4");
		System.out.println(requestToken.toString());
		
		
		System.out.println("5 \n authorizationurl : "+service.getAuthorizationUrl(requestToken));
		String req = service.getAuthorizationUrl(requestToken);
		
		 //Verifier verifier = new Verifier(request.parameter.token);
		Verifier verifier = new Verifier(req);
		System.out.println(verifier);
		// Verifier verifier = new Verifier(request.parameter.token);

		// Trade the Request Token and Verifier for the Access Token
		Token accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("accesss Token" + accessToken);
		
		OAuthRequest request = new OAuthRequest(Verb.POST,
				PROTECTED_RESOURCE_URI);
		request.addBodyParameter("param", "value");
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println(response.getBody());
	}
}
