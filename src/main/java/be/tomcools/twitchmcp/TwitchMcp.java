package be.tomcools.twitchmcp;

import be.tomcools.twitchmcp.client.TwitchClient;
import io.quarkiverse.mcp.server.TextContent;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import io.quarkiverse.mcp.server.ToolResponse;
import jakarta.inject.Inject;

/**
 * Contains the MCP Definitions.
 */
public class TwitchMcp {
    @Inject
    TwitchClient client;

    @Tool(description = "Send message to the Twitch Chat")
    ToolResponse sendMessageToChat(@ToolArg(description = "The message") String message) {
        client.sendMessage(message);
        return ToolResponse.success(new TextContent("Successfully sent message: " + message));
    }
}
