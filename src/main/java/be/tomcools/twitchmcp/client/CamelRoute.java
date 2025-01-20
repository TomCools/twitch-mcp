package be.tomcools.twitchmcp.client;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Apache Camel route, using camel-irc to connect to Twitch Chat (which is IRC Based).
 */
@ApplicationScoped
public class CamelRoute extends RouteBuilder {

    @ConfigProperty(name = "twitch.channel")
    String channel;
    @ConfigProperty(name = "twitch.auth")
    String authToken;

    @Override
    public void configure() throws Exception {
        String twitchIrcUrl = "irc:%s@irc.chat.twitch.tv:6667?nickname=%s&password=oauth:%s&channels=#%s"
                .formatted(channel, channel, authToken, channel);

        // Receives messages from Twitch Chat and logs them.
        from(twitchIrcUrl)
                .routeId("receiveMessageFromTwitch")
                .log("RECEIVED MESSAGE")
                .to("log:info");

        // Allows us to send messages to Twitch
        from("direct:sendToIrc")
                .routeId("sendMessageToTwitch")
                .setHeader("irc.sendTo", constant("#" + channel))
                .setBody(simple("${body}"))
                .to(twitchIrcUrl);

    }
}
