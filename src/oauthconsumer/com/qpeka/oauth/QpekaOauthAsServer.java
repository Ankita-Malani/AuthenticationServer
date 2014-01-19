package oauthconsumer.com.qpeka.oauth;

import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class QpekaOauthAsServer {

	OAuthConfig config;
	DefaultApi10a api;

	private static final String PROTECTED_RESOURCE_URI = "http://www.qpeka.com/QPEKA/v0.2/user/login:(userid, tokenid, firstname, lastname, middlename, gender)";

	// private static final String PROTECTED_RESOURCE_URI =
	// http://www.qpeka.com/QPEKA/v0.2/people/~/connections:(id,last-name);

	/*public static void main(String[] args) {

		String nonce = BCrypt.hashpw(
				Integer.toString(((byte) System.currentTimeMillis()) ^ 0xDE),
				BCrypt.gensalt());

		OAuthService service = new ServiceBuilder().provider(QpekaOauthAsServerApi.class)
				.apiKey(nonce).apiSecret(nonce).callback("http://198.100.180.44").build();

		// Token requestToken = service.getRequestToken();
		Token requestToken = service.getRequestToken();

		System.out.println(requestToken.toString());
		Scanner in = new Scanner(System.in);

		Verifier verifier = new Verifier(in.next());
		// Verifier verifier = new Verifier(request.parameter.token);

		// Trade the Request Token and Verifier for the Access Token
		Token accessToken = service.getAccessToken(requestToken, verifier);
		OAuthRequest request = new OAuthRequest(Verb.POST,
				PROTECTED_RESOURCE_URI);
		request.addBodyParameter("param", "value");
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println(response.getBody());
	}*/
}
