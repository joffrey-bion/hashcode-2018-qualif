package org.luxons.hashcode.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import org.hildan.hashcode.utils.solver.Solvable;

public class Problem implements Solvable {

  private final int nRows;

  private final int nCols;

  private final int nCars;

  private final int nRides;

  private final int bonus;

  private final int nSteps;

  private List<Ride> rides;

  private List<Car> cars;

  public Problem(int nRows, int nCols, int nCars, int nRides, int bonus, int nSteps) {
    this.nRows = nRows;
    this.nCols = nCols;
    this.nCars = nCars;
    this.nRides = nRides;
    this.bonus = bonus;
    this.nSteps = nSteps;
    this.cars = createCars();
  }

  private List<Car> createCars() {
    List<Car> cars = new ArrayList<>(nCars);
    for (int i = 0; i < nCars; i++) {
      cars.add(new Car());
    }
    return cars;
  }

  public void setRides(List<Ride> rides) {
    this.rides = rides;
  }

  private PriorityQueue<Ride> computeQueue() {
    rides.forEach(r -> r.evaluate(nSteps, bonus));
    return new PriorityQueue<>(rides);
  }

  @Override
  public Iterable<? extends CharSequence> solve() {
    PriorityQueue<Ride> queue = computeQueue();

    while (!queue.isEmpty()) {
      Ride ride = queue.poll();
      Optional<Car> car = findBestCarFor(ride);
      car.ifPresent(c -> c.rides.add(ride));
    }

    return computeOutput();
  }

  private Optional<Car> findBestCarFor(Ride ride) {
    return cars.stream().filter(this::isAvailable).findFirst();
  }

  private boolean isAvailable(Car car) {
    return false;
  }

  private List<String> computeOutput() {
    return cars.stream().map(this::printCarRides).collect(Collectors.toList());
  }

  private String printCarRides(Car car) {
    String rideIdsStr = car.rides.stream() //
            .map(Ride::getId) //
            .map(Object::toString) //
            .collect(Collectors.joining(" "));
    return car.rides.size() + " " + rideIdsStr;
  }
}
