package com.mercury;

import javax.management.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javafx.application.Application;
import com.mercury.*;

import javafx.stage.Stage;

import com.mercury.enums.Page;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(Page.LOGIN.loadScene());
        primaryStage.show();
    }

}