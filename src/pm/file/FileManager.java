package pm.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import pm.data.DataManager;
import pm.data.Player;
import pm.data.PlayerBuilder;
import pm.data.Team;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;
import saf.ui.AppMessageDialogSingleton;

/**
 * This class serves as the file management component for this application,
 * providing all I/O services.
 *
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class FileManager implements AppFileComponent {
    
    private String leagueFilePath;

    /**
     * This method is for saving user work, which in the case of this
     * application means the data that constitutes the page DOM.
     * 
     * @param data The data management component for this application.
     * 
     * @param filePath Path (including file name/extension) to where
     * to save the data to.
     * 
     * @throws IOException Thrown should there be an error writing 
     * out data to the file.
     */
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {

    }
      
    /**
     * This method loads data from a JSON formatted file into the data 
     * management component and then forces the updating of the workspace
     * such that the user may edit the data.
     * 
     * @param data Data management component where we'll load the file into.
     * 
     * @param filePath Path (including file name/extension) to where
     * to load the data from.
     * 
     * @throws IOException Thrown should there be an error reading
     * in data from the file.
     */
    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        
    }

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    /**
     * This method exports the contents of the data manager to a 
     * Web page including the html page, needed directories, and
     * the CSS file.
     * 
     * @param data The data management component.
     * 
     * @param filePath Path (including file name/extension) to where
     * to export the page to.
     * 
     * @throws IOException Thrown should there be an error writing
     * out data to the file.
     */
    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {

    }
    
    /**
     * This method is provided to satisfy the compiler, but it
     * is not used by this application.
     */
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
	// NOTE THAT THE Web Page Maker APPLICATION MAKES
	// NO USE OF THIS METHOD SINCE IT NEVER IMPORTS
	// EXPORTED WEB PAGES
    }
    
    public void loadTeams(AppDataComponent data) throws IOException{
        DataManager dataManager= (DataManager) data;
        HashMap<String,Team> teams= dataManager.getTeamsArray();
        
        JsonObject obj= loadJSONFile("./data/teamProperties.json");
        JsonArray dataArray = obj.getJsonArray("Teams");
        
        for(int i=0; i<dataArray.size();i++){
            JsonObject object = dataArray.getJsonObject(i);
            String city=object.getString("CityName");
            String team= object.getString("TeamName");
            String abbrev= object.getString("Abbrevation");
            String conference= object.getString("Conference");
            
            Team newTeam = new Team(city,team,abbrev,conference);
            teams.put(abbrev, newTeam);
        }
    }

    public void loadPlayers(AppDataComponent data) throws IOException{
        DataManager dataManager= (DataManager) data;
        HashMap<String,Team> teams= dataManager.getTeamsArray();
        JsonObject obj= loadJSONFile(leagueFilePath);
        JsonArray dataArray = obj.getJsonArray("Players");
        
        
        for(int i=0; i<dataArray.size();i++){
            JsonObject object = dataArray.getJsonObject(i);
            String first=object.getString("FirstName");
            String last= object.getString("LastName");
            int height= object.getInt("Height");
            int weight= object.getInt("Weight");
            String position= object.getString("Position");
            String birthday= object.getString("DOB");
            int uniform= object.getInt("Uniform");
            String city= object.getString("City");
            String state= object.getString("State");
            String college= object.getString("College");
            int exp= object.getInt("Exp");
            int injury= object.getInt("Injury");
            int timeInj= object.getInt("TimeInjured");
            int inside= object.getInt("InsideScoring");
            int jumper= object.getInt("JumpShot");
            int ft= object.getInt("FtShot");
            int threept= object.getInt("3pShot");
            int threeUsage= object.getInt("3pUsage");
            int handling= object.getInt("Handling");
            int passing= object.getInt("Passing");
            int postDef= object.getInt("PostDefense");
            int perDef= object.getInt("PerimeterDefense");
            int stealing= object.getInt("Stealing");
            int blocking= object.getInt("Blocking");
            int OReb= object.getInt("OReb");
            int DReb= object.getInt("DReb");
            int fouling= object.getInt("Fouling");
            int strength= object.getInt("Strength");
            int quickness= object.getInt("Quickness");
            int jumping= object.getInt("Jumping");
            int stamina= object.getInt("Stamina");
            String team= object.getString("Team");
            
            
            

            Player p = new PlayerBuilder()
                    .setFirstName(first)
                    .setLastName(last)
                    .setHeight(height)
                    .setWeight(weight)
                    .setPos(position)
                    .setDOB(birthday)
                    .setCity(city)
                    .setState(state)
                    .setCollege(college)
                    .setExp(exp)
                    .setInjury(injury)
                    .setTimeInjured(timeInj)
                    .setInsideScoring(inside)
                    .setJumpShot(jumper)
                    .setFtShot(ft)
                    .setThreeShot(threept)
                    .setThreeUsage(threeUsage)
                    .setHandling(handling)
                    .setPassing(passing)
                    .setPostDefense(postDef)
                    .setPerimeterDefense(perDef)
                    .setStealing(stealing)
                    .setBlocking(blocking)
                    .setoReb(OReb)
                    .setdReb(DReb)
                    .setFouling(fouling)
                    .setStrength(strength)
                    .setQuickness(quickness)
                    .setJumping(jumping)
                    .setStamina(stamina)
                    .buildPlayer();
            
            if(team.equals("FA")){
                dataManager.addFreeAgent(p);
            }
            else{
                
                try{
            Team currTeam=(Team) dataManager.getTeamsArray().get(team);
            currTeam.addPlayers(p);}
                catch(NullPointerException e){
                    System.out.println(team);
                }
           
        }
            
        }
        
        for (Object key : dataManager.getTeamsArray().keySet() ) {
            Team ok= (Team) dataManager.getTeamsArray().get(key);
            System.out.println("Team Name"+ok.toString());
            System.out.println("Roster Size"+ok.getRosterSize());
            for(Player p: ok.getPlayers()){
                System.out.println(p.toString());
            }
            
            System.out.println("\n");
   
         }
        
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
