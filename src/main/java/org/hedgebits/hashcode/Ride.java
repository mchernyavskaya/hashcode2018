package org.hedgebits.hashcode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;

@Data
@AllArgsConstructor
class Ride {
    private int id;
    private final Point startPoint;
    private final Point endPoint;
    private final long earliestStart;
    private final long latestFinish;

    static Ride parse(String s, int id) {
        Scanner scanner = new Scanner(s);
        return new Ride(
                id,
                new Point(scanner.nextInt(), scanner.nextInt()),
                new Point(scanner.nextInt(), scanner.nextInt()),
                scanner.nextLong(), scanner.nextLong());
    }

    int distance() {
        return startPoint.distance(endPoint);
    }
}
