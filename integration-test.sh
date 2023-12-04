#!/bin/bash

docker-compose down
docker volume rm pierpont_postgres || true
docker-compose build --no-cache
docker-compose up -d

docker run \
  -v "${PWD}"/src/main/resources/Pierpont.postman_collection.json:/etc/postman/Pierpont.postman_collection.json \
  --network pierpont_pierpont \
  -t postman/newman \
  run /etc/postman/Pierpont.postman_collection.json --env-var "BASE_URL=pierpont:8080"
