package com.toropov.oleg.piece;

import com.toropov.oleg.coordinate.Coordinates;

public class PieceFactory {
    public Piece fromFenChar(char fenChar, Coordinates coordinates) {
        Color pieceColor = (Character.isUpperCase(fenChar)) ? Color.WHITE : Color.BLACK;
        fenChar = Character.toLowerCase(fenChar);

        return switch (fenChar) {
            case 'p' -> new Pawn("Pawn", pieceColor, coordinates);
            case 'r' -> new Rook("Rook", pieceColor, coordinates);
            case 'n' -> new Knight("Knight", pieceColor, coordinates);
            case 'b' -> new Bishop("Bishop", pieceColor, coordinates);
            case 'q' -> new Queen("Queen", pieceColor, coordinates);
            case 'k' -> new King("King", pieceColor, coordinates);
            default -> throw new RuntimeException("Unknown FEN char!");
        };
    }
}
