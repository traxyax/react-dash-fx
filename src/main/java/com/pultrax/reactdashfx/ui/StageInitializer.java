package com.pultrax.reactdashfx.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    @Value("classpath:/fxml/Dashboard.fxml")
    private Resource dashResource;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(dashResource.getURL());
            fxmlLoader.setControllerFactory(ReactDashUI.configurableApplicationContext::getBean);
            Parent parent = fxmlLoader.load();

            Stage stage = event.getStage();
            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.setTitle("Reactive Dashboard");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
