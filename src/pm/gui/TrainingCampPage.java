/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import pm.data.Team;
import saf.AppTemplate;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_IMAGES;

/**
 *
 * @author sajidkamal
 */
public class TrainingCampPage extends Page{
    Pane workPane;
    AppTemplate app;
    VBox fullPane;
    HBox firstRow;
    HBox secondRow;
    HBox thirdRow;
    ComboBox<Team> teamPicker;
    Label gmCashLabel;
    Text cash;
    
    private final String STOCKTON_FILEPATH= FILE_PROTOCOL+PATH_IMAGES+"Stockton.png";
    private final String STOCKTON_ATTRIBUTES="+5 Passing\n+5 Handling\n+5 Stealing";
    
    private final String ALLEN_FILEPATH= FILE_PROTOCOL+PATH_IMAGES+"ShuttlesWorth.png";
    private final String ALLEN_ATTRIBUTES="+5 3PT Shooting\n+5 Jump Shot\n+5 FT Shooting";
    
    private final String HAKEEM_FILEPATH= FILE_PROTOCOL+PATH_IMAGES+"Hakeem.png";
    private final String HAKEEM_ATTRIBUTES="+5 Inside\n+5 Blocking\n+5 D. Rebounding";
    
    private final String MUTUMBO_FILEPATH=FILE_PROTOCOL+PATH_IMAGES+"Mutumbo.png";
    private final String MUTUMBO_ATTRIBUTES="+5 Blocking\n+3 Post Defense\n+3 D.Rebounding\n+3 O.Rebounding";
    
    private final String JORDAN_FILEPATH=FILE_PROTOCOL+PATH_IMAGES+"Jordan.png";
    private final String JORDAN_ATTRIBUTES="+5 Jump Shot\n+3 3PT Shooting\n+5 Inside\n+3 O.Rebounding\n+5 Perimeter Defense";
    
    public TrainingCampPage(AppTemplate app, Pane workPane){
        this.workPane=workPane;
        this.app=app;
        
        fullPane= new VBox();
       
        firstRow= new HBox();
        secondRow= new HBox();
        thirdRow= new HBox();
        
        teamPicker= new ComboBox<>();
        gmCashLabel= new Label("GM Cash Available: ");
        cash= new Text();
                
   
         StackPane stocktonImage= initImage(STOCKTON_ATTRIBUTES,STOCKTON_FILEPATH);
         StackPane allenImage= initImage(ALLEN_ATTRIBUTES,ALLEN_FILEPATH);
         StackPane hakeemImage= initImage(HAKEEM_ATTRIBUTES,HAKEEM_FILEPATH);
         StackPane mutumboImage= initImage(MUTUMBO_ATTRIBUTES,MUTUMBO_FILEPATH);
         StackPane jordanImage= initImage(JORDAN_ATTRIBUTES,JORDAN_FILEPATH);
         
         firstRow.getChildren().addAll(stocktonImage,allenImage,hakeemImage,mutumboImage,jordanImage);
        
        firstRow.setSpacing(20);
        fullPane.getChildren().addAll(teamPicker,gmCashLabel,firstRow,secondRow,thirdRow);
        

    }
    
    private StackPane initImage(String name ,String imagePath){
        StackPane stack = new StackPane();
        Image image = new Image(imagePath);
        
        ImageView iv2 = new ImageView();
         iv2.setImage(image);
         iv2.setPreserveRatio(true);
         iv2.setSmooth(true);
         iv2.setCache(true);
         iv2.setFitHeight(150);
         iv2.setFitWidth(150);
         
         
         Text attributes= new Text(name);
         
         attributes.setVisible(false);
         attributes.setFill(Color.WHITE);
         stack.setAlignment(Pos.TOP_LEFT);
         iv2.getStyleClass().add("imageTrans");
         stack.getChildren().addAll(iv2,attributes);
         
         stack.setOnMouseEntered(e->{
         attributes.setVisible(true);
         });
        stack.setOnMouseExited(e->{attributes.setVisible(false);});

        return stack;
    }

    @Override
    public void showPage() {
        workPane.getChildren().add(fullPane);
    }
}
