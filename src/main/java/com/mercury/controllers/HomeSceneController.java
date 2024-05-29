package com.mercury.controllers;


import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.mercury.entity.ProjectEntity;
import com.mercury.entity.UserEntity;
import com.mercury.repository.Repository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeSceneController {
    
    protected Long user = 0L;
    protected Repository<ProjectEntity> repository = new Repository(ProjectEntity.class);
    protected CompletableFuture<ProjectEntity> projects;

    @FXML
    protected void initialize()
    {
        this.projects = repository.select(user);
    }
}