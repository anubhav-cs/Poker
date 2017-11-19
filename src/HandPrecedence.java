/* HandPrecedence class encapsulates the Classification Type and corresponding
 * Ranks of cards required to break tie scenarios(tie-breakers).
 */

public class HandPrecedence{

// record the classification type     
    private final Classification handClassification;

// Rank Array to record the tieBreaker information
    private final Rank[] tieBreaker;

/* Constructor: requires classification and Rank array to create
 * Precedence object
 */
    public HandPrecedence(Classification type, Rank[] tieBreaker){
        this.handClassification = type;
        this.tieBreaker = tieBreaker;
    }

// return the Classification type of this card
    public Classification getClassName(){
        return this.handClassification; //Note-handClassification is immutable
    }

// return tieBreaker Rank Array of this card
    public Rank[] getTieBreaker(){
        return Rank.copyRankArray(this.tieBreaker); //Note-Arrays are mutable
    }

/* Method returns a string corresponding to a Hand Classification in the 
 * format asked in problem statement
 */
    public String toString(){
        switch (this.handClassification.toString()){
            case "STRAIGHT_FLUSH":
                return tieBreaker[0].toString()+
                    "-high straight flush";
            case "FOUR_OF_A_KIND":
                return "Four "+tieBreaker[0].toString()+"s";
            case "FULL_HOUSE":
                return tieBreaker[0].toString()+"s full of "+
                    tieBreaker[1]+"s";
            case "FLUSH":
                return tieBreaker[0].toString()+"-high flush";
            case "STRAIGHT":
                return tieBreaker[0].toString()+"-high straight";
            case "THREE_OF_A_KIND":
                return "Three "+tieBreaker[0]+"s";
            case "TWO_PAIR":
                return tieBreaker[0].toString()+"s over "+
                    tieBreaker[1].toString()+"s";
            case "ONE_PAIR":
                return "Pair of "+tieBreaker[0]+"s";
            case "HIGH_CARD":
                return tieBreaker[0].toString()+"-high";
        }
        return null;
    }
 
/* Method to compare two precedence objects.
 * Returns true if this object equals argument
 */
    public boolean equals(Object other){
        if(other == null 
            || this.getClass() != other.getClass())
        {
            return false;
        }
        HandPrecedence temp = (HandPrecedence) other;
        return this.compareTo(temp)==0;
    }
   
/* Implementation of compareTo with respect to precedence of Hand
 * returns -ive value if this HandPrecedence is less than argument,
 * 0 if both are equal, else +ive value
 */
    public int compareTo(Object other){

        HandPrecedence temp = (HandPrecedence) other;

        int returnval = temp.getClassName().compareTo(this.handClassification);
        if(returnval!=0) return returnval;
        
        Rank[] otherTieBreaker = temp.getTieBreaker();
        
        for(int i=0; i<otherTieBreaker.length; i++){
            if(!this.tieBreaker[i].equals(otherTieBreaker[i]))
                return this.tieBreaker[i].compareTo(otherTieBreaker[i]);
        }
        return 0;
    }
    
}
