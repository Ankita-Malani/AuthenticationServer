package oauthconsumer.com.qpeka.oauth;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class QpekaOauthApi extends DefaultApi10a  {

	
	public static final String REQUEST_URL = "localhost:8080/QPEKA/v0.2/oauth/requestToken";
	private static final String AUTHORIZE_URL = "localhost:8080/QPEKA/v0.2/oauth/authenticateUser?qpekaoauth_token=%s";
	  
	private final Set<String> scope;
	
	public static QpekaOauthApi instance = null;
	
	public QpekaOauthApi() {
		scope = Collections.emptySet();
	}
	
	public QpekaOauthApi(Set<String> set) {
		this.scope = Collections.unmodifiableSet(set);
	}
	
	@Override
	public String getAccessTokenEndpoint() {
		return "localhost:8080/QPEKA/v0.2/oauth/accessToken";
	}
	
	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return String.format(AUTHORIZE_URL, requestToken.getToken());
	}
	
	public static QpekaOauthApi getInstance() {
		return instance == null ? instance = new QpekaOauthApi() : instance;
	}
	
	@Override
	public String getRequestTokenEndpoint() {
		return (scope.isEmpty() ? REQUEST_URL : REQUEST_URL + "?scope=" + scopeAsString());
	}
	
	private String scopeAsString() {
		
		System.out.println(scope);
		StringBuilder builders = new StringBuilder();
		for(String sc : scope) {
			builders.append(sc);
		}
		return builders.substring(1);
	}
	
	 public static QpekaOauthApi withScopes(String scopes)
	  {
		 Set<String> scopeSet = new HashSet<String>(Arrays.asList(scopes));
	    return new QpekaOauthApi(scopeSet);
	  }
	 
	
	public static void main(String[] args) {
		System.out.println(QpekaOauthApi.getInstance().getAccessTokenEndpoint());
		System.out.println(QpekaOauthApi.getInstance().getRequestTokenEndpoint());
		System.out.println(QpekaOauthApi.getInstance().getAuthorizationUrl(new Token(REQUEST_URL, AUTHORIZE_URL)));
		System.out.println(QpekaOauthApi.getInstance().getAuthorizationUrl(new Token(QpekaOauthApi.getInstance().getAccessTokenEndpoint(), AUTHORIZE_URL)));
	}
}
