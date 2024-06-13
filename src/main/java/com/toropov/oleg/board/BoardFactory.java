package com.toropov.oleg.board;

import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.coordinate.HistoryMoves;
import com.toropov.oleg.piece.Color;
import com.toropov.oleg.piece.PieceFactory;

import java.util.Objects;

public class BoardFactory {
    private final PieceFactory pieceFactory = new PieceFactory();

    public Board fromFEN(String fen) {
        Board board = new Board(fen);

        String[] parts = fen.split(" ");
        String piecePositions = parts[0];

        board.startColor = (Objects.equals(parts[1], "w")) ? Color.WHITE : Color.BLACK;

        String[] fenRows = piecePositions.split("/");

        for (int i = 0; i < fenRows.length; i++) {
            String row = fenRows[i];
            int rank = 8 - i;

            int file = 1;
            for (int j = 0; j < row.length(); j++) {
                char fenChar = row.charAt(j);

                if (Character.isDigit(fenChar)) {
                    file += Character.getNumericValue(fenChar);
                } else {
                    Coordinates coordinates = new Coordinates(file, rank);
                    board.setPiece(coordinates, pieceFactory.fromFenChar(fenChar, coordinates));
                    file++;
                }
            }
        }

        return board;
    }

    public Board copy(Board source) {
        Board clone = fromFEN(source.startingFen);
        for (HistoryMoves historyMove : source.historyMoves) {
            clone.makeMoveForCloneBoard(historyMove);
        }

        return clone;
    }
}

