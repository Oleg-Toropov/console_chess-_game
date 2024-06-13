package com.toropov.oleg.board;

import com.toropov.oleg.piece.Color;
import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.piece.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

public class BoardConsoleRenderer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";
    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[44m";
    public static final String TWO_EM_SPACE = "\u2003" + "\u2003";
    public static final String EN_SPACE = "\u2002";
    public static final String SPACE = "\u2003" + "\u2005" + "\u200A";

    public void render(Board board, Piece pieceToMove) {
        showLettersForBoard();

        Set<Coordinates> availableMoveSquares = emptySet();
        if(pieceToMove != null){
            availableMoveSquares = pieceToMove.getAvailableMoveSquares(board);
        }

        for (int rank = 8; rank >= 1; rank--) {
            StringBuilder line = new StringBuilder();
            line.append(rank).append(" ");
            for (int file = 1; file <= 8; file++) {
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHighLight = availableMoveSquares.contains(coordinates);

                if (board.isSquareEmpty(coordinates)) {
                    line.append(getSpriteForEmptySquare(coordinates, isHighLight));
                } else {
                    line.append(getPieceSprite(board.getPiece(coordinates), isHighLight));
                }

            }

            line.append(ANSI_RESET).append(" ").append(rank);
            System.out.println(line);
        }
        showLettersForBoard();
    }

    public void render(Board board) {
        render(board, null);
    }

    public void showLettersForBoard() {
        System.out.println(TWO_EM_SPACE + "A" + SPACE + "B" + SPACE + "C" + SPACE + "D" + SPACE +
                "E" + SPACE + "F" + SPACE + "G" + SPACE + "H");
    }

    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighLighted) {
        String result = sprite;

        if (pieceColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }

        if (isHighLighted) {
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        } else if (isSquareDark) {
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }

        return result;
    }

    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighLight) {
        return colorizeSprite(TWO_EM_SPACE, null, Board.isSquareDark(coordinates), isHighLight);
    }

    private String selectUnicodeSpriteForPiece(Piece piece) {
        return switch (piece.getClass().getSimpleName()) {
            case "Pawn" -> "♟";
            case "Knight" -> "♞";
            case "Bishop" -> "♝";
            case "Rook" -> "♜";
            case "Queen" -> "♛";
            case "King" -> "♚";
            default -> "";
        };
    }

    private String getPieceSprite(Piece piece, boolean isHighLight) {
        return colorizeSprite(EN_SPACE + selectUnicodeSpriteForPiece(piece) + EN_SPACE,
                piece.color, Board.isSquareDark(piece.coordinates), isHighLight);
    }
}
