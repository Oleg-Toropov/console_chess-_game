package com.toropov.oleg.game;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.board.BoardConsoleRenderer;
import com.toropov.oleg.coordinate.InputCoordinates;
import com.toropov.oleg.coordinate.Move;
import com.toropov.oleg.piece.Color;

import java.util.List;

public class Game {
    private final Board board;
    private final BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    private final List<GameStateChecker> checkers = List.of(
            new StalemateGameChecker(),
            new GamemateGameStateChecker());

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop() {
        Color colorToMove = board.startColor;

        GameState state = determineGameState(board, colorToMove);

        while (state == GameState.ONGOING) {
            renderer.render(board);

            System.out.println((colorToMove == Color.WHITE) ? "White's move." : "Black's move.");

            Move move = InputCoordinates.inputMove(board, colorToMove, renderer);

            if (PawnPromotion.checkForPawnPromotion(board, move)) {
                board.changePawnToInputPiece(move);
            } else {
                board.makeMove(move);
            }

            Castling.makeMoveOfRookDuringCastling(board, move);

            colorToMove = colorToMove.opposite();

            state = determineGameState(board, colorToMove);


        }

        renderer.render(board);
        System.out.println("Game ended with state = " + state);
    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker : checkers) {
            GameState state = checker.check(board, color);

            if (state != GameState.ONGOING) {
                return state;
            }
        }

        return GameState.ONGOING;
    }
}
