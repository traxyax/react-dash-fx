package com.pultrax.reactdashfx.debezium;

import com.pultrax.reactdashfx.fxcontroller.DashboardController;
import io.debezium.config.Configuration;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import javafx.application.Platform;
import org.apache.kafka.connect.source.SourceRecord;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DebeziumListener {

    private DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

    public DebeziumListener(Configuration dbConnectorConfiguration) {
        this.debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(dbConnectorConfiguration.asProperties())
                .notifying(this::handleEvent)
                .build();

        dashboardController = DashboardController.getInstance();
    }

    private DashboardController dashboardController;

    public void handleEvent(List<RecordChangeEvent<SourceRecord>> recordChangeEvents, DebeziumEngine.RecordCommitter<RecordChangeEvent<SourceRecord>> recordChangeEventRecordCommitter) {

        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                dashboardController.update();
            }
        });
    }

    private final Executor executor = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void start() {
        this.executor.execute(debeziumEngine);
    }

    @PreDestroy
    public void stop() throws IOException {
        if (this.debeziumEngine != null) {
            this.debeziumEngine.close();
            
        }
    }
}
