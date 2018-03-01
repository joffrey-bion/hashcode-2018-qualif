package org.luxons.hashcode.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.hildan.hashcode.utils.solver.Solvable;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

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
    for (int i = 0; i < rides.size(); i++) {
      rides.get(i).setId(i);
    }
  }

  private PriorityQueue<Ride> computeQueue() {
    rides.forEach(r -> r.setImportance(nSteps, bonus));
    return new PriorityQueue<>(rides);
  }

  @Override
  public Iterable<? extends CharSequence> solve() {
    PriorityQueue<Ride> queue = computeQueue();

    while (!queue.isEmpty()) {
      Ride ride = queue.poll();
      Optional<Car> car = findBestCarFor(ride);
      car.ifPresent(c -> c.addRide(ride, nSteps));
    }

    return computeOutput();
  }

  private Optional<Car> findBestCarFor(Ride ride) {
    TreeMap<Integer, List<Car>> carsByETA = cars.stream()
            .collect(groupingBy(c -> c.earliestStartFor(ride), TreeMap::new, toList()));
    Entry<Integer, List<Car>> carEntry = carsByETA.firstEntry();
    if (carEntry == null || carEntry.getKey() + ride.length() >= nSteps) {
      return Optional.empty();
    }
    List<Car> equallyEarlyCars = carEntry.getValue();
    return equallyEarlyCars.stream().max(Comparator.comparing(Car::getStepOfAvailability));
  }

  private List<String> computeOutput() {
    return cars.stream().map(this::printCarRides).collect(toList());
  }

  private String printCarRides(Car car) {
    String rideIdsStr = car.getRides().stream() //
            .map(Ride::getId) //
            .map(Object::toString) //
            .collect(Collectors.joining(" "));
    return car.getRides().size() + " " + rideIdsStr;
  }
}
