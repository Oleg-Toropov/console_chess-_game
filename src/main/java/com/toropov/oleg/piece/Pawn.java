package com.toropov.oleg.piece;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.board.BoardUtils;
import com.toropov.oleg.coordinate.CoordinatesShift;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Pawn extends Piece {

    public Pawn(String type, Color color, Coordinates coordinates) {
        super(type, color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        if (this.color == Color.WHITE) {
            result.add(new CoordinatesShift(0, 1));
            result.add(new CoordinatesShift(1, 1));
            result.add(new CoordinatesShift(-1, 1));
            if (this.coordinates.rank == 2) {
                result.add(new CoordinatesShift(0, 2));
            }
        } else {
            result.add(new CoordinatesShift(0, -1));
            result.add(new CoordinatesShift(1, -1));
            result.add(new CoordinatesShift(-1, -1));
            if (this.coordinates.rank == 7) {
                result.add(new CoordinatesShift(0, -2));
            }
        }

        return result;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        if (Objects.equals(this.coordinates.file, coordinates.file)) {
            int rankShift = Math.abs(this.coordinates.rank - coordinates.rank);

            if (rankShift == 2) {
                List<Coordinates> between = BoardUtils.getVerticalCoordinateBetween(this.coordinates, coordinates);

                return (board.isSquareEmpty(between.get(0))) && board.isSquareEmpty(coordinates);
            } else {
                return board.isSquareEmpty(coordinates);
            }
        } else {
            if (board.isSquareEmpty(coordinates)) {
                return false;
            } else {
                return board.getPiece(coordinates).color != this.color;
            }
        }
    }

    @Override
    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        if (!Objects.equals(this.coordinates.file, coordinates.file)) {

            return !board.isSquareEmpty(coordinates) && board.getPiece(coordinates).color != this.color
                    || board.isSquareEmpty(coordinates);
        }

        return false;
    }
}
