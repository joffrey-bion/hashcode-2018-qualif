package org.luxons.hashcode.model;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public class Ride implements Comparable<Ride> {

  private static int nextId = 0;

  private final int id;

  private int earliestStart;

  private int latestFinish;

  private Position start;

  private Position end;

  private int length = -1;

  private long importance = -1;

  public Ride() {
    this.id = nextId++;
  }

  public int getId() {
    return id;
  }

  public int getEarliestStart() {
    return earliestStart;
  }

  public void setEarliestStart(int earliestStart) {
    this.earliestStart = earliestStart;
  }

  public int getLatestFinish() {
    return latestFinish;
  }

  public void setLatestFinish(int latestFinish) {
    this.latestFinish = latestFinish;
  }

  public Position getStart() {
    return start;
  }

  public void setStart(Position start) {
    this.start = start;
  }

  public Position getEnd() {
    return end;
  }

  public void setEnd(Position end) {
    this.end = end;
  }

  public int length() {
    if (length < 0) {
      length = start.distanceTo(end);
    }
    return length;
  }

  public void evaluate(int nStepsTotal, int bonus) {
    long l = (nStepsTotal - latestFinish) * length();
    long b = (nStepsTotal - earliestStart) * bonus;
    importance = l + b;
  }

  public long getImportance() {
    return importance;
  }

  @Override
  public int compareTo(@NotNull Ride o) {
    return (int) (importance - o.importance);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ride ride = (Ride)o;
    return id == ride.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
