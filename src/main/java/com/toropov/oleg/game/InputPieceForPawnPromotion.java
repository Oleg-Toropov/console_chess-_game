package com.toropov.oleg.game;

import com.toropov.oleg.coordinate.Coordinates;
import com.toropov.oleg.piece.*;

import java.util.Scanner;

public class InputPieceForPawnPromotion {
    private final static Scanner sc = new Scanner(System.in);

    public static Piece inputPiece (Color color, Coordinates coordinates) {

        while (true) {
            System.out.println("Enter the name of the piece for pawn promotion. (ex. Queen, Rook, Knight, Bishop):");
            String line = sc.nextLine().trim().toLowerCase();

            switch (line) {
                case "queen" -> {
                    return new Queen("Queen", color, coordinates);
                }
                case "rook" -> {
                    return new Rook("Rook", color, coordinates);
                }
                case "knight" -> {
                    return new Knight("Knight", color, coordinates);
                }
                case "bishop" -> {
                    return new Bishop("Bishop", color, coordinates);
                }
                default -> System.out.println("Invalid name of the piece, try again!");
            }
        }
    }
}
