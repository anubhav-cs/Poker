/* Card class represents the structure of a card object and provides
 * interface to compare cards and retrieve rank.
 */

public class Card{
    
// cardRank stores the rank of the card
    private final Rank cardRank;

// cardSuit stores the type of suit of the card
    private final Suit cardSuit;


/* Empty constructor not defined to prevent developers from creating
 * an instance of card without suit and rank.
 *
 * public Card(){}
 */

/* Contructor definition with 2 character card input string as argument.
 * Used to create a card object with Rank and Suit.
 */
    public Card(String input) throws InvalidInput{
        if(input==null){
            throw new NullPointerException("Cannot create Card"+
                    "object with null String");
        }
        else if((this.cardRank=Rank.fromString(input))==null
                    || (this.cardSuit=Suit.fromString(input))==null)
        {
            //Throw error in case the input string with card name was invalid
            throw new InvalidInput("Error: invalid card name '"+input+"'");
        }
    }

// returns true if the Rank of argument card is same as this card
    public boolean equalsRank(Card other){
        if(other==null
            || !this.cardRank.equals(other.cardRank))
        {
            return false;
        }

        return true;
    }

// returns true if the Suit of argument card is same as this card
    public boolean equalsSuit(Card other){
        if(other==null
            || !this.cardSuit.equals(other.cardSuit))
        {
            return false;
        }

        return true;
    }

/* Implementation of compareTo with respect to rank of Cards
 * returns -ive value if this card's Rank is less than argument's,
 * 0 if both are equal, else +ive value
 */
    public int compareTo(Object other){

        Card temp = (Card) other;

        return this.cardRank.compareTo(temp.cardRank);
        
    }

// returns the Rank of this card
    public Rank getRank(){
        return this.cardRank; //Note- cardRank is immutable object
    }

// returns a copy of Card Array passed as argument
    private static Card[] copyCardArray(Card[] input){

        Card[] temp = new Card[input.length];

        for(int i=0; i<input.length; i++){
            temp[i] = input[i]; // Note-Card is immutable class
        }

        return temp;
    }

}
