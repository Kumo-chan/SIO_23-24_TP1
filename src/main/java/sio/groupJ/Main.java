package sio.groupJ;

import sio.tsp.TspData;
import sio.tsp.TspParsingException;
import sio.tsp.TspTour;

import java.io.FileNotFoundException;

public final class Main {

  public enum Algorithm {
    NN,
    DENN
  }
  public static void main(String[] args) {

    // TODO
    //  - Renommage du package ;
    //  - Implémentation des classes NearestNeighbor et DoubleEndsNearestNeighbor ;
    //  - Affichage des statistiques dans la classe Main ;
    //  - Documentation abondante des classes comprenant :
    //    - la javadoc, avec auteurs et description des implémentations ;
    //    - des commentaires sur les différentes parties de vos algorithmes.

    // Longueurs optimales :
    // att532 : 86729
    // rat575 : 6773
    // rl1889 : 316536
    // u574   : 36905
    // u1817  : 57201
    // vm1748 : 336556

    // Exemple de lecture d'un jeu de données :
    try {
      System.out.println("Reading data from att532");
      TspData data = TspData.fromFile("data/att532.dat");
      System.out.println("NN:");
      NearestNeighbor n = new NearestNeighbor();
      TspTour tour = n.computeTour(data, 0);
      System.out.println(tour);
      System.out.println("DENN:");
      DoubleEndsNearestNeighbor b = new DoubleEndsNearestNeighbor();
      System.out.println(b.computeTour(data, 0) + "\n");

    } catch (TspParsingException e) {
      System.out.println("Parsing error" + e.getMessage());
    } catch (FileNotFoundException e) {
      System.out.println("File not found" + e.getMessage());
    }
    Statistics s1 = new Statistics(Algorithm.NN, "data/att532.dat");
    Statistics s2 = new Statistics(Algorithm.DENN, "data/att532.dat");
    Statistics.computeBestAlgo(s1,s2);
    Statistics.compareToShortestRoute(86729, s1, s2);

    Statistics s3 = new Statistics(Algorithm.NN, "data/rat575.dat");
    Statistics s4 = new Statistics(Algorithm.DENN, "data/rat575.dat");
    Statistics.computeBestAlgo(s3,s4);
    Statistics.compareToShortestRoute(6773, s3, s4);


    Statistics s5 = new Statistics(Algorithm.NN, "data/rl1889.dat");
    Statistics s6 = new Statistics(Algorithm.DENN, "data/rl1889.dat");
    Statistics.computeBestAlgo(s5,s6);
    Statistics.compareToShortestRoute(316536, s5, s6);

    Statistics s7 = new Statistics(Algorithm.NN, "data/u574.dat");
    Statistics s8 = new Statistics(Algorithm.DENN, "data/u574.dat");
    Statistics.computeBestAlgo(s7,s8);
    Statistics.compareToShortestRoute(36905, s7, s8);


    Statistics s9 = new Statistics(Algorithm.NN, "data/u1817.dat");
    Statistics s10 = new Statistics(Algorithm.DENN, "data/u1817.dat");
    Statistics.computeBestAlgo(s9,s10);
    Statistics.compareToShortestRoute(57201, s9, s10);

    Statistics s11 = new Statistics(Algorithm.NN, "data/vm1748.dat");
    Statistics s12 = new Statistics(Algorithm.DENN, "data/vm1748.dat");
    Statistics.computeBestAlgo(s9,s10);
    Statistics.compareToShortestRoute(336556, s11, s12);


  }

}
