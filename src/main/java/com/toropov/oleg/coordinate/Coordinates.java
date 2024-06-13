package com.toropov.oleg.coordinate;

import java.util.Objects;

public class Coordinates {

    public final Integer file;
    public final Integer rank;

    public Coordinates(Integer file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(this.file + shift.fileShift, this.rank + shift.rankShift);
    }

    public boolean canShift(CoordinatesShift shift) {
        int f = this.file + shift.fileShift;
        int r = this.rank + shift.rankShift;

        return f >= 1 && f <= 8 && r >= 1 && r <= 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (!Objects.equals(file, that.file)) return false;
        return rank.equals(that.rank);
    }

    @Override
    public int hashCode() {
        int result = file.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }
}
