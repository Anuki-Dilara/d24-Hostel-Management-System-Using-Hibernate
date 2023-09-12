package lk.ijse.d24.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.d24.bo.BOFactory;
import lk.ijse.d24.bo.custom.StudentBO;
import lk.ijse.d24.dto.StudentDTO;
import lk.ijse.d24.dto.tm.StudentTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Find_student_form_controller implements Initializable {
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<StudentTM> tblStudents;


    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    private ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setCellValueFactories();
            loadTableStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setCellValueFactories(){
        colId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }
    private void loadTableStudents() throws Exception {
        studentTMS= FXCollections.observableArrayList();
        List<StudentDTO> students = studentBO.getAll();

        for (StudentDTO student: students) {
            studentTMS.add(new StudentTM(student.getStudent_id(), student.getName(), student.getAddress(), student.getContact(),student.getDob(), student.getGender()));

        }
        tblStudents.setItems(studentTMS);
    }
}
