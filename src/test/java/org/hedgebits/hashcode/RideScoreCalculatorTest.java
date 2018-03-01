package org.hedgebits.hashcode;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RideScoreCalculatorTest {

    @Test
    public void testGetScore(){
        final Point start = new Point(1, 2);
        final Point end = new Point(1, 4);
        Ride ride = new Ride(start, end, 5, 8);
        final Point car = new Point(0, 0);

        final int score = RideScoreCalculator.getScore(ride, car, 100);
        assertThat(score).isEqualTo(102);
    }

}