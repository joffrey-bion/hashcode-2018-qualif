package org.luxons.hashcode;

import org.hildan.hashcode.utils.parser.HCParser;
import org.hildan.hashcode.utils.parser.readers.HCReader;
import org.hildan.hashcode.utils.parser.readers.ObjectReader;
import org.hildan.hashcode.utils.runner.HCRunner;
import org.hildan.hashcode.utils.runner.UncaughtExceptionsPolicy;
import org.hildan.hashcode.utils.solver.HCSolver;
import org.luxons.hashcode.model.Position;
import org.luxons.hashcode.model.Problem;
import org.luxons.hashcode.model.Ride;

public class Main {

  public static void main(String[] args) {
    ObjectReader<Problem> rootReader = problemReader();
    HCParser<Problem> parser = new HCParser<>(rootReader);
    HCSolver<Problem> solver = new HCSolver<>(parser, Problem::solve);
    HCRunner<String> runner = new HCRunner<>(solver, UncaughtExceptionsPolicy.PRINT_ON_STDERR);
    runner.run(args);
  }

  private static ObjectReader<Problem> problemReader() {
    return HCReader.withVars("R", "C", "F", "N", "B", "T")
            .createFrom6Vars(Problem::new, "R", "C", "F", "N", "B", "T")
            .thenList(Problem::setRides, "N", createRideReader());
  }

  private static ObjectReader<Ride> createRideReader() {
    ObjectReader<Position> positionReader = positionReader();
    return HCReader.create(Ride::new)
            .thenChild(Ride::setStart, positionReader)
            .thenChild(Ride::setEnd, positionReader)
            .thenInt(Ride::setEarliestStart)
            .thenInt(Ride::setLatestFinish);
  }

  private static ObjectReader<Position> positionReader() {
    return HCReader.createFrom2Ints(Position::new);
  }
}
