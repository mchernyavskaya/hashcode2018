package org.hedgebits.hashcode;

public class RideCalc {

    //todo: current T
    public static int getScore(Point rideStart, Point rideEnd, int earliestStart, int latestFinish, Point carPosition, int bonus) {
        int carRideToStart = rideStart.distance(carPosition);

        boolean isStartInEarliest = carRideToStart <= earliestStart;

        int possibleStartTime = isStartInEarliest ? earliestStart : carRideToStart;

        int rideLen = rideStart.distance(rideEnd);
        int finishTime = possibleStartTime + rideLen + 1;
        if (finishTime > latestFinish) {
            return 0;
        }

        if (isStartInEarliest) {
            return rideLen + bonus;
        } else {
            return  rideLen;
        }
    }

}
