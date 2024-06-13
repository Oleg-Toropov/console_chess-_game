package com.toropov.oleg.board;

import com.toropov.oleg.coordinate.HistoryMoves;
import com.toropov.oleg.coordinate.Move;
import com.toropov.oleg.game.InputPieceForPawnPromotion;
import com.toropov.oleg.piece.Color;
import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Board {
    public final String startingFen;
    private final HashMap<Coordinates, Piece> pieces = new HashMap<>();
    public List<HistoryMoves> historyMoves = new ArrayList<>();
    public Color startColor;

    Board(String startingFen) {
        this.startingFen = startingFen;
    }

    public void setPiece(Coordinates coordinates, Piece piece) {
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }

    private void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);
    }

    public void makeMove(Move move) {
        Piece piece = getPiece(move.from);
        removePiece(move.from);
        setPiece(move.to, piece);

        historyMoves.add(new HistoryMoves(move, piece));
    }

    public void makeMoveForCloneBoard(HistoryMoves move) {
        Piece piece = move.piece;
        removePiece(move.move.from);
        setPiece(move.move.to, piece);

        historyMoves.add(new HistoryMoves(move.move, piece));
    }

    public void changePawnToInputPiece(Move move) {
        Piece pawn = getPiece(move.from);
        removePiece(move.from);

        Piece piece = InputPieceForPawnPromotion.inputPiece(pawn.color, move.from);
        setPiece(move.to, piece);

        historyMoves.add(new HistoryMoves(move, piece));
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);
    }

    public static boolean isSquareDark(Coordinates coordinates) {
        return ((coordinates.file + coordinates.rank) % 2) == 0;
    }

    public List<Piece> getPiecesByColor(Color color) {
        List<Piece> result = new ArrayList<>();
        for (Piece piece : pieces.values()) {
            if (piece.color == color) {
                result.add(piece);
            }
        }

        return result;
    }

    public Piece getKingByColor(Color color) {
        List<Piece> piecesFromBoard = getPiecesByColor(color);
        Piece king = null;

        for (Piece piece : piecesFromBoard) {
            if (piece.type.equals("King")) {
                king = piece;
            }
        }

        if (king == null) {
            throw new IllegalStateException("No king found");
        }

        return king;
    }

    public boolean isSquareAttackedByColor(Coordinates coordinates, Color color) {
        List<Piece> pieces = getPiecesByColor(color);

        for (Piece piece : pieces) {
            Set<Coordinates> attackedSquares = piece.getAttackedSquares(this);

            if (attackedSquares.contains(coordinates)) {
                return true;
            }
        }

        return false;
    }
}
