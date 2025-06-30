#!/bin/bash

# Build and start containers
echo "Building and starting containers..."
docker compose up --build -d

# Show logs
echo "Showing logs..."
docker compose logs -f