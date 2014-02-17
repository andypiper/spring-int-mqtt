package hello;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

@Configuration
@ImportResource("/hello/integration.xml")
public class Application {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Application.class);
    }

    @Bean
    public String newline() {
        return System.getProperty("line.separator");
    }

    @Bean
    public String tstamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(new Date());
    }

    @Bean
    public Twitter twitterTemplate(OAuth2Operations oauth2) {
        return new TwitterTemplate(oauth2.authenticateClient().getAccessToken());
    }

    @Bean
    public OAuth2Operations oauth2Template(Environment env) {
        return new OAuth2Template(env.getProperty("clientId"), env.getProperty("clientSecret"), "", "https://api.twitter.com/oauth2/token");
    }

    @Bean
    public DefaultMqttPahoClientFactory clientFactory() {
        // could define username and password for broker connection here
        return new DefaultMqttPahoClientFactory();
    }

}
