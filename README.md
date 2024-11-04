```shell
echo '{
  "name": "aflow-connector",
  "config": {
      "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
      "database.hostname": "postgres",
      "database.port": "5432",
      "database.user": "postgres",
      "database.password": "postgres",
      "database.dbname": "aflow_db",
      "database.server.name": "dbserver1",
      "table.include.list": "public.events"
    }
}' > debezium.json
```

```shell
curl -H 'Content-Type: application/json' debezium:8083/connectors --data "@debezium.json"
```

```SQL
CREATE or replace TABLE kafka_input (json_message String)
        ENGINE = Kafka
        SETTINGS
            kafka_broker_list = 'kafka:29092',
            kafka_topic_list = 'dbserver1.public.events',
            kafka_group_name = 'chgroup1',
            kafka_format = 'JSONAsString';
```

```SQL
SELECT *
FROM kafka_input
SETTINGS stream_like_engine_allow_direct_select = 1
```