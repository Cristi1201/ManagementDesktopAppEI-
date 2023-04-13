package com.utm.msei.controllers.administration;

import com.utm.msei.controllers.administration.actions.*;
import com.utm.msei.controllers.interfaces.ControllerI;
import com.utm.msei.util.ImageHandler;
import com.utm.msei.handler.StageHandler;
import com.utm.msei.persistence.dto.*;
import com.utm.msei.persistence.dto.enums.EntityTypeEnum;
import com.utm.msei.security.PasswordHandler;
import com.utm.msei.util.PdfCreator;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.apache.logging.log4j.util.Strings;
import org.controlsfx.control.CheckComboBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.utm.msei.Main.main;
import static com.utm.msei.Main.serviceHandler;
import static com.utm.msei.controllers.login.Login.userHandler;

public class Administratie implements ControllerI {


    @FXML
    private ImageView imageView;

    @FXML
    private Text numePrenumeAdmin, statusAdmin, emailAdmin, telefonAdmin, mesajSavePane, clasaText, numeTata, telefonTata, numeMama, telefonMama;

    @FXML
    private TextField iNumeTata, iPrenumeTata, iTelefonTata, iNumeMama, iPrenumeMama, iTelefonMama;

    @FXML
    private PasswordField passwordNow, newPassword, repeatPassword;

    @FXML
    private StackPane mainPaneAdmin, profesoriPaneAdmin, profesoriDisciplinePaneAdmin, orarPaneAdmin, orarPane, alegeClasaPane, blankPane, eleviPaneAdmin;

    @FXML
    private TableView<CadreHandler.CadreTable> tableCadre;
    @FXML
    private TableColumn<CadreHandler.CadreTable, String> numeColumn;
    @FXML
    private TableColumn<CadreHandler.CadreTable, String> prenumeColumn;
    @FXML
    private TableColumn<CadreHandler.CadreTable, String> emailColumn;
    @FXML
    private TableColumn<CadreHandler.CadreTable, String> functieColumn;
    @FXML
    private TableColumn<CadreHandler.CadreTable, String> telefonColumn;
    @FXML
    private TableColumn<CadreHandler.CadreTable, String> idnpColumn;
    @FXML
    private TableColumn<CadreHandler.CadreTable, LocalDate> dataNastereColumn;
    @FXML
    private Button addCadre, deleteCadre, saveCadreTable, cadreAdminBtn, profDiscAdminBtn, orarAdminBtn,
            eleviAdminBtn, buttonOk, addDisciplina, deleteDisciplina, saveDiscipline, saveProfDiscTable,
            alegeClasaOrarAdmin, saveOrar, downloadOrar, resetPasswordBtn, resetPasswordBtnAdmin, anuleazaResetPasswordBtn,
            modificaDiriginteBtn, modifyParentElevBtn, okParentElevBtn, salveazaModifyParent, anuleazaModifyParent;
    @FXML
    private AnchorPane mainAdmin;
    @FXML
    private Pane savePane, resetPasswordPane, parentElevPane, modifyParentElevPane;
    @FXML
    private HBox navBar;
    @FXML
    private VBox userVBox, actionTableCadre;


    @FXML
    private TableView<DiscProfHandler.DiscProfTable> profDiscTable;
    @FXML
    private TableColumn<DiscProfHandler.DiscProfTable, String> profesorColumn;
    @FXML
    private TableColumn<DiscProfHandler.DiscProfTable, List> disciplinaProfColumn;

    @FXML
    private TableView<DiscProfHandler.Discipline> disciplineTable;
    @FXML
    private TableColumn<DiscProfHandler.Discipline, String> disciplinaColumn;


    @FXML
    private ComboBox<String> claseComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> diriginteComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> tataComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> mamaComboBox = new ComboBox<>();
    private DiscProfHandler discipline;

    @FXML
    private TableView<OrarHandler.OrarTable> orarTable;
    @FXML
    private TableColumn<OrarHandler.OrarTable, String> ziOrarColumn;
    @FXML
    private TableColumn<OrarHandler.OrarTable, String> durataOrarColumn;
    @FXML
    private TableColumn<OrarHandler.OrarTable, String> disciplinaOrarColumn;
    @FXML
    private TableView<EleviHandler.ElevTable> tableElevi;
    @FXML
    private TableColumn<EleviHandler.ElevTable, String> elevNumeColumn, elevPrenumeColumn, elevEmailColumn, elevTelefonColumn, elevIdnpColumn;
    @FXML
    private TableColumn<EleviHandler.ElevTable, LocalDate> elevDataNastereColumn;
    @FXML
    private TableColumn<EleviHandler.ElevTable, Void> elevParintiColumn;


    @FXML
    public void start() {
        this.hideAll();

        // TODO noutati

        final String[] salt_hash = PasswordHandler.getSaltAndHashFromRecord(userHandler.getUser().getPassword());
        resetPasswordPane.setVisible(true);
        // if email is the same as password (how it is when new user is created), change it
        if (PasswordHandler.validatePassword(userHandler.getUser().getEmail(), salt_hash[0], salt_hash[1])) {
            this.resetPassword();
        }

        this.initCredentials();
        this.initPoza();


        navBar.setPadding(new Insets(10));
        navBar.setSpacing(10);

        Region leftSpacer = new Region();
        navBar.setHgrow(leftSpacer, Priority.SOMETIMES);
        Region centerSpacer1 = new Region();
        navBar.setHgrow(centerSpacer1, Priority.SOMETIMES);
        Region centerSpacer2 = new Region();
        navBar.setHgrow(centerSpacer2, Priority.SOMETIMES);
        Region centerSpacer3 = new Region();
        navBar.setHgrow(centerSpacer3, Priority.SOMETIMES);
        Region rightSpacer = new Region();
        navBar.setHgrow(rightSpacer, Priority.NEVER);

        navBar.getChildren().removeAll(cadreAdminBtn, profDiscAdminBtn, orarAdminBtn, eleviAdminBtn);
        navBar.getChildren().addAll(leftSpacer, cadreAdminBtn, centerSpacer1, profDiscAdminBtn, centerSpacer2, orarAdminBtn, centerSpacer3, eleviAdminBtn, rightSpacer);
        navBar.setAlignment(Pos.CENTER_LEFT);

        resetPasswordBtnAdmin.setOnAction(event -> {
            this.resetPassword();
        });

        cadreAdminBtn.setOnMouseClicked(event -> {
            this.showCadreAdmin();
        });

        profDiscAdminBtn.setOnMouseClicked(event -> {
            this.showProfesoriDisciplineAdmin();
        });
        orarAdminBtn.setOnMouseClicked(event -> {
            this.showChooseClasaAdmin();
        });
        eleviAdminBtn.setOnAction(event -> {
            this.initializeShowElevAdmin();
        });
    }

    @FXML
    private void initializeShowElevAdmin() {
        this.hideAll();
        orarPaneAdmin.setVisible(true);
        alegeClasaPane.setVisible(true);
        mainPaneAdmin.setVisible(true);

        ClaseComboBoxHandler claseComboBoxHandler = new ClaseComboBoxHandler();

        claseComboBox.setItems(FXCollections.observableArrayList(claseComboBoxHandler.getAllClase().stream().map(c -> c.getName()).collect(Collectors.toList())));

        alegeClasaOrarAdmin.setOnAction(event -> {
            String clasaName = claseComboBox.getSelectionModel().getSelectedItem();
            if (clasaName != null) {
                alegeClasaPane.setVisible(false);
                this.showElevAdmin(clasaName);
            } else {
                mesajSavePane.setText("Alegeti clasa");
                savePane.setVisible(true);
                buttonOk.setOnAction(click -> {
                    savePane.setVisible(false);
                });
            }
        });
    }

    @FXML
    private void showElevAdmin(String clasaName) {
        this.hideAll();
        mainPaneAdmin.setVisible(true);
        eleviPaneAdmin.setVisible(true);
        actionTableCadre.setVisible(true);

        LocalDate dateNow = LocalDate.now();
        clasaText.setText("Clasa " + clasaName);

        EleviHandler.DiriginteClasaComboBoxHandler diriginte = new EleviHandler.DiriginteClasaComboBoxHandler(clasaName);
        diriginteComboBox.setItems(FXCollections.observableArrayList(diriginte.getAllProfs().stream().map(p -> p.getIdUser().getNume() + " " + p.getIdUser().getPrenume()).collect(Collectors.toList())));
        ProfesorDto dir = diriginte.getDirForClass();
        if (dir != null) {
            diriginteComboBox.setValue(dir.getIdUser().getNume() + " " + dir.getIdUser().getPrenume());
        }
//      TODO see if up sets ok, if not, use this
//       Loop through the ComboBox items and check for a match
//        for (String item : comboBox.getItems()) {
//            if (item.equals(data)) {
//                comboBox.setValue(item);
//                break;
//            }
//        }
        modificaDiriginteBtn.setOnAction(event -> {
            String profName = diriginteComboBox.getSelectionModel().getSelectedItem();
            if (profName != null) {
                diriginte.updateDirForClass(profName);
            }
        });





        buttonOk.setOnAction(event -> {
            mesajSavePane.setText("");
            savePane.setVisible(false);
            tableElevi.getItems().clear();
            start();
        });

        // set to resize
        profesoriPaneAdmin.heightProperty().addListener((obs, oldVal, newVal) -> {
            double newHeight = newVal.doubleValue();
            tableElevi.setPrefHeight(newHeight);
        });
        profesoriPaneAdmin.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = newVal.doubleValue();
            tableElevi.setPrefWidth(newWidth);
        });

        // set cells
        elevNumeColumn.setCellValueFactory(new PropertyValueFactory<EleviHandler.ElevTable, String>("nume"));
        elevNumeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        elevPrenumeColumn.setCellValueFactory(new PropertyValueFactory<EleviHandler.ElevTable, String>("prenume"));
        elevPrenumeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        elevEmailColumn.setCellValueFactory(new PropertyValueFactory<EleviHandler.ElevTable, String>("email"));
        elevEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        elevTelefonColumn.setCellValueFactory(new PropertyValueFactory<EleviHandler.ElevTable, String>("telefon"));
        elevTelefonColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        elevIdnpColumn.setCellValueFactory(new PropertyValueFactory<EleviHandler.ElevTable, String>("idnp"));
        elevIdnpColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        elevDataNastereColumn.setCellValueFactory(new PropertyValueFactory<EleviHandler.ElevTable, LocalDate>("dataNastere"));
        elevDataNastereColumn.setCellFactory(column -> {
            TableCell<EleviHandler.ElevTable, LocalDate> cell = new TableCell<EleviHandler.ElevTable, LocalDate>() {
                private final DatePicker datePicker = new DatePicker();

                {
                    datePicker.setConverter(new StringConverter<LocalDate>() {
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                        @Override
                        public String toString(LocalDate date) {
                            if (date != null) {
                                return dateFormatter.format(date);
                            } else {
                                return "";
                            }
                        }

                        @Override
                        public LocalDate fromString(String string) {
                            if (string != null && !string.isEmpty()) {
                                return LocalDate.parse(string, dateFormatter);
                            } else {
                                return null;
                            }
                        }
                    });

                    datePicker.setOnAction(event -> {
                        LocalDate date = datePicker.getValue();
                        if (date.isAfter(dateNow)) {
                            savePane.toFront();
                            mesajSavePane.setText("Revizuiți data !");
                            savePane.setVisible(true);
                            getTableView().getItems().get(getIndex()).setDataNastere(date);
                            datePicker.setValue(date);
                            buttonOk.setOnAction(click -> {
                                savePane.setVisible(false);
                                savePane.toFront();
                            });
                        } else {
                            EleviHandler.ElevTable cadreTable = getTableView().getItems().get(getIndex());
                            if (cadreTable.getDataNastere() == null || (cadreTable.getDataNastere()!=null && !cadreTable.getDataNastere().equals(date))) {
                                cadreTable.setDataNastere(date);
                                cadreTable.setModified();
                            }
                        }
                    });
                }

                @Override
                protected void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        datePicker.setValue(date);
                        setGraphic(datePicker);
                    }
                }
            };
            return cell;
        });
        elevParintiColumn.setCellFactory(column -> {
            return new TableCell<EleviHandler.ElevTable, Void>() {
                private final Button button = new Button("Parinti");

                {
                    button.setOnAction(event -> {
                        EleviHandler.ElevTable person = getTableRow().getItem();
                        showParent(person);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
            };
        });

        // set buttons positions
        eleviPaneAdmin.setAlignment(addCadre, Pos.TOP_RIGHT);
        eleviPaneAdmin.setMargin(addCadre, new Insets(0, 0, 0, 0));
        eleviPaneAdmin.setAlignment(deleteCadre, Pos.TOP_RIGHT);
        eleviPaneAdmin.setMargin(deleteCadre, new Insets(addCadre.getHeight() + 2, 0, 0, 0));
        eleviPaneAdmin.setAlignment(saveCadreTable, Pos.BOTTOM_RIGHT);
        eleviPaneAdmin.setMargin(saveCadreTable, new Insets(0, 0, 0, 0));

        EleviHandler eleviHandler = new EleviHandler(clasaName);
        tableElevi.setItems(FXCollections.observableArrayList(eleviHandler.getAllElevi()));

        // update
        elevNumeColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            EleviHandler.ElevTable rowData = event.getTableView().getItems().get(row);
            rowData.setNume(event.getNewValue());
            rowData.setModified();
        });
        elevPrenumeColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            EleviHandler.ElevTable rowData = event.getTableView().getItems().get(row);
            rowData.setPrenume(event.getNewValue());
            rowData.setModified();
        });
        elevTelefonColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            EleviHandler.ElevTable rowData = event.getTableView().getItems().get(row);
            rowData.setTelefon(event.getNewValue());
            rowData.setModified();
        });
        elevIdnpColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            EleviHandler.ElevTable rowData = event.getTableView().getItems().get(row);
            rowData.setIdnp(event.getNewValue());
            rowData.setModified();
        });
        elevDataNastereColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            EleviHandler.ElevTable rowData = event.getTableView().getItems().get(row);
            rowData.setDataNastere(event.getNewValue());
            rowData.setModified();
        });

        // add
        addCadre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tableElevi.getItems().add(new EleviHandler.ElevTable());
            }
        });

        // delete
        final EleviHandler.ElevTable[] selected = {null};
        tableElevi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    selected[0] = tableElevi.getSelectionModel().getSelectedItem();
                }
            }
        });
        deleteCadre.setOnAction((ActionEvent event) -> {
            eleviHandler.setDeleted(selected[0]);
            tableElevi.getItems().remove(selected[0]);
        });

        // save
        saveCadreTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boolean incompleteField = false;

                for (EleviHandler.ElevTable user : tableElevi.getItems()) {
                    if (user.getDataNastere().isAfter(dateNow)) {
                            mesajSavePane.setText("Revizuiți data !");
                            savePane.toFront();
                            savePane.setVisible(true);
                            buttonOk.setOnAction(click -> {
                                savePane.setVisible(false);
                                savePane.toBack();
                            });
                        incompleteField = true;
                    } else if (user.getNume() == null || user.getPrenume() == null || user.getIdnp() == null) {
                        mesajSavePane.setText("Completați câmpurile !");
                        savePane.toFront();
                        savePane.setVisible(true);
                        buttonOk.setOnAction(event -> {
                            mesajSavePane.setText("");
                            savePane.toBack();
                            savePane.setVisible(false);
                        });
                        incompleteField = true;
                        break;
                    } else {
                        buttonOk.setOnAction(event -> {
                            mesajSavePane.setText("");
                            savePane.setVisible(false);
                            tableCadre.getItems().clear();
                            start();
                        });
                    }
                }

                if (!incompleteField) {
                    for (EleviHandler.ElevTable user : tableElevi.getItems()) {
                        if (user.isNew()) {
                            if (user.getNume().length() < 3 || user.getPrenume().length() < 3 || user.getIdnp().length() != 13 || user.getDataNastere() == null) {
                                mesajSavePane.setText("Revizuiți campurile !");
                                savePane.setVisible(true);
                                buttonOk.setOnAction(event -> {
                                    mesajSavePane.setText("");
                                    savePane.setVisible(false);
                                });
                            } else {
                                buttonOk.setOnAction(event -> {
                                    mesajSavePane.setText("");
                                    savePane.setVisible(false);
                                    tableCadre.getItems().clear();
                                    start();
                                });

                                UserDto userDto = new UserDto();
                                userDto.setNume(user.getNume());
                                userDto.setPrenume(user.getPrenume());
                                userDto.setUserType(List.of(EntityTypeEnum.ELEV));
                                userDto.setTelefon(user.getTelefon());
                                userDto.setIdnp(user.getIdnp());
                                userDto.setDataNastere(user.getDataNastere());
                                userDto.setPoza(ImageHandler.imageToByte());
                                String email = "e-" + userDto.getNume() + "." + userDto.getPrenume() + "@msei.md";
                                userDto.setEmail(email);
                                String salt = PasswordHandler.generateSalt();
                                userDto.setPassword(PasswordHandler.getRecordFromSaltAndHash(salt, PasswordHandler.hashPassword(email, salt)));
                                UserDto userDtoDb = serviceHandler.getUserService().save(userDto);

                                ElevDto newElev = new ElevDto(userDtoDb);
                                newElev.setIdClasa(eleviHandler.getClasa().getId());
                                serviceHandler.getElevService().save(newElev);
                            }
                        } else if (user.isModified()) {
                            if (user.getNume().length() < 3 || user.getPrenume().length() < 3 || user.getIdnp().length() != 13) {
                                mesajSavePane.setText("Revizuiți campurile !");
                                savePane.setVisible(true);
                                buttonOk.setOnAction(event -> {
                                    mesajSavePane.setText("");
                                    savePane.setVisible(false);
                                });
                            } else {
                                buttonOk.setOnAction(event -> {
                                    mesajSavePane.setText("");
                                    savePane.setVisible(false);
                                    tableCadre.getItems().clear();
                                    start();
                                });
                                UserDto userDto = new UserDto();
                                userDto.setId(user.getUserDto().getId());
                                userDto.setNume(user.getNume());
                                userDto.setPrenume(user.getPrenume());
                                userDto.setTelefon(user.getTelefon());
                                userDto.setIdnp(user.getIdnp());
                                userDto.setDataNastere(user.getDataNastere());
                                int i = serviceHandler.getUserService().update(userDto);
                            }
                        }

                    }
                    for (EleviHandler.ElevTable user : eleviHandler.getAllElevi()) {
                        if (user.isDeleted()) {
                            serviceHandler.getUserService().delete(user.getUserDto().getId());
                        }
                    }
                    savePane.toFront();
                    savePane.setVisible(true);
                    savePane.getParent();
                    mesajSavePane.setText("Succes");
                    buttonOk.setOnAction(event -> {
                        savePane.setVisible(false);
                        savePane.toBack();
                        start();
                    });
                }
            }
        });
    }

    @FXML
    private void showParent(EleviHandler.ElevTable person) {
        parentElevPane.setVisible(true);
        TataDto tata = person.getTataDto();
        MamaDto mama = person.getMamaDto();
        if (tata != null) {
            String numePrenumeTata = tata.getNume() + " " + tata.getPrenume();
            numeTata.setText(numePrenumeTata.trim().isEmpty() ? "-" : numePrenumeTata);
            telefonTata.setText(tata.getTelefon().isEmpty() ? "-" : tata.getTelefon());
        } else {
            numeTata.setText("-");
            telefonTata.setText("-");
        }

        if (mama != null) {
            String numePrenumeMama = mama.getNume() + " " + mama.getPrenume();
            numeMama.setText(numePrenumeMama.trim().isEmpty() ? "-" : numePrenumeMama);
            telefonMama.setText(mama.getTelefon().isEmpty() ? "-" : mama.getTelefon());
        } else {
            numeMama.setText("-");
            telefonMama.setText("-");
        }

        okParentElevBtn.setOnAction(click -> {
            parentElevPane.setVisible(false);
        });

        modifyParentElevBtn.setOnAction(click -> {
            parentElevPane.setVisible(false);
            showModifyParent(person);
        });
    }

    @FXML
    private void showModifyParent(EleviHandler.ElevTable person) {
        modifyParentElevPane.setVisible(true);
        TataDto tata = person.getTataDto();
        MamaDto mama = person.getMamaDto();

        ParintiHandler parinti = new ParintiHandler(person);

        tataComboBox.setItems(FXCollections.observableArrayList(parinti.getAllTata().stream().map(t -> t.getNume() + " " + t.getPrenume() + ", " + t.getTelefon()).collect(Collectors.toList())));
        TataDto tataDtoOfElev = parinti.getTataDto(person);
        if (tataDtoOfElev != null) {
            tataComboBox.setValue(tataDtoOfElev.getNume() + " " + tataDtoOfElev.getPrenume());
        }
        mamaComboBox.setItems(FXCollections.observableArrayList(parinti.getAllMama().stream().map(m -> m.getNume() + " " + m.getPrenume() + ", " + m.getTelefon()).collect(Collectors.toList())));
        MamaDto mamaDtoOfElev = parinti.getMamaDto(person);
        if (mamaDtoOfElev != null) {
            mamaComboBox.setValue(mamaDtoOfElev.getNume() + " " + mamaDtoOfElev.getPrenume());
        }
        tataComboBox.setOnAction(event -> {
            String selectedValue = tataComboBox.getValue();
            TataDto choosedTata = parinti.getTataDto(selectedValue);
            iNumeTata.setText(choosedTata.getNume());
            iPrenumeTata.setText(choosedTata.getPrenume());
            iTelefonTata.setText(choosedTata.getTelefon());
        });
        mamaComboBox.setOnAction(event -> {
            String selectedValue = mamaComboBox.getValue();
            MamaDto choosedMama = parinti.getMamaDto(selectedValue);
            iNumeMama.setText(choosedMama.getNume());
            iPrenumeMama.setText(choosedMama.getPrenume());
            iTelefonMama.setText(choosedMama.getTelefon());
        });

        salveazaModifyParent.setOnAction(event -> {
            if (!iNumeTata.getText().trim().isEmpty() && !iPrenumeTata.getText().trim().isEmpty() && !iTelefonTata.getText().trim().isEmpty() &&
                    !iNumeMama.getText().trim().isEmpty() && !iPrenumeMama.getText().trim().isEmpty() && !iTelefonMama.getText().trim().isEmpty()) {
                boolean saved = parinti.saveParent(iNumeTata.getText(), iPrenumeTata.getText(), iTelefonTata.getText(), iNumeMama.getText(), iPrenumeMama.getText(), iTelefonMama.getText());
                if (saved) {
                    savePane.setVisible(true);
                    mesajSavePane.setText("Succes");
                    savePane.toFront();
                    buttonOk.setOnAction(click -> {
                        savePane.setVisible(false);
                        modifyParentElevPane.setVisible(false);
                        savePane.toFront();
                    });
                } else {
                    savePane.setVisible(true);
                    savePane.toFront();
                    mesajSavePane.setText("Eroare");
                    buttonOk.setOnAction(click -> {
                        savePane.toBack();
                        savePane.setVisible(false);
                    });
                }
            }
        });




//        EleviHandler.DiriginteClasaComboBoxHandler diriginte = new EleviHandler.DiriginteClasaComboBoxHandler(clasaName);
//        diriginteComboBox.setItems(FXCollections.observableArrayList(diriginte.getAllProfs().stream().map(p -> p.getIdUser().getNume() + " " + p.getIdUser().getPrenume()).collect(Collectors.toList())));
//        ProfesorDto dir = diriginte.getDirForClass();
//        if (dir != null) {
//            diriginteComboBox.setValue(dir.getIdUser().getNume() + " " + dir.getIdUser().getPrenume());
//        }
    }

    @FXML
    private void resetPassword() {
        mainPaneAdmin.setVisible(true);
        blankPane.setVisible(true);
        resetPasswordPane.setVisible(true);
        resetPasswordPane.toFront();
        final String[] salt_hash = PasswordHandler.getSaltAndHashFromRecord(userHandler.getUser().getPassword());
        navBar.setDisable(true);

        anuleazaResetPasswordBtn.setOnAction(event -> {
            passwordNow.setText(Strings.EMPTY);
            newPassword.setText(Strings.EMPTY);
            repeatPassword.setText(Strings.EMPTY);
            navBar.setDisable(false);

            this.hideAll();
        });

        resetPasswordBtn.setOnAction(event -> {
            if (PasswordHandler.validatePassword(passwordNow.getText(), salt_hash[0], salt_hash[1]) &&
                    newPassword.getText().equals(repeatPassword.getText()) &&
                    !newPassword.getText().equals(passwordNow.getText())) {

                Pattern uppers = Pattern.compile(".*[A-Z].*");
                Pattern lower = Pattern.compile(".*[a-z].*");
                Pattern numbers = Pattern.compile(".*\\d.*");

                if (newPassword.getText().length() > 8 &&
                        uppers.matcher(newPassword.getText()).matches() &&
                        lower.matcher(newPassword.getText()).matches() &&
                        numbers.matcher(newPassword.getText()).matches()) {

                    String pass = newPassword.getText();

                    String salt = PasswordHandler.generateSalt();
                    String hash = PasswordHandler.hashPassword(pass, salt);
                    String rec = PasswordHandler.getRecordFromSaltAndHash(salt, hash);
                    serviceHandler.getUserService().updatePassword(userHandler.getUser().getId(), rec);

                    passwordNow.setText(Strings.EMPTY);
                    newPassword.setText(Strings.EMPTY);
                    repeatPassword.setText(Strings.EMPTY);
                    savePane.setVisible(true);
                    mesajSavePane.setText("Succes");
                    resetPasswordPane.setDisable(true);
                    blankPane.setVisible(false);
                    buttonOk.setOnAction(click -> {
                        savePane.setVisible(false);
                        resetPasswordPane.setDisable(false);
                        resetPasswordPane.setVisible(false);
                        navBar.setDisable(false);
                    });
                } else {
                    savePane.setVisible(true);
                    mesajSavePane.setText("Parolă slabă");
                    resetPasswordPane.setDisable(true);
                    buttonOk.setOnAction(click -> {
                        savePane.setVisible(false);
                        resetPasswordPane.setDisable(false);
                    });
                }
            } else {
//                savePane.toFront();
                savePane.setVisible(true);
                mesajSavePane.setText("Ați introdus greșit");
                resetPasswordPane.setDisable(true);
                buttonOk.setOnAction(click -> {
                    savePane.setVisible(false);
                    resetPasswordPane.setDisable(false);
                });
            }
        });
    }

    @FXML
    private void showChooseClasaAdmin() {
        this.hideAll();
        mainPaneAdmin.setVisible(true);
        orarPaneAdmin.setVisible(true);
        alegeClasaPane.setVisible(true);
        orarPane.setVisible(false);
        orarTable.setVisible(false);

        ClaseComboBoxHandler claseComboBoxHandler = new ClaseComboBoxHandler();

        claseComboBox.setItems(FXCollections.observableArrayList(claseComboBoxHandler.getAllClase().stream().map(c -> c.getName()).collect(Collectors.toList())));

        alegeClasaOrarAdmin.setOnAction(event -> {
            String clasaName = claseComboBox.getSelectionModel().getSelectedItem();
            if (clasaName != null) {
                alegeClasaPane.setVisible(false);
                this.showOrarAdmin(clasaName);
            } else {
                mesajSavePane.setText("Alegeti clasa");
                savePane.setVisible(true);
                buttonOk.setOnAction(click -> {
                    savePane.setVisible(false);
                });
            }
        });
    }

    @FXML
    private void showOrarAdmin(String clasaName) {
        alegeClasaPane.setVisible(false);
        orarPane.setVisible(true);
        orarTable.setVisible(true);
        orarPane.setAlignment(saveOrar, Pos.BOTTOM_RIGHT);
        orarPane.setAlignment(downloadOrar, Pos.BOTTOM_RIGHT);
        orarPane.setMargin(downloadOrar, new Insets(0, 35, 0, 0));

        OrarHandler orarHandler = new OrarHandler();

        ziOrarColumn.setCellValueFactory(new PropertyValueFactory<OrarHandler.OrarTable, String>("zi"));
        ziOrarColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        durataOrarColumn.setCellValueFactory(new PropertyValueFactory<OrarHandler.OrarTable, String>("durata"));
        durataOrarColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        disciplinaOrarColumn.setCellValueFactory(new PropertyValueFactory<OrarHandler.OrarTable, String>("discProf"));
        disciplinaOrarColumn.setCellFactory(column -> new TableCell<OrarHandler.OrarTable, String>() {
            private final ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(orarHandler.getAllDiscProfAsString()));

            {
                comboBox.getItems().add(Strings.EMPTY);
                setGraphic(null);
                comboBox.setOnAction(event -> {
                    OrarHandler.OrarTable orarRow = getTableView().getItems().get(getIndex());
                    String choosedValue = comboBox.getValue();

                    if (choosedValue.equals(Strings.EMPTY)) {
                        orarRow.setDeleted();
                    } else {
                        OrarDto orarForProf = serviceHandler.getOrarService().checkAvailabilityOfProfForDay(orarRow, orarHandler.getChoosedProfesorDto(choosedValue));
                        if (orarForProf == null) {
                            orarRow.setDiscProf(comboBox.getValue(), orarHandler.getAllDiscProf());
                            orarRow.setModified();
                            setGraphic(null);
                            orarTable.refresh();
                            savePane.setVisible(false);
                        } else {
                            if (orarForProf.getIdZi().equals(orarRow.getZiDto()) && orarForProf.getIdDurata().equals(orarRow.getDurataDto()) && orarForProf.getIdClasa().equals(orarHandler.getClasa())) {
                                orarRow.setDiscProf(comboBox.getValue(), orarHandler.getAllDiscProf());
                                orarRow.setModified();
                                setGraphic(null);
                                orarTable.refresh();
                                savePane.setVisible(false);
                                saveOrar.setDisable(false);
                            } else {
                                savePane.setVisible(true);
                                mesajSavePane.setText("Profesor ocupat\nClasa: " + orarForProf.getIdClasa().getNume() + ", " + orarForProf.getIdDiscProf().getIdDisciplina().getDisciplina());
                                orarPane.setDisable(true);
                                saveOrar.setDisable(true);
                                buttonOk.setOnAction(click -> {
                                    savePane.setVisible(false);
                                    orarTable.setItems(FXCollections.observableArrayList(orarHandler.getOrarForClass(clasaName)));
                                    orarPane.setDisable(false);
                                });
                            }
                        }
                    }
                });
                setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                        setGraphic(comboBox);
                        comboBox.setValue(getItem());
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
            }
        });


        orarTable.setItems(FXCollections.observableArrayList(orarHandler.getOrarForClass(clasaName)));

        saveOrar.setOnAction(event -> {
            for (OrarHandler.OrarTable orar : orarTable.getItems()) {
                if (orar.isWasDeleted()) {
                    OrarDto orarDto = new OrarDto();
                    if (orar.getId() != 0) {
                        serviceHandler.getOrarService().delete(orarDto.getId());
                    }
                } else if (orar.wasModified()) {
                    if (!orar.getDiscProf().equals(Strings.EMPTY)) {
                        if (orar.isNew()) {
                            OrarDto newOrar = new OrarDto();
                            newOrar.setIdClasa(orarHandler.getClasa());
                            newOrar.setIdZi(orar.getZiDto());
                            newOrar.setIdDurata(orar.getDurataDto());
                            newOrar.setIdDiscProf(orar.getDiscProfDto());
                            serviceHandler.getOrarService().save(newOrar);
                        } else {
                            serviceHandler.getOrarService().update(orarHandler.getClasa(), orar.getZiDto(), orar.getDurataDto(), orar.getDiscProfDto());
                        }
                    }
                }
            }
            mesajSavePane.setText("Succes");
            savePane.setVisible(true);
            orarPane.setDisable(true);

            buttonOk.setOnAction(click -> {
                orarPane.setDisable(false);
                savePane.setVisible(false);
            });
        });

        downloadOrar.setOnAction(event -> {
            String fileName = PdfCreator.createPdfOrar(orarTable.getItems(), orarHandler.getClasa());
            if (fileName != null) {
                mesajSavePane.setText("Succes\n" + fileName);
                savePane.setVisible(true);
                buttonOk.setOnAction(click -> {
                    savePane.setVisible(false);
                });
            } else {
                mesajSavePane.setText("Închideți fișierul");
                savePane.setVisible(true);
                buttonOk.setOnAction(click -> {
                    savePane.setVisible(false);
                });
            }
        });
        // TODO populate table method for class
    }

    @FXML
    private void initCredentials() {
        numePrenumeAdmin.setText(userHandler.getUser().getNume() + " " + userHandler.getUser().getPrenume());
        String userType = userHandler.getUser().getUserType().stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
        if (userType.split(",").length > 1) {
            userType = userType.substring(0, userType.lastIndexOf(',') + 1) + "\n" + userType.substring(userType.lastIndexOf(',') + 1, userType.length());
        }
        statusAdmin.setText(userType);
        emailAdmin.setText(userHandler.getUser().getEmail());
        telefonAdmin.setText(userHandler.getUser().getTelefon());
    }

    @FXML
    private void initPoza() {
        imageView.setOnDragOver(event -> {
            if (event.getGestureSource() != imageView && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        imageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                String imagePath = db.getFiles().get(0).toURI().toString();
                userHandler.getUser().setPoza(ImageHandler.imageToByte(imagePath));
                serviceHandler.getUserService().updatePozaBy(userHandler.getUser());
                imageView.setImage(new Image(imagePath));
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
        imageView.setImage(ImageHandler.byteToImage(userHandler.getUser().getPoza()));
    }

    @FXML
    private void showCadreAdmin() {
        this.hideAll();
        mainPaneAdmin.setVisible(true);
        profesoriPaneAdmin.setVisible(true);
        actionTableCadre.setVisible(true);
        buttonOk.setOnAction(event -> {
            mesajSavePane.setText("");
            savePane.setVisible(false);
            tableCadre.getItems().clear();
            start();
        });

        // set to resize
        profesoriPaneAdmin.heightProperty().addListener((obs, oldVal, newVal) -> {
            double newHeight = newVal.doubleValue();
            tableCadre.setPrefHeight(newHeight);
        });
        profesoriPaneAdmin.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = newVal.doubleValue();
            tableCadre.setPrefWidth(newWidth);
        });

        // set cells
        numeColumn.setCellValueFactory(new PropertyValueFactory<CadreHandler.CadreTable, String>("nume"));
        numeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<CadreHandler.CadreTable, String>("prenume"));
        prenumeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellValueFactory(new PropertyValueFactory<CadreHandler.CadreTable, String>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        functieColumn.setCellValueFactory(new PropertyValueFactory<>("functie"));
        functieColumn.setCellFactory(column -> new TableCell<CadreHandler.CadreTable, String>() {
            private final CheckComboBox<String> checkComboBox = new CheckComboBox<>();
            private boolean updating = true;

            {
                checkComboBox.getItems().addAll(List.of(EntityTypeEnum.DIRECTOR.toString(), EntityTypeEnum.ADJUNCT.toString(), EntityTypeEnum.PROFESOR.toString()));
                checkComboBox.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) change -> {
                    if (!updating) {
                        updating = true;
                        CadreHandler.CadreTable cadreTable = getTableView().getItems().get(getIndex());
                        cadreTable.setFunctie(String.join(",", checkComboBox.getCheckModel().getCheckedItems()));
                        cadreTable.setModified();
                        updating = false;
                    }
                });
            }

            @Override
            protected void updateItem(String items, boolean empty) {
                super.updateItem(items, empty);
                if (empty) {
                    updating = false;
                    setGraphic(null);
                } else {
                    updating = true;
                    checkComboBox.getCheckModel().clearChecks();
                    if (getTableView().getItems().get(getIndex()).getFunctie() != null) {
                        for (String s : getTableView().getItems().get(getIndex()).getFunctie().split(",")) {
                            s = s.trim();
                            int index = checkComboBox.getItems().indexOf(s);
                            if (index >= 0) {
                                checkComboBox.getCheckModel().checkIndices(index);
                            }
                        }
                        updating = false;
                    } else {
                        updating = false;
                        checkComboBox.getCheckModel().clearChecks();
                    }
                    setGraphic(checkComboBox);
                }
            }
        });
        telefonColumn.setCellValueFactory(new PropertyValueFactory<CadreHandler.CadreTable, String>("telefon"));
        telefonColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idnpColumn.setCellValueFactory(new PropertyValueFactory<CadreHandler.CadreTable, String>("idnp"));
        idnpColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dataNastereColumn.setCellValueFactory(new PropertyValueFactory<CadreHandler.CadreTable, LocalDate>("dataNastere"));
        dataNastereColumn.setCellFactory(column -> {
            TableCell<CadreHandler.CadreTable, LocalDate> cell = new TableCell<CadreHandler.CadreTable, LocalDate>() {
                private final DatePicker datePicker = new DatePicker();

                {
                    datePicker.setConverter(new StringConverter<LocalDate>() {
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                        @Override
                        public String toString(LocalDate date) {
                            if (date != null) {
                                return dateFormatter.format(date);
                            } else {
                                return "";
                            }
                        }

                        @Override
                        public LocalDate fromString(String string) {
                            if (string != null && !string.isEmpty()) {
                                return LocalDate.parse(string, dateFormatter);
                            } else {
                                return null;
                            }
                        }
                    });

                    datePicker.setOnAction(event -> {
                        LocalDate date = datePicker.getValue();
                        LocalDate now = LocalDate.now();
                        if (date.isAfter(now)) {
                            mesajSavePane.setText("Revizuiți data !");
                            savePane.setVisible(true);
                            datePicker.setValue(getTableView().getItems().get(getIndex()).getDataNastere());
                            buttonOk.setOnAction(click -> {
                                savePane.setVisible(false);
                            });
                        } else {
                            CadreHandler.CadreTable cadreTable = getTableView().getItems().get(getIndex());
                            if (!cadreTable.getDataNastere().equals(date)) {
                                cadreTable.setDataNastere(date);
                                cadreTable.setModified();
                            }
                        }
                    });
                }

                @Override
                protected void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        datePicker.setValue(date);
                        setGraphic(datePicker);
                    }
                }
            };
            return cell;
        });

        // set buttons positions
        profesoriPaneAdmin.setAlignment(addCadre, Pos.TOP_RIGHT);
        profesoriPaneAdmin.setMargin(addCadre, new Insets(0, 0, 0, 0));
        profesoriPaneAdmin.setAlignment(deleteCadre, Pos.TOP_RIGHT);
        profesoriPaneAdmin.setMargin(deleteCadre, new Insets(addCadre.getHeight() + 2, 0, 0, 0));
        profesoriPaneAdmin.setAlignment(saveCadreTable, Pos.BOTTOM_RIGHT);
        profesoriPaneAdmin.setMargin(saveCadreTable, new Insets(0, 0, 0, 0));

        // set data
        CadreHandler cadreHandler = new CadreHandler();
        tableCadre.setItems(FXCollections.observableArrayList(cadreHandler.getAllUsers()));

        // update
        numeColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            CadreHandler.CadreTable rowData = event.getTableView().getItems().get(row);
            rowData.setNume(event.getNewValue());
            rowData.setModified();
        });
        prenumeColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            CadreHandler.CadreTable rowData = event.getTableView().getItems().get(row);
            rowData.setPrenume(event.getNewValue());
            rowData.setModified();
        });
        telefonColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            CadreHandler.CadreTable rowData = event.getTableView().getItems().get(row);
            rowData.setTelefon(event.getNewValue());
            rowData.setModified();
        });
        idnpColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            CadreHandler.CadreTable rowData = event.getTableView().getItems().get(row);
            rowData.setIdnp(event.getNewValue());
            rowData.setModified();
        });
        dataNastereColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            CadreHandler.CadreTable rowData = event.getTableView().getItems().get(row);
            rowData.setDataNastere(event.getNewValue());
            rowData.setModified();
        });

        // add
        addCadre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tableCadre.getItems().add(new CadreHandler.CadreTable());
            }
        });

        // delete
        final CadreHandler.CadreTable[] selected = {null};
        tableCadre.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    selected[0] = tableCadre.getSelectionModel().getSelectedItem();
                }
            }
        });
        deleteCadre.setOnAction((ActionEvent event) -> {
            cadreHandler.setDeleted(selected[0]);
            tableCadre.getItems().remove(selected[0]);
        });

        // save
        saveCadreTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boolean incompleteField = false;

                for (CadreHandler.CadreTable user : tableCadre.getItems()) {
                    if (user.getNume() == null || user.getPrenume() == null || user.getFunctie() == null || user.getIdnp() == null || user.getDataNastere() == null) {
                        mesajSavePane.setText("Completați câmpurile !");
                        savePane.setVisible(true);
                        buttonOk.setOnAction(event -> {
                            mesajSavePane.setText("");
                            savePane.setVisible(false);
                        });
                        incompleteField = true;
                        break;
                    } else {
                        buttonOk.setOnAction(event -> {
                            mesajSavePane.setText("");
                            savePane.setVisible(false);
                            tableCadre.getItems().clear();
                            start();
                        });
                    }
                }

                if (!incompleteField) {
                    for (CadreHandler.CadreTable user : tableCadre.getItems()) {
                        if (user.isNew()) {
                            if (user.getNume().length() < 3 || user.getPrenume().length() < 3 || user.getFunctie() == null || user.getIdnp().length() != 13 || user.getDataNastere() == null) {
                                mesajSavePane.setText("Revizuiți campurile !");
                                savePane.setVisible(true);
                                buttonOk.setOnAction(event -> {
                                    mesajSavePane.setText("");
                                    savePane.setVisible(false);
                                });
                            } else {
                                buttonOk.setOnAction(event -> {
                                    mesajSavePane.setText("");
                                    savePane.setVisible(false);
                                    tableCadre.getItems().clear();
                                    start();
                                });
                                UserDto userDto = new UserDto();
                                userDto.setNume(user.getNume());
                                userDto.setPrenume(user.getPrenume());
                                userDto.setUserType(Arrays.stream(user.getFunctie().split(","))
                                        .map(EntityTypeEnum::valueOf)
                                        .collect(Collectors.toList()));
                                userDto.setTelefon(user.getTelefon());
                                userDto.setIdnp(user.getIdnp());
                                userDto.setDataNastere(user.getDataNastere());
                                userDto.setPoza(ImageHandler.imageToByte());
                                if (userDto.getUserType().contains(EntityTypeEnum.DIRECTOR)) {
                                    String email = "d-" + userDto.getNume() + "." + userDto.getPrenume() + "@msei.md";
                                    userDto.setEmail(email);
                                    String salt = PasswordHandler.generateSalt();
                                    userDto.setPassword(PasswordHandler.getRecordFromSaltAndHash(salt, PasswordHandler.hashPassword(email, salt)));
                                    UserDto userDtoDb = serviceHandler.getUserService().save(userDto);
                                    AdministratieDto administratieDto = new AdministratieDto(userDtoDb);
                                    serviceHandler.getAdministratieService().save(administratieDto);
                                } else if (userDto.getUserType().contains(EntityTypeEnum.ADJUNCT)) {
                                    String email = "da-" + userDto.getNume() + "." + userDto.getPrenume() + "@msei.md";
                                    userDto.setEmail(email);
                                    String salt = PasswordHandler.generateSalt();
                                    userDto.setPassword(PasswordHandler.getRecordFromSaltAndHash(salt, PasswordHandler.hashPassword(email, salt)));
                                    UserDto userDtoDb = serviceHandler.getUserService().save(userDto);
                                    AdministratieDto administratieDto = new AdministratieDto(userDtoDb);
                                    serviceHandler.getAdministratieService().save(administratieDto);
                                } else if (userDto.getUserType().contains(EntityTypeEnum.PROFESOR)) {
                                    String email = "p-" + userDto.getNume() + "." + userDto.getPrenume() + "@msei.md";
                                    userDto.setEmail(email);
                                    String salt = PasswordHandler.generateSalt();
                                    userDto.setPassword(PasswordHandler.getRecordFromSaltAndHash(salt, PasswordHandler.hashPassword(email, salt)));
                                    UserDto userDtoDb = serviceHandler.getUserService().save(userDto);
                                    ProfesorDto profesorDto = new ProfesorDto(userDtoDb);
                                    serviceHandler.getProfesorService().save(profesorDto);
                                }
                            }
                        } else if (user.isModified()) {
                            if (user.getNume().length() < 3 || user.getPrenume().length() < 3 || user.getFunctie() == null || user.getIdnp().length() != 13) {
                                mesajSavePane.setText("Revizuiți campurile !");
                                savePane.setVisible(true);
                                buttonOk.setOnAction(event -> {
                                    mesajSavePane.setText("");
                                    savePane.setVisible(false);
                                });
                            } else {
                                buttonOk.setOnAction(event -> {
                                    mesajSavePane.setText("");
                                    savePane.setVisible(false);
                                    tableCadre.getItems().clear();
                                    start();
                                });
                                UserDto userDto = new UserDto();
                                userDto.setId(user.getId());
                                userDto.setNume(user.getNume());
                                userDto.setPrenume(user.getPrenume());
                                userDto.setUserType(Arrays.stream(user.getFunctie().split(","))
                                        .map(EntityTypeEnum::valueOf)
                                        .collect(Collectors.toList()));
                                userDto.setTelefon(user.getTelefon());
                                userDto.setIdnp(user.getIdnp());
                                userDto.setDataNastere(user.getDataNastere());
                                if (userDto.getUserType().contains(EntityTypeEnum.DIRECTOR)) {
                                    userDto.setEmail(user.getEmail());
                                    int i = serviceHandler.getUserService().updateAdminOrProf(userDto);
                                } else if (userDto.getUserType().contains(EntityTypeEnum.ADJUNCT)) {
                                    userDto.setEmail(user.getEmail());
                                    int i = serviceHandler.getUserService().updateAdminOrProf(userDto);
                                } else if (userDto.getUserType().contains(EntityTypeEnum.PROFESOR)) {
                                    userDto.setEmail(user.getEmail());
                                    int i = serviceHandler.getUserService().updateAdminOrProf(userDto);
                                }
                            }
                        }

                    }
                }
                for (CadreHandler.CadreTable user : cadreHandler.getAllUsers()) {
                    if (user.isDeleted()) {
                        serviceHandler.getUserService().delete(user.getId());
                    }
                }
                savePane.setVisible(true);
                mesajSavePane.setText("Succes");
                buttonOk.setOnAction(event -> {
                    savePane.setVisible(false);
                    start();
                });
            }
        });
    }

    @FXML
    private void showProfesoriDisciplineAdmin() {
        this.hideAll();
        mainPaneAdmin.setVisible(true);
        profesoriDisciplinePaneAdmin.setVisible(true);

        discipline = new DiscProfHandler();
        this.initializeProfDiscTable();

        // save
        saveProfDiscTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boolean incompleteField = false;

                for (DiscProfHandler.DiscProfTable discProfTable : profDiscTable.getItems()) {
                    if (discProfTable.getDiscipline() == null || discProfTable.getDiscipline().isEmpty()) {
                        mesajSavePane.setText("Profesor fară disciplină !");
                        savePane.setVisible(true);
                        buttonOk.setOnAction(event -> {
                            mesajSavePane.setText("");
                            savePane.setVisible(false);
                        });
                        incompleteField = true;
                        break;
                    } else {
                        buttonOk.setOnAction(event -> {
                            mesajSavePane.setText("");
                            savePane.setVisible(false);
                            tableCadre.getItems().clear();
                            start();
                        });
                    }
                }

                if (!incompleteField) {
                    for (DiscProfHandler.DiscProfTable discProfTable : profDiscTable.getItems()) {
                        if (discProfTable.isModified()) {
                            serviceHandler.getDisciplinaProfesorService().updateDiscsForProf(discProfTable.getProfesorDto(), discProfTable.getDiscipline());
                        }
                    }
                    discipline = new DiscProfHandler();
                    initializeProfDiscTable();
                    disciplineTable.setItems(FXCollections.observableArrayList(discipline.getAllDisicipline()));
                    mesajSavePane.setText("Succes");
                    savePane.setVisible(true);
                    buttonOk.setOnAction(event -> {
                        savePane.setVisible(false);
                    });
                }

            }
        });

        // 2nd table - discipline
        disciplinaColumn.setCellValueFactory(new PropertyValueFactory<DiscProfHandler.Discipline, String>("disciplina"));
        disciplinaColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        disciplineTable.setItems(FXCollections.observableArrayList(discipline.getAllDisicipline()));

        // update
        disciplinaColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            DiscProfHandler.Discipline rowData = event.getTableView().getItems().get(row);
            rowData.setDisciplina(event.getNewValue());
            rowData.setModified();
        });

        // add
        addDisciplina.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                disciplineTable.getItems().add(new DiscProfHandler.Discipline());
            }
        });
        // delete
        final DiscProfHandler.Discipline[] selected = {null};
        disciplineTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    selected[0] = disciplineTable.getSelectionModel().getSelectedItem();
                }
            }
        });
        deleteDisciplina.setOnAction((ActionEvent event) -> {
            discipline.setDeletedDisc(selected[0]);
            disciplineTable.getItems().remove(selected[0]);
        });
        // save
        saveDiscipline.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boolean incompleteField = false;

                for (DiscProfHandler.Discipline disc : disciplineTable.getItems()) {
                    if (disc.getDisciplina() == null || disc.getDisciplina().isEmpty()) {
                        mesajSavePane.setText("Completați câmpurile !");
                        savePane.setVisible(true);
                        buttonOk.setOnAction(event -> {
                            mesajSavePane.setText("");
                            savePane.setVisible(false);
                        });
                        incompleteField = true;
                        break;
                    } else {
                        buttonOk.setOnAction(event -> {
                            mesajSavePane.setText("");
                            savePane.setVisible(false);
                            tableCadre.getItems().clear();
                            start();
                        });
                    }
                }

                if (!incompleteField) {
                    for (DiscProfHandler.Discipline disc : disciplineTable.getItems()) {
                        if (disc.isNew()) {
                            DisciplinaDto disciplinaDto = new DisciplinaDto();
                            disciplinaDto.setDisciplina(disc.getDisciplina());
                            serviceHandler.getDisciplinaService().save(disciplinaDto);
                        } else if (disc.isModified()) {
                            DisciplinaDto disciplinaDto = new DisciplinaDto();
                            disciplinaDto.setId(disc.getId());
                            disciplinaDto.setDisciplina(disc.getDisciplina());
                            serviceHandler.getDisciplinaService().update(disciplinaDto);
                        }
                    }
                }
                for (DiscProfHandler.Discipline disc : discipline.getAllDisicipline()) {
                    if (disc.isDeleted()) {
                        serviceHandler.getDisciplinaProfesorService().deleteWhereDisc(disc.getDisciplinaDto());
                        serviceHandler.getDisciplinaService().delete(disc.getId());
                    }
                }
                discipline = new DiscProfHandler();
                initializeProfDiscTable();
                disciplineTable.setItems(FXCollections.observableArrayList(discipline.getAllDisicipline()));
                mesajSavePane.setText("Succes");
                savePane.setVisible(true);
                buttonOk.setOnAction(event -> {
                    savePane.setVisible(false);
                });
            }
        });
    }

    @FXML
    private void initializeProfDiscTable() {
        profesorColumn.setCellValueFactory(new PropertyValueFactory<DiscProfHandler.DiscProfTable, String>("profesor"));
        profesorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        disciplinaProfColumn.setCellValueFactory(new PropertyValueFactory<DiscProfHandler.DiscProfTable, List>("disciplineName"));
        disciplinaProfColumn.setCellFactory(column -> new TableCell<DiscProfHandler.DiscProfTable, List>() {
            private final CheckComboBox<String> checkComboBox = new CheckComboBox<>();
            private boolean updating = true;

            {
                checkComboBox.getItems().addAll(discipline.getAllDisicipline().stream().map(d -> d.getDisciplina()).collect(Collectors.toList()));
                checkComboBox.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) change -> {
                    if (!updating) {
                        updating = true;
                        DiscProfHandler.DiscProfTable discProfTable = getTableView().getItems().get(getIndex());
                        discProfTable.setDiscipline(checkComboBox.getCheckModel().getCheckedItems());
                        discProfTable.setModified();
                        updating = false;
                    }
                });
            }

            @Override
            protected void updateItem(List items, boolean empty) {
                super.updateItem(items, empty);
                if (empty) {
                    updating = false;
                    setGraphic(null);
                } else {
                    updating = true;
                    checkComboBox.getCheckModel().clearChecks();
                    if (getTableView().getItems().get(getIndex()).getDiscipline() != null && !getTableView().getItems().get(getIndex()).getDiscipline().isEmpty()) {
                        for (DisciplinaDto discDto : getTableView().getItems().get(getIndex()).getDiscipline()) {
                            int index = checkComboBox.getItems().indexOf(discDto.getDisciplina());
                            if (index >= 0) {
                                checkComboBox.getCheckModel().checkIndices(index);
                            }
                        }
                        updating = false;
                    } else {
                        updating = false;
                        checkComboBox.getCheckModel().clearChecks();
                    }
                    setGraphic(checkComboBox);
                }
            }
        });
        // set data
        profDiscTable.setItems(FXCollections.observableArrayList(discipline.getProfDiscs()));
    }


    @FXML
    private void hideAll() {
        mainPaneAdmin.setVisible(false);
        profesoriPaneAdmin.setVisible(false);
        profesoriDisciplinePaneAdmin.setVisible(false);
        orarPaneAdmin.setVisible(false);
        orarPane.setVisible(false);
        alegeClasaPane.setVisible(false);
        savePane.setVisible(false);
        resetPasswordPane.setVisible(false);
        blankPane.setVisible(false);
        eleviPaneAdmin.setVisible(false);
        actionTableCadre.setVisible(false);
        parentElevPane.setVisible(false);
        modifyParentElevPane.setVisible(false);



//        actionTableCadre.setVisible(false);
//        savePane.setVisible(false);
//
//        mainPaneAdmin.setVisible(false);
//
//        profesoriPaneAdmin.setVisible(false);
//        profesoriDisciplinePaneAdmin.setVisible(false);
//
//        orarPaneAdmin.setVisible(false);
//        orarPane.setVisible(false);
//        alegeClasaPane.setVisible(false);
//
//        blankPane.setVisible(false);
//
//        savePane.setVisible(false);
//        resetPasswordPane.setVisible(false);
//        actionTableCadre.setVisible(false);
    }

    @FXML
    public void backAdminMain() {
        StageHandler.setScene("fxml/main.fxml");
    }
}
