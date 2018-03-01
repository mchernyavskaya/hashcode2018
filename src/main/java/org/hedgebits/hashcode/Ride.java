package org.hedgebits.hashcode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;

@Data
@AllArgsConstructor
class Ride {

    private final int startRow;
    private final int startCol;
    private final int endRow;
    private final int endCol;
    private final long s;
    private final long f;

    static Ride parse(String s) {
        Scanner scanner = new Scanner(s);
        return new Ride(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt(), scanner.nextLong(), scanner.nextLong());
    }

}
