package com.toropov.oleg.piece;

import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.coordinate.CoordinatesShift;

import java.util.Set;

public class Queen extends LongRangePiece implements IRook, IBishop {
    public Queen(String type, Color color, Coordinates coordinates) {
        super(type, color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> result = getRookMoves();
        result.addAll(getBishopMoves());
        return result;
    }
}
