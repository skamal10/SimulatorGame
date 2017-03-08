/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import pm.data.DataManager;
import pm.file.FileManager;
import properties_manager.PropertiesManager;
import saf.AppTemplate;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;
import static saf.settings.AppPropertyType.LOAD_ERROR_MESSAGE;
import static saf.settings.AppPropertyType.LOAD_ERROR_TITLE;
import static saf.settings.AppPropertyType.LOAD_WORK_TITLE;
import static saf.settings.AppPropertyType.SAVE_WORK_TITLE;
import static saf.settings.AppPropertyType.WORK_FILE_EXT;
import static saf.settings.AppPropertyType.WORK_FILE_EXT_DESC;
import static saf.settings.AppStartupConstants.PATH_WORK;
import saf.ui.AppMessageDialogSingleton;

/**
 *
 * @author sajidkamal
 */
public class MainMenuWorkspace {
    
    AppTemplate app;
    TextField leagueName;
    TextField JSONFileField;
    Button uploadFile;
    VBox textArea;
    Pane workspace;
    HBox headerPane;
    HBox infoPane;
    Button okButton;
    Button cancelButton;
    HBox fileChooserPane;
    
    // Start up menu
    public MainMenuWorkspace(Pane initWorkspace,AppTemplate initApp){
        
        app=initApp;

        textArea= new VBox();
        
        headerPane = new HBox();
        Label header= new Label("Create New League");
        
        header.getStyleClass().add("heading_label");
        headerPane.getChildren().add(header);
        
        infoPane = new HBox();
        Label leagueText= new Label("League Name: ");
        leagueName= new TextField();
        
        fileChooserPane = new HBox();
        Label uploadText = new Label("Upload League File:");
        JSONFileField= new TextField();
        JSONFileField.setEditable(false);
        uploadFile= new Button("Upload File");
        
        HBox buttonPane= new HBox();
        okButton= new Button("OK");
        cancelButton= new Button("Cancel");
        buttonPane.getChildren().addAll(okButton,cancelButton);
        
        fileChooserPane.getChildren().addAll(uploadText,JSONFileField,uploadFile);
        infoPane.getChildren().addAll(leagueText,leagueName);
        textArea.getChildren().addAll(headerPane,infoPane,fileChooserPane,buttonPane);
        
        
        
        workspace= initWorkspace;
        workspace.getChildren().add(textArea);
       
        initStyles();
        initHandlers();
        
        
        
    }
    
    public void initStyles(){
        textArea.prefWidthProperty().bind(workspace.widthProperty());
        textArea.getStyleClass().add("vbox_style");
        textArea.setPadding(new Insets(80, 60, 80, 60));
        textArea.setSpacing(20);
        
        infoPane.setSpacing(38);
        fileChooserPane.setSpacing(10);
        
        headerPane.getStyleClass().add("vbox_style");
        headerPane.setAlignment(Pos.CENTER);
    }


    // Initiliaze all of the Main Menu handlers
    public void initHandlers(){
        okButton.setOnAction(e->{
            DataManager data =(DataManager) app.getDataComponent();
            FileManager fileManager = (FileManager) app.getFileComponent();
            Workspace workspace = (Workspace) app.getWorkspaceComponent();
            
            
            data.setLeagueName(leagueName.getText());
            fileManager.setLeagueFilePath(JSONFileField.getText());
            
            try {
                fileManager.importData(data,JSONFileField.getText());
            } catch (IOException ex) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show("ERROR", "There was a problem loading your file");
            }

            workspace.initMainInterface();
        });
        
        uploadFile.setOnAction(e->{
              fileChooserPath();
        });
    }
    
    public void fileChooserPath(){
        // WE'LL NEED TO GET CUSTOMIZED STUFF WITH THIS
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	
        // AND NOW ASK THE USER FOR THE FILE TO OPEN
        FileChooser fc = new FileChooser();
	fc.setTitle(props.getProperty(LOAD_WORK_TITLE));
        File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());

        if(selectedFile!=null){
            JSONFileField.setText(selectedFile.getAbsolutePath());
            
        }
        
        
    }
}
