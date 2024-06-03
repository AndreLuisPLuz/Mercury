package com.mercury;


import javafx.application.Application;
import javafx.stage.Stage;

import com.mercury.enums.Page;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(Page.HOME.loadScene());
        primaryStage.show();
    }

}