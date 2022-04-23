package am.aua.chess.core;

public class Rook extends Piece implements Cloneable{
    private boolean hasMoved;

    public Rook() {
        this(Chess.PieceColor.WHITE);
    }

    public Rook(Chess.PieceColor color) {
        this(color, false);
    }

    public Rook(Chess.PieceColor color, boolean hasMoved) {
        super(color);
        this.hasMoved = hasMoved;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            if (this.hasMoved)
                return "S";
            else
                return "R";
        else
            if (this.hasMoved)
                return "s";
            else
                return "r";
    }

    public Position[] allDestinations(Chess chess, Position p) {
        return Rook.reachablePositions(chess, p);
    }

    static Position[] reachablePositions(Chess chess, Position p) {
        //Accessed by Queens from the same package, does not need to be public.
        int[] rankOffsets = {1, -1, 0, 0};
        int[] fileOffsets = {0, 0, 1, -1};
        Position[] result = new Position[0];

        for (int d = 0; d < 4; d++) {
            int i = p.getRank() + rankOffsets[d];
            int j = p.getFile() + fileOffsets[d];
            while (i >= 0 && i < Chess.BOARD_RANKS
                    && j >= 0 && j < Chess.BOARD_FILES) {
                Position current = Position.generateFromRankAndFile(i, j);

                if (chess.isEmpty(current))
                    result = Position.appendPositionsToArray(result,current);
                else {
                    if (chess.getPieceAt(current).getPieceColor()
                            != chess.getPieceAt(p).getPieceColor())
                        result = Position.appendPositionsToArray(result, current);
                    break;
                }
                i += rankOffsets[d];
                j += fileOffsets[d];
            }
        }

        return result;
    }

    @Override
    public Object clone() {
        return super.clone();
    }
}
