package org.luxons.hashcode.model;

public class Position {

  public final int row;

  public final int col;

  public Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int distanceTo(Position other) {
    return Math.abs(other.row - row) + Math.abs(other.col - col);
  }
}
