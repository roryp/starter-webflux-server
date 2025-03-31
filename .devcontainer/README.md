# DevContainer for Spring AI MCP Server

This folder contains the configuration files for the VS Code DevContainer setup for developing the Spring AI MCP Server.

## What's Included

- Java 17 development environment
- Maven 3.8.7
- Git, curl, jq, and other utility tools
- Spring Boot extensions for VS Code
- Java development extensions for VS Code

## Getting Started

1. Install [VS Code](https://code.visualstudio.com/)
2. Install the [Remote - Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers) extension
3. Clone this repository
4. Open the repository in VS Code
5. When prompted, click "Reopen in Container"
   - Alternatively, press F1 and select "Dev Containers: Reopen in Container"

## Features

- Pre-configured Java 17 environment
- Maven already installed and configured
- Project dependencies automatically downloaded on container creation
- Port 8080 forwarded to your local machine
- Git configured for development

## Customization

You can customize the DevContainer by modifying:
- `.devcontainer/devcontainer.json` - Container configuration
- `.devcontainer/Dockerfile` - Container image definition