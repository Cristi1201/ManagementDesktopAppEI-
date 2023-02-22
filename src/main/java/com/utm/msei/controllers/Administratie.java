package com.utm.msei.controllers;

import com.utm.msei.controllers.login.Login;
import com.utm.msei.converter.ImageHandler;
import com.utm.msei.handler.StageHandler;
import com.utm.msei.persistence.dto.UserDto;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static com.utm.msei.controllers.login.Login.userHandler;

public class Administratie  {



    @FXML
    private ImageView imageView;
    @FXML
    private Button uploadPhoto;

    public Administratie() {
    }



    public void start() throws IOException {
        StageHandler.setScene("fxml/administratie.fxml");
    }

    @FXML
    public void uploadPhoto() {
        imageView.setImage(ImageHandler.byteToImage(userHandler.getUser().getPoza()));
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @FXML
    public void backResetPassword() {
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
    }


}
