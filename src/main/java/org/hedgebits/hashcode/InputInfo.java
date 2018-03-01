package org.hedgebits.hashcode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;

@Data
@AllArgsConstructor
class InputInfo {

    private final int rows;
    private final int cols;
    private final int fleet;
    private final int rides;
    private final int bonus;
    private final long steps;

    static InputInfo parse(String s) {
        Scanner scanner = new Scanner(s);
        return new InputInfo(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt(), scanner.nextInt(), scanner.nextLong());
    }

}
