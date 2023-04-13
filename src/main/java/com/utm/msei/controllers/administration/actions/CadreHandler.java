package com.utm.msei.controllers.administration.actions;

import com.utm.msei.persistence.dto.UserDto;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.control.CheckComboBox;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.utm.msei.Main.serviceHandler;

public class CadreHandler {

    private List<UserDto> allUsers;
    private List<CadreTable> allUsersTable;

    public CadreHandler() {
        allUsers = serviceHandler.getUserService().getAllCadre();
        allUsersTable = allUsers.stream().map(user -> new CadreTable(user.getId(), user.getNume(), user.getPrenume(), user.getEmail(), user.getUserType().stream().map(Enum::name)
                .collect(Collectors.joining(",")), user.getTelefon(), user.getIdnp(), user.getDataNastere())).collect(Collectors.toList());
    }

    public List<CadreTable> getAllUsers() {
        return allUsersTable;
    }

    public void setDeleted(CadreTable selected) {
        for (CadreTable cd : allUsersTable) {
            if (cd.getId() == selected.getId()) {
                cd.setDeleted();
                break;
            }
        }
    }

    public static class CadreTable {
        /**
         * 0 -> user is new
         */
        @Getter
        private int id;
        @Getter
        @Setter
        private String nume;
        @Getter
        @Setter
        private String prenume;
        @Getter
        @Setter
        private String email;
        @Getter
        @Setter
        private String functie;
        @Getter
        @Setter
        private String telefon;
        @Getter
        @Setter
        private String idnp;
        @Getter
        @Setter
        private LocalDate dataNastere;
        private boolean wasModified = false;
        private boolean wasDeleted = false;

        public CadreTable() {
            this.id = 0;
        }

        public CadreTable(int id, String nume, String prenume, String email, String userType, String telefon, String idnp, LocalDate dataNastere) {
            this.id = id;
            this.nume = nume;
            this.prenume = prenume;
            this.email = email;
            this.functie = userType.startsWith("[") ? userType.substring(1, userType.length()-1) : userType;
            this.telefon = telefon;
            this.idnp = idnp;
            this.dataNastere = dataNastere;
        }

//        public void setFunctie(ObservableList<String> checkedItems) {
//            functie = String.join(",", checkedItems);
//        }
//
//        public Set<String> getFunctie() {
//            return new LinkedHashSet<>(List.of(this.functie.split(",")));
//        }

        public boolean isNew() {
            return this.id == 0;
        }

        public void setModified() {
            this.wasModified = true;
        }
        public void setDeleted() {
            this.wasDeleted = true;
        }
        public boolean isModified() {
            return this.wasModified;
        }
        public boolean isDeleted() {
            return wasDeleted;
        }
    }


//    example singleChoice comboBox for tableView
//
//    public static class FunctieCellFactory implements Callback<TableColumn<CadreTable, String>, TableCell<CadreTable, String>> {
//        private final List<String> options;
//
//        public FunctieCellFactory(List<String> options) {
//            this.options = options;
//        }
////        public FunctieCellFactory(ComboBox<String> options) {
////            this.options = options;
////        }
//
//        @Override
//        public TableCell<CadreTable, String> call(TableColumn<CadreTable, String> column) {
//            return new ComboBoxTableCell<>(FXCollections.observableArrayList(options)) {
//                @Override
//                public void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (empty || item == null) {
//                        setText(null);
//                    } else {
//                        setText(item);
//                    }
//                }
//            };
//        }
//    }

}
