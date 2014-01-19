package oauthconsumer.com.qpeka.oauth;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;

public class QpekaOauthAsServerApi extends DefaultApi10a
{
 private static final String AUTHORIZE_URL = "%s://%s/QPEKA/v0.2/user/login?oauth_token=%s";
 //private static final String REQUEST_TOKEN_RESOURCE = "%s://%s:%s/auth/oauth/v1/request";
 private static final String REQUEST_TOKEN_RESOURCE = "%s://%s/AuthenticationServer/qpeka/oauth/generatetoken";
 private static final String ACCESS_TOKEN_RESOURCE = "%s://%s/QPEKA/v0.2/user/login/token";
 
 private static String host, method, port;
 
 @Override
 public String getAccessTokenEndpoint()
 {
    setHostname();
    return String.format(ACCESS_TOKEN_RESOURCE, method, host, port);
 }
 
 @Override
 public String getAuthorizationUrl(Token token)
 {
    setHostname();
    return String.format(AUTHORIZE_URL, method, host, token.getToken());
 }
 
 @Override
 public String getRequestTokenEndpoint()
 {
     setHostname();
     System.out.println(method+host+port);
     System.out.println(REQUEST_TOKEN_RESOURCE);
    return String.format(REQUEST_TOKEN_RESOURCE, method, host, port);
 }
 
 /*
  * Loads the host, port, and method from the properties file the first time this method is run.
  */
private void setHostname()
{
   if (null == host || null == port || null == method)
   {
     Properties prop = loadProperties();
     host = prop.getProperty("oauth1.hostname", "http://198.100.180.45");
     port = prop.getProperty("oauth1.port", "8080");
     method = prop.getProperty("oauth1.method", "http");
   }
}

protected static Properties loadProperties()
{
   final Properties prop = new Properties();
   try
   {
     final InputStream propertiesStream = QpekaOauthAsServerApi.class.getResourceAsStream("/mysql.properties");
     if (propertiesStream != null)
       prop.load(propertiesStream);
   }
   catch (IOException e)
   {
     throw new OAuthException("Error while reading properties file", e);
   }
   return prop;
}
}