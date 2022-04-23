package am.aua.chess.puzzles;

import am.aua.chess.core.*;

import java.util.*;

public final class Puzzle implements Comparable<Puzzle> {
    public enum Difficulty {EASY, MEDIUM, HARD, UNSPECIFIED}

    private String piecesArrangement;
    private Chess.PieceColor playerTurn;
    private Difficulty puzzleDifficulty;
    private String description;

    public Puzzle() {
        this("rs---------npp-pp--p--pln-pP------p-PP---PS-N---P---BP-P-----S-L,WHITE,MEDIUM",
                "Additional puzzle that should be added.");
    }

    public Puzzle(String piecesArrangement, String description) {
        this.description = description;
        this.piecesArrangement = piecesArrangement;

        try {
            String board = piecesArrangement.split(",")[0];
            verifyPieceArrangement(board);

        } catch (MalformedPuzzleException e) {
            System.out.println(e.getMessage());
        }
    }

    public Puzzle(Puzzle puzzle) {
        this.description = puzzle.description;
        this.piecesArrangement = puzzle.piecesArrangement;

        String currentPlayerTurn = puzzle.piecesArrangement.split(",")[1];
        String currentPuzzleDifficulty = puzzle.piecesArrangement.split(",").length == 3 ?
                puzzle.piecesArrangement.split(",")[2] : "";
        boolean isDifficultySpecified = false;
        if (currentPuzzleDifficulty.length() > 0) {
            for (Difficulty currentDifficulty : Difficulty.values()) {
                if (Objects.equals(currentDifficulty, currentPuzzleDifficulty)) {
                    isDifficultySpecified = true;
                    this.puzzleDifficulty = Difficulty.valueOf(currentPuzzleDifficulty);
                    System.out.println(String.format("Difficulty level set to %s", currentPuzzleDifficulty));
                }
            }
        } else if (!isDifficultySpecified || Objects.equals(currentPuzzleDifficulty, "UNSPECIFIED")) {
            this.puzzleDifficulty = Difficulty.UNSPECIFIED;
            System.out.println("Difficulty level is set to Unspecified. Please enter (valid) Difficulty level!!!");
        }

        if (Objects.equals(currentPlayerTurn, "WHITE")) {
            this.playerTurn = Chess.PieceColor.WHITE;
            System.out.println("White's turn.");
        } else if (Objects.equals(currentPlayerTurn, "BLACK")) {
            this.playerTurn = Chess.PieceColor.BLACK;
            System.out.println("Black's turn.");
        } else {
            System.out.println("Wrong color.");
        }

        this.playerTurn = Chess.PieceColor.valueOf(currentPlayerTurn);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPiecesArrangement() {
        return piecesArrangement;
    }

    public void setPiecesArrangement(String piecesArrangement) {
        this.piecesArrangement = piecesArrangement;
    }

    public Chess.PieceColor getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Chess.PieceColor playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Difficulty getPuzzleDifficulty() {
        return puzzleDifficulty;
    }

    public void setPuzzleDifficulty(Difficulty puzzleDifficulty) {
        this.puzzleDifficulty = puzzleDifficulty;
    }

    public String toString() {
        String firstLine = getPiecesArrangement() + ", " + getPlayerTurn() + ", " + getPuzzleDifficulty();
        String secondLine = getDescription();
        return firstLine + "\n" + secondLine;
    }

    public boolean equals(Puzzle otherPuzzle) {
        if (otherPuzzle == null) {
            return false;
        }
        return (Objects.equals(getPiecesArrangement(), otherPuzzle.piecesArrangement) &&
                Objects.equals(getPuzzleDifficulty(), otherPuzzle.puzzleDifficulty) &&
                Objects.equals(getPlayerTurn(), otherPuzzle.playerTurn));
    }

    public static void verifyPieceArrangement(String s) throws MalformedPuzzleException {
        if (s.length() != 64) {
            throw new MalformedPuzzleException();
        }
    }

    public int compareTo(Puzzle otherPuzzle) {
        if (equals(otherPuzzle)) {
            return 0;
        }
        if(Difficulty.valueOf(otherPuzzle.puzzleDifficulty.toString()).ordinal()
        > Difficulty.valueOf(this.puzzleDifficulty.toString()).ordinal()){
            return 1;
        } else if(Difficulty.valueOf(otherPuzzle.puzzleDifficulty.toString()).ordinal()
                < Difficulty.valueOf(this.puzzleDifficulty.toString()).ordinal()){
            return  -1;
        } else {
            if(otherPuzzle.playerTurn.toString() == "BLACK"){
                return 1;
            } else if(otherPuzzle.playerTurn.toString() == "WHITE"){
                return -1;
            } else {
                if(String.compareToIgnoreCase())
            }
        }
        return 1;
    }
}









