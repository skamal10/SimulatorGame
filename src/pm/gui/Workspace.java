package pm.gui;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pm.data.DataManager;
import pm.data.Team;
import pm.file.FileManager;
import saf.ui.AppGUI;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;

/**
 * This class serves as the workspace component for this application, providing
 * the user interface controls for editing work.
 *
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class Workspace extends AppWorkspaceComponent {

    // HERE'S THE APP
    AppTemplate app;

    // IT KNOWS THE GUI IT IS PLACED INSIDE
    AppGUI gui;
    private Pane mainPane; // THIS IS THE CENTER PANE WHERE INFO WILL BE DISPLAYED
    private StartUpWorkspace startApp;
    private MainMenuWorkspace mainMenu;
    private RosterView rosterPage;
    private MainInterface primaryInterface;
    /**
     * Constructor for initializing the workspace, note that this constructor
     * will fully setup the workspace user interface for use.
     *
     * @param initApp The application this workspace is part of.
     *
     * @throws IOException Thrown should there be an error loading application
     * data for setting up the user interface.
     */
    public Workspace(AppTemplate initApp) throws IOException {
	// KEEP THIS FOR LATER
	app = initApp;
	// KEEP THE GUI FOR LATER
	gui = app.getGUI();
        workspace= new Pane();
        workspace.getStyleClass().add("max_pane");
        
        // LETS INIT ALL THE SCREENS
        startApp= new StartUpWorkspace(workspace,app); // INIT THE START MENU
        this.setScreen(startApp);
        
        mainMenu = new MainMenuWorkspace(workspace,app);
        
        primaryInterface = new MainInterface(app,workspace); 
        mainPane=primaryInterface.workPane;
        rosterPage= new RosterView(app, mainPane);

    }
    
    public void initMainInterface() throws IOException{
      DataManager data = (DataManager)app.getDataComponent();
      FileManager fileManager= (FileManager)app.getFileComponent();
 
      workspace.getChildren().clear();
      fileManager.loadTeams(data);
      fileManager.loadPlayers(data);
    }

    
    public void setScreen(Screen screen){
        workspace.getChildren().clear();
        screen.show();
    }
    
    public void setPage(Page page){
        mainPane.getChildren().clear();
        page.showPage();
    }
    
    
    
    
    
    
    /**
     * This function specifies the CSS style classes for all the UI components
     * known at the time the workspace is initially constructed. Note that the
     * tag editor controls are added and removed dynamicaly as the application
     * runs so they will have their style setup separately.
     */
    @Override
    public void initStyle() {
        
        workspace.getStyleClass().add("max_pane");
	// NOTE THAT EACH CLASS SHOULD CORRESPOND TO
	// A STYLE CLASS SPECIFIED IN THIS APPLICATION'S
	// CSS FILE
    }

    /**
     * This function reloads all the controls for editing tag attributes into
     * the workspace.
     */
    @Override
    public void reloadWorkspace() {

    }

    /**
     * @return the mainPane
     */
    public Pane getMainPane() {
        return mainPane;
    }

    /**
     * @return the startApp
     */
    public StartUpWorkspace getStartApp() {
        return startApp;
    }

    /**
     * @return the mainMenu
     */
    public MainMenuWorkspace getMainMenu() {
        return mainMenu;
    }

    /**
     * @return the rosterPage
     */
    public RosterView getRosterPage() {
        return rosterPage;
    }

    /**
     * @return the primaryInterface
     */
    public MainInterface getPrimaryInterface() {
        return primaryInterface;
    }
}
