package be.tomcools.twitchmcp.client;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class TwitchClient {

    @Inject
    ProducerTemplate producerTemplate;

    public void sendMessage(String message) {
        producerTemplate.sendBody("direct:sendToIrc", message);
    }

    // Post something to the Twitch chat after connect.
    void onStart(@Observes StartupEvent ev) {
        this.sendMessage("Twitch MCP Server connected");
    }
}
