# Twitch MCP Server

I got inspired to build this because of the following blog post
by [Max Rydahl Andersen](https://quarkus.io/author/maxandersen): https://quarkus.io/blog/mcp-server/.
I had written a Twitch Chat integration before, so decided to combine that knowledge with a Quarkus based MCP server as
described by Max.

This project is described in a bit more detail on my blog: https://tomcools.be/post/2025-jan-twitch-chat-mcp/

## Building the MCP Server

This application is currently not pushed to Maven Central, so you need to build it locally and install it in your .m2
folder using `mvn install`.
Next we need a way to start the JAR file. In the examples below you'll see I
used [JBang](https://www.jbang.dev/documentation/guide/latest/installation.html).

## Running the MCP server

### With MCP Inspector

Run `npx @modelcontextprotocol/inspector` to start a local inspector service.

- Create an MCP configuration to run the following
    - command: `jbang`
    - arguments: `["--quiet", "-Dtwitch.channel=YOUR_CHANNEL_NAME", "-Dtwitch.auth=YOUR_API_KEY", "be.tomcools:twitch-mcp:1.0.0-SNAPSHOT:runner"]`

Now you can manually call the tools.

### With Claude Desktop

For Claude in claude_desktop_config.json

```json
{
  "mcpServers": {
    "twitch-mcp-tomcools": {
      "command": "jbang",
      "args": [
        "--quiet",
        "-Dtwitch.channel=YOUR_CHANNEL_NAME",
        "-Dtwitch.auth=YOUR_API_KEY",
        "be.tomcools:twitch-mcp:1.0.0-SNAPSHOT:runner"
      ]
    }
  }
}
```

After restart, the tool should appear in your Claude UI.