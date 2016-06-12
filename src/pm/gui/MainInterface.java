/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import saf.AppTemplate;

/**
 *
 * @author sajidkamal
 */
public class MainInterface {
    
    AppTemplate app;
    Pane workspace;
    BorderPane fullPane;
    VBox sideBar;
    Pane workPane;
    FlowPane bottomPane;
    
    
    public MainInterface(AppTemplate initApp, Pane initWorkspace){
        app= initApp;
        workspace= initWorkspace;
        
        fullPane= new BorderPane();
        sideBar= new VBox();
        workPane= new Pane();
        bottomPane = new FlowPane();

        fullPane.setBottom(bottomPane);
        fullPane.setLeft(sideBar);
        fullPane.setCenter(workPane);
        
        workspace.getChildren().add(fullPane);

        initStyles();
        
    }
    
    public void initStyles(){
        
        //FIRST BIND ALL THE PANES ACCORDINGLY
        
        fullPane.prefWidthProperty().bind(workspace.widthProperty());
        fullPane.prefHeightProperty().bind(workspace.heightProperty());

        fullPane.getStyleClass().add("max_pane");
        bottomPane.getStyleClass().add("vbox_style");
        bottomPane.setPrefHeight(50);
        
        sideBar.getStyleClass().add("sidebar");
        sideBar.setPrefWidth(250);
        

        
        
 
    }
    
}
