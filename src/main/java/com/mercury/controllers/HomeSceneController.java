package com.mercury.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeSceneController {
    
    protected String test;

    @FXML
    protected Button btnTest;

    @FXML
    protected void initializeEvent(ActionEvent e) throws Exception {
        btnTest.setText(test);
    }
}
