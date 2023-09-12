package lk.ijse.d24.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.d24.bo.BOFactory;
import lk.ijse.d24.bo.custom.DashBoardBO;
import lk.ijse.d24.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class Dashboard_form_controller implements Initializable {

    @FXML
    private AnchorPane MainPane;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Text lblTotalRooms;

    @FXML
    private Text lblAvailableRooms;

    @FXML
    private Text lblBookedRooms;

    @FXML
    private Text txtTime;

    @FXML
    private Text txtDate;

    @FXML
    private Text lblREGStu;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnRegisterStudent;

    @FXML
    private JFXButton btnManageRoom;

    @FXML
    private JFXButton btnReserveRoom;

    @FXML
    private JFXButton btnPending;

    /*@FXML
    private JFXTextField tfUsername;

    @FXML
    private JFXTextField tfPassword;*/
    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;
    @FXML
    private AnchorPane changeDetailsPane;
    @FXML
    private Text lblTopic;
    private UserDTO userDTO = new UserDTO();
    private DashBoardBO dashboardBO = (DashBoardBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DASHBOARD);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTxtDateTime();
        try {
            setTotReservedRoomsCount();
            setTotAvailableRoomsCount();
            setREGStuCount();
            changeDetailsPane.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setTotReservedRoomsCount() throws Exception {
        lblBookedRooms.setText(String.valueOf(dashboardBO.getAllReservationCount()));
    }

    private void setTotAvailableRoomsCount() throws Exception {
        lblAvailableRooms.setText(String.valueOf(dashboardBO.getTotOfAvailableRoomsCount()));
    }
    private void setREGStuCount() throws Exception {
        lblREGStu.setText(String.valueOf(dashboardBO.getREGStuCount()));
    }

    private void setTxtDateTime() {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        java.util.Date Date = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(Date);
        txtDate.setText(dateString);

    }
    private void updateTime() {
        LocalTime now = LocalTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        txtTime.setText(formattedTime);
    }
    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dashboard_form_controller.class.getResource("/view/dashboard_form.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
    }

    public void btnRegisterStudentOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("Manage");
    }

    public void btnManageRoomOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/room_form.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("Manage");
    }

    public void btnReserveRoomOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/reservation_form.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("Manage");
    }


    public void btnPendingOnAction(ActionEvent actionEvent) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("/view/pending_payments_form.fxml"));
        MainPane.getChildren().setAll(node);
        lblTopic.setText("Manage");
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Logout?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {


            Stage loginStage = (Stage) btnDashboard.getScene().getWindow();
            loginStage.close();
            FXMLLoader fxmlLoader= new FXMLLoader(this.getClass().getResource("/view/login_form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        }
    }

    public void linkChangeDetailsOnAction(ActionEvent actionEvent) {
        loadUserDetails();
        changeDetailsPane.setVisible(!changeDetailsPane.isVisible());

    }


    public void btnNewLoginOnAction(ActionEvent actionEvent) {
        boolean isVaild =check();
        if (isVaild){
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to make Changes?", yes, no).showAndWait();
            if (result.orElse(no) == yes) {
                try {
                    userDTO.setUsername(tfUsername.getText());
                    userDTO.setPassword(tfPassword.getText());

                    boolean isUpdated = dashboardBO.updateUser(userDTO);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION , "User Details Updated!").show();

                        linkChangeDetailsOnAction(new ActionEvent());
                    }else {
                        new Alert(Alert.AlertType.WARNING , "User Details didn't Updated!!!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR , "Oops..Something went wrong!!!").show();
                }
            }
        }


    }
    private boolean check() {
        if (!tfPassword.getText().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new Alert(Alert.AlertType.WARNING , "Please enter a valid Password!").show();
            return false;
        }
        if (!tfUsername.getText().matches("^[A-Za-z]+$")){
            new Alert(Alert.AlertType.WARNING , "Please enter a valid User Name").show();
            return false;
        }
        return true;
    }

    public void setUser(UserDTO user) {
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
    }
    private void loadUserDetails() {
        try {
            userDTO = dashboardBO.getUser(userDTO);

            tfUsername.setText(userDTO.getUsername());
            tfPassword.setText(userDTO.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
