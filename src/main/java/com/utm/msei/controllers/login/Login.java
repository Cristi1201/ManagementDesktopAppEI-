package com.utm.msei.controllers.login;

import com.utm.msei.controllers.Administratie;
import com.utm.msei.handler.StageHandler;
import com.utm.msei.persistence.dto.UserDto;
import com.utm.msei.persistence.dto.enums.EntityTypeEnum;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static com.utm.msei.Main.serviceHandler;

public class Login {

    @FXML
    private Label welcomeText;
    @FXML
    private TextField email, password;
    @FXML
    private VBox loginVBox;
    @FXML
    private Text wrongCredentials, resetCredentials;

    public static UserDto user;

    public static UserHandler userHandler = new UserHandler();

    public void start() throws IOException {
        StageHandler.setScene("fxml/main.fxml");
    }

    @FXML
    protected void login() throws IOException {
        wrongCredentials.setVisible(false);

        String userEmail = email.getText();
        String userPassword = password.getText();
        if (StringUtils.isEmpty(userEmail) || StringUtils.isEmpty(userPassword)) {
            wrongCredentials.setVisible(true);
        } else {
            UserDto userDto = serviceHandler.getUserService().findByCredentials(userEmail, userPassword);
            if (userDto == null) {
                wrongCredentials.setVisible(true);
            } else {
//                UserI entity;
                if (EntityTypeEnum.ADMINISTRATION.equals(userDto.getUserType())) {
                    userHandler.setUser(userDto);
                    Administratie entity = new Administratie();
                    entity.start();
                } else if (EntityTypeEnum.PROFESSOR.equals(userDto.getUserType())) {
//                    user = new Profesor(userDto);
//                    entity.start();
//                } else if (EntityTypeEnum.PROFESSOR.equals(userDto.getUserType())) {
//                    user = new Student / Elev (userDto);
//                    entity.start();
                }
            }
        }






//        UserDto userDto = new UserDto();
//        userDto.setEmail("a");
//
//        String pass = "a";
//        String salt = PasswordHandler.generateSalt();
//        String hash = PasswordHandler.hashPassword(pass, salt);
//        String rec = PasswordHandler.getRecordFromSaltAndHash(salt, hash); // concat to db
//        String[] salthash = PasswordHandler.getSaltAndHashFromRecord(rec);
//        System.out.println(salt);
//        System.out.println(hash);
//        System.out.println(rec);
//        System.out.println(salthash[0]);
//        System.out.println(salthash[1]);
//        System.out.println(PasswordHandler.validatePassword(pass, salthash[0], salthash[1]));
//
//        userDto.setPassword(rec);
//        userDto.setUserType(EntityTypeEnum.ADMINISTRATION);
//        userDto.setNume("Iuliana");
//        userDto.setPrenume("Bragovschi");
//        userDto.setTelefon("079112233");
//        userDto.setIdnp("1234567890123");
//        userDto.setDataNastere(Date.valueOf(LocalDate.of(1976, 1, 17)));
//        System.out.println(Arrays.toString(ImageHandler.imageToByte()));
//        userDto.setPoza(ImageHandler.imageToByte());
//
//
//        AdministratieDto administratieDto = new AdministratieDto(AdministratorStatusEnum.DIRECTOR, userDto);
//        serviceHandler.getAdministratieService().save(administratieDto);
    }


    @FXML
    protected void resetPassword() throws IOException {

        loginVBox.setVisible(false);

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.start();
    }
}









