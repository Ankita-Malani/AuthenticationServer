package oauthconsumer.com.qpeka.oauth;

import java.util.Properties;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;

public class QpekaOauthAsClientApi extends DefaultApi20 {
	
	 private static final String DEFAULT_METHOD = "https";
	 private static final String DEFAULT_PORT = "8080";
	 private static final String DEFAULT_HOST = "alpha.qpeka.com";
	 private static final String AUTHORIZE_URL = "%s://%s:%s/auth/oauth/v2/authorize?response_type=code";
	 
	 private static String host;
	 private static String port;
	 private static String method;
	 
	 @Override
	 public String getAccessTokenEndpoint()
	 {
	    setHostname();
	    return String.format("%s://%s:%s/auth/oauth/v2/token?grant_type=authorization_code", method, host, port);
	 }
	 
	 @Override
	 public Verb getAccessTokenVerb()
	 {
	    return Verb.POST;
	 }
	 
	 @Override
	 public AccessTokenExtractor getAccessTokenExtractor()
	 {
	    return new JsonTokenExtractor();
	 }
	 
	 @Override
	 public String getAuthorizationUrl(OAuthConfig config)
	 {
	    setHostname();
	    StringBuilder authUrl = new StringBuilder();
	    authUrl.append(String.format(AUTHORIZE_URL, method, host, port));
	 
	    // Append scope if present
	    if (config.hasScope())
	    {
	      authUrl.append("&scope=").append(OAuthEncoder.encode(config.getScope()));
	    }
	 
	    // add redirect URI if callback isn't equal to 'oob'
	    if (!config.getCallback().equalsIgnoreCase("oob"))
	    {
	      authUrl.append("&redirect_uri=").append(OAuthEncoder.encode(config.getCallback()));
	    }
	 
	    authUrl.append("&client_id=").append(OAuthEncoder.encode(config.getApiKey()));
	    return authUrl.toString();
	 }
	 
	 /*
	   * sets the host, port, and method from a properties file the first time this method is run.
	   */
	 private void setHostname()
	 {
	    if (null == host || null == port || null == method)
	    {
	      Properties prop = QpekaOauthAsServerApi.loadProperties();
	      host = prop.getProperty("oauth2.authz.hostname", QpekaOauthAsClientApi.DEFAULT_HOST);
	      port = prop.getProperty("oauth2.authz.port", QpekaOauthAsClientApi.DEFAULT_PORT);
	      method = prop.getProperty("oauth2.authz.method", QpekaOauthAsClientApi.DEFAULT_METHOD);
	    }
	 }
	 
	 
}