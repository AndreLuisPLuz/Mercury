package com.mercury.enums;

import com.mercury.App;

import java.net.URL;
import java.util.function.Consumer;

import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public enum Page {
    LOGIN("login-scene.fxml"),
    HOME("home-scene.fxml"),
    PROJECT("project-scene.fxml");

    private String filepath;

    
    public Scene loadScene() {
        URL url = App.class.getResource(filepath);
        FXMLLoader loader = new FXMLLoader(url);

        try {
            return new Scene(loader.load());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public <T> Scene loadScene(Consumer<T> consumer) {
        URL url = App.class.getResource(filepath);
        FXMLLoader loader = new FXMLLoader(url);

        T controller = loader.getController();
        consumer.accept(controller);

        try {
            return new Scene(loader.load());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    Page(String path) {
        this.filepath = path;
    }
}
