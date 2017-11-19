/*
 * Exception Class to throw and catch invalid string input from users
 */

public class InvalidInput extends Exception {

    public InvalidInput(String msg){
        super(msg);
    }

    public InvalidInput(){
        super("Invalid Input");
    }
}
