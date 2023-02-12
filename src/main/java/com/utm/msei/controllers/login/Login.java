package com.utm.msei.controllers.login;

import com.utm.msei.handler.StageHandler;
import com.utm.msei.persistence.dto.ZiSaptamanaDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

import static com.utm.msei.Main.serviceHandler;

public class Login {

    @FXML
    private Label welcomeText;
    @FXML
    private TextField email;
    @FXML
    private VBox loginVBox;
    @FXML
    private Text wrongCredentials, resetCredentials;

    public void start() throws IOException {
        StageHandler.setScene("fxml/main.fxml");
    }

    @FXML
    protected void login() {

        ZiSaptamanaDto zi = new ZiSaptamanaDto("Luni");


        ZiSaptamanaDto ziDTO = serviceHandler.getZiSaptamanaService().save(zi);


        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println(ziDTO.getId());
        System.out.println(ziDTO.getZi());

        System.out.println("++++++++++++++++++++++++++++++++");
        wrongCredentials.setVisible(true);
        resetCredentials.setVisible(true);
    }

    @FXML
    protected void resetPassword() throws IOException {

        loginVBox.setVisible(false);

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.start();
    }



}









