/*
 * Enumerated type with the ranks of the Cards in a Suit as values
 */
public enum Rank {
    _2 ,_3 ,_4 ,_5 ,_6 ,_7 ,_8 ,_9 ,_10 ,_Jack ,_Queen ,_King ,_Ace;

// First character in the 2 character input string represents Rank.
    private static final int CARDTYPE_INPUT_INDEX = 0;

/* Returns Rank of a card represented by 2 character string input.
 * returns null in case of invalid card string
 */
    public static Rank fromString(String input){
        
        char cardTypeCharVal;

        input = input.toUpperCase();

        if(input!=null && input.length()==2){
            cardTypeCharVal = input.charAt(CARDTYPE_INPUT_INDEX);

            if(cardTypeCharVal == 'T'){ // Since, card 10 is represented as T
                cardTypeCharVal = '1';
            }else if(cardTypeCharVal == '1'){
                cardTypeCharVal = '0';
            }

            for(Rank cRank:Rank.values()){ 
                if(cardTypeCharVal == cRank.toString().charAt(0)){
                    return cRank;
                }
            }
        }

        return null;
    }
    
//Utility function to copy Rank Type Array
    public static Rank[] copyRankArray(Rank[] input){

        Rank[] temp = new Rank[input.length];
        
        for(int i=0; i<input.length; i++){
            temp[i] = input[i];
        }

        return temp;
    }

//Overriden method to return enum values without leading _
    public String toString(){

        String temp = super.toString();

        return temp.substring(1,temp.length());
    }
}
