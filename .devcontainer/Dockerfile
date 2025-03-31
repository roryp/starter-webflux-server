FROM mcr.microsoft.com/devcontainers/java:17

ARG MAVEN_VERSION="3.8.7"
ARG USER="vscode"

# Install additional packages
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends git-core curl lsof jq

# Clean up
RUN apt-get autoremove -y \
    && apt-get clean -y \
    && rm -rf /var/lib/apt/lists/*

# Allow for a consistent java home location for settings - image is changing over time
RUN if [ ! -d "/docker-java-home" ]; then ln -s "${JAVA_HOME}" /docker-java-home; fi

# Set the default shell to bash instead of sh
ENV SHELL /bin/bash