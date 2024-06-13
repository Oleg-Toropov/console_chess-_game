package com.toropov.oleg.piece;

import com.toropov.oleg.coordinate.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public interface IBishop {
    default Set<CoordinatesShift> getBishopMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        // bottom-left to top-right and backward
        for (int i = -7; i <= 7; i++) {
            if (i == 0) {
                continue;
            }
            result.add(new CoordinatesShift(i, i));
        }

        // bottom-right to top-left and backward
        for (int i = -7; i <= 7; i++) {
            if (i == 0) {
                continue;
            }
            result.add(new CoordinatesShift(i, -i));
        }

        return result;
    }
}
