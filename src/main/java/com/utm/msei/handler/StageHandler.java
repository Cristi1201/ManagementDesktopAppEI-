package com.utm.msei.handler;

import com.utm.msei.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageHandler {

    private static Stage stage = null;

    private final static String packageName = "/com/utm/msei/";

    public static void setScene(String fxmlPath) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(packageName + fxmlPath));

        Scene scene;
        if(stage != null) {
            scene = new Scene(fxmlLoader.load(), stage.getScene().getWidth(), stage.getScene().getHeight());
        } else {
            stage = new Stage();
            scene = new Scene(fxmlLoader.load(), 860, 560);
        }
        scene.getStylesheets().add(Main.class.getResource("css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ManagementDesktopAppEI");
        stage.setResizable(true);
        stage.setMinWidth(860);
        stage.setMinHeight(560);
        stage.show();
    }
}
