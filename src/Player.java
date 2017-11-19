/*
 * Player Class- 
 * Stores player number and poker hand. Provides interface
 * to get the PokerHand Classification and player information.
 */

public class Player implements Comparable{

// Stores the player number
    private final int playerNumber;

// Stores the PokerHand object, Hand with the Player
    private final PokerHand playerHand;

/* Empty constructor not defined to prevent anyone from creating
 * an instance of Player without playernumber, Cards and Precedence
 *
 * public Player(){}
 */

/* Contructor: requires String Array representing 5 cardsand playerNumber 
 * as argument.
 * Creates Player object with Hand of cards and Player Number
 */
    public Player(String[] inputS, int pNum) throws InvalidInput{
        if(inputS == null){
            throw new NullPointerException
                ("Cannot create Player object with null String array");
        }
        else{
            this.playerHand = new PokerHand(inputS);
            this.playerNumber = pNum;
        }
    }

/* Uncomment the below section in case Player's Poker Hand has to be viewed
    public PokerHand getPlayerHand(){
        return this.playerHand; //Note- playerHand is Immutable object
    }
*/

// returns HandPrecedence object of Hand with a Player
    public HandPrecedence getClassification(){
        return this.playerHand.getClassification();
    }

// returns player number of a Player
    public int getPlayerNumber(){
        return this.playerNumber;
    }

/* Implementation of compareTo with respect to precedence of Hand with Player
 * returns -ive value if this HandPrecedence is less than argument,
 * 0 if both are equal, else +ive value
 */

    public int compareTo(Object other){
        Player temp = (Player) other;
        return this.playerHand.compareTo(temp.playerHand);
    }

}
