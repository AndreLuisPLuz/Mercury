package com.mercury.controllers;

import com.mercury.entity.UserEntity;
import com.mercury.enums.Page;
import com.mercury.repository.Repository;
import com.mercury.services.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginSceneController {
    private final UserService uService = new UserService();
    
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

    @FXML
    private void btLoginClick(ActionEvent e) throws Exception {
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        Boolean isValidLogin;

        try {
            isValidLogin = uService.isLoginAttemptValid(username, password).get();
        } catch (Exception ex) {
            isValidLogin = false;
        }
        
        if (isValidLogin) {
            Stage stage = (Stage)btLogin.getScene().getWindow();
            stage.setScene(Page.HOME.loadScene((HomeSceneController hsc) -> {
                UserEntity user;
                try {
                    user = uService.getUserByUsername(username).get();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                hsc.user = user;
            }));
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Usuário ou senha inválidos.");

            alert.showAndWait();
        }
    }
}
