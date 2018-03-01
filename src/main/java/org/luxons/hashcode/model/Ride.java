package org.luxons.hashcode.model;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public class Ride implements Comparable<Ride> {

  private long id;

  private int earliestStart;

  private int latestFinish;

  private Position start;

  private Position end;

  private int length = -1;

  private long importance = -1;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public void setImportance(int nStepsTotal, int bonus) {
    long l = (nStepsTotal - latestFinish) * length() * 2;
    long b = (nStepsTotal - earliestStart) * bonus / 10;
    importance = (l + b) / length();
    if (importance < 0) {
      throw new IllegalStateException("Negative importance for a ride");
    }
  }

  public long getImportance() {
    return importance;
  }

  @Override
  public int compareTo(@NotNull Ride o) {
    // biggest first in queues
    return (int) (o.importance - importance);
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
