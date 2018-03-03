package org.hedgebits.hashcode;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class Application {
    private static final List<String> FILES = Arrays.asList(
            "./a_example.in", "./b_should_be_easy.in",
            "./c_no_hurry.in", "./d_metropolis.in", "./e_high_bonus.in"
    );
    public static void main(String[] args) throws IOException {
        for (String file : FILES) {
            processFile(file);
        }
    }

    private static void processFile(String fn) throws IOException {
        final Scanner scanner = new Scanner(Resources.getResource(fn).openStream());
        InputInfo inputInfo = InputInfo.parse(scanner.nextLine());
        List<Ride> rides = new ArrayList<>();
        int i = 0;
        while (scanner.hasNextLine()) {
            rides.add(Ride.parse(scanner.nextLine(), i++));
        }
        List<Car> cars = new ArrayList<>();
        for (int j = 0; j < inputInfo.getFleet(); j++) {
            cars.add(new Car(j, new Point(0, 0)));
        }

        long currentTime = 0;
        Set<Car> availableCars = new HashSet<>(cars);
        Set<Ride> availableRides = new HashSet<>(rides);
        PriorityQueue<RideScore> q = new PriorityQueue<>();

        // create all possible pairings of car to ride
        for (Car car : cars) {
            for (Ride ride : rides) {
                final RideScore score = RideScoreCalculator.getScore(ride, car, inputInfo.getBonus(), currentTime);
                q.add(score);
            }
        }
        long totalScore = 0;

        PriorityQueue<RideScore> activeRides = new PriorityQueue<>(
                Comparator.comparingLong(RideScore::getFinishTime));
        final HashMap<Car, List<Ride>> resultMap = new HashMap<>();

        while (!availableCars.isEmpty() && !availableCars.isEmpty() && !q.isEmpty()) {
            final RideScore score = q.poll();
            if (availableRides.contains(score.getRide())
                    && availableCars.contains(score.getCar())) {
                availableCars.remove(score.getCar());
                availableRides.remove(score.getRide());
                activeRides.add(score);
                score.getCar().setPos(score.getRide().getEndPoint());
                resultMap.computeIfAbsent(
                        score.getCar(), c -> new ArrayList<>()).add(score.getRide());
                totalScore += score.getScore();
                // System.out.println(score.getRide().getId() + ":" + score.getScore());
            }
        }

        while (!availableRides.isEmpty() && currentTime <= inputInfo.getFinishTime()) {
            final RideScore firstDone = activeRides.poll();
            currentTime = firstDone.getFinishTime();
            PriorityQueue<RideScore> doneQ = new PriorityQueue<>();
            for (Ride availableRide : availableRides) {
                doneQ.add(RideScoreCalculator.getScore(availableRide, firstDone.getCar(),
                        inputInfo.getBonus(), currentTime));
            }
            final RideScore score = doneQ.poll();
            availableRides.remove(score.getRide());
            activeRides.add(score);
            score.getCar().setPos(score.getRide().getEndPoint());
            resultMap.computeIfAbsent(
                    score.getCar(), c -> new ArrayList<>()).add(score.getRide());
            totalScore += score.getScore();
            // System.out.println(score.getRide().getId() + ":" + score.getScore());
        }

        final Path path = Paths.get(fn + ".out");
        List<String> result = new ArrayList<>();

        for (List<Ride> entry : resultMap.values()) {
            StringBuilder sb = new StringBuilder();
            sb.append(entry.size()).append(" ");
            for (Ride ride : entry) {
                sb.append(ride.getId()).append(" ");
            }
            result.add(sb.toString());
        }
        Files.write(path, result, CREATE);
        System.out.println("Total score: " + totalScore);
    }
}
