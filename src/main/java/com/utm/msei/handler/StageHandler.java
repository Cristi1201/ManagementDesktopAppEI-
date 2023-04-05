package com.utm.msei.handler;

import com.utm.msei.Main;
import com.utm.msei.controllers.interfaces.ControllerI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageHandler {

    private static Stage stage = null;

    private final static String packageName = "/com/utm/msei/";

    public static void setScene(String fxmlPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(packageName + fxmlPath));
        Scene scene;
        try {
            if (stage != null) {
                scene = new Scene(fxmlLoader.load(), stage.getScene().getWidth(), stage.getScene().getHeight());
                ControllerI controller = fxmlLoader.getController();
                controller.start();
            } else {
                stage = new Stage();
                scene = new Scene(fxmlLoader.load(), 860, 560);
            }
            stage.setScene(scene);
            stage.setTitle("ManagementDesktopAppEI");
            stage.setResizable(true);
            stage.setMinWidth(860);
            stage.setMinHeight(560);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
