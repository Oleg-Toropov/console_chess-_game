package com.toropov.oleg;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.board.BoardFactory;
import com.toropov.oleg.game.Game;

public class Main {
    public static void main(String[] args) {
        Board board = (new BoardFactory()).fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        Game game = new Game(board);
        game.gameLoop();
    }
}


