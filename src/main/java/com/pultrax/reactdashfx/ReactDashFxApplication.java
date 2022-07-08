package com.pultrax.reactdashfx;

import com.pultrax.reactdashfx.ui.ReactDashUI;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactDashFxApplication {

    public static void main(String[] args) {
        Application.launch(ReactDashUI.class, args);
    }

}
