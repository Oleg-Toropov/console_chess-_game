package com.toropov.oleg.coordinate;

import com.toropov.oleg.board.Board;
import com.toropov.oleg.board.BoardConsoleRenderer;
import com.toropov.oleg.board.BoardFactory;
import com.toropov.oleg.piece.Color;
import com.toropov.oleg.piece.Piece;

import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {
    private final static Scanner sc = new Scanner(System.in);

    public static Coordinates input() {
        int file, rank;

        while (true) {
            String line = sc.nextLine().trim().toLowerCase();

            if (!line.matches("^[a-h][1-8]$")) {
                System.out.println("Invalid coordinates, try again!");
                continue;
            }

            file = fileCalculation(line.charAt(0));
            rank = Character.getNumericValue(line.charAt(1));

            return new Coordinates(file, rank);
        }
    }

    public static int fileCalculation(char letter) {
        char[] fileLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int file = 0;
        for (int i = 0; i < fileLetters.length; i++) {
            if (letter == fileLetters[i]) {
                file = i + 1;
                break;
            }
        }
        return file;
    }

    public static Coordinates inputPieceCoordinatesForColor(Color color, Board board) {
        while (true) {
            System.out.println("Enter the coordinates of the piece for it move (ex. A1):");
            Coordinates coordinates = input();

            if (board.isSquareEmpty(coordinates)) {
                System.out.println("Empty square");
                continue;
            }

            Piece piece = board.getPiece(coordinates);
            if (piece.color != color) {
                System.out.println("Wrong color");
                continue;
            }

            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
            if (availableMoveSquares.isEmpty()) {
                System.out.println("Blocked piece");
                continue;
            }

            return coordinates;
        }
    }

    public static Coordinates inputAvailableSquare(Set<Coordinates> coordinates) {

        while (true) {
            System.out.println("Enter your move for selected piece:");
            Coordinates input = input();

            if (!coordinates.contains(input)) {
                System.out.println("Non-available square");
                continue;
            }

            return input;
        }
    }

    public static Move inputMove(Board board, Color color, BoardConsoleRenderer renderer) {

        while (true) {
            Coordinates sourceCoordinates = InputCoordinates.inputPieceCoordinatesForColor(color, board);

            Piece piece = board.getPiece(sourceCoordinates);

            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);

            renderer.render(board, piece);
            Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquares);

            Move move = new Move(sourceCoordinates, targetCoordinates);

            if (validateIfKingInCheckAfterMove(board, color, move)) {
                System.out.println("Your king is under attack!");
                continue;
            }

            return move;
        }
    }


    private static boolean validateIfKingInCheckAfterMove(Board board, Color color, Move move) {
        Board copy = (new BoardFactory()).copy(board);
        copy.makeMove(move);

        Piece king = copy.getKingByColor(color);

        return copy.isSquareAttackedByColor(king.coordinates, color.opposite());
    }
}
