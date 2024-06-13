package com.toropov.oleg.game;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.board.BoardFactory;
import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.coordinate.Move;
import com.toropov.oleg.piece.Color;
import com.toropov.oleg.piece.Piece;

import java.util.List;
import java.util.Set;

public class GamemateGameStateChecker extends GameStateChecker {
    @Override
    public GameState check(Board board, Color color) {

        if (isGameStateOngoing(board, color)) {
            return GameState.ONGOING;
        }

        List<Piece> pieces = board.getPiecesByColor(color);
        for (Piece piece : pieces) {
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);

            for (Coordinates coordinates : availableMoveSquares) {
                Board clone = new BoardFactory().copy(board);
                clone.makeMove(new Move(piece.coordinates, coordinates));

                if (isGameStateOngoing(clone, color)) {
                    return GameState.ONGOING;
                }
            }
        }

        return (color == Color.WHITE) ? GameState.CHECKMATE_TO_WHITE_KING : GameState.CHECKMATE_TO_BLACK_KING;
    }

    private boolean isGameStateOngoing(Board board, Color color) {
        Piece king = board.getKingByColor(color);

        return !board.isSquareAttackedByColor(king.coordinates, color.opposite());
    }
}
