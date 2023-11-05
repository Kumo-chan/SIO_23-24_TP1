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
      System.out.println(b.computeTour(data, 0));

    } catch (TspParsingException e) {
      System.out.println("Parsing error" + e.getMessage());
    } catch (FileNotFoundException e) {
      System.out.println("File not found" + e.getMessage());
    }
    Statistics s1 = new Statistics(532, Algorithm.NN, "data/att532.dat");
    Statistics s2 = new Statistics(532, Algorithm.DENN, "data/att532.dat");
    Statistics.computeBestAlgo(s1,s2);

  }

}
