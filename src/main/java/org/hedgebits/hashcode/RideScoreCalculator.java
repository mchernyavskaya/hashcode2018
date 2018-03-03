package org.hedgebits.hashcode;

@SuppressWarnings("SameParameterValue")
class RideScoreCalculator {

    /**
     * Calculates ride score
     */
    static RideScore getScore(Ride ride, Car car, int bonus, long currentTime) {

        long carRideToStart = currentTime +
                ride.getStartPoint().distance(car.getPos());

        boolean isStartInEarliest = carRideToStart <= ride.getEarliestStart();

        long possibleStartTime = isStartInEarliest ?
                ride.getEarliestStart() : carRideToStart;

        int rideLen = ride.distance();
        long finishTime = possibleStartTime + rideLen + 1;
        if (finishTime > ride.getLatestFinish()) {
            return new RideScore(0, finishTime, ride, car);
        }

        int score = isStartInEarliest ? rideLen + bonus : rideLen;

        return new RideScore(score, finishTime, ride, car);
    }
}
