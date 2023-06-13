package com.example.jafafxlearn.controller.Staff;

import com.example.jafafxlearn.RuntimeConfiguration;
import com.example.jafafxlearn.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class staffSideNav implements Initializable {
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button countTaskDone,countTask;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCountData();
        bp.setCenter(ap);
    }
    private void setCountData() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();
        String myid = RuntimeConfiguration.getLoginId();
        //get spv count

        //get Task count
        String query = "select count(id) as count from kpi where userID="+myid;
        try {
            Statement statement = connection.createStatement();
            ResultSet output = statement.executeQuery(query);
            while (output.next()){
                String res = output.getString("count");
                countTask.setText("   "+res+"\nTask");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //get Task Done count
        query = "select count(id) as count from kpi  where (valueActual != '-' or timeActual != '-') and userID="+myid;
        try {
            Statement statement = connection.createStatement();
            ResultSet output = statement.executeQuery(query);
            while (output.next()){
                String res = output.getString("count");
                countTaskDone.setText("      "+res+"\nTask Done");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dashboardNav(ActionEvent event) {

        setCountData();
        bp.setCenter(ap);
    }

    public void controllingNav(ActionEvent event) {
        loadPage("staff/controlling-page");
    }

    public  void loadPage(String page){
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("/"+page+".fxml"));
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        bp.setCenter(root);
    }

    public void logout(ActionEvent event) {
        try {
            Window mywindow = bp.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Stage stage = (Stage) mywindow;
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 1000, 700));
            stage.show();
            RuntimeConfiguration.saveLoginId("0");
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
