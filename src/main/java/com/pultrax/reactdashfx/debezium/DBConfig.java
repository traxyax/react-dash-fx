package com.pultrax.reactdashfx.debezium;

import io.debezium.config.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class DBConfig {

    @Value("${database.host}")
    private String dbHost = "localhost";

    @Value("${database.port}")
    private String dbPort = "3306";

    @Value("${database.username}")
    private String  dbUsername = "trax";

    @Value("${database.password}")
    private String dbPassword = "pass";

    @Value("${database.name}")
    private String dbName = "db_javafx";

    @Value("${database.type}")
    private String dbType = "mysql";

    @Bean
    public Configuration dbConnector() {

        return io.debezium.config.Configuration.create()
                .with("name", dbName + "-" + dbType + "-connector")
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
                .with("database.server.name", dbName + "-" + dbType + "-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", "/tmp/dbhistory.dat")
                .build();
    }
}
