package com.utm.msei.controllers.login;

import com.utm.msei.controllers.interfaces.ControllerI;
import com.utm.msei.handler.StageHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class ResetPassword implements ControllerI {

    @FXML
    private static VBox resetPasswordVBox;

    public void start() {
        StageHandler.setScene("fxml/login/resetPassword.fxml");
    }


    @FXML
    public void resetPassword() {
    }

    @FXML
    public void backResetPassword() throws IOException {
        StageHandler.setScene("fxml/main.fxml");
    }


}
