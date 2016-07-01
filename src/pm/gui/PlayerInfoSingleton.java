/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import pm.data.Contract;
import pm.data.Player;
import pm.data.Team;
import saf.ui.AppMessageDialogSingleton;


/**
 *
 * @author sajidkamal
 */
public class PlayerInfoSingleton extends Stage{
    static PlayerInfoSingleton singleton = null;
    NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
    boolean editing=false;
    private final static ObservableList<String> POSITIONS=FXCollections.observableArrayList("PG","SG","SF","PF","C");
    int year=2015;
    
    Label playerName;
    HBox wholePane;
    VBox personalInfoPane;
    GridPane personalInfoGrid;
    HBox playerNamePane;
    HBox buttonPane;
    TextField[] textFieldArray; // this is used to easily enable and disable textfields
    
    
    TextField firstName;
    TextField lastName;
    TextField collegeField;
    TextField ageField;
    TextField hometownField;
    TextField heightField;
    TextField weightField;
    ComboBox posField;
    TextField expField;

    Scene playerScene;
    Button okButton;
    Button cancelButton;
    Button editButton;
    
    TableView contractTable;
    
    private PlayerInfoSingleton() {}
    
    public static PlayerInfoSingleton getSingleton() {
	if (singleton == null)
	    singleton = new PlayerInfoSingleton();
	return singleton;
    }
    
    
    public void init(Stage owner) {
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        
        
        personalInfoGrid= new GridPane();
       
        // This is the label which says the players name. The content of this label is determined in the show() method
        playerName=new Label();
        playerNamePane = new HBox(playerName);
  

        // Label+TextField for First Name Info
        Label firstNameLabel= new Label("First Name");
        firstName= new TextField();
        firstName.setPromptText("First Name");
        personalInfoGrid.add(firstNameLabel,0,1);
        personalInfoGrid.add(firstName,1,1);
        

        
        //Label+TextField for Last Name Info
        Label lastNameLabel= new Label("Last Name");
        lastName= new TextField();
        lastName.setPromptText("Last Name");
        personalInfoGrid.add(lastNameLabel,0,2);
        personalInfoGrid.add(lastName,1,2);
        
        
        //Label+TextField for College Info
        Label collegeLabel= new Label("College");
        collegeField= new TextField();
        personalInfoGrid.add(collegeLabel,0,3);
        personalInfoGrid.add(collegeField,1,3);
        

        //Label+TextField for Home Town Info
        Label hometownLabel= new Label("Home Town");
        hometownField= new TextField();
        personalInfoGrid.add(hometownLabel,0,4);
        personalInfoGrid.add(hometownField,1,4);
 
        
        //Label+TextField for Age Info
        Label ageLabel = new Label("Age");
        ageField= new TextField();
        personalInfoGrid.add(ageLabel,0,5);
        personalInfoGrid.add(ageField,1,5);

        
        //Label+TextField for Height Info
        Label heightLabel= new Label("Height");
        heightField= new TextField();
        personalInfoGrid.add(heightLabel,0,6);
        personalInfoGrid.add(heightField,1,6);

        //Label+TextField for Weight Info
        Label weightLabel= new Label("Weight");
        weightField= new TextField();
        personalInfoGrid.add(weightLabel,0,7);
        personalInfoGrid.add(weightField,1,7);

        ////Label+TextField for Experience Info
       Label expLabel= new Label("Experience");
       expField= new TextField();
       personalInfoGrid.add(expLabel,0,8);
       personalInfoGrid.add(expField,1,8);

        
       //Label+TextField for Position Info
       Label posLabel= new Label("Pos");
       posField= new ComboBox(POSITIONS);
       personalInfoGrid.add(posLabel,0,9);
       personalInfoGrid.add(posField,1,9);

       // THESE ARE THE BUTTONS WHICH WILL HAVE TO STAY AT THE BOTTOM OF THE PAGE
       okButton= new Button("OK");
       cancelButton= new Button("Cancel");
       editButton= new Button("Edit");
       buttonPane= new HBox(editButton,okButton,cancelButton);
       personalInfoGrid.add(buttonPane,0,10);

       
        // ADD ALL THE TEXT FIELDS INSIDE AN ARRAY
       textFieldArray= new TextField[]{firstName,lastName,collegeField,hometownField,ageField,heightField,weightField,expField};
       
       personalInfoPane=new VBox(playerNamePane,personalInfoGrid,buttonPane);
       
       initContractTable();
       
       
       wholePane= new HBox(personalInfoPane,contractTable);

       playerScene = new Scene(wholePane);
        
        this.setScene(playerScene);
        
        initStyles();
    }
    public void initContractTable(){
       contractTable = new TableView();
        
      
        
        TableColumn yearColumn= new TableColumn("Year");
        yearColumn.setSortable(false);
        yearColumn.setCellFactory( new Callback<TableColumn, TableCell>()
{
    @Override
    public TableCell call(TableColumn p )
    {
        return new TableCell()
        {
            @Override
            public void updateItem( Object item, boolean empty )
            {
                super.updateItem( item, empty );
                setGraphic( null );
                setText( empty ? null : year+getIndex()+ "" );
                
            }
        };
    }
});
        
        
        TableColumn<Integer,String> contractColumn= new TableColumn("Contract");
        contractColumn.setCellValueFactory((TableColumn.CellDataFeatures<Integer, String> p) -> new ReadOnlyObjectWrapper(fmt.format(p.getValue()).replaceAll("\\.00", "")));
        
        yearColumn.setSortable(false);
        
       
        contractTable.getColumns().addAll(yearColumn,contractColumn);
        
        
        
        
    }
    
    
    public void show(Player p,TableView rosterTable){
        
        editing=false;                  // THESE ARE FOR EDITING THE PLAYERS
        updateTextFields(editing);
        
        setTitle(p.toString());
        
    
        //NOW LETS POPULATE THE TEXTFIELDS WITH PLAYER INFO
        playerName.setText(p.toString());
        firstName.setText(p.getFirstName());
        lastName.setText(p.getLname());
        collegeField.setText(p.getCollege());
        hometownField.setText(p.getCity());
        ageField.setText(String.valueOf(p.getAge()));
        heightField.setText(String.valueOf(p.getHeight()));
        weightField.setText(String.valueOf(p.getWeight()));
        posField.getSelectionModel().select(p.getPos());
        expField.setText(String.valueOf(p.getExp()));
        
        
        // THIS IS THE EVENT HANDLER FOR THE COMBOBOX TO CHOOSE THE POSITION
        posField.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                           p.setPos(t1);
            }    
        });
        
        ObservableList<Integer>test= p.getContract().getContractArray();
        contractTable.setItems(test);
        
        
        
       okButton.setOnAction(e->{
       if(editing){
       p.setFirstName(firstName.getText());
       p.setLname(lastName.getText());
       p.setCollege(collegeField.getText());
       p.setCity(hometownField.getText());
       p.setAge(Integer.parseInt(ageField.getText()));
       p.setHeight(Integer.parseInt(heightField.getText()));
       p.setWeight(Integer.parseInt(weightField.getText()));
       p.setExp(Integer.parseInt(expField.getText()));
       rosterTable.refresh();
       }
       this.close();
       });
       
       cancelButton.setOnAction(e->{
       this.close();
       });
       
       
       showAndWait();
       
       
    }
    
    public void initStyles(){
         playerNamePane.setAlignment(Pos.CENTER);
        buttonPane.setAlignment(Pos.CENTER);
        
    }
    public void updateTextFields(boolean disable){
        for(int i=0;i<textFieldArray.length;i++){
            textFieldArray[i].setDisable(!disable);
        }
        posField.setDisable(!disable);
        
    }
}
