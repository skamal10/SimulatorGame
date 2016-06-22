/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import pm.data.DataManager;
import pm.data.Player;
import pm.data.Team;
import saf.AppTemplate;

/**
 *
 * @author sajidkamal
 */
public class RosterView {
    
    TableView<Player> roster;
    ObservableList<Team> teams;
    ObservableList<Player> players;
    VBox rosterPane;
    Pane workPane;
    AppTemplate app;
    ComboBox teamPicker;
    TableView rosterTable;
    
    public RosterView(AppTemplate app, Pane workPane){
        this.app=app;
        this.workPane=workPane;
        
        DataManager data= (DataManager) app.getDataComponent();
        teams= data.getTeamsArray();
        
        rosterPane= new VBox();
       
        
        teamPicker = new ComboBox(teams);
         teamPicker.valueProperty().addListener(new ChangeListener<Team>() {
            @Override 
            public void changed(ObservableValue ov, Team t, Team t1) {                
                           loadRoster(t1);
            }    
        });

        rosterTable = new TableView();
        teamPicker.getSelectionModel().select(0);
        
        initTable();
        initStyles();
        
       rosterPane.getChildren().addAll(teamPicker,rosterTable);
       
    

    }
    
    public void show(){
        workPane.getChildren().add(rosterPane);
    }
    
    public void loadRoster(Team t){
        rosterTable.setItems(t.getPlayers());
    }
   
    public void initTable(){
        
        TableColumn<Player,String> playerName= new TableColumn("Name");
        playerName.setSortable(true);
        playerName.setCellValueFactory(
        new PropertyValueFactory<>("Name"));
         
        playerName.setMinWidth(160);
         
        TableColumn<Player,String> playerPos= new TableColumn("Pos");
        playerPos.setSortable(true);
        playerPos.setCellValueFactory(
            new PropertyValueFactory<>("Pos"));
  
        TableColumn<Player,Integer> playerAge= new TableColumn("Age");
        playerAge.setSortable(true);
        playerAge.setCellValueFactory(
        new PropertyValueFactory<>("Age"));

          
        TableColumn<Player,String> playerHeight= new TableColumn("Height");
        playerHeight.setSortable(true);
        playerHeight.setCellValueFactory(
        new PropertyValueFactory<>("RealHeight"));

        
        TableColumn<Player,Integer> playerWeight= new TableColumn("Weight");
        playerWeight.setSortable(true);
        playerWeight.setCellValueFactory(
        new PropertyValueFactory<>("Weight"));
         
        TableColumn<Player,Integer> playerExp= new TableColumn("Exp");
        playerExp.setSortable(true);
        playerExp.setCellValueFactory(
        new PropertyValueFactory<>("Exp"));
        
        TableColumn<Player,Integer> playerInRating= new TableColumn("Inside");
        playerInRating.setSortable(true);
        playerInRating.setCellValueFactory(
        new PropertyValueFactory<>("InsideGrade"));
        
        TableColumn<Player,Integer> playerMidRating= new TableColumn("Mid");
        playerMidRating.setSortable(true);
        playerMidRating.setCellValueFactory(
        new PropertyValueFactory<>("MidGrade"));
        
        TableColumn<Player,Integer> playerThreeRating= new TableColumn("Three");
        playerThreeRating.setSortable(true);
        playerThreeRating.setCellValueFactory(
        new PropertyValueFactory<>("ThreePointGrade"));
        
        TableColumn<Player,Integer> playerHandleRating= new TableColumn("Handle");
        playerHandleRating.setSortable(true);
        playerHandleRating.setCellValueFactory(
        new PropertyValueFactory<>("HandleGrade"));
        
        TableColumn<Player,Integer> playerPostRating= new TableColumn("Post D");
        playerPostRating.setSortable(true);
        playerPostRating.setCellValueFactory(
        new PropertyValueFactory<>("PostDefenseGrade"));
        
        TableColumn<Player,Integer> playerPerimeterRating= new TableColumn("Per D");
        playerPerimeterRating.setSortable(true);
        playerPerimeterRating.setCellValueFactory(
        new PropertyValueFactory<>("PerimeterDefenseGrade"));
        
        TableColumn<Player,Integer> playerReboundRating= new TableColumn("Rebounding");
        playerReboundRating.setSortable(true);
        playerReboundRating.setCellValueFactory(
        new PropertyValueFactory<>("ReboundingGrade"));

    rosterTable.getColumns().addAll(playerName,playerPos,playerAge,playerHeight,playerWeight,playerExp,playerInRating,playerMidRating,playerThreeRating,playerHandleRating,playerPostRating,playerPerimeterRating,playerReboundRating);
    }
    
    public void initStyles(){
        rosterPane.prefHeightProperty().bind(workPane.heightProperty());
        rosterPane.prefWidthProperty().bind(workPane.widthProperty());
        rosterPane.getStyleClass().add("test");
        rosterPane.setSpacing(20);
        rosterPane.setAlignment(Pos.CENTER);
       
    }
    
}
