package com.toropov.oleg.game;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.coordinate.Move;
import com.toropov.oleg.piece.Piece;

import java.util.Objects;

public class PawnPromotion {
    public static boolean checkForPawnPromotion(Board board, Move move) {
        Piece piece = board.getPiece(move.from);

        return Objects.equals(piece.type, "Pawn") && (move.to.rank == 8 || move.to.rank == 1);
    }
}
