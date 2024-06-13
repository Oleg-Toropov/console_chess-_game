package com.toropov.oleg.coordinate;

import com.toropov.oleg.piece.Piece;

public class HistoryMoves {
    public final Move move;
    public final Piece piece;

    public HistoryMoves(Move move, Piece piece) {
        this.move = move;
        this.piece = piece;
    }
}
