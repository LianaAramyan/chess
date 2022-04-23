package am.aua.chess.cli;

import am.aua.chess.core.Chess;
import am.aua.chess.core.Move;
import am.aua.chess.core.Position;
import java.util.Scanner;

public class ChessConsole {
    private Chess game;

    public void play() {
        Scanner sc = new Scanner(System.in);
        String inputLine;

        //starts a game with the default arrangement.
        this.game = new Chess();

        //starts a game with a given arrangement for testing purposes.
        /*
        this.game = new Chess(
                        "--------" +
                        "--pp--p-" +
                        "--P----P" +
                        "----b---" +
                        "k------K" +
                        "--N---B-" +
                        "--PP--p-" +
                        "--------",
                Chess.PieceColor.WHITE);
         */

        print();

        while (!game.isGameOver()) {
            if (game.getTurn() == Chess.PieceColor.WHITE)
                System.out.println("White's move: ");
            else
                System.out.println("Black's move: ");

            inputLine = sc.nextLine();
            String[] input = inputLine.split(" ");

            Position p1 = null, p2 = null;

            if (input.length >= 1) {
                if (input[0].equals("resign")) {
                    System.out.println(game.getTurn() + " has resigned.");
                    return;
                }

                if (input[0].equals("debug")) {
                    debug();
                    print();
                    continue;
                }

                p1 = Position.generateFromString(input[0]);

                if (p1 == null || game.getPieceAt(p1) == null) {
                    System.out.println("Invalid position. Please try again.");
                    continue;
                }

                if (input.length == 1) {
                    // Players are informed about wrong turns, but the squares for
                    // the opponent's piece are still highlighted
                    if (game.getPieceAt(p1).getPieceColor() != game.getTurn())
                        System.out.println("That piece belongs to the opponent.");
                    print(p1);
                }
                else if (input.length == 2) {
                    if (game.getPieceAt(p1).getPieceColor() != game.getTurn()) {
                        System.out.println("That piece belongs to the opponent.");
                        continue;
                    }

                    boolean success = false;

                    p2 = Position.generateFromString(input[1]);

                    // checking if p1 != null is not necessary
                    // its negation is already checked on line 59
                    if (p2 != null) {
                        Move m = new Move(p1, p2);
                        success = game.performMove(m);
                    }
                    if (!success)
                        System.out.println("Invalid move. Please try again.");

                    print();
                }
            }
        }
    }

    public void print(Position origin) {
        Position[] highlights = null;
        if (origin != null)
            highlights = game.reachableFrom(origin);

        for (int i = 0; i < Chess.BOARD_RANKS; i++) {
            System.out.print((Chess.BOARD_RANKS - i) + " ");

            for (int j = 0; j < Chess.BOARD_FILES; j++) {
                boolean isHighlighted = false;

                //include the square the piece is currently at
                if (origin != null
                        && origin.getRank() == i && origin.getFile() == j)
                    isHighlighted = true;

                for (int k = 0; highlights != null && k < highlights.length; k++)
                    if (highlights[k].getRank() == i
                            && highlights[k].getFile() == j)
                    {
                        isHighlighted = true;
                        break;
                    }

                if (isHighlighted)
                    System.out.print("\u001b[31m");

                System.out.print("[");

                Position current = Position.generateFromRankAndFile(i, j);
                if (game.isEmpty(current))
                    System.out.print(" ");
                else
                    System.out.print(game.getPieceAt(current));

                System.out.print("]");

                if (isHighlighted)
                    System.out.print("\u001b[0m");
            }
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H ");
        System.out.println();
    }

    public void print() {
        print(null);
    }

    public void debug() {
        System.out.println("This method is for testing purposes.");
        //System.out.println(game.getAllDestinationsByColor(Chess.PieceColor.WHITE).length);
    }
}
