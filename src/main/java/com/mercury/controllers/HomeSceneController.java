package com.mercury.controllers;


import java.util.List;

import com.mercury.entity.ProjectEntity;
import com.mercury.entity.UserEntity;
import com.mercury.repository.Repository;
import com.microsoft.sqlserver.jdbc.dataclassification.Label;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;;

public class HomeSceneController {
    
    protected UserEntity user;
    protected Repository<ProjectEntity> repository = new Repository(ProjectEntity.class);
    protected List<ProjectEntity> projects;
    protected String labelContent = "Pimba";

    @FXML
    private VBox vbox;

    @FXML
    private Pane panezinho;

    @FXML
    private FlowPane flowPane;




    @FXML
    protected void initialize()
    {
        try{
            this.projects = repository.selectMany().get();
        }
        catch(Exception e){
            System.out.println("Você foi pimbado");
        }

        Pane pane = new Pane();
        pane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        pane.setPrefSize(200, 200);
        Button btn = new Button();

       Text text =  new Text(labelContent);
       text.setFill(Color.WHITE);

        pane.getChildren().add(text); 
        text.setLayoutX(50);
        text.setLayoutY(50);
        pane.getChildren().addAll(btn);  
        flowPane.getChildren().add(pane);

        
    }
}