package com.example.jafafxlearn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root,1000,700));
       // stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
