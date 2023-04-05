package com.utm.msei.controllers.administration;

import com.utm.msei.controllers.administration.actions.CadreHandler;
import com.utm.msei.controllers.interfaces.ControllerI;
import com.utm.msei.converter.ImageHandler;
import com.utm.msei.handler.StageHandler;
import com.utm.msei.persistence.dto.AdministratieDto;
import com.utm.msei.persistence.dto.ProfesorDto;
import com.utm.msei.persistence.dto.UserDto;
import com.utm.msei.persistence.dto.enums.EntityTypeEnum;
import com.utm.msei.security.PasswordHandler;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.bytebuddy.asm.Advice;
import org.controlsfx.control.CheckComboBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.utm.msei.Main.serviceHandler;
import static com.utm.msei.controllers.login.Login.userHandler;

public class Administratie implements ControllerI {


    @FXML
    private ImageView imageView;

    @FXML
    private Text numePrenumeAdmin, statusAdmin, emailAdmin, telefonAdmin, mesajSavePane;


    @FXML
    private StackPane mainPaneAdmin, profesoriPaneAdmin, profesoriDisciplinePaneAdmin;

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
    private Button addCadre, deleteCadre, saveCadreTable, cadreAdminBtn, profDiscAdminBtn, orarAdminBtn, eleviAdminBtn, buttonOk;
    @FXML
    private AnchorPane mainAdmin;
    @FXML
    private Pane savePane;
    @FXML
    private HBox navBar;
    @FXML
    private VBox userVBox, actionTableCadre;

    @FXML
    public void start() {

        // TODO noutati
        // TODO SAVE PANE

        this.initCredentials();
        this.initPoza();

        this.hideAll();

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

        cadreAdminBtn.setOnMouseClicked(event -> {
            this.showCadreAdmin();
        });

        profDiscAdminBtn.setOnMouseClicked(event -> {
            this.showProfesoriDisciplineAdmin();
        });
//        orarAdminBtn.setOnMouseClicked(event -> {
//            this.showCadreAdmin();
//        });

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
    private void showProfesoriDisciplineAdmin() {
        this.hideAll();
        mainPaneAdmin.setVisible(true);
        profesoriDisciplinePaneAdmin.setVisible(true);
//        actionTableCadre.setVisible(true);

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
                            cadreTable.setDataNastere(date);
                            cadreTable.setModified();
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
                            System.out.println("\n\n\n NEW\t\tNEW\t\tNEW\t\tNEW\t\tNEW\t\tNEW\t\tNEW");
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
                            System.out.println("\n\n\n MDOIFIED\t\tMDOIFIED\t\tMDOIFIED\t\tMDOIFIED\t\tMDOIFIED\t\tMDOIFIED\t\t");
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
//                                userDto.setUserType(user.getFunctie());
                                userDto.setTelefon(user.getTelefon());
                                userDto.setIdnp(user.getIdnp());
                                userDto.setDataNastere(user.getDataNastere());
                                if (userDto.getUserType().contains(EntityTypeEnum.DIRECTOR)) {
                                    userDto.setEmail(user.getEmail());
                                    int i = serviceHandler.getUserService().update(userDto);
                                } else if (userDto.getUserType().contains(EntityTypeEnum.ADJUNCT)) {
                                    userDto.setEmail(user.getEmail());
                                    int i = serviceHandler.getUserService().update(userDto);
                                } else if (userDto.getUserType().contains(EntityTypeEnum.PROFESOR)) {
                                    userDto.setEmail(user.getEmail());
                                    int i = serviceHandler.getUserService().update(userDto);
                                }

//                                serviceHandler.getUserService().update(userDto);
                            }
                        }

                    }
                }
                for (CadreHandler.CadreTable user : cadreHandler.getAllUsers()) {
                    if (user.isDeleted()) {
                        System.out.println("\n\n\n DELETED\t\tDELETED\t\tDELETED\t\tDELETED\t\tDELETED\t\t");

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
    private void hideAll() {
        actionTableCadre.setVisible(false);
        savePane.setVisible(false);

        mainPaneAdmin.setVisible(false);

        profesoriPaneAdmin.setVisible(false);
        profesoriDisciplinePaneAdmin.setVisible(false);
    }

    @FXML
    public void backAdminMain() {
        StageHandler.setScene("fxml/main.fxml");
    }
}
