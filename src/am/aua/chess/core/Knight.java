package am.aua.chess.core;

public class Knight extends Piece {
    public Knight() {
        super();
    }

    public Knight(Chess.PieceColor color) {
        super(color);
    }

    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            return "N";
        else
            return "n";
    }

    public Position[] allDestinations(Chess chess, Position p) {
        int[][] pattern = {
                {p.getRank() + 2, p.getFile() + 1},
                {p.getRank() + 2, p.getFile() - 1},
                {p.getRank() - 2, p.getFile() + 1},
                {p.getRank() - 2, p.getFile() - 1},

                {p.getRank() + 1, p.getFile() + 2},
                {p.getRank() + 1, p.getFile() - 2},
                {p.getRank() - 1, p.getFile() + 2},
                {p.getRank() - 1, p.getFile() - 2}
        };

        Position[] result = new Position[0];

        for (int i = 0; i < pattern.length; i++) {
            Position potential = Position.generateFromRankAndFile(pattern[i][0],
                    pattern[i][1]);
            if (potential != null && (chess.isEmpty(potential)
                    || chess.getPieceAt(potential).getPieceColor()
                    != chess.getPieceAt(p).getPieceColor()))
                result = Position.appendPositionsToArray(result, potential);
        }

        return result;
    }
}
