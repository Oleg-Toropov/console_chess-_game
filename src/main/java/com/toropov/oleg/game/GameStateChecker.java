package com.toropov.oleg.game;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.piece.Color;

public abstract class GameStateChecker {
    public abstract GameState check(Board board, Color color);
}
