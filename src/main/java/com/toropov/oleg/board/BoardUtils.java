package com.toropov.oleg.board;

import com.toropov.oleg.coordinate.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {
    public static List<Coordinates> getDiagonalCoordinateBetween(Coordinates source, Coordinates target) {
        List<Coordinates> result = new ArrayList<>();

        int fileShift = (source.file < target.file) ? 1 : -1;
        int rankShift = (source.rank < target.rank) ? 1 : -1;

        int file = source.file + fileShift, rank = source.rank + rankShift;

        while (file != target.file && rank != target.rank) {
            result.add(new Coordinates(file, rank));

            file += fileShift;
            rank += rankShift;
        }

        return result;
    }

    public static List<Coordinates> getVerticalCoordinateBetween(Coordinates source, Coordinates target) {
        List<Coordinates> result = new ArrayList<>();
        int rankShift = (source.rank < target.rank) ? 1 : -1;
        int rank = source.rank + rankShift;

        while (rank != target.rank) {
            result.add(new Coordinates(source.file, rank));
            rank += rankShift;
        }

        return result;
    }

    public static List<Coordinates> getHorizontalCoordinateBetween(Coordinates source, Coordinates target) {
        List<Coordinates> result = new ArrayList<>();
        int fileShift = (source.file < target.file) ? 1 : -1;
        int file = source.file + fileShift;

        while (file != target.file) {
            result.add(new Coordinates(file, source.rank));
            file += fileShift;
        }

        return result;
    }
}
