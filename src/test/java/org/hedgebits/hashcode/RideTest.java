package org.hedgebits.hashcode;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RideTest {

    @Test
    public void test(){
        final Point start = new Point(1, 2);
        final Point end = new Point(1, 4);
        final Point car = new Point(0, 0);

        final int score = RideCalc.getScore(start, end, 5, 8, car, 100);
        assertThat(score).isEqualTo(102);
    }

}