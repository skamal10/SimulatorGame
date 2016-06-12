/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import saf.AppTemplate;

/**
 *
 * @author sajidkamal
 */
public class startUpWorkspace {
   
    AppTemplate app;
    Button startNewButton;
    Button loadButton;
    Button exitButton;
    VBox workspace;
    Pane fullWorkspace;
    public startUpWorkspace(Pane initWorkspace,AppTemplate initApp){
        
        
        app= initApp;
        fullWorkspace = initWorkspace;
        workspace= new VBox();
        
        startNewButton=new Button("Start New League");
        loadButton= new Button("Load Existing League");
        exitButton= new Button("Exit Application");
        workspace.getStyleClass().add("start_up");
        workspace.getChildren().addAll(startNewButton,loadButton,exitButton);

        fullWorkspace.getChildren().add(workspace);
        
        initHandlers();
        initStyles();

        
    }
    
    public void initStyles(){
        workspace.prefWidthProperty().bind(fullWorkspace.widthProperty());
        workspace.prefHeightProperty().bind(fullWorkspace.heightProperty());
        workspace.setAlignment(Pos.CENTER);
        
        
        
        
    }
    
    public void initHandlers(){
        startNewButton.setOnAction(e->{
            startHandler();
        });
        
        loadButton.setOnAction(e->{
            loadHandler();
        });
        
        exitButton.setOnAction(e->{
            exitHandler();
        });
    }
    public void startHandler(){
        Workspace mainWorkspace= (Workspace) app.getWorkspaceComponent();
        mainWorkspace.mainMenuStartUp(); // init the start-up menu
        
    }
    
    public void loadHandler(){
        System.out.println("Not Yet Supported");
    }
    public void exitHandler(){
        System.out.println("Not Yet Supported");
    }
}
