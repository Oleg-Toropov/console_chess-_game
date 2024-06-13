package com.toropov.oleg.piece;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.board.BoardUtils;
import com.toropov.oleg.coordinate.Coordinates;

import java.util.List;
import java.util.Objects;

public abstract class LongRangePiece extends Piece {
    public LongRangePiece(String type, Color color, Coordinates coordinates) {
        super(type, color, coordinates);
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);

        if (result) {
            return isSquareAvailableForAttack(coordinates, board);
        } else {
            return false;
        }
    }

    @Override
    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        List<Coordinates> coordinateBetween;

        if (Objects.equals(this.coordinates.file, coordinates.file)) {
            coordinateBetween = BoardUtils.getVerticalCoordinateBetween(this.coordinates, coordinates);
        } else if (Objects.equals(this.coordinates.rank, coordinates.rank)) {
            coordinateBetween = BoardUtils.getHorizontalCoordinateBetween(this.coordinates, coordinates);
        } else {
            coordinateBetween = BoardUtils.getDiagonalCoordinateBetween(this.coordinates, coordinates);
        }

        for (Coordinates c : coordinateBetween) {
            if (!board.isSquareEmpty(c)) {
                return false;
            }
        }

        return true;
    }
}
