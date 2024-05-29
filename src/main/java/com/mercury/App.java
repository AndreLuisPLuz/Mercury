package com.mercury;

import javafx.application.Application;
import javafx.stage.Stage;

import com.mercury.entity.UserEntity;
import com.mercury.enums.Page;
import com.mercury.repository.BaseRepository;


public class App extends Application {
    public static void main(String[] args) {
        // launch(args);
        BaseRepository<UserEntity> repo = new BaseRepository<>(UserEntity.class);
        try {
            UserEntity user = repo.select(Long.parseLong("1")).get();
            System.out.println(user.toString());
        } catch (Exception e) {
            System.out.println("torxa kkkk");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(Page.LOGIN.loadScene());
        primaryStage.show();
    }

}