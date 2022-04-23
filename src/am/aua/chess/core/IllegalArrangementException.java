package am.aua.chess.core;

public class IllegalArrangementException extends Exception{
    public IllegalArrangementException(){
        super("Illegal arrangement!");
    }
    public IllegalArrangementException(String s){
        super(s);
    }
}
