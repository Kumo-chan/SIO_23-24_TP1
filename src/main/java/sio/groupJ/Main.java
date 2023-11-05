package sio.groupJ;

/**
 * The main class for running TSP (Traveling Salesman Problem) statistics computation.
 *
 * @author Patrick Furrer, Sarah Jallon
 */
public final class Main {

  /**
   * An enumeration representing the available TSP algorithms (NN: Nearest Neighbor, DENN: Double Ends Nearest Neighbor).
   */
  public enum Algorithm {
    NN,
    DENN
  }

  public static void main(String[] args) {

    // Optimal lengths for different TSP data sets
    double[] optimalLengths = {86729, 6773, 316536, 36905, 57201, 336556};

    // Compute and display statistics for TSP instances using different algorithms and data files
    Statistics s1 = new Statistics(Algorithm.NN, "data/att532.dat");
    Statistics s2 = new Statistics(Algorithm.DENN, "data/att532.dat");
    System.out.println(Statistics.computeStats(86729, s1, s2));

    Statistics s3 = new Statistics(Algorithm.NN, "data/rat575.dat");
    Statistics s4 = new Statistics(Algorithm.DENN, "data/rat575.dat");
    System.out.println(Statistics.computeStats(6773, s3, s4));

    Statistics s5 = new Statistics(Algorithm.NN, "data/rl1889.dat");
    Statistics s6 = new Statistics(Algorithm.DENN, "data/rl1889.dat");
    System.out.println(Statistics.computeStats(316536, s5, s6));

    Statistics s7 = new Statistics(Algorithm.NN, "data/u574.dat");
    Statistics s8 = new Statistics(Algorithm.DENN, "data/u574.dat");
    System.out.println(Statistics.computeStats(36905, s7, s8));

    Statistics s9 = new Statistics(Algorithm.NN, "data/u1817.dat");
    Statistics s10 = new Statistics(Algorithm.DENN, "data/u1817.dat");
    System.out.println(Statistics.computeStats(57201, s9, s10));

    Statistics s11 = new Statistics(Algorithm.NN, "data/vm1748.dat");
    Statistics s12 = new Statistics(Algorithm.DENN, "data/vm1748.dat");
    System.out.println(Statistics.computeStats(336556, s11, s12));

    // Compute and display the mean of the ratio difference for NN and DENN
    System.out.println("Mean of the ratio difference for NN: " +
            String.format("%.3f", Statistics.generalMeanDistanceRatio(
                    new Statistics[]{s1, s3, s5, s7, s9, s11}, optimalLengths)));

    System.out.println("Mean of the ratio difference for DENN: " +
            String.format("%.3f", Statistics.generalMeanDistanceRatio(
                    new Statistics[]{s2, s4, s6, s8, s10, s12}, optimalLengths)));
  }
}
