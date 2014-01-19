package oauthconsumer.com.qpeka.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import Utils.BCrypt;

public class QpekaOauth {

	private static final String URL = "http://www.qpeka.com/QPEKA/v0.2/oauth/requestToken";

	public static void main(String[] args) {

		String nonce = BCrypt.hashpw(
				Integer.toString(((byte) System.currentTimeMillis()) ^ 0xDE),
				BCrypt.gensalt());
		
		OAuthService service = new ServiceBuilder()
				.provider(QpekaOauthAsServerApi.class).apiKey("ggfdg").apiSecret("gfdgfdg").build();

		Token requestToken = service.getRequestToken();
		System.out.println(requestToken);
		System.out.println(service.getAuthorizationUrl(requestToken));
		
		//Verifier verifier = new Verifier(request.parameter.token);
		 
		// Trade the Request Token and Verifier for the Access Token
		//Token accessToken = service.getAccessToken(requestToken, verifier);
	}
}
