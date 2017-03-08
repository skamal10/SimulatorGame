/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.data;

import javafx.beans.value.ObservableValue;

/**
 *
 * @author sajidkamal
 */
public class LetterGrade {
    private static final double insideAvg=43.77;
    private static final double insideStDev=10.45;
    private static final double outsideAvg= 45.90;
    private static final double outsideStDev= 16.60;
    private static final double threeAvg=36.39;
    private static final double threeStDev=30.11;
    private static final double handleAvg=31.56;
    private static final double handleStDev=15.15;
    private static final double postAvg= 38.43;
    private static final double postStDev=18.48;
    private static final double perimeterAvg=42.57;
    private static final double perimeterStDev=17.10;
    private static final double reboundingAvg=38.93;
    private static final double reboundingStDev=18.17;
    
    
    private String inside;
    private String midRange;
    private String threePoint;
    private String handling;
    private String postDefense;
    private String perimeterDefense;
    private String rebounding;
    private String potential;
    Player p;

    
    private LetterGrade(Player p){
        this.p=p;
    }
    public static LetterGrade makeGrades(Player p){
        LetterGrade grades= new LetterGrade(p);
        grades.calculateRatings();
        return grades;
    }

    /**
 * Calculate the letter grades for each category based on the raw attributes
 *
 */
    public void calculateRatings(){
        
       int insideRaw= limitRating(p.getInsideScoring());
       int strengthRaw= limitRating(p.getStrength());
       int insideRating= limitRating(((insideRaw*2.75)+(strengthRaw*1.50))/4.25);
       inside= calculateGrade(insideRating,insideAvg,insideStDev);

       int outsideRaw= limitRating(p.getJumpShot());
       midRange=calculateGrade(outsideRaw,outsideAvg,outsideStDev);
       
       
       int threeRaw= limitRating(p.getThreeShot());
       threePoint= calculateGrade(threeRaw,threeAvg,threeStDev);
       
       int handleRaw= limitRating(p.getHandling());
       int passingRaw= limitRating(p.getPassing());
       int passingRating= limitRating((handleRaw*0.35)+(passingRaw*0.65));
       handling= calculateGrade(passingRating,handleAvg,handleStDev);
       
       int postRaw= limitRating(p.getHandling());
       int blockingRaw= limitRating(p.getBlocking());
       int postRating= limitRating((postRaw*0.55)+(blockingRaw*0.45));
       postDefense= calculateGrade(postRating,postAvg,postStDev);
       
       int perimeterRaw= limitRating(p.getPerimeterDefense());
       int stealingRaw= limitRating(p.getStealing());
       int quicknessRaw= limitRating(p.getQuickness());
       int perimeterRating= limitRating((perimeterRaw*0.55)+(stealingRaw*0.35)+(quicknessRaw*0.10));
       perimeterDefense= calculateGrade(perimeterRating,perimeterAvg,perimeterStDev);
       
       int dRebRaw= limitRating(p.getdReb());
       int oRebRaw= limitRating(p.getoReb());
       int jumpingRaw= limitRating(p.getJumping());
       int reboundingRating= limitRating((dRebRaw*0.55)+(oRebRaw*0.35)+(jumpingRaw*0.10));
       rebounding= calculateGrade(reboundingRating,reboundingAvg,reboundingStDev);
       
       
    }
    

    /**
 * Given the stDeviation of a given category, compute the letter grade for each raw rating.
 *
 * @param  rating raw attribute number
 * @param  avg    League-wide avg of the given attribute
 * @return      Letter Grade given the attribute
 */
    private String calculateGrade(int rating, double avg,double stDev){
        String grade;
        
        if((rating-avg)>=((20/8)*stDev)){
            grade="A";
        }
        else if((rating-avg)>=((14/8)*stDev)){
            grade="A-";
        }
        else if((rating-avg)>=((12/8)*stDev)){
            grade="B+";
        }
        else if((rating-avg)>=((5/8)*stDev)){
            grade="B";
        }
        else if((rating-avg)>=((0)*stDev)){
            grade="B-";
        }
        else if((rating-avg)>=((-5/8)*stDev)){
            grade="C+";
        }
        else if((rating-avg)>=((-1)*stDev)){
            grade= "C";
        }
        else if((rating-avg)>=((-11/8)*stDev)){
            grade= "C-";
        }
        else if((rating-avg)>=((-15/8)*stDev)){
            grade="D+";
        }
        else if((rating-avg)>=((-18/8)*stDev)){
            grade="D";
        }
        else {
            grade="F";
        }

       return grade;
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
    /**
     * @return the inside
     */
    public String getInside() {
        return inside;
    }

    /**
     * @param inside the inside to set
     */
    public void setInside(String inside) {
        this.inside = inside;
    }

    /**
     * @return the midRange
     */
    public String getMidRange() {
        return midRange;
    }

    /**
     * @param midRange the midRange to set
     */
    public void setMidRange(String midRange) {
        this.midRange = midRange;
    }

    /**
     * @return the threePoint
     */
    public String getThreePoint() {
        return threePoint;
    }

    /**
     * @param threePoint the threePoint to set
     */
    public void setThreePoint(String threePoint) {
        this.threePoint = threePoint;
    }

    /**
     * @return the handling
     */
    public String getHandling() {
        return handling;
    }

    /**
     * @param handling the handling to set
     */
    public void setHandling(String handling) {
        this.handling = handling;
    }

    /**
     * @return the postDefense
     */
    public String getPostDefense() {
        return postDefense;
    }

    /**
     * @param postDefense the postDefense to set
     */
    public void setPostDefense(String postDefense) {
        this.postDefense = postDefense;
    }

    /**
     * @return the perimeterDefense
     */
    public String getPerimeterDefense() {
        return perimeterDefense;
    }

    /**
     * @param perimeterDefense the perimeterDefense to set
     */
    public void setPerimeterDefense(String perimeterDefense) {
        this.perimeterDefense = perimeterDefense;
    }

    /**
     * @return the rebounding
     */
    public String getRebounding() {
        return rebounding;
    }

    /**
     * @param rebounding the rebounding to set
     */
    public void setRebounding(String rebounding) {
        this.rebounding = rebounding;
    }

    /**
     * @return the potential
     */
    public String getPotential() {
        return potential;
    }

    /**
     * @param potential the potential to set
     */
    public void setPotential(String potential) {
        this.potential = potential;
    }
    
}
