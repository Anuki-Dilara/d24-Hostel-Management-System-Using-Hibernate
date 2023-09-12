package lk.ijse.d24;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.d24.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("/view/login_form.fxml")).load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
        new Thread(() ->{
            startDB();
        }).start();
    }
    private void startDB(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }
}
