package org.hedgebits.hashcode;

import com.google.common.io.Resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException {

        final Scanner scanner = new Scanner(Resources.getResource("./a_example.in").openStream());
        InputInfo inputInfo = InputInfo.parse(scanner.nextLine());
        List<Ride> rides = new ArrayList<>();
        while (scanner.hasNextLine()) {
            rides.add(Ride.parse(scanner.nextLine()));
        }
        System.out.println(inputInfo);
        System.out.println(rides);
    }

}
