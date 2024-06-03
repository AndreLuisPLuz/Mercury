package com.mercury.controllers;


import java.util.List;

import com.mercury.entity.ProjectEntity;
import com.mercury.entity.UserEntity;
import com.mercury.repository.Repository;

import javafx.fxml.FXML;

public class HomeSceneController {
    
    protected UserEntity user;
    protected Repository<ProjectEntity> repository = new Repository(ProjectEntity.class);
    protected List<ProjectEntity> projects;

    @FXML
    protected void initialize()
    {
        try{
            this.projects = repository.selectMany().get();
        }
        catch(Exception e){
            System.out.println("Você foi pimbado");
        }
        
    }
}