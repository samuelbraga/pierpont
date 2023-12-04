#!/bin/bash

docker ps
echo "########"
docker ps -a
CONTAINER_ID=$(docker ps -aqf "name=pierpont_pierpont_1")
BASE_URL="$CONTAINER_ID:8080"
echo "$CONTAINER_ID"
echo "$BASE_URL"
newman run src/main/resources/Pierpont.postman_collection.json --env-var "BASE_URL=$BASE_URL"
