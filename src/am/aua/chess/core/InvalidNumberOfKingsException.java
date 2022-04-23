package am.aua.chess.core;

public class InvalidNumberOfKingsException extends IllegalArrangementException{
    public InvalidNumberOfKingsException(){
        super("Invalid number of Kings on the board!");
    }
    public InvalidNumberOfKingsException(String s){
        super(s);
    }
}
