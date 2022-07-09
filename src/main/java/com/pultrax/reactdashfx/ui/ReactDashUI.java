package com.pultrax.reactdashfx.ui;

import com.pultrax.reactdashfx.ReactDashFxApplication;
import com.pultrax.reactdashfx.debezium.DBConfig;
import com.pultrax.reactdashfx.debezium.DebeziumListener;
import io.debezium.config.Configuration;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ReactDashUI extends Application {

    private static ConfigurableApplicationContext configurableApplicationContext;
    private Configuration dbConnectorConfiguration;
    private DebeziumListener debeziumListener;

    public static ConfigurableApplicationContext getConfigurableApplicationContext() {
        return configurableApplicationContext;
    }

    @Override
    public void init() throws Exception {
        configurableApplicationContext = new SpringApplicationBuilder(ReactDashFxApplication.class).run();
        dbConnectorConfiguration = new DBConfig().dbConnector();
        debeziumListener = new DebeziumListener(dbConnectorConfiguration);
    }

    @Override
    public void start(Stage stage) throws Exception {
        configurableApplicationContext.publishEvent(new StageReadyEvent(stage));
        debeziumListener.start();
    }

    @Override
    public void stop() throws Exception {
        configurableApplicationContext.close();
        debeziumListener.stop();
        Platform.exit();
    }
}
