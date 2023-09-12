package lk.ijse.d24.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.d24.bo.BOFactory;
import lk.ijse.d24.bo.custom.ReservationBO;
import lk.ijse.d24.dto.ReservationDTO;
import lk.ijse.d24.dto.RoomDTO;
import lk.ijse.d24.dto.StudentDTO;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Reservation_form_controller implements Initializable {
    @FXML
    private TextField txtRID;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXComboBox<String> cmbstatus;

    @FXML
    private JFXComboBox<String> cmbRType;

    @FXML
    private JFXButton btnDetails;

    @FXML
    private DatePicker txtRDate;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtAddress;
    @FXML
    private Label lblKMoney;
    @FXML
    private JFXComboBox<String> genderCombo;
    private ReservationBO reservationBO;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservationBO= (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
        try {
            loadType();
            loadStatus();
            loadGender();
            setDate();
            txtRID.setText(reservationBO.setReservationID());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDate() {
        java.util.Date Date = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(Date);
        txtRDate.setValue(LocalDate.parse(dateString));
    }

    private void loadStatus() {
        try {
            ObservableList<String> options = FXCollections.observableArrayList(
                    "Paid",
                    "Pending"
            );
            cmbstatus.setItems(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadGender() {
        try {
            ObservableList<String> options = FXCollections.observableArrayList(
                    "Male",
                    "Female",
                    "Other"
            );
            genderCombo.setItems(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadType() {
        try {
            ObservableList<String> options = FXCollections.observableArrayList(
                    "Non-AC",
                    "Non-AC / Food",
                    "AC",
                    "AC / Food"
            );
            cmbRType.setItems(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void btnDetailsOnAction(ActionEvent event) throws IOException {
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("/view/reservation_details_form.fxml")).load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Reservation Details");
        stage.show();

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean isValid = check();
        if (isValid){
            try {
                StudentDTO student = new StudentDTO(
                        txtStudentId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtContact.getText(),
                        txtDOB.getValue(),
                        genderCombo.getValue(),
                        new ArrayList<>()
                );

                RoomDTO room = getRoom(cmbRType.getValue());

                if (room != null) { // Check if room is not null
                    ReservationDTO reservation = new ReservationDTO(
                            txtRID.getText(),
                            txtRDate.getValue(),
                            cmbstatus.getValue(),
                            student,
                            room
                    );

                    student.getReservations().add(reservation);
                    room.setReservations(new ArrayList<>());
                    room.getReservations().add(reservation);

                    boolean isRegistered = reservationBO.registerStudent(reservation);
                    if (isRegistered) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Registration Completed!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Registration Failed!!!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Selected room is invalid or not found!").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Oops..Something Went Wrong...").show();
            }
        }
    }

    private RoomDTO getRoom(String value) {
        try {

            List<RoomDTO> allRooms = reservationBO.getAllRooms();
            for (RoomDTO room : allRooms) {
                if (value.equals(room.getType())){
                    return room;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void txtStudentIdOnAction(ActionEvent actionEvent) {

        //need to implement
        try {
            ReservationDTO reservation = reservationBO.setFields(txtStudentId.getText());
            if (reservation!=null){


            }else{
                new Alert(Alert.AlertType.WARNING, "No Matching Student found!").show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cmbRTypeOnAction(ActionEvent actionEvent) {
        RoomDTO room = getRoom(cmbRType.getValue());
        lblKMoney.setText("Rs: " + room.getKey_money());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValid = check();
        if (isValid){
            try {
                StudentDTO student = new StudentDTO(
                        txtStudentId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtContact.getText(),
                        txtDOB.getValue(),
                        genderCombo.getValue(),
                        new ArrayList<>()
                );

                RoomDTO room = getRoom(cmbRType.getValue());

                if (room != null) {
                    ReservationDTO reservation = new ReservationDTO(
                            txtRID.getText(),
                            txtRDate.getValue(),
                            cmbstatus.getValue(),
                            student,
                            room
                    );

                    student.getReservations().add(reservation);
                    room.setReservations(new ArrayList<>());
                    room.getReservations().add(reservation);

                    boolean isRegistered = reservationBO.UpdateStudent(reservation);
                    if (isRegistered) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Registration Completed!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Registration Failed!!!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Selected room is invalid or not found!").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Oops..Something Went Wrong...").show();
            }
        }
    }


    private boolean check() {

        if (!txtName.getText().matches("^[A-Za-zÀ-ÖØ-öø-ÿ-]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ-]+)+$")){
            new Alert(Alert.AlertType.WARNING , "Please enter a valid User Name").show();
            return false;
        }
        if (!txtContact.getText().matches("^\\+?\\d{8,}$")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid Contact number").show();
            return false;
        }
        if (!txtAddress.getText().matches("^.+$")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid Address").show();
            return false;
        }

        return true;
    }
}
