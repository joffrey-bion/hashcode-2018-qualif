package org.luxons.hashcode.model;

import java.util.Collections;
import java.util.List;

import org.hildan.hashcode.utils.solver.Solvable;

public class Problem implements Solvable {

  private final int nRows;

  private final int nCols;

  private final int nVehicles;

  private final int nRides;

  private final int bonus;

  private final int nSteps;

  private List<Ride> rides;

  public Problem(int nRows, int nCols, int nVehicles, int nRides, int bonus, int nSteps) {
    this.nRows = nRows;
    this.nCols = nCols;
    this.nVehicles = nVehicles;
    this.nRides = nRides;
    this.bonus = bonus;
    this.nSteps = nSteps;
  }

  public void setRides(List<Ride> rides) {
    this.rides = rides;
  }

  @Override
  public Iterable<? extends CharSequence> solve() {

    // TODO

    return Collections.emptyList();
  }
}
