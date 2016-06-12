package pm.gui;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
        
        System.out.println(workspace.getHeight());
        
        startUpWorkspace startApp= new startUpWorkspace(workspace,app);
    }
    
    public void mainMenuStartUp(){
    workspace.getChildren().clear();
    MainMenuWorkspace mainMenu = new MainMenuWorkspace(workspace,app);
    }
    public void initMainInterface(){
        workspace.getChildren().clear();
      MainInterface primaryInterface = new MainInterface(app,workspace); 
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
}
