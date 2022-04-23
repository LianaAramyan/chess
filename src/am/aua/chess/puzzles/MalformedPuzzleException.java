package am.aua.chess.puzzles;

public class MalformedPuzzleException extends Exception {
    public MalformedPuzzleException(){
        super("Malformed puzzle!!");
    }
    public MalformedPuzzleException(String s){
        super(s);
    }
}
