package com.pultrax.reactdashfx.fxcontroller;

import javafx.fxml.Initializable;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DashboardController implements Initializable {

    private static DashboardController instance;

    private DashboardController() {

    }

    public static DashboardController getInstance() {
        if (instance == null) {
            instance = new DashboardController();
        }

        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
