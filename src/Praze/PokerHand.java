/*
 * PokerHand Class encapsulates the cards assigned to players
 * and records their classification as per Poker rules
 */

public class PokerHand{

//Constant: stores the default size of a Poker hand
    private static final int POKER_HAND_SIZE = 5;

//Array to store all cards in Hand
    private final Card[] arrCard;

//Records the precedence of a Hand(both classification and tie-breaker ranks)
    private final HandPrecedence precedence;

/* Empty constructor not defined to prevent anyone from creating
 * an instance of PokerHand without Cards and Precedence
 *
 * public PokerHand(){}
 */

/* Contructor: requires array of strings  as argument, containing 
 * 5 two-character strings.
 * Creates Poker Hand object with a array of 5 Cards and
 * precedence of the set of cards as per Poker rules.
 */
    public PokerHand(String[] input) throws InvalidInput{

        if(input == null){
            throw new NullPointerException("Error: null array passed");
        }
        else if(input.length!=5){
            throw new InvalidInput("Error: wrong number of arguments; " +
                                    "must be a multiple of 5");
        }
        else{
            this.arrCard = sortOnRank(resolveCards(input));
            this.precedence = this.classifyPokerHand();
        }
    }

/* Implementation of compareTo with respect to precedence of Poker Hand
 * returns -ive value if this HandPrecedence is less than argument,
 * 0 if both are equal, else +ive value
 */
    public int compareTo(Object other){
        PokerHand temp = (PokerHand) other;
        return this.precedence.compareTo(temp.getClassification());
    }
   
// returns the precedence of this object
    public HandPrecedence getClassification(){
        return this.precedence; //Note-precedence is immutable object;
    }

/*  Uncomment this section in case you want to get a copy of assigned cards
    public Card[] getCards(){
        return copyCardArray(this.arrCard);
    }
*/

// returns Array of Cards corresponding to input string, in a Poker hand
    private Card[] resolveCards(String[] input) throws InvalidInput{

        Card[] temp = new Card[input.length];

        for(int i=0; i<input.length; i++){
            temp[i] = new Card(input[i]);
        }

        return temp;
    }

// sorts an input card array on Rank and returns the same
    private Card[] sortOnRank(Card[] input){

        for(int i=1; i<input.length; i++){

            int j=i;

            Card temp = input[j];

            while(j>0 && input[j-1].compareTo(temp)>0){
                input[j] = input[j-1];
                j--;
            }

            input[j]=temp;
        }
        return input;
    }

// Classifies array of cards in a Poker Hand are returns HandPrcedence object
    private HandPrecedence classifyPokerHand(){

        Card[] arrCard = this.arrCard;
       
        // Check if Straight, Flush or Straight Flush
        HandPrecedence precedence;
        if((precedence=checkStraightOrFlush())!=null){
            return checkStraightOrFlush();
        }

        // Stores count of same rank cards, at index of last card of that rank
        int[] arrCountSameRank = generateSameRankArray();
        
        int index_threeOfAKind = -1, index_pair = -1;
        for(int i=0; i<arrCountSameRank.length; i++){
            switch (arrCountSameRank[i]){
                case 3: //value 3 represents 4-of-a-kind
                    return new HandPrecedence(Classification.FOUR_OF_A_KIND,
                            createRankArray(2, arrCard[i].getRank(),
                                arrCard[(i+1)%5].getRank()));
                case 2: // value 2 represents 3-of-kind
                    if(index_pair>0){ // if previously detected a Pair
                        return new HandPrecedence(Classification.FULL_HOUSE,
                                createRankArray(2,arrCard[i].getRank(),
                                    arrCard[index_pair].getRank()));
                    }else 
                        index_threeOfAKind = i; // store index of 3-of-a-kind
                    break;
                case 1: // value 1 represents a pair
                    if(index_threeOfAKind>0){ // 3-of-a-kind detected earlier
                        return new HandPrecedence(Classification.FULL_HOUSE,
                                createRankArray(2, arrCard[index_threeOfAKind].
                                    getRank(), arrCard[i].getRank()));
                    }else if(index_pair>0){ // a pair detected earlier
                        return new HandPrecedence(
                                Classification.TWO_PAIR,
                                ((arrCard[index_pair].compareTo(arrCard[i])>0) 
                                 ? createRankArray(3,
                                     arrCard[index_pair].getRank(),
                                     arrCard[i].getRank())
                                 : createRankArray(3,arrCard[i].getRank(),
                                     arrCard[index_pair].getRank())));
                    }else index_pair = i; //else store index of the pair
                    break;
            }   //end switch
        }   //end loop

        //Now a simple scan to check remaining possible classification.
        if(index_threeOfAKind>0){
            return new HandPrecedence(Classification.THREE_OF_A_KIND,
                   createRankArray(3,arrCard[index_threeOfAKind].getRank()));
        }else if(index_pair>0){
            return new HandPrecedence(Classification.ONE_PAIR,
                    createRankArray(4,arrCard[index_pair].getRank()));
        }else{
            createRankArray(5);
            return new HandPrecedence(Classification.
                    HIGH_CARD, createRankArray(5));
        }
    }

/* Check if Straight, Flush or Straight Flush 
 * and returns corresponding HandPrecedence Object
 */
    private HandPrecedence checkStraightOrFlush(){

        boolean inSeq = checkIfAllInSequence(this.arrCard);

        boolean sameSuit = checkIfAllSameSuit(this.arrCard);
        
        if(inSeq){
            int length = this.arrCard.length;
            
            Rank[] maxRank = createRankArray(1,
                   this.arrCard[length-1].getRank());
            return (sameSuit)
                ? new HandPrecedence(Classification.STRAIGHT_FLUSH, maxRank)
                : new HandPrecedence(Classification.STRAIGHT, maxRank);
        }else if(sameSuit){   
            return new HandPrecedence(Classification.FLUSH,createRankArray(5));
        }
        return null;
    }
    
// returns true if all 5 cards are of same suit
    private static boolean checkIfAllSameSuit(Card[] input){
        int count = 0;

        for(int i=0; i<input.length-1; i++){
            if(input[i+1].equalsSuit(input[i])){
                count++;
            }
        }
        if(count==input.length-1){
           return true; 
        }
        return false;
    }

//returns true if all 5 cards are in sequence
    private static boolean checkIfAllInSequence(Card[] input){
        int count = 0;

        for(int i=0; i<input.length-1; i++){

            while(i<input.length-1 
                    && input[i+1].getRank().ordinal()
                    == input[i].getRank().ordinal()+1)
            {
                count++;
                i++;
            }
        }
        return (count==4);
    }
    
/* Creates a Rank Array with length n, and Rank args as values.
 * If there are no or less than n Rank type args, then it fills value from
 * this.arrCard's Ranks in decreasing order of Rank
 */
    private Rank[] createRankArray(int n, Rank...args){
        Rank[] rankArr = new Rank[n];
        for(int i=0; i<args.length; i++){
            rankArr[i] = args[i];
        }
        if(n>args.length)   fillMissingRank(rankArr);

        return rankArr;
    }
    
/* generate an array which stores number of same rank cards, at the 
 * index of last card of same Rank
 */
    private int[] generateSameRankArray(){
        
        int[] arrCountSameRank = new int[5];

        // loop to generate arrCountSameRank array
        for(int i=0; i<this.arrCard.length-1; i++){
           int count = 0;
           while(i<this.arrCard.length-1 && 
                   this.arrCard[i+1].equalsRank(this.arrCard[i])){
                count++;
                i++;
            }
            arrCountSameRank[i] = count;
        }
        
        return arrCountSameRank;
    }
    
/* fills the empty ranks in tieBreaker array from the remaining cards
 */
    private void fillMissingRank(Rank[] arrRank){
        for(int i=0; i<arrRank.length; i++){
            int j = this.arrCard.length;
            if(arrRank[i]==null){
                int count = 1;
                while(j>0 && count!=0){
                    count = 0;
                    j--;
                    for(Rank r:arrRank){
                        if(r!=null && this.arrCard[j].getRank().equals(r)){
                            count++;
                        }
                    }
                }
                arrRank[i] = this.arrCard[j].getRank();
            }
        }
    }
}

