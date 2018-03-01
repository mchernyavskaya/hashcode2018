package org.hedgebits.hashcode;

class RideScoreCalculator {

    // TODO: current time
    static int getScore(Ride ride, Point carPosition, int bonus) {
        int carRideToStart = ride.getStartPoint().distance(carPosition);

        boolean isStartInEarliest = carRideToStart <= ride.getEarliestStart();

        long possibleStartTime = isStartInEarliest ? ride.getEarliestStart() : carRideToStart;

        int rideLen = ride.distance();
        long finishTime = possibleStartTime + rideLen + 1;
        if (finishTime > ride.getLatestFinish()) {
            return 0;
        }
        return isStartInEarliest ? rideLen + bonus : rideLen;
    }

}
