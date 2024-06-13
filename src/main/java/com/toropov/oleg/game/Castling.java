package com.toropov.oleg.game;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.board.BoardUtils;
import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.coordinate.HistoryMoves;
import com.toropov.oleg.coordinate.Move;
import com.toropov.oleg.piece.Color;
import com.toropov.oleg.piece.King;
import com.toropov.oleg.piece.Rook;

import java.util.List;
import java.util.Objects;


public class Castling {
    private static final Coordinates WHITE_KING_START = new Coordinates(5, 1);
    private static final Coordinates BLACK_KING_START = new Coordinates(5, 8);

    private static final Coordinates WHITE_KING_LEFT = new Coordinates(3, 1);
    private static final Coordinates WHITE_KING_RIGHT = new Coordinates(7, 1);

    private static final Coordinates BLACK_KING_LEFT = new Coordinates(3, 8);
    private static final Coordinates BLACK_KING_RIGHT = new Coordinates(7, 8);

    private static final Move WHITE_ROOK_LEFT = new Move(new Coordinates(1, 1), new Coordinates(4, 1));
    private static final Move WHITE_ROOK_RIGHT = new Move(new Coordinates(8, 1), new Coordinates(6, 1));
    private static final Move BLACK_ROOK_LEFT = new Move(new Coordinates(1, 8), new Coordinates(4, 8));
    private static final Move BLACK_ROOK_RIGHT = new Move(new Coordinates(8, 8), new Coordinates(6, 8));

    public static boolean whiteQueensideCastling = true;
    public static boolean whiteKingsideCastling = true;
    public static boolean blackQueensideCastling = true;
    public static boolean blackKingsideCastling = true;

    private static void checkingMoves(Board board, Color color) {

        if (color == Color.WHITE) {
            if (!Objects.equals(board.getPiece(WHITE_KING_START), new King("King", Color.WHITE, WHITE_KING_START))) {
                whiteQueensideCastling = false;
                whiteKingsideCastling = false;
                return;
            }

            if (!Objects.equals(board.getPiece(WHITE_ROOK_LEFT.from), new Rook("Rook", Color.WHITE, WHITE_ROOK_LEFT.from))) {
                whiteQueensideCastling = false;
            }

            if (!Objects.equals(board.getPiece(WHITE_ROOK_RIGHT.from), new Rook("Rook", Color.WHITE, WHITE_ROOK_RIGHT.from))) {
                whiteKingsideCastling = false;
            }

            for (HistoryMoves move : board.historyMoves) {
                if (whiteQueensideCastling && (Objects.equals(move.move.from, WHITE_KING_START) || Objects.equals(move.move.from, WHITE_ROOK_LEFT.from))) {
                    whiteQueensideCastling = false;
                }
                if (whiteKingsideCastling && (Objects.equals(move.move.from, WHITE_KING_START) || Objects.equals(move.move.from, WHITE_ROOK_RIGHT.from))) {
                    whiteKingsideCastling = false;
                }
            }

        } else {
            if (!Objects.equals(board.getPiece(BLACK_KING_START), new King("King", Color.BLACK, BLACK_KING_START))) {
                blackQueensideCastling = false;
                blackKingsideCastling = false;
                return;
            }

            if (!Objects.equals(board.getPiece(BLACK_ROOK_LEFT.from), new Rook("Rook", Color.BLACK, BLACK_ROOK_LEFT.from))) {
                blackQueensideCastling = false;
            }

            if (!Objects.equals(board.getPiece(BLACK_ROOK_RIGHT.from), new Rook("Rook", Color.BLACK, BLACK_ROOK_RIGHT.from))) {
                blackKingsideCastling = false;
            }

            for (HistoryMoves move : board.historyMoves) {
                if (blackQueensideCastling && (Objects.equals(move.move.from, BLACK_KING_START) || Objects.equals(move.move.from, BLACK_ROOK_LEFT.from))) {
                    blackQueensideCastling = false;
                }
                if (blackKingsideCastling && (Objects.equals(move.move.from, BLACK_KING_START) || Objects.equals(move.move.from, BLACK_ROOK_RIGHT.from))) {
                    blackKingsideCastling = false;
                }
            }
        }
    }

    private static void checkEmptySquaresBetween(Board board, Color color) {
        checkingMoves(board, color);

        if (color == Color.WHITE) {
            if (whiteQueensideCastling) {
                List<Coordinates> coordinateBetween =
                        BoardUtils.getHorizontalCoordinateBetween(WHITE_ROOK_LEFT.from, WHITE_KING_START);

                for (Coordinates c : coordinateBetween) {
                    if (!board.isSquareEmpty(c)) {
                        whiteQueensideCastling = false;
                    }
                }
            }

            if (whiteKingsideCastling) {
                List<Coordinates> coordinateBetween =
                        BoardUtils.getHorizontalCoordinateBetween(WHITE_ROOK_RIGHT.from, WHITE_KING_START);

                for (Coordinates c : coordinateBetween) {
                    if (!board.isSquareEmpty(c)) {
                        whiteKingsideCastling = false;
                    }
                }
            }
        } else {
            if (blackQueensideCastling) {
                List<Coordinates> coordinateBetween =
                        BoardUtils.getHorizontalCoordinateBetween(BLACK_ROOK_LEFT.from, BLACK_KING_START);

                for (Coordinates c : coordinateBetween) {
                    if (!board.isSquareEmpty(c)) {
                        blackQueensideCastling = false;
                    }
                }
            }

            if (blackKingsideCastling) {
                List<Coordinates> coordinateBetween =
                        BoardUtils.getHorizontalCoordinateBetween(BLACK_ROOK_RIGHT.from, BLACK_KING_START);

                for (Coordinates c : coordinateBetween) {
                    if (!board.isSquareEmpty(c)) {
                        blackKingsideCastling = false;
                    }
                }
            }
        }
    }

    public static void checkAttackedSquares(Board board, Color color) {
        checkEmptySquaresBetween(board, color);

        if (color == Color.WHITE) {
            if (whiteQueensideCastling) {
                List<Coordinates> coordinateBetween =
                        BoardUtils.getHorizontalCoordinateBetween(new Coordinates(2, 1), WHITE_ROOK_RIGHT.to);

                for (Coordinates c : coordinateBetween) {
                    if (board.isSquareAttackedByColor(c, color.opposite())) {
                        whiteQueensideCastling = false;
                    }
                }
            }

            if (whiteKingsideCastling) {
                List<Coordinates> coordinateBetween =
                        BoardUtils.getHorizontalCoordinateBetween(new Coordinates(8, 1), WHITE_ROOK_LEFT.to);

                for (Coordinates c : coordinateBetween) {
                    if (board.isSquareAttackedByColor(c, color.opposite())) {
                        whiteKingsideCastling = false;
                    }
                }
            }
        } else {
            if (blackQueensideCastling) {
                List<Coordinates> coordinateBetween =
                        BoardUtils.getHorizontalCoordinateBetween(new Coordinates(2, 8), BLACK_ROOK_RIGHT.to);

                for (Coordinates c : coordinateBetween) {
                    if (board.isSquareAttackedByColor(c, color.opposite())) {
                        blackQueensideCastling = false;
                    }
                }
            }

            if (blackKingsideCastling) {
                List<Coordinates> coordinateBetween =
                        BoardUtils.getHorizontalCoordinateBetween(new Coordinates(8, 8), BLACK_ROOK_LEFT.to);

                for (Coordinates c : coordinateBetween) {
                    if (board.isSquareAttackedByColor(c, color.opposite())) {
                        blackKingsideCastling = false;
                    }
                }
            }
        }
    }

    public static void makeMoveOfRookDuringCastling(Board board, Move move) {
        if (board.getPiece(move.to).type.equals("King")) {
            if (Objects.equals(move.from, WHITE_KING_START)) {
                if (Objects.equals(move.to, WHITE_KING_LEFT)) {
                    board.makeMove(WHITE_ROOK_LEFT);
                } else if (Objects.equals(move.to, WHITE_KING_RIGHT)) {
                    board.makeMove(WHITE_ROOK_RIGHT);
                }
            } else if (Objects.equals(move.from, BLACK_KING_START)) {
                if (Objects.equals(move.to, BLACK_KING_LEFT)) {
                    board.makeMove(BLACK_ROOK_LEFT);
                } else if (Objects.equals(move.to, BLACK_KING_RIGHT)) {
                    board.makeMove(BLACK_ROOK_RIGHT);
                }
            }
        }
    }
}
