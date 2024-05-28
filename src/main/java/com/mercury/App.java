package com.mercury;

import javafx.stage.Stage;

import com.mercury.enums.Page;

import javafx.application.Application;

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