package com.toropov.oleg.game;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.piece.Color;
import com.toropov.oleg.piece.Piece;

import java.util.List;
import java.util.Set;

public class StalemateGameChecker extends GameStateChecker {
    @Override
    public GameState check(Board board, Color color) {
        List<Piece> pieces = board.getPiecesByColor(color);

        for (Piece piece : pieces) {
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);

            if(!availableMoveSquares.isEmpty()) {
                return GameState.ONGOING;
            }
        }

        return GameState.STALEMATE;
    }
}
