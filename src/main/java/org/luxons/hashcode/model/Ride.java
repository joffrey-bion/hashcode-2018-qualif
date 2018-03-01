package org.luxons.hashcode.model;

public class Ride {

  private int earliestStart;

  private int latestFinish;

  private Position start;

  private Position end;

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
}
