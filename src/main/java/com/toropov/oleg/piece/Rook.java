package com.toropov.oleg.piece;

import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.coordinate.CoordinatesShift;

import java.util.Set;

public class Rook extends LongRangePiece implements IRook {
    public Rook(String type, Color color, Coordinates coordinates) {
        super(type, color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getRookMoves();
    }
}
