package am.aua.chess.core;

public abstract class Piece implements Cloneable{
    private static Chess.PieceColor color;

    public Piece(Chess.PieceColor color) {
        this.color = color;
    }

    public Piece() {
        this(Chess.PieceColor.WHITE);
    }

    public Position[] allDestinations(Chess chess, Position p) {
        return null;
    }

    public static Chess.PieceColor getPieceColor() {
        return color;
    }

    public Object clone(){
        try {
            Piece copy = (Piece)super.clone();
            return copy;
        }
        catch (CloneNotSupportedException e){
            return null;
        }
    }
}
