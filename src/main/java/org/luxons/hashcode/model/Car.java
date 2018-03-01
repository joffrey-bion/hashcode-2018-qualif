package org.luxons.hashcode.model;

import java.util.ArrayList;
import java.util.List;

public class Car {

  public Position pos = new Position(0, 0);

  public final List<Ride> rides = new ArrayList<>();
}
