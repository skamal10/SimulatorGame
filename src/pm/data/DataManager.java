package pm.data;

import saf.components.AppDataComponent;
import saf.AppTemplate;

/**
 * This class serves as the data management component for this application.
 *
 * @author Sajid Kamal
 * @author ?
 * @version 1.0
 */
public class DataManager implements AppDataComponent {
    // THIS IS A SHARED REFERENCE TO THE APPLICATION
    AppTemplate app;
    private String leagueName;
    private String leagueFilePath;

    /**
     * THis constructor creates the data manager and sets up the
     *
     *
     * @param initApp The application within which this data manager is serving.
     */
    public DataManager(AppTemplate initApp) throws Exception {
	// KEEP THE APP FOR LATER
	app = initApp;
    }
    
   
    
    /**
     * This function clears out the HTML tree and reloads it with the minimal
     * tags, like html, head, and body such that the user can begin editing a
     * page.
     */
    @Override
    public void reset() {

    }

    /**
     * @return the leagueName
     */
    public String getLeagueName() {
        return leagueName;
    }

    /**
     * @param leagueName the leagueName to set
     */
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    /**
     * @return the leagueFilePath
     */
    public String getLeagueFilePath() {
        return leagueFilePath;
    }

    /**
     * @param leagueFilePath the leagueFilePath to set
     */
    public void setLeagueFilePath(String leagueFilePath) {
        this.leagueFilePath = leagueFilePath;
    }
}
