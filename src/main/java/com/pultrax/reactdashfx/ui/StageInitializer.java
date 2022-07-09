package com.pultrax.reactdashfx.ui;

import com.pultrax.reactdashfx.fxcontroller.DashboardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
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

    @Value("classpath:/css/global.css")
    private Resource cssResource;

    @Value("classpath:/css/caspian.css")
    private Resource caspianCssResource;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(dashResource.getURL());
            fxmlLoader.setControllerFactory(ReactDashUI.getConfigurableApplicationContext()::getBean);
            fxmlLoader.setController(DashboardController.getInstance());
            Parent parent = fxmlLoader.load();

            Stage stage = event.getStage();
            Scene scene = new Scene(parent);

            scene.getStylesheets().add(caspianCssResource.getURL().toString());
            scene.getStylesheets().add(getClass().getResource("/css/global.css").toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Reactive Dashboard");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
