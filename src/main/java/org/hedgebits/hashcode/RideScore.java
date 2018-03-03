package org.hedgebits.hashcode;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class RideScore implements Comparable<RideScore> {
    private final int score;
    private final long finishTime;
    private Ride ride;
    private Car car;

    @Override
    public int compareTo(RideScore o) {
        final int scoreFactor = Integer.compare(o.score, score);
        if (scoreFactor != 0) {
            return scoreFactor;
        }
        return Long.compare(finishTime, o.finishTime);
    }
}
