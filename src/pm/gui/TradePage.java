/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import pm.data.DataManager;
import pm.data.Helpers;
import pm.data.Player;
import pm.data.Team;
import saf.AppTemplate;
import saf.ui.AppMessageDialogSingleton;

/**
 *
 * @author sajidkamal
 */
public class TradePage extends Page {
    
    private final AppTemplate app;
    private final Pane workPane;
    VBox fullPane;
    VBox team1Pane;
    Pane team1HeaderPane;
    Pane team2HeaderPane;
    VBox team2Pane;
    VBox tradePane;
    HBox tablesPane;
    HBox payrollPane1;
    HBox capspacePane1;
    HBox payrollPane2;
    HBox capspacePane2;
    ComboBox teamPicker1;
    ComboBox teamPicker2;
    TableView team1Table;
    TableView team2Table;
    
    ListView<Player> tradeList1;
    ListView<Player> tradeList2;
    
    
    Label capspace1;
    Label payroll1;
    Label capspace2;
    Label payroll2;
    
    Label payrollLabel1;
    Label capspaceLabel1;
    Label payrollLabel2;
    Label capspaceLabel2;
    
    Label team1Receives;
    Label team2Receives;
    
    Label team1TotalSalaryLabel;
    Label team2TotalSalaryLabel;
    Label team1SalaryInLabel;
    Label team2SalaryInLabel;
    
    Text team1SalaryTotal;
    Text team2SalaryTotal;
    Text team1SalaryIn;
    Text team2SalaryIn;
    
    Button addPlayer;
    Button addPlayer2;
    NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);

    public TradePage(AppTemplate app, Pane workPane){
        this.app=app;
        this.workPane=workPane;
        DataManager dataManager= (DataManager) app.getDataComponent();
        
        //Init all the panes
        fullPane=new VBox();
        tablesPane= new HBox();
        team1Pane= new VBox();
        team2Pane= new VBox();
        tradePane= new VBox();
        team1HeaderPane= new Pane();
        team2HeaderPane= new Pane();
        payrollPane1= new HBox(); // FOR TEAM1-This is where the label will go. There will be another label next to it that will have the numerical value for the payroll.
        capspacePane1=new HBox(); //  FOR TEAM1-This is where the label will go. There will be another label next to it that will have the numerical value for the capspace.
        payrollPane2= new HBox();// FOR TEAM2-This is where the label will go. There will be another label next to it that will have the numerical value for the payroll.
        capspacePane2= new HBox();// FOR TEAM2-This is where the label will go. There will be another label next to it that will have the numerical value for the payroll.
        
        //Init all the Tables
        team1Table= new TableView();
        team2Table= new TableView();
        initTable(team1Table);
        initTable(team2Table);
        
        // init ListView
        tradeList1= new ListView<>();
        tradeList2= new ListView<>();
        initTradeTable(tradeList1);
        initTradeTable(tradeList2);
     
        

        //Init all the ComboBoxes
        teamPicker1=new ComboBox();
        teamPicker2= new ComboBox();
        teamPicker1.setItems(dataManager.getTeamsArray());
        teamPicker2.setItems(dataManager.getTeamsArray());

        //INIT BUTTONS
        addPlayer=new Button("Add Player");
        addPlayer2= new Button("Add Player");
        
        
        //INIT ALL THE LABELS
        payrollLabel1= new Label("Payroll: "); // This is the label for team1 Payroll:
        capspaceLabel1= new Label("Capspace: ");// This is the label for team1 Capspace;
        payrollLabel2= new Label("Payroll: ");// This is the label for team2 Payroll
        capspaceLabel2= new Label("Capspace: ");// This is the label for team2 Capspace;
        
        payroll1= new Label();// This is the actual numerical value for the payroll of team 1
        capspace1= new Label();// This is the actual numerical value for the capspace of team 1
        payroll2= new Label(); // This is the actual numerical value for the payroll of team 2.
        capspace2= new Label(); // tHis is the actual numerical value for the capspace of team 2.
        
        team1Receives= new Label(); // This is the label on top of the listview that says "TeamName trades"
        team2Receives= new Label();
        
        
        team1TotalSalaryLabel=new Label("Outgoing Salary: ");// This is the label for team 1 Total Outgoing salary
        team2TotalSalaryLabel= new Label("Outgoing Salary: "); // THis is the label for team2 Total Outgoing salary
        
        team1SalaryInLabel= new Label("Incoming Salary: "); // Label for team 1 Total Incoming salary
        team2SalaryInLabel= new Label("Incoming Salary: ");// Label for team2 Total Incoming Salary
        
        team1SalaryTotal= new Text("$0"); // Numerical value for the total outgoing salary of team 1
        team2SalaryTotal= new Text("$0");// Numerical value for the total outgoing salary of team 2 
        
        team1SalaryIn= new Text("$0");// Numerical value for the total incoming salary of team 1
        team2SalaryIn= new Text("$0");// Numerical value for the total incoming salary of team 2
        
        
        HBox totalSalaryPane1= new HBox(team1TotalSalaryLabel,team1SalaryTotal); // These are the panes where the outgoing salary labels go
        HBox totalSalaryPane2= new HBox(team2TotalSalaryLabel,team2SalaryTotal);
        
        HBox totalSalaryInPane1= new HBox(team1SalaryInLabel,team1SalaryIn);// These are the panes where the incoming salary labels go
        HBox totalSalaryInPane2= new HBox(team2SalaryInLabel,team2SalaryIn);
        
        payrollPane1.getChildren().addAll(payrollLabel1,payroll1); // these are the panes where the payroll/capspace labels go
        capspacePane1.getChildren().addAll(capspaceLabel1,capspace1);
        
        payrollPane2.getChildren().addAll(payrollLabel2,payroll2);
        capspacePane2.getChildren().addAll(capspaceLabel2,capspace2);
        
        
        tradePane.getChildren().addAll(team1Receives,tradeList1,totalSalaryPane1,totalSalaryInPane1,team2Receives,tradeList2,totalSalaryPane2,totalSalaryInPane2);
        
        team1Pane.getChildren().addAll(teamPicker1,team1HeaderPane,team1Table,payrollPane1,capspacePane1,addPlayer);
        team2Pane.getChildren().addAll(teamPicker2,team2HeaderPane,team2Table,payrollPane2,capspacePane2,addPlayer2);
        
        tablesPane.getChildren().addAll(team1Pane,tradePane,team2Pane);
        fullPane.getChildren().addAll(tablesPane);
        
       initHandlers();
       initStyles();
       

    }
    
     @Override
    public void showPage() {
        workPane.getChildren().add(fullPane);
       teamPicker1.getSelectionModel().select(0);
       teamPicker2.getSelectionModel().select(1);
    }
    
    private void initTable(TableView rosterTable){
        
        TableColumn<Player, String> playerName = new TableColumn("Name");
        playerName.setSortable(true);
        playerName.setCellValueFactory((TableColumn.CellDataFeatures<Player, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getName()+", "+p.getValue().getPos()));
        
        playerName.setPrefWidth(120);
        TableColumn<Player, Integer> playerAge = new TableColumn("Age");
        playerAge.setSortable(true);
        playerAge.setCellValueFactory(
                new PropertyValueFactory<>("Age"));
        
        
        
        TableColumn<Player, Integer> playerSalary = new TableColumn("Salary");
        playerSalary.setSortable(true);
        playerSalary.setCellValueFactory((TableColumn.CellDataFeatures<Player, Integer> p) -> new ReadOnlyObjectWrapper(fmt.format(p.getValue().getContract().getYear1()).replaceAll("\\.00", "")));
        
        
        
        playerName.prefWidthProperty().bind(rosterTable.widthProperty().multiply(0.5));
        playerAge.prefWidthProperty().bind(rosterTable.widthProperty().multiply(0.15));
        playerSalary.prefWidthProperty().bind(rosterTable.widthProperty().multiply(0.35));
        
        
        rosterTable.getColumns().addAll(playerName,playerAge,playerSalary);
    }
    
    
    private void initTradeTable(ListView<Player> tradeTable){
        
        tradeTable.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>() {
            @Override
            public ListCell<Player> call(ListView<Player> param) {
                return new XCell();
            }
        
        });
    }

    private void loadRoster1(Team t) {
        DataManager dataManager= (DataManager) app.getDataComponent();
        team1Table.setItems(t.getPlayers()); // Load all the players into the 
    }
    private void loadRoster2(Team t){
        DataManager dataManager= (DataManager) app.getDataComponent();
        team2Table.setItems(t.getPlayers());
        team2Receives.setText(t.toString()+" Trades");
        payroll2.setText(Helpers.convertMoney(t.getCurrentTotalSalary()));
        
        capspace2.setText(Helpers.convertMoney(dataManager.calculateTeamCapSpace(t)));
        if(capspace2.getText().contains("-")){
            capspace2.setTextFill(Color.RED);
        }
        else{
            capspace2.setTextFill(Color.GREEN);
        }
    }
    
    private void updateLabels(Team t,Label teamReceives, Label payroll, Label capspace){
        DataManager dataManager= (DataManager) app.getDataComponent();
        teamReceives.setText(t.toString()+" Trades");
      
        payroll.setText(Helpers.convertMoney(t.getCurrentTotalSalary()));
        
        capspace.setText(Helpers.convertMoney(dataManager.calculateTeamCapSpace(t)));
        if(capspace.getText().contains("-")){
            capspace.setTextFill(Color.RED);
        }
        else{
            capspace.setTextFill(Color.GREEN);
        }
    }
    
    private void initHandlers(){
        teamPicker1.valueProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue ov, Team t, Team t1) {
                loadRoster1(t1);
            }
        });
        
        teamPicker2.valueProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue ov, Team t, Team t1) {
                loadRoster2(t1); // this loads all the roster tables
            }
        });
        
        addPlayer.setOnAction(e->{
            addPlayer1Handler();
        });
        
        
        addPlayer2.setOnAction(e->{
            addPlayer2Handler();
        });
        
    }
    
    private void addPlayer1Handler(){
        Player p=(Player) team1Table.getSelectionModel().getSelectedItem();
        
        if(tradeList1.getItems().contains(p)){
            AppMessageDialogSingleton dialog= AppMessageDialogSingleton.getSingleton();
            dialog.show("Error", "Player is already in the trade!");
        }
        else{
        tradeList1.getItems().add(p);
        }
        
        disableRows();
    }
    
    private void addPlayer2Handler(){
         Player p=(Player) team2Table.getSelectionModel().getSelectedItem();
        
        if(tradeList2.getItems().contains(p)){
            AppMessageDialogSingleton dialog= AppMessageDialogSingleton.getSingleton();
            dialog.show("Error", "Player is already in the trade!");
        }
        else{
        tradeList2.getItems().add(p);
        }
        
        disableRows();
    }
    
    private void initStyles(){
        team1Table.setPrefWidth(300);
        team2Table.setPrefWidth(300);
        
        team1Receives.getStyleClass().add("prompt_label");
        team2Receives.getStyleClass().add("prompt_label");
        
        tablesPane.setSpacing(50);
        

        
        tradeList1.prefHeightProperty().bind(workPane.heightProperty().divide(3));
        tradeList2.prefHeightProperty().bind(workPane.heightProperty().divide(3));
        fullPane.setAlignment(Pos.CENTER);
        payrollLabel1.setStyle("-fx-font-weight: bold");
        payrollLabel2.setStyle("-fx-font-weight: bold");
        capspaceLabel1.setStyle("-fx-font-weight: bold");
        capspaceLabel2.setStyle("-fx-font-weight: bold");
        
 
        
    }
   

    public void disableRows(){
        
    int i = 0;
    for (Node n: team1Table.lookupAll("TableRow")) {
      if (n instanceof TableRow) {
        TableRow row = (TableRow) n;
        if (tradeList1.getItems().contains(team1Table.getItems().get(i))) {
          row.getStyleClass().add("spacing");
          row.setDisable(true);
        }
        i++;
        if (i == team1Table.getItems().size())
          break;
      }
    }
  }
static class XCell extends ListCell<Player> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button button = new Button("-");
        Player lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(e->{});
        }
        @Override
        protected void updateItem(Player item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                label.setText(item!=null ? item.getName()+"->"+String.format("%.2fM", item.getContract().getYear1()/ 1000000.0) : "<null>");
                setGraphic(hbox);
            }
        }
    }
}
    

