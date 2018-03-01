package org.hedgebits.hashcode;

class RideScoreCalculator {

    // TODO: current time
    static RideScore getScore(Ride ride, Point carPosition, int bonus, int currentTime) {

        int carRideToStart = currentTime + ride.getStartPoint().distance(carPosition);

        boolean isStartInEarliest = carRideToStart <= ride.getEarliestStart();

        long possibleStartTime = isStartInEarliest ? ride.getEarliestStart() : carRideToStart;

        int rideLen = ride.getStartPoint().distance(ride.getEndPoint());
        long finishTime = possibleStartTime + rideLen + 1;
        if (finishTime > ride.getLatestFinish()) {
            return new RideScore(0, finishTime);
        }

        int score = isStartInEarliest ?  rideLen + bonus : rideLen;

        return new RideScore(score, finishTime);


    }



}
