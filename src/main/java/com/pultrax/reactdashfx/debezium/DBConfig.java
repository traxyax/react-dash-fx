package com.pultrax.reactdashfx.debezium;

import io.debezium.config.Configuration;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.context.annotation.Bean;

public class DBConfig {

    private DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

    @Bean
    public Configuration dbConnector() {
        String dbHost = "localhost";
        int dbPort = 3306;
        String  dbUsername = "root";
        String dbPassword = "";
        String dbName = "db_javafx";

        return io.debezium.config.Configuration.create()
                .with("name", dbName + "-mysql-connector")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/tmp/offsets.dat")
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", dbHost)
                .with("database.port", dbPort)
                .with("database.user", dbUsername)
                .with("database.password", dbPassword)
                .with("database.dbname", dbName)
                .with("database.include.list", dbName)
                .with("include.schema.changes", "false")
                .with("database.server.id", "10181")
                .with("database.server.name", dbName + "-mysql-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", "/tmp/dbhistory.dat")
                .build();
    }
}
