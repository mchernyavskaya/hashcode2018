package org.hedgebits.hashcode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;

@Data
@AllArgsConstructor
class InputInfo {
    // 3 4 2 3 2 10
    private final int rows;
    private final int cols;
    private final int fleet;
    private final int rides;
    private final int bonus;
    private final long finishTime;

    static InputInfo parse(String s) {
        Scanner scanner = new Scanner(s);
        return new InputInfo(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                scanner.nextInt(), scanner.nextInt(), scanner.nextLong());
    }

}
