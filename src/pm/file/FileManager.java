package pm.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import pm.data.Contract;
import pm.data.DataManager;
import pm.data.DraftPick;
import pm.data.LetterGrade;
import pm.data.Player;
import pm.data.PlayerBuilder;
import pm.data.Team;
import pm.data.Transaction;
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
        
        
        DataManager dataManager= (DataManager)data;
        
        
        JsonArray teamInfo= buildTeams(dataManager);
        JsonArray playerInfo= buildPlayers(dataManager);
        JsonArray transactions= buildTransactions(dataManager);
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        
         JsonObject JSO1 = Json.createObjectBuilder()
                 .add("Team Info", teamInfo)
                 .add("Player Info", playerInfo)
                 .add("Transactions",transactions)
                 .build();
         
          arrayBuilder.add(JSO1);
        
        JsonArray jA = arrayBuilder.build();

        StringWriter sw = new StringWriter();
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add("LeagueFile", jA)
                .build();

        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        JsonWriter jsonWriter = writerFactory.createWriter(sw);

        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();

        // INIT THE WRITER
        OutputStream os = new FileOutputStream(filePath);
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(filePath);
        pw.write(prettyPrinted);
        pw.close();
 
    }
    private JsonArray buildTeams(DataManager dataManager){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Team t:dataManager.getTeamsArray()){
            JsonObject JSO1 = Json.createObjectBuilder()
                    .add("Team City",t.getCityName())
                    .add("Team Name",t.getTeamName())
                    .add("Abbrevation", t.getAbbrevation())
                    .add("GM Cash", t.getGMCash())
                    .add("Wins", t.getWinCount())
                    .add("Losses", t.getLoseCount())
                    .add("Conference", t.getConference())
                    .add("Cap Holds", t.getCapHold())
                    .add("Draft Picks", buildDraftPicks(t))
                    .build();
            arrayBuilder.add(JSO1);
        }
        JsonArray jA = arrayBuilder.build();
        return jA;
    }
    
    private JsonArray buildDraftPicks(Team t){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(DraftPick d:t.getDraftPicks()){
            JsonObject JSO1 = Json.createObjectBuilder()
                    .add("Team", d.getTeam().getAbbrevation())
                    .add("Year", d.getYear())
                    .add("Round", d.getRound())
                    .build();
            arrayBuilder.add(JSO1);
    }
        JsonArray jA = arrayBuilder.build();
        return jA;
    }
    
    private JsonArray buildPlayers(DataManager dataManager){
          JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        ObservableList<Player> players= dataManager.getPlayersArray();
        for(Player p: players){
             JsonObject JSO1 = Json.createObjectBuilder()
                     .add("FirstName", p.getFirstName())
                     .add("LastName", p.getLname())
                     .add("Height", p.getHeight())
                     .add("Position", p.getPos())
                     .add("DOB", p.getDOB())
                     .add("City", p.getCity())
                     .add("State", p.getState())
                     .add("College", p.getCollege())
                     .add("Exp", p.getExp())
                     .add("Injury", p.getInjury())
                     .add("InsideScoring", p.getInsideScoring())
                     .add("JumpShot", p.getJumpShot())
                     .add("FtShot", p.getFtShot())
                     .add("3pShot",p.getThreeShot())
                     .add("3pUsage", p.getThreeUsage())
                     .add("Handling", p.getHandling())
                     .add("Passing", p.getPassing())
                     .add("PostDefense", p.getPostDefense())
                     .add("PerimeterDefense", p.getPerimeterDefense())
                     .add("Stealing", p.getStealing())
                     .add("Blocking", p.getBlocking())
                     .add("OReb", p.getoReb())
                     .add("DReb", p.getdReb())
                     .add("Fouling", p.getFouling())
                     .add("Strength", p.getStrength())
                     .add("Quickness", p.getQuickness())
                     .add("Jumping", p.getJumping())
                     .add("Stamina", p.getStamina())
                     .add("Team",p.getTeam().getAbbrevation())
                     .add("Contract1", p.getContract().getYear1())
                     .add("Contract2", p.getContract().getYear2())
                     .add("Contract3", p.getContract().getYear3())
                     .add("Contract4", p.getContract().getYear4())
                     .add("Contract5", p.getContract().getYear5())
                     .add("Contract6", p.getContract().getYear6())
                     .add("Contract7", p.getContract().getYear7())
                     .build(); 
             arrayBuilder.add(JSO1);
    }
        JsonArray jA = arrayBuilder.build();
        return jA;
        
        
    }
     private JsonArray buildTransactions(DataManager dataManager){
         JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
         for(Transaction t:dataManager.getTransactions()){
             JsonObject JSO1 = Json.createObjectBuilder()
                     .add("Header", t.getHeading())
                     .add("Detail", t.getDetail())
                     .build();
                     arrayBuilder.add(JSO1);
         }
         JsonArray jA = arrayBuilder.build();
            return jA;
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
        HashMap<String,Team> teams= dataManager.getTeamsMap();
        ObservableList<Team> teamsArr= dataManager.getTeamsArray();
        
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
            teamsArr.add(newTeam); 
            
            DraftPick firstRoundPick1= new DraftPick(newTeam,dataManager.getYear(),true);
            DraftPick secondRoundPick1= new DraftPick(newTeam,dataManager.getYear(),false);
            DraftPick firstRoundPick2= new DraftPick(newTeam,dataManager.getYear()+1,true);
            DraftPick secondRoundPick2= new DraftPick(newTeam,dataManager.getYear()+1,false);
            DraftPick firstRoundPick3= new DraftPick(newTeam,dataManager.getYear()+2,true);
            DraftPick secondRoundPick3= new DraftPick(newTeam,dataManager.getYear()+2,false);
            
            newTeam.addDraftPicks(firstRoundPick1);
            newTeam.addDraftPicks(secondRoundPick1);
            newTeam.addDraftPicks(firstRoundPick2);
            newTeam.addDraftPicks(secondRoundPick2);
            newTeam.addDraftPicks(firstRoundPick3);
            newTeam.addDraftPicks(secondRoundPick3);
        }
    }

    public void loadPlayers(AppDataComponent data) throws IOException{
        DataManager dataManager= (DataManager) data;
        HashMap<String,Team> teams= dataManager.getTeamsMap();
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
            int contract1= object.getInt("Contract1");
            int contract2= object.getInt("Contract2");
            int contract3= object.getInt("Contract3");
            int contract4= object.getInt("Contract4");
            int contract5= object.getInt("Contract5");
            int contract6= object.getInt("Contract6");
            int contract7= object.getInt("Contract7");
            
            
            String [] birth= birthday.split("/");
            int year=Integer.parseInt(birth[2]);
            int age= 2016-year;

            Player p = new PlayerBuilder()
                    .setFirstName(first)
                    .setLastName(last)
                    .setHeight(height)
                    .setWeight(weight)
                    .setPos(position)
                    .setAge(age)
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
            
            LetterGrade grades=LetterGrade.makeGrades(p);
            p.setGrades(grades);
            
            Contract contract= p.getContract();
            contract.setAllYears(contract1, contract2, contract3, contract4, contract5, contract6, contract7);

           /* if(team.equals("FA")){
                dataManager.addFreeAgent(p);
            }
            else{*/
            Team currTeam=(Team) dataManager.getTeamsMap().get(team);
            currTeam.addPlayers(p);
            p.setTeam(currTeam);
            dataManager.getPlayersArray().add(p);
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

    public int limitRating(double rating){
        if(rating>100){
            rating=100;
        }
        else if(rating<1){
            rating=1;
        }
        return (int) Math.floor(rating);
    }
    
   
    
    

  
}