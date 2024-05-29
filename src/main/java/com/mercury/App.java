package com.mercury;

import javafx.application.Application;
import javafx.stage.Stage;

import com.mercury.entity.UserEntity;
import com.mercury.enums.Page;
import com.mercury.repository.Repository;


public class App extends Application {
    public static void main(String[] args) throws Exception {
        // launch(args);
        Repository<UserEntity> repo = new Repository<>(UserEntity.class);
        try {
            UserEntity user = repo.select(Long.parseLong("1")).get();
            System.out.println(user.toString());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(Page.LOGIN.loadScene());
        primaryStage.show();
    }
}