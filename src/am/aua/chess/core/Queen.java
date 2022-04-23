package am.aua.chess.core;

public class Queen extends Piece {
    public Queen() {
        super();
    }

    public Queen(Chess.PieceColor color) {
        super(color);
    }

    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            return "Q";
        else
            return "q";
    }

    public Position[] allDestinations(Chess chess, Position p) {
        Position[] result = Rook.reachablePositions(chess, p);
        result = Position.appendPositionsToArray(result,
                Bishop.reachablePositions(chess, p));
        return result;
    }
}
