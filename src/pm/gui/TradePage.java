/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
    Button proposeTradeButton;
    Button testButton;

    NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
    int currentIndex1 = 0;
    int currentIndex2 = 1;

    SimpleIntegerProperty totalSalaryTeam1;
    SimpleIntegerProperty totalSalaryTeam2;

    public TradePage(AppTemplate app, Pane workPane) {
        this.app = app;
        this.workPane = workPane;
        DataManager dataManager = (DataManager) app.getDataComponent();

        totalSalaryTeam1 = new SimpleIntegerProperty(0);
        totalSalaryTeam2 = new SimpleIntegerProperty(0);

        //Init all the panes
        fullPane = new VBox(); // This is the entire pane. 
        tablesPane = new HBox(); // This is the HBox where all the Tables and ListView Nodes will go
        team1Pane = new VBox(); // This is where the Table and labels for team 1 will go
        team2Pane = new VBox(); // This is where the Table and lables for team 2 will go
        tradePane = new VBox(); // This is where both the ListViews will be displayed. 
        team1HeaderPane = new Pane(); // This is still in progress, but the plan is to have a small banner that says the teams name above the table.
        team2HeaderPane = new Pane();
        payrollPane1 = new HBox(); // FOR TEAM1-This is where the label will go. There will be another label next to it that will have the numerical value for the payroll.
        capspacePane1 = new HBox(); //  FOR TEAM1-This is where the label will go. There will be another label next to it that will have the numerical value for the capspace.
        payrollPane2 = new HBox();// FOR TEAM2-This is where the label will go. There will be another label next to it that will have the numerical value for the payroll.
        capspacePane2 = new HBox();// FOR TEAM2-This is where the label will go. There will be another label next to it that will have the numerical value for the payroll.

        //Init all the Tables
        team1Table = new TableView();
        team2Table = new TableView();
        initTable(team1Table);
        initTable(team2Table);

        // init ListView
        tradeList1 = new ListView<>();
        tradeList2 = new ListView<>();
        initTradeTable(tradeList1);
        initTradeTable(tradeList2);

        //Init all the ComboBoxes
        teamPicker1 = new ComboBox();
        teamPicker2 = new ComboBox();
        teamPicker1.setItems(dataManager.getTeamsArray());
        teamPicker2.setItems(dataManager.getTeamsArray());

        //INIT BUTTONS
        addPlayer = new Button("Add Player");
        addPlayer2 = new Button("Add Player");
        proposeTradeButton = new Button("Propose Trade");
        testButton = new Button("Test");

        //INIT ALL THE LABELS
        payrollLabel1 = new Label("Payroll: "); // This is the label for team1 Payroll:
        capspaceLabel1 = new Label("Capspace: ");// This is the label for team1 Capspace;
        payrollLabel2 = new Label("Payroll: ");// This is the label for team2 Payroll
        capspaceLabel2 = new Label("Capspace: ");// This is the label for team2 Capspace;

        payroll1 = new Label();// This is the actual numerical value for the payroll of team 1
        capspace1 = new Label();// This is the actual numerical value for the capspace of team 1
        payroll2 = new Label(); // This is the actual numerical value for the payroll of team 2.
        capspace2 = new Label(); // tHis is the actual numerical value for the capspace of team 2.

        team1Receives = new Label(); // This is the label on top of the listview that says "TeamName trades"
        team2Receives = new Label();

        team1TotalSalaryLabel = new Label("Outgoing Salary: ");// This is the label for team 1 Total Outgoing salary
        team2TotalSalaryLabel = new Label("Outgoing Salary: "); // THis is the label for team2 Total Outgoing salary

        team1SalaryInLabel = new Label("Incoming Salary: "); // Label for team 1 Total Incoming salary
        team2SalaryInLabel = new Label("Incoming Salary: ");// Label for team2 Total Incoming Salary

        team1SalaryTotal = new Text("$0"); // Numerical value for the total outgoing salary of team 1
        team2SalaryTotal = new Text("$0");// Numerical value for the total outgoing salary of team 2 

        team1SalaryIn = new Text("$0");// Numerical value for the total incoming salary of team 1
        team2SalaryIn = new Text("$0");// Numerical value for the total incoming salary of team 2

        initLabelHandling();

        HBox totalSalaryPane1 = new HBox(team1TotalSalaryLabel, team1SalaryTotal); // These are the panes where the outgoing salary labels go
        HBox totalSalaryPane2 = new HBox(team2TotalSalaryLabel, team2SalaryTotal);

        HBox totalSalaryInPane1 = new HBox(team1SalaryInLabel, team1SalaryIn);// These are the panes where the incoming salary labels go
        HBox totalSalaryInPane2 = new HBox(team2SalaryInLabel, team2SalaryIn);

        payrollPane1.getChildren().addAll(payrollLabel1, payroll1); // these are the panes where the payroll/capspace labels go
        capspacePane1.getChildren().addAll(capspaceLabel1, capspace1);

        payrollPane2.getChildren().addAll(payrollLabel2, payroll2);
        capspacePane2.getChildren().addAll(capspaceLabel2, capspace2);

        tradePane.getChildren().addAll(team1Receives, tradeList1, totalSalaryPane1, totalSalaryInPane1, team2Receives, tradeList2, totalSalaryPane2, totalSalaryInPane2, proposeTradeButton);

        team1Pane.getChildren().addAll(teamPicker1, team1HeaderPane, team1Table, payrollPane1, capspacePane1, addPlayer,testButton);
        team2Pane.getChildren().addAll(teamPicker2, team2HeaderPane, team2Table, payrollPane2, capspacePane2, addPlayer2);

        tablesPane.getChildren().addAll(team1Pane, tradePane, team2Pane);
        fullPane.getChildren().addAll(tablesPane);

        initHandlers();
        initStyles();

    }

    @Override
    public void showPage() {
        workPane.getChildren().add(fullPane);
        teamPicker1.getSelectionModel().select(currentIndex1);
        teamPicker2.getSelectionModel().select(currentIndex2);
    }

    private void initTable(TableView rosterTable) {

        TableColumn<Player, String> playerName = new TableColumn("Name");
        playerName.setSortable(true);
        playerName.setCellValueFactory((TableColumn.CellDataFeatures<Player, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getName() + ", " + p.getValue().getPos()));

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

        rosterTable.getColumns().addAll(playerName, playerAge, playerSalary);
    }

    private void initTradeTable(ListView<Player> tradeTable) {
        tradeTable.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>() {
            @Override
            public ListCell<Player> call(ListView<Player> param) {
                return new XCell();
            }
        });
    }

    private void loadRoster(Team t, TableView teamTable) {
        DataManager dataManager = (DataManager) app.getDataComponent();
        teamTable.setItems(t.getPlayers()); // Load all the players into the 
    }

    private void updateLabels(Team t, Label teamReceives, Label payroll, Label capspace) {
        DataManager dataManager = (DataManager) app.getDataComponent();
        teamReceives.setText(t.toString() + " Trades");

        payroll.setText(Helpers.convertMoney(t.getCurrentTotalSalary()));

        capspace.setText(Helpers.convertMoney(dataManager.calculateTeamCapSpace(t)));
        if (capspace.getText().contains("-")) {
            capspace.setTextFill(Color.RED);
        } else {
            capspace.setTextFill(Color.GREEN);
        }
    }

    private void initHandlers() {
        teamPicker1.valueProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue ov, Team t, Team t1) {
                loadRoster(t1, team1Table);
                updateLabels(t1, team1Receives, payroll1, capspace1);
                tradeList1.getItems().clear();
                currentIndex1 = teamPicker1.getSelectionModel().getSelectedIndex();
                disableRows();
            }
        });

        teamPicker2.valueProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue ov, Team t, Team t1) {
                loadRoster(t1, team2Table); // this loads all the roster tables
                updateLabels(t1, team2Receives, payroll2, capspace2);
                tradeList2.getItems().clear();
                currentIndex2 = teamPicker2.getSelectionModel().getSelectedIndex();
            }
        });

        addPlayer.disableProperty().bind(Bindings.isEmpty(team1Table.getSelectionModel().getSelectedItems()));
        addPlayer2.disableProperty().bind(Bindings.isEmpty(team2Table.getSelectionModel().getSelectedItems()));

        addPlayer.setOnAction(e -> {
            addPlayer1Handler();
        });

        addPlayer2.setOnAction(e -> {
            addPlayer2Handler();
        });

        proposeTradeButton.setOnAction(e -> {
            completeTrade();
        });

        testButton.setOnAction(e -> {
             DataManager dataManager = (DataManager) app.getDataComponent();
             
            Team t1= (Team) teamPicker1.getSelectionModel().getSelectedItem();
            Team t2= (Team) teamPicker2.getSelectionModel().getSelectedItem();
            
            System.out.println("Team 1 Payroll: "+Helpers.convertMoney(t1.getCurrentTotalSalary()));
            System.out.println("Team 1 Cap space:"+Helpers.convertMoney(dataManager.calculateTeamCapSpace(t1)));
            
             System.out.println("Team 1 Payroll: "+Helpers.convertMoney(t2.getCurrentTotalSalary()));
            System.out.println("Team 1 Cap space:"+Helpers.convertMoney(dataManager.calculateTeamCapSpace(t2)));
            
        });

    }

    private void completeTrade() {
        ObservableList<Player> team1Players = tradeList1.getItems();
        ObservableList<Player> team2Players = tradeList2.getItems();
        Team t1 = (Team) teamPicker1.getSelectionModel().getSelectedItem();
        Team t2 = (Team) teamPicker2.getSelectionModel().getSelectedItem();
        String tradeResult = checkTrade(t1, t2, team1Players, team2Players);

        if (tradeResult.equals("works")) {

            

            for (Player p : team1Players) {
                t1.getPlayers().remove(p);
                t2.getPlayers().add(p);
            }

            for (Player p : team2Players) {
                t2.getPlayers().remove(p);
                t1.getPlayers().add(p);
            }
            /*t1.getPlayers().removeAll(team1Players);
        t2.getPlayers().removeAll(team2Players);
        t2.getPlayers().addAll(team1Players);
        t1.getPlayers().addAll(team2Players);*/
            team1Table.refresh();
            team2Table.refresh();

            tradeList1.getItems().clear();
            tradeList2.getItems().clear();
            clearTrade();
            disableRows();
            updateLabels(t1, team1Receives, payroll1, capspace1);
            updateLabels(t2, team2Receives, payroll2, capspace2);
        } else {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Trade Failed", tradeResult);
        }

    }

    private void addPlayer1Handler() {
        Player p = (Player) team1Table.getSelectionModel().getSelectedItem();
        tradeList1.getItems().add(p);
        totalSalaryTeam1.set(totalSalaryTeam1.get() + p.getContract().getYear1());
        team1Table.getSelectionModel().clearSelection();
        disableRows();
    }

    private void addPlayer2Handler() {
        Player p = (Player) team2Table.getSelectionModel().getSelectedItem();
        tradeList2.getItems().add(p);
        totalSalaryTeam2.set(totalSalaryTeam2.get() + p.getContract().getYear1());
        team1Table.getSelectionModel().clearSelection();
        disableRows();
    }

    private void initStyles() {
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

    public void disableRows() {
        int i = 0;
        for (Node n : team1Table.lookupAll("TableRow")) {
            if (n instanceof TableRow) {
                TableRow row = (TableRow) n;
                if (tradeList1.getItems().contains(team1Table.getItems().get(i))) {
                    if (!row.getStyleClass().contains("spacing")) {
                        row.getStyleClass().add("spacing");
                    }
                    row.setDisable(true);
                } else {
                    row.setDisable(false);
                    row.getStyleClass().remove("spacing");
                }
                i++;
                if (i == team1Table.getItems().size()) {
                    break;
                }
            }
        }
    }

    private void initLabelHandling() {
        totalSalaryTeam1.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal,
                    Object newVal) {
                team1SalaryTotal.setText(Helpers.convertMoney(totalSalaryTeam1.get()));
                team2SalaryIn.setText(Helpers.convertMoney(totalSalaryTeam1.get()));

            }
        });
        totalSalaryTeam2.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal,
                    Object newVal) {
                team2SalaryTotal.setText(Helpers.convertMoney(totalSalaryTeam2.get()));
                team1SalaryIn.setText(Helpers.convertMoney(totalSalaryTeam2.get()));
            }
        });

    }

    private String checkTrade(Team t1, Team t2, ObservableList<Player> team1Players, ObservableList team2Players) {
        DataManager data = (DataManager) app.getDataComponent();
        String trade = "works";
        int team1Cap = data.calculateTeamCapSpace(t1);
        int team2Cap = data.calculateTeamCapSpace(t2);

        int team1Salary = totalSalaryTeam1.get(); // team 1 outgoing
        int team2Salary = totalSalaryTeam2.get(); // team 1 incoming

        // FIRST LETS LOOK AT TEAM 1
        if (team1Salary <= 9800000) {
            double max = (team1Salary * 1.5) + 100000;

            if (team2Salary <= max) {
                trade = "works";
            } else {

                trade = t1.toString() + " outgoing salary is " + Helpers.convertMoney(team1Salary) + " and the incoming salary is " + Helpers.convertMoney(team2Salary)
                        + ".\nIn order to make this trade work, since the " + t1.toString() + " have an outgoing salary of under"
                        + "$9,800,000, the incoming salary must be 150% of the outgoing salary plus $100,000. \n"
                        + "In order to make this trade work, please cut atleast " + Helpers.convertMoney((int) (team2Salary - max)) + " from the incoming salary.";
            }

        } else if (team1Salary > 9800000 & team1Salary <= 19600000) {

            double max = team1Salary + 5000000;
            if (team2Salary <= max) {
                trade = "works";
            } else {
                trade = t1.toString() + " outgoing salary is " + Helpers.convertMoney(team1Salary) + " and the incoming salary is " + Helpers.convertMoney(team2Salary)
                        + ".\nIn order to make this trade work, since the " + t1.toString() + " have an outgoing salary of between"
                        + "\n$9,800,000 and 19,600,000, the incoming salary must be equal to the outgoing salary plus $5,000,000.\n"
                        + "In order to make this trade work, please cut atleast " + Helpers.convertMoney((int) (team2Salary - max)) + " from the incoming salary.";
            }

        } else if (team1Salary > 19600000) {
            double max = (team1Salary * 1.25) + 100000;
            if (team2Salary <= (team1Salary * 1.25) + 100000) {
                trade = "works";
            } else {
                trade = t1.toString() + " outgoing salary is " + Helpers.convertMoney(team1Salary) + " and the incoming salary is " + Helpers.convertMoney(team2Salary)
                        + ".\nIn order to make this trade work, since the " + t1.toString() + " have an outgoing salary of greater than 19,600,000, "
                        + "the incoming salary must be 125% of the outgoing salary plus $100,000. \n"
                        + "In order to make this trade work, please cut atleast " + Helpers.convertMoney((int) (team2Salary - max)) + " from the incoming salary.";
            }
        }

        if (!trade.equals("works")) {
            String x = trade;
            trade = checkCapTeam1(t1, team1Cap, team1Salary, team2Salary);
            if (trade == null) {
                trade = x;
            }
            if (!trade.equals("works")) {
                return trade;
            }

        }

        // NOW LETS LOOK AT TEAM 2
        if (team2Salary <= 9800000) {

            double max = (team2Salary * 1.5) + 100000;
            if (team1Salary <= max) {
                trade = "works";
            } else {
                trade = t2.toString() + " outgoing salary is " + Helpers.convertMoney(team2Salary) + " and the incoming salary is " + Helpers.convertMoney(team1Salary)
                        + ".\n In order to make this trade work, since the " + t2.toString() + " have an outgoing salary of under "
                        + "$9,800,000, \n the incoming salary must be 150% of the outgoing salary plus $100,000. \n"
                        + "In order to make this trade work, please cut atleast " + Helpers.convertMoney((int) (team1Salary - max)) + " from the incoming salary.";
            }

        } else if (team2Salary > 9800000 & team2Salary <= 19600000) {
            double max = team2Salary + 5000000;
            if (team1Salary <= max) {
                trade = "works";
            } else {
                trade = t2.toString() + "outgoing salary is " + team2Salary + " and the incoming salary is " + team1Salary
                        + " . In order to make this trade work, since the " + t2.toString() + " have an outgoing salary of between"
                        + "$9,800,000 and 19,600,000, the incoming salary must be equal to the outgoing salary plus $5,000,000. \n"
                        + "In order to make this trade work, please cut atleast " + (team1Salary - max) + " from the incoming salary.";
            }

        } else if (team2Salary > 19600000) {
            double max = (team2Salary * 1.25) + 100000;
            if (team1Salary <= max) {
                trade = "works";
            } else {
                trade = t2.toString() + " outgoing salary is " + team2Salary + " and the incoming salary is " + team1Salary
                        + " . In order to make this trade work, since the " + t2.toString() + " have an outgoing salary of greater than 19,600,000, "
                        + "the incoming salary must be 125% of the outgoing salary plus $100,000. \n"
                        + "In order to make this trade work, please cut atleast " + Helpers.convertMoney((int) (team1Salary - max)) + " from the incoming salary.";
            }
        }

        if (!trade.equals("works")) {
            String x = trade;
            trade = checkCapTeam2(t2, team2Cap, team1Salary, team2Salary);
            if (trade == null) {
                trade = x;
            }

        }

        if(t1.getRosterSize()+team2Players.size()>15){
            trade= t1.toString()+" have too many players as a result of this trade.";
        }
        else if(t2.getRosterSize()+team1Players.size()>15){
            trade=t2.toString()+" have too many players as a result of this trade.";
        }

        return trade;

    }

    private String checkCapTeam1(Team t1, int team1Cap, int team1Salary, int team2Salary) {

        String trade = null;

        if (team1Cap > 0) {
            if (team1Cap - (team1Salary - team2Salary) > -100000) {
                trade = "works";
            } else {
                trade = t1.toString() + " were under the cap before this trade. As a result of this trade, " + t1.toString() + " will have a capspace of " + Helpers.convertMoney(team1Cap - (team2Salary - team1Salary)) + ".\nIn order for this trade to work, the " + t1.toString() + " cannot go more than $100,000 "
                        + "over the cap as a result of this trade.";
            }
        }
        return trade;
    }

    private String checkCapTeam2(Team t2, int team2Cap, int team1Salary, int team2Salary) {

        String trade = null;
        if (team2Cap > 0) {

            if (team2Cap - (team1Salary - team2Salary) > -100000) {
                trade = "works";
            } else {
                trade = t2.toString() + " were under the cap before this trade. As a result of this trade, " + t2.toString() + " will have a capspace of " + Helpers.convertMoney(team2Cap - (team1Salary - team2Salary)) + ".\n In order for this trade to work, the " + t2.toString() + " cannot go more than $100,000 "
                        + "over the cap as a result of this trade.";
            }
        }
        return trade;
    }

    private void clearTrade() {
        tradeList1.getItems().clear();
        tradeList2.getItems().clear();
        totalSalaryTeam1.set(0);
        totalSalaryTeam2.set(0);
    }

    private void calculateSalaries() {
        int salaryTeam1 = 0;
        int salaryTeam2 = 0;
        for (Player p : tradeList1.getItems()) {
            salaryTeam1 += p.getContract().getYear1();
        }
        for (Player p : tradeList2.getItems()) {
            salaryTeam2 += p.getContract().getYear1();
        }

        totalSalaryTeam1.set(salaryTeam1);
        totalSalaryTeam2.set(salaryTeam2);

    }

    class XCell extends ListCell<Player> {

        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button button = new Button("-");
        Player lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(e -> {
                super.getListView().getItems().remove(super.getItem());
                calculateSalaries();
                disableRows();
            });
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
                label.setText(item != null ? item.getName() + "->" + String.format("%.2fM", item.getContract().getYear1() / 1000000.0) : "<null>");
                setGraphic(hbox);
            }
        }
    }
}
