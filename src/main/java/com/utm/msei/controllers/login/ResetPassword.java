package com.utm.msei.controllers.login;

import com.utm.msei.handler.StageHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component

public class ResetPassword {

    @FXML
    private static VBox resetPasswordVBox;

    public void start() throws IOException {
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
