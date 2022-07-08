package com.pultrax.reactdashfx.ui;

import com.pultrax.reactdashfx.ReactDashFxApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ReactDashUI extends Application {

    private static ConfigurableApplicationContext configurableApplicationContext;

    public static ConfigurableApplicationContext getConfigurableApplicationContext() {
        return configurableApplicationContext;
    }

    @Override
    public void init() throws Exception {
        configurableApplicationContext = new SpringApplicationBuilder(ReactDashFxApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        configurableApplicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        configurableApplicationContext.close();
        Platform.exit();
    }
}
