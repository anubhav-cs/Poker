/*
 * Enumerated type with the ranks of the Cards in a Suit as values
 */

public enum Suit {
    CLUBS ,DIAMONDS ,HEARTS, SPADES;
    
// Second character in the 2 character input string represents Rank.
    private static final int CARDSUIT_INPUT_INDEX = 1;

/* Returns Suit type for a card represented by 2 character string input.
 * returns null in case of invalid card string
 */
    public static Suit fromString(String input){
        char suitCharVal ;

        input = input.toUpperCase();

        if(input!=null && input.length()==2){
            suitCharVal = input.charAt(CARDSUIT_INPUT_INDEX);

            for(Suit cSuit:Suit.values()){
                if(suitCharVal == cSuit.toString().charAt(0)){
                    return cSuit;
                }
            }
        }

        return null;
    }
}
