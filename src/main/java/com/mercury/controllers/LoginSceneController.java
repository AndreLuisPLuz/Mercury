package com.mercury.controllers;

import com.mercury.enums.Page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginSceneController {
    
    @FXML
    protected Button btnOia;

    @FXML
    protected void nextPage(ActionEvent e) throws Exception {
        Stage currentStage = (Stage)btnOia.getScene().getWindow();

        currentStage.setScene(Page.HOME.loadScene((HomeSceneController n) -> {
            n.test = "123";
        }));
    }
}
