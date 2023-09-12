package lk.ijse.d24.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.d24.bo.BOFactory;
import lk.ijse.d24.bo.custom.ReservationBO;
import lk.ijse.d24.dto.ReservationDTO;
import lk.ijse.d24.dto.RoomDTO;
import lk.ijse.d24.dto.StudentDTO;
import lk.ijse.d24.dto.tm.ResDetailsTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Reservation_details_form_controller implements Initializable {
    @FXML
    private TableView<ResDetailsTM> tblResDetails;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colRegDate;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colStatus;


    private ObservableList<ResDetailsTM> resDetailsTMS = FXCollections.observableArrayList();
    private ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadCellValueFactories();
            loadRTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadCellValueFactories() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colResId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    private void loadRTable() throws Exception {
        resDetailsTMS = FXCollections.observableArrayList();
        List<ReservationDTO> reservations = reservationBO.getAllDetails();
        for (ReservationDTO reservation : reservations) {
            StudentDTO student = reservation.getStudent();
            RoomDTO room = reservation.getRoom();

            resDetailsTMS.add(new ResDetailsTM(
                    student.getStudent_id(),
                    student.getName(),
                    student.getAddress(),
                    student.getContact(),
                    student.getDob(),
                    student.getGender(),
                    reservation.getRes_id(),
                    reservation.getDate(),
                    room.getType(),
                    reservation.getStatus()
            ));
        }

        tblResDetails.setItems(resDetailsTMS);
    }
}
