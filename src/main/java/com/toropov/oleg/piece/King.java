package com.toropov.oleg.piece;

import com.toropov.oleg.game.Castling;
import com.toropov.oleg.board.Board;
import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.coordinate.CoordinatesShift;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.toropov.oleg.game.Castling.*;

public class King extends Piece {

    public King(String type, Color color, Coordinates coordinates) {
        super(type, color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                result.add(new CoordinatesShift(i, j));
            }
        }

        result.add(new CoordinatesShift(-2, 0));
        result.add(new CoordinatesShift(2, 0));

        return result;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);

        if (Objects.equals(new Coordinates(this.coordinates.file - 2, this.coordinates.rank), coordinates)) {
            if (whiteQueensideCastling || blackQueensideCastling) {
                Castling.checkAttackedSquares(board, this.color);
            }
            return (this.color == Color.WHITE) ? whiteQueensideCastling : blackQueensideCastling;
        } else if (Objects.equals(new Coordinates(this.coordinates.file + 2, this.coordinates.rank), coordinates)) {
            if (whiteKingsideCastling || blackKingsideCastling) {
                Castling.checkAttackedSquares(board, this.color);
            }
            return (this.color == Color.WHITE) ? whiteKingsideCastling : blackKingsideCastling;
        } else {
            return result && !board.isSquareAttackedByColor(coordinates, color.opposite());
        }
    }
}
