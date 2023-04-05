package com.utm.msei.controllers.login;

import com.utm.msei.controllers.interfaces.ControllerI;
import com.utm.msei.converter.ImageHandler;
import com.utm.msei.handler.StageHandler;
import com.utm.msei.persistence.dto.AdministratieDto;
import com.utm.msei.persistence.dto.UserDto;
import com.utm.msei.persistence.dto.enums.EntityTypeEnum;
import com.utm.msei.security.PasswordHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.utm.msei.Main.serviceHandler;

public class Login implements ControllerI {

    @FXML
    private Label welcomeText;
    @FXML
    private TextField email, password;
    @FXML
    private VBox loginVBox;
    @FXML
    private Text wrongCredentials, resetCredentials;

    @FXML
    private Text numePrenume, status;//, email, telefon;

    public static UserHandler userHandler = new UserHandler();

    public void start() {
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
                System.out.println(userDto.getUserType());
                if (userDto.getUserType().contains(EntityTypeEnum.DIRECTOR) || userDto.getUserType().contains(EntityTypeEnum.ADJUNCT)) {
                    userHandler.setUser(userDto);

//                    StageHandler.setScene("fxml/administratie.fxml");
//
//                    numePrenume.setText(userHandler.getUser().getNume() + userHandler.getUser().getPrenume());
//                    status.setText(userHandler.getUser().getUserType().toString());
//                    email.setText(useserrHandler.getUser().getEmail());
////                    telefon.setText(uHandler.getUser().getTelefon());

                    StageHandler.setScene("fxml/administratie.fxml");

//                    Administratie entity = new Administratie();
//                    entity.start();

//                    Administratie.start();

                } else if (EntityTypeEnum.PROFESOR.equals(userDto.getUserType())) {
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
//        userDto.setUserType(List.of(EntityTypeEnum.DIRECTOR));
//        userDto.setNume("Iuliana");
//        userDto.setPrenume("Bragovschi");
//        userDto.setTelefon("079112233");
//        userDto.setIdnp("1234567890123");
//        userDto.setDataNastere(LocalDate.of(1976, 1, 17));
//        userDto.setPoza(ImageHandler.imageToByte());
//
//        UserDto u = serviceHandler.getUserService().save(userDto);
//        AdministratieDto administratieDto = new AdministratieDto(u);
//        serviceHandler.getAdministratieService().save(administratieDto);
    }


    @FXML
    protected void resetPassword() throws IOException {

        loginVBox.setVisible(false);

        ResetPassword resetPassword = new ResetPassword();
        resetPassword.start();
    }
}









