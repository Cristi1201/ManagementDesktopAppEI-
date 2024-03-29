package com.utm.msei.executor;

import com.utm.msei.controllers.login.Login;
import com.utm.msei.handler.StageHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFxInitializer extends Application {

    private Login login = new Login();


    @Override
    public void start(Stage stage) throws Exception {
        StageHandler.setScene("fxml/main.fxml");
        login.start();
    }

    public static void launch() {
        Application.launch();
    }
}
