package com.toropov.oleg.piece;

import com.toropov.oleg.coordinate.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public interface IRook {
    default Set<CoordinatesShift> getRookMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        // bottom to top and backward
        for (int i = -7; i <= 7; i++) {
            if (i == 0) {
                continue;
            }
            result.add(new CoordinatesShift(0, i));
        }

        // left to right and backward
        for (int i = -7; i <= 7; i++) {
            if (i == 0) {
                continue;
            }
            result.add(new CoordinatesShift(i, 0));
        }

        return result;
    }
}
