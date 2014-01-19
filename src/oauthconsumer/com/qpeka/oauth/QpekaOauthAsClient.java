package oauthconsumer.com.qpeka.oauth;

import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class QpekaOauthAsClient {

	private static final String PROTECTED_RESOURCE_URL = "https://alpha.qpeka.com";
	private static final Token EMPTY_TOKEN = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String apikey = "Consumer";
		String apiSecret = "Secret";
		String callback = "http://198.100.180.44";
		createOauthServiceObj(apikey, apiSecret, callback);

	}

	private static void createOauthServiceObj(String apikey, String apiSecret,
			String callback) {

		// The callback URL that was registered must match the “redirect_uri”
		// parameter exactly!
		OAuthService service = new ServiceBuilder()
				.provider(QpekaOauthAsClientApi.class).apiKey(apikey)
				.apiSecret(apiSecret).callback(callback).build();

		// Obtain the Authorization URL
		System.out.println("Fetching the Authorization URL...");
		String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
		System.out.println("Got the Authorization URL!");
		System.out
				.println("Now go and authorize Scribe here (Log in as 'User1'/'Passw0rd'):");
		System.out.println(authorizationUrl);
		System.out.println("And paste the authorization code here");
		System.out.print(">>");
		Scanner in = new Scanner(System.in);
		Verifier verifier = new Verifier(in.nextLine());
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET,
				PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getCode());
		System.out.println(response.getBody());
	}

}
