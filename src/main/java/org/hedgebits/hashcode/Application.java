package org.hedgebits.hashcode;

import com.google.common.io.Resources;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException {

        final Scanner scanner = new Scanner(Resources.getResource("./sample1.txt").openStream());
        final int rows = scanner.nextInt();


    }

}
