#/bin/bash
docker run --rm \
  -v ${PWD}/src/main/resources/db/migration:/flyway/sql \
  flyway/flyway \
  -url=jdbc:postgresql://192.168.100.69/pierpont \
  -user=postgres \
  -password=postgres \
  migrate

