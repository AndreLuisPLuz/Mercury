package com.mercury.controllers;

import com.mercury.enums.Page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSceneController {
    
    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    private Button btLogin;

    @FXML
    void submitLogin(ActionEvent event) {
        
        // Como trocar de tela:
        Stage stage = (Stage)btLogin.getScene().getWindow();
        stage.setScene(Page.PROJECT.loadScene());
    }
}
