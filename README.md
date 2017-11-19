# Poker

` The program is currently terminal only, with a cross platform implementation planned for future`

 * TO RUN from terminal:-
   `javac ./src/*.java -d ./bin/`
   `java ./bin/Poker`
  
 * The Poker program takes cards as command line arguments, in multiple of 5
 * A card is input as string of 2 characters with the first character
   representing rank and the second suit of card.
 
 * Each set of 5 card represents a player hand. If number of arguments is 5,
   the program prints the classification description of the hand. However,
   if number of arguments is 10 or more, the program automatically assumes
   multiple players and assigns 5 cards to each player(in order of 
   input), and then prints the winners after printing classification 
   description of each player's hand.
  
 * The program decides the winner in the following manner :-
 
 * There are nine classifications of hand in Poker:
   Straight flush, Four of a kind, Full house, Flush, Straight,
   Three of a kind, Two pair, One pair, High card
   (in decreasing order of precedence)
  
 * In case of multiple players, the player with higher precedence hand wins.
 * If there are multiple players with same hand classification, rank of
   cards is used to break the tie, as follows:
 * Straight flush: Rank of highest ranking card
 * Four of a kind: Rank of the set of four card, followed by rank of last card
 * Full house: Rank of the set of three, followed by rank of pair
 * Flush: Rank of the cards from highest to lowest rank.
 * Straight: Rank of highest ranking card
 * Three of a kind: Rank of set of three, followed by rank of remaining cards 
 * in decreasing order of ranks
 * Two pair: Rank of pair with higher rank, followed by rank of other pair,
 * followed by rank of last card
 * One pair: Rank of the pair, followed by rank of remaining cards in 
 * decreasing order of ranks
 * High cards: Rank of the cards from highest to lowest rank.
  
 * In case the tie could not be broken with this, a draw is declared.
 
