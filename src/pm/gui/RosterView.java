/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pm.data.DataManager;
import pm.data.Player;
import pm.data.Team;
import saf.AppTemplate;

/**
 *
 * @author sajidkamal
 */
public class RosterView extends Page {
    

    ObservableList<Team> teams;
    ObservableList<Player> players;
    VBox rosterPane;
    Pane workPane;
    AppTemplate app;
    ComboBox teamPicker;
    TableView rosterTable;
    TableView statsTable;
    TabPane rosterTabs;
    int currentTeamIndex=0; // this is to keep track of which team page you are on, so if you reload this page, it will start off at the same page.
    
    public RosterView(AppTemplate app, Pane workPane){
        this.app=app;
        this.workPane=workPane;
        
        DataManager data= (DataManager) app.getDataComponent();
        teams= data.getTeamsArray();
        
        rosterPane= new VBox();
        rosterTabs= new TabPane();

        teamPicker = new ComboBox(teams);
         teamPicker.valueProperty().addListener(new ChangeListener<Team>() {
            @Override 
            public void changed(ObservableValue ov, Team t, Team t1) {                
                           loadRoster(t1);
                           currentTeamIndex=teamPicker.getItems().indexOf(t1);
            }    
        });

         
        
        rosterTable = new TableView();
        initRosterTable();
        
        statsTable= new TableView();
        initStatsTable();
        

         Tab rosters= new Tab();
         rosters.setText("Roster");
         rosters.setClosable(false);
         rosters.setContent(rosterTable);
         
        Tab stats= new Tab();
        stats.setText("Stats");
        stats.setClosable(false);
        stats.setContent(statsTable);
        
         
         rosterTabs.getTabs().addAll(rosters,stats);
       rosterPane.getChildren().addAll(teamPicker,rosterTabs);
       
       initStyles();
       
    

    }
    
    @Override
    public void showPage(){
        workPane.getChildren().add(rosterPane);
        teamPicker.getSelectionModel().select(currentTeamIndex);
        loadRoster((Team) teamPicker.getItems().get(currentTeamIndex));
    }
    
    public void loadRoster(Team t){
        rosterTable.setItems(t.getPlayers());
        statsTable.setItems(t.getPlayers());
    }
   
    public void initRosterTable(){
        
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
        
        TableColumn<Player,String> playerInRating= new TableColumn("Inside");
        playerInRating.setSortable(true);
       playerInRating.setCellValueFactory((CellDataFeatures<Player, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getGrades().getInside()));
        
        TableColumn<Player,String> playerMidRating= new TableColumn("Mid");
        playerMidRating.setSortable(true);
        playerMidRating.setCellValueFactory((CellDataFeatures<Player, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getGrades().getMidRange()));
        
        TableColumn<Player,String> playerThreeRating= new TableColumn("Three");
        playerThreeRating.setSortable(true);
        playerThreeRating.setCellValueFactory((CellDataFeatures<Player, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getGrades().getThreePoint()));
        
        TableColumn<Player,String> playerHandleRating= new TableColumn("Handle");
        playerHandleRating.setSortable(true);
      playerHandleRating.setCellValueFactory((CellDataFeatures<Player, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getGrades().getHandling()));
        
        TableColumn<Player,String> playerPostRating= new TableColumn("Post D");
        playerPostRating.setSortable(true);
       playerPostRating.setCellValueFactory((CellDataFeatures<Player, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getGrades().getPostDefense()));
        
        TableColumn<Player,String> playerPerimeterRating= new TableColumn("Per D");
        playerPerimeterRating.setSortable(true);
        playerPerimeterRating.setCellValueFactory((CellDataFeatures<Player, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getGrades().getPerimeterDefense()));
        
        TableColumn<Player,String> playerReboundRating= new TableColumn("Rebounding");
        playerReboundRating.setSortable(true);
        playerReboundRating.setCellValueFactory((CellDataFeatures<Player, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getGrades().getRebounding()));

        
    rosterTable.getColumns().addAll(playerName,playerPos,playerAge,playerHeight,playerWeight,playerExp,playerInRating,playerMidRating,playerThreeRating,playerHandleRating,playerPostRating,playerPerimeterRating,playerReboundRating);
    }
    
    
    public void initStatsTable(){
         TableColumn<Player,String> playerName= new TableColumn("Name");
        playerName.setSortable(true);
        playerName.setCellValueFactory(
        new PropertyValueFactory<>("Name"));
         
        playerName.setMinWidth(120);
         
        TableColumn<Player,String> playerPos= new TableColumn("Pos");
        playerPos.setSortable(true);
        playerPos.setCellValueFactory(
            new PropertyValueFactory<>("Pos"));

         
       TableColumn<Player,Integer> gamesPlayed= new TableColumn("GP");
       gamesPlayed.setSortable(true);
       gamesPlayed.setCellValueFactory((CellDataFeatures<Player, Integer> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getGP()));
        
        TableColumn<Player,Double> minutesPlayed= new TableColumn("MP");
        minutesPlayed.setSortable(true);
        minutesPlayed.setCellValueFactory((CellDataFeatures<Player,Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getMP()));
        
        TableColumn<Player,Double> fgMade= new TableColumn("FGM");
        fgMade.setSortable(true);
        fgMade.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getFGM()));
        
        TableColumn<Player,Double> fgAttempt= new TableColumn("FGA");
        fgAttempt.setSortable(true);
        fgAttempt.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getFGA()));
        
        TableColumn<Player,Double> fgPercent= new TableColumn("FG%");
        fgPercent.setSortable(true);
        fgPercent.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getFGper()));

        TableColumn<Player,Double> threeMade= new TableColumn("3PM");
        threeMade.setSortable(true);
        threeMade.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getThreePM()));
        
        TableColumn<Player,Double> threeAttempted= new TableColumn("3PA");
        threeAttempted.setSortable(true);
        threeAttempted.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getThreePA()));
        
        TableColumn<Player,Double> threePercent= new TableColumn("3P%");
        threePercent.setSortable(true);
        threePercent.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getThreePer()));
        
        TableColumn<Player,Double> ftm= new TableColumn("FTM");
        ftm.setSortable(true);
        ftm.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getFTM()));
        
        TableColumn<Player,Double> fta= new TableColumn("FTA");
        fta.setSortable(true);
        fta.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getFTA()));
        
        TableColumn<Player,Double> ftper= new TableColumn("FT%");
        ftper.setSortable(true);
        ftper.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getFTper()));
        
        TableColumn<Player,Double> or= new TableColumn("OR");
        or.setSortable(true);
        or.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getOR()));
        
        TableColumn<Player,Double> dr= new TableColumn("DR");
        dr.setSortable(true);
        dr.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getDR()));
        
        TableColumn<Player,Double> tot= new TableColumn("REB");
        tot.setSortable(true);
        tot.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getTotReb()));

        TableColumn<Player,Double> ast= new TableColumn("AST");
        ast.setSortable(true);
        ast.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getAST()));
        
        TableColumn<Player,Double> blk= new TableColumn("BLK");
        blk.setSortable(true);
        blk.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getBLK()));
        
        TableColumn<Player,Double> stl= new TableColumn("STL");
        stl.setSortable(true);
        stl.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getSTL()));
        
        TableColumn<Player,Double> pf= new TableColumn("PF");
        pf.setSortable(true);
        pf.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getPF()));
        
        TableColumn<Player,Double> to= new TableColumn("TO");
        to.setSortable(true);
        to.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getTO()));
        
        TableColumn<Player,Double> pts= new TableColumn("PPG");
        pts.setSortable(true);
        pts.setCellValueFactory((CellDataFeatures<Player, Double> p) -> new ReadOnlyObjectWrapper(p.getValue().getStats().getPPG()));
        
    statsTable.getColumns().addAll(playerName,playerPos,gamesPlayed,minutesPlayed,fgMade,fgAttempt,fgPercent,threeMade,threeAttempted,threePercent,ftm,fta,ftper,or,dr,tot,ast,blk,stl,pf,to,pts);
    }
    public void initStyles(){
        rosterPane.prefHeightProperty().bind(workPane.heightProperty());
        rosterPane.prefWidthProperty().bind(workPane.widthProperty());
        rosterPane.getStyleClass().add("test");
        rosterPane.setSpacing(20);
        rosterPane.setAlignment(Pos.CENTER);
       
    }
    
}
