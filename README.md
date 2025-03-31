# Spring AI MCP Weather and Echo Server Sample with WebFlux Starter

This sample project demonstrates how to create an MCP server using the Spring AI MCP Server Boot Starter with WebFlux transport. It implements both a weather service that exposes tools for retrieving weather information using the National Weather Service API and an echo service that provides simple text echo functionality.

For more information, see the [MCP Server Boot Starter](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html) reference documentation.

## Overview

The sample showcases:
- Integration with `spring-ai-mcp-server-webflux-spring-boot-starter`
- Support for SSE (Server-Sent Events)
- Automatic tool registration using Spring AI's `@Tool` annotation
- Two weather-related tools:
  - Get weather forecast by location (latitude/longitude)
  - Get weather alerts by US state
- Echo service tools:
  - Echo back messages exactly as received
- Function-based tool:
  - Convert text to uppercase

## Dependencies

The project requires the Spring AI MCP Server WebFlux Boot Starter:

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-mcp-server-webflux-spring-boot-starter</artifactId>
</dependency>
```

This starter provides:
- Reactive transport using Spring WebFlux (`WebFluxSseServerTransport`)
- Auto-configured reactive SSE endpoints
- Included `spring-boot-starter-webflux` and `mcp-spring-webflux` dependencies

## Building the Project

Build the project using Maven:
```bash
./mvnw clean install -DskipTests
```

## Running the Server

```bash
java -jar target/mcp-echo-starter-webflux-server-0.0.1-SNAPSHOT.jar
```

## Using cURL with the MCP Server

The MCP server uses Server-Sent Events (SSE) for bi-directional communication. To interact with the server using cURL, follow these steps:

### 1. Establish an SSE Connection

First, establish a connection to the SSE endpoint to get a session ID:

```bash
curl -N http://localhost:8080/sse
```

This will return something like:

```
event:endpoint
data:/mcp/message?sessionId=2cee6f57-60c9-4650-941b-b4048230c45a
```

**IMPORTANT:** Keep this terminal window open as all responses from the server will be returned through this SSE stream, not through the POST request response.

### 2. Send Messages with the Session ID

In a separate terminal window, use the session ID returned in step 1 to make calls to the MCP server. For this specific MCP server implementation, use the following format:

```bash
curl -X POST \
  "http://localhost:8080/mcp/message?sessionId=YOUR_SESSION_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "jsonrpc": "2.0",
    "method": "call_tool",
    "id": "123",
    "params": {
      "name": "echoMessage",
      "parameters": {
        "message": "Hello from curl! Testing MCP connection."
      }
    }
  }'
```

Replace `YOUR_SESSION_ID` with the actual session ID received from the SSE connection.

**Note:** The response to your API call will not be returned in the POST response. Instead, it will appear in the first terminal window where the SSE connection is established. This is how the MCP protocol works - all responses flow through the SSE channel.

### Example Tool Calls

#### Echo a message:

```bash
curl -X POST \
  "http://localhost:8080/mcp/message?sessionId=YOUR_SESSION_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "jsonrpc": "2.0",
    "method": "call_tool",
    "id": "123",
    "params": {
      "name": "echoMessage",
      "parameters": {
        "message": "Hello from curl! Testing MCP connection."
      }
    }
  }'
```

#### Get weather forecast:

```bash
curl -X POST \
  "http://localhost:8080/mcp/message?sessionId=YOUR_SESSION_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "jsonrpc": "2.0",
    "method": "call_tool",
    "id": "456",
    "params": {
      "name": "getWeatherForecastByLocation",
      "parameters": {
        "latitude": 47.6062,
        "longitude": -122.3321
      }
    }
  }'
```

#### List available tools:

```bash
curl -X POST \
  "http://localhost:8080/mcp/message?sessionId=YOUR_SESSION_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "jsonrpc": "2.0",
    "method": "list_tools",
    "id": "list-1"
  }'
```

### Troubleshooting

If you encounter an "Invalid message format" error, ensure that:

1. Your JSON is properly formatted with no syntax errors
2. You're using the correct message structure (following JSON-RPC 2.0 format)
3. The session ID is correctly appended to the URL as a query parameter
4. You maintain an active SSE connection during your API calls

If your POST requests appear to work (no error message) but you don't see any response:
1. Check the terminal window where you established the SSE connection
2. Ensure the SSE connection is still active and hasn't timed out
3. Try sending another tool call to verify connectivity

Here's the complete protocol flow:

1. Start an SSE connection with `curl -N http://localhost:8080/sse` in Terminal 1
2. Receive a session ID
3. Use that session ID in subsequent JSON-RPC 2.0 formatted calls to `/mcp/message?sessionId=YOUR_SESSION_ID` in Terminal 2
4. Watch for responses in Terminal 1 (the SSE window)

## Docker Support

The project includes a Dockerfile for containerization:

```bash
docker build --pull --rm -f 'DockerFile' -t 'starterwebfluxserver:latest' '.' 
docker run -d -p 8080:8080 starterwebfluxserver:latest
```

## Last Updated

This documentation was last updated on: March 31, 2025
