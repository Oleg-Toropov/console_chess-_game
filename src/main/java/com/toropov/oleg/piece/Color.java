package com.toropov.oleg.piece;

public enum Color {
    WHITE, BLACK;

    public Color opposite() {
        return (this == WHITE) ? BLACK : WHITE;
    }
}
