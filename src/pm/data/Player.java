/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.data;

/**
 *
 * @author sajidkamal
 */
public class Player {
    
    private String fname;
    private String lname;
    private int height;
    private String realHeight;
    private int weight;
    private int age;
    private String pos;
    private String DOB;
    private String city;
    private String State;
    private String College;
    private int exp;
    private int injury;
    private int timeInjured;
    private int insideScoring;
    private int jumpShot;
    private int ftShot;
    private int threeShot;
    private int threeUsage;
    private int handling;
    private int passing;
    private int postDefense;
    private int perimeterDefense;
    private int stealing;
    private int blocking;
    private int oReb;
    private int dReb;
    private int fouling;
    private int strength;
    private int quickness;
    private int jumping;
    private int stamina;
    private Team team;
    private LetterGrade grades;
    
    
    public Player(PlayerBuilder builder){
        
    this.fname=builder.fname;
    this.lname=builder.lname;
    this.age= builder.age;
    this.height=builder.height;
    this.weight=builder.weight;
    this.pos= builder.pos;
    this.DOB= builder.DOB;
    this.city= builder.city;
    this.State=builder.State;
    this.College=builder.College;
    this.exp= builder.exp;
    this.injury= builder.injury;
    this.timeInjured= builder.timeInjured;
    this.insideScoring = builder.insideScoring;
    this.jumpShot = builder.jumpShot;
    this.ftShot = builder.ftShot;
    this.threeShot= builder.threeShot;
    this.threeUsage=builder.threeUsage;
    this.handling= builder.handling;
    this.passing = builder.passing;
    this.postDefense= builder.postDefense;
    this.perimeterDefense= builder.perimeterDefense;
    this.stealing = builder.stealing;
    this.blocking= builder.blocking;
    this.oReb= builder.oReb;
    this.dReb= builder.dReb;
    this.fouling= builder.fouling;
    this.strength= builder.strength;
    this.quickness= builder.strength;
    this.jumping = builder.jumping;
    this.stamina = builder.stamina;
    this.team = builder.team;
    
    this.realHeight=Util.convertHeight(height);
    }
    
    public Player(){
        
    }
    
    
    @Override
    public String toString(){
        return fname+" "+lname;
    }
    /**
     * @return the name
     */
    
    public String getFirstName() {
        return fname;
    }

    /**
     * @param name the name to set
     */
    public void setFirstName(String name) {
        this.fname = name;
    }

       /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }
    
    public String getName(){
        return fname+" "+lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }
    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
        this.realHeight=Util.convertHeight(height);
    }
    public String getRealHeight(){
        return realHeight;
    }
    

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * @return the DOB
     */
    public String getDOB() {
        return DOB;
    }

    /**
     * @param DOB the DOB to set
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the State
     */
    public String getState() {
        return State;
    }

    /**
     * @param State the State to set
     */
    public void setState(String State) {
        this.State = State;
    }

    /**
     * @return the College
     */
    public String getCollege() {
        return College;
    }

    /**
     * @param College the College to set
     */
    public void setCollege(String College) {
        this.College = College;
    }

    /**
     * @return the exp
     */
    public int getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * @return the injury
     */
    public int getInjury() {
        return injury;
    }

    /**
     * @param injury the injury to set
     */
    public void setInjury(String injury) {
        this.setInjury(injury);
    }

    /**
     * @param injury the injury to set
     */
    public void setInjury(int injury) {
        this.injury = injury;
    }

    /**
     * @return the timeInjured
     */
    public int getTimeInjured() {
        return timeInjured;
    }

    /**
     * @param timeInjured the timeInjured to set
     */
    public void setTimeInjured(int timeInjured) {
        this.timeInjured = timeInjured;
    }

    /**
     * @return the insideScoring
     */
    public int getInsideScoring() {
        return insideScoring;
    }

    /**
     * @param insideScoring the insideScoring to set
     */
    public void setInsideScoring(int insideScoring) {
        this.insideScoring = insideScoring;
    }

    /**
     * @return the jumpShot
     */
    public int getJumpShot() {
        return jumpShot;
    }

    /**
     * @param jumpShot the jumpShot to set
     */
    public void setJumpShot(int jumpShot) {
        this.jumpShot = jumpShot;
    }

    /**
     * @return the ftShot
     */
    public int getFtShot() {
        return ftShot;
    }

    /**
     * @param ftShot the ftShot to set
     */
    public void setFtShot(int ftShot) {
        this.ftShot = ftShot;
    }

    /**
     * @return the threeShot
     */
    public int getThreeShot() {
        return threeShot;
    }

    /**
     * @param threeShot the threeShot to set
     */
    public void setThreeShot(int threeShot) {
        this.threeShot = threeShot;
    }

    /**
     * @return the threeUsage
     */
    public int getThreeUsage() {
        return threeUsage;
    }

    /**
     * @param threeUsage the threeUsage to set
     */
    public void setThreeUsage(int threeUsage) {
        this.threeUsage = threeUsage;
    }

    /**
     * @return the handling
     */
    public int getHandling() {
        return handling;
    }

    /**
     * @param handling the handling to set
     */
    public void setHandling(int handling) {
        this.handling = handling;
    }

    /**
     * @return the passing
     */
    public int getPassing() {
        return passing;
    }

    /**
     * @param passing the passing to set
     */
    public void setPassing(int passing) {
        this.passing = passing;
    }

    /**
     * @return the postDefense
     */
    public int getPostDefense() {
        return postDefense;
    }

    /**
     * @param postDefense the postDefense to set
     */
    public void setPostDefense(int postDefense) {
        this.postDefense = postDefense;
    }

    /**
     * @return the perimeterDefense
     */
    public int getPerimeterDefense() {
        return perimeterDefense;
    }

    /**
     * @param perimeterDefense the perimeterDefense to set
     */
    public void setPerimeterDefense(int perimeterDefense) {
        this.perimeterDefense = perimeterDefense;
    }

    /**
     * @return the stealing
     */
    public int getStealing() {
        return stealing;
    }

    /**
     * @param stealing the stealing to set
     */
    public void setStealing(int stealing) {
        this.stealing = stealing;
    }

    /**
     * @return the blocking
     */
    public int getBlocking() {
        return blocking;
    }

    /**
     * @param blocking the blocking to set
     */
    public void setBlocking(int blocking) {
        this.blocking = blocking;
    }

    /**
     * @return the oReb
     */
    public int getoReb() {
        return oReb;
    }

    /**
     * @param oReb the oReb to set
     */
    public void setoReb(int oReb) {
        this.oReb = oReb;
    }

    /**
     * @return the dReb
     */
    public int getdReb() {
        return dReb;
    }

    /**
     * @param dReb the dReb to set
     */
    public void setdReb(int dReb) {
        this.dReb = dReb;
    }

    /**
     * @return the fouling
     */
    public int getFouling() {
        return fouling;
    }

    /**
     * @param fouling the fouling to set
     */
    public void setFouling(int fouling) {
        this.fouling = fouling;
    }

    /**
     * @return the strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * @return the quickness
     */
    public int getQuickness() {
        return quickness;
    }

    /**
     * @param quickness the quickness to set
     */
    public void setQuickness(int quickness) {
        this.quickness = quickness;
    }

    /**
     * @return the jumping
     */
    public int getJumping() {
        return jumping;
    }

    /**
     * @param jumping the jumping to set
     */
    public void setJumping(int jumping) {
        this.jumping = jumping;
    }

    /**
     * @return the stamina
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * @param stamina the stamina to set
     */
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    /**
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * @return the grades
     */
    public LetterGrade getGrades() {
        return grades;
    }

    /**
     * @param grades the grades to set
     */
    public void setGrades(LetterGrade grades) {
        this.grades = grades;
    }
    
    public String getInsideGrade(){
        return grades.getInside();
    }
    public String getMidGrade(){
        return grades.getMidRange();
    }
    public String getThreePointGrade(){
        return grades.getThreePoint();
    }
    public String getHandleGrade(){
        return grades.getHandling();
    }
    public String getPostDefenseGrade(){
        return grades.getPostDefense();
    }
    public String getPerimeterDefenseGrade(){
        return grades.getPerimeterDefense();
    }
    public String getReboundingGrade(){
        return grades.getRebounding();
    }

}
