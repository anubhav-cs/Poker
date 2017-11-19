/* Class representing Poker game as a whole; is the first point of entry 
 * in the project
 *
 * The Poker program takes cards as command line arguments, in multiple of 5
 * A card is input as string of 2 characters with the first character
 * representing rank and the second suit of card.
 *
 * Each set of 5 card represents a player hand. If number of arguments is 5,
 * the program prints the classification description of the hand. However,
 * if number of arguments is 10 or more, the program automatically assumes
 * multiple players and assigns 5 cards to each player(in order of 
 * input), and then prints the winners after printing classification 
 * description of each player's hand.
 *
 * The program decides the winner in the following manner :-
 * 
 * There are nine classifications of hand in Poker:
 * Straight flush, Four of a kind, Full house, Flush, Straight,
 * Three of a kind, Two pair, One pair, High card
 * (in decreasing order of precedence)
 *
 * In case of multiple players, the player with higher precedence hand wins.
 * If there are multiple players with same hand classification, rank of
 * cards is used to break the tie, as follows:
 * Straight flush: Rank of highest ranking card
 * Four of a kind: Rank of the set of four card, followed by rank of last card
 * Full house: Rank of the set of three, followed by rank of pair
 * Flush: Rank of the cards from highest to lowest rank.
 * Straight: Rank of highest ranking card
 * Three of a kind: Rank of set of three, followed by rank of remaining cards 
 *                  in decreasing order of ranks
 * Two pair: Rank of pair with higher rank, followed by rank of other pair,
 *           followed by rank of last card
 * One pair: Rank of the pair, followed by rank of remaining cards in 
 *           decreasing order of ranks
 * High cards: Rank of the cards from highest to lowest rank.
 *
 * In case the tie could not be broken with this, a draw is declared.
 */
public class Poker{

    private static final int HAND_SIZE = 5;

    public static void main(String[] args){
        try{
            int numInput = args.length;

            int numPlayers;

            String[] arrTemp = new String[HAND_SIZE];
            Player[] pokerPlayer;

            //divide input arguments in set of 5 and create player object
            if(numInput < 1 || (numInput%5!=0)){
                throw new InvalidInput("Error: wrong number of arguments; " +
                        "must be a multiple of 5");
            }
            else{
                numPlayers = numInput/HAND_SIZE;

                pokerPlayer = new Player[numPlayers];
                
                for(int i=0; i<numPlayers; i++){

                    int index = i*HAND_SIZE;

                    for(int j=0; j<HAND_SIZE; j++){
                        arrTemp[j] = args[index+j];
                    }
                    pokerPlayer[i] = new Player(arrTemp, i+1);
                }
            }

            // Below code calls methods to print output on the command line
            printPlayersDescription(pokerPlayer);

            if(numPlayers>1){
                Player[] winner = findWinners(pokerPlayer);
                printWinners(winner);
            }

        }catch(InvalidInput e){
            //print message in case of invalid input
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

// Prints winner(s)' information as specified in problem statement
    private static void printWinners(Player[] playerList){

        if(playerList.length==1){
            System.out.println("Player "+playerList[0].getPlayerNumber()+
                    " wins.");
        }
        else{
            System.out.print("Players ");
            for(int i=0; i<playerList.length; i++){
                if(i==playerList.length-1){
                    System.out.print(playerList[i].getPlayerNumber()+" ");
                }else if(i==playerList.length-2){
                    System.out.print(playerList[i].getPlayerNumber()+" and ");
                }else System.out.print(playerList[i].getPlayerNumber()+", ");
            }
            System.out.println("draw.");
        }
    }

// Prints player description as per problem statement
    private static void printPlayersDescription(Player[] playerList){
        for(Player p: playerList){
            System.out.println("Player "+p.getPlayerNumber()+": "
                    +p.getClassification().toString());
        }
    }

// Method to find winner(s) from a list of players
    private static Player[] findWinners(Player[] playerList){

        //sort the playerList based on precedence of their Poker Hand
        java.util.Arrays.sort(playerList);

        Player[] winners;

        int count = 0;

        int j = playerList.length-1;

        //to check for draw
        while(j>0 && playerList[j].compareTo(playerList[j-1])==0){
            j--;
        }

        count = playerList.length - j; //stores number of winners

        winners = new Player[count];
        for(int i=0; i<winners.length; i++){
            winners[i] = playerList[playerList.length-count+i];
        }
        return winners;
    }

}
