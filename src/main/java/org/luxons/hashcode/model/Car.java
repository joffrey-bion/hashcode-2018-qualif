package org.luxons.hashcode.model;

import java.util.ArrayList;
import java.util.List;

public class Car {

  private final List<Ride> rides = new ArrayList<>();

  private int endStep = 0;

  private Position endPos = new Position(0, 0);

  public void addRide(Ride ride) {
    rides.add(ride);
    endStep = earliestStartFor(ride) + ride.length();
    endPos = ride.getEnd();
  }

  public int earliestStartFor(Ride ride) {
    int distanceToNextRide = distanceToReach(ride);
    return Math.max(endStep + distanceToNextRide, ride.getEarliestStart());
  }

  private int distanceToReach(Ride ride) {
    return endPos.distanceTo(ride.getStart());
  }

  public int getStepOfAvailability() {
    return endStep;
  }

  public List<Ride> getRides() {
    return rides;
  }
}
