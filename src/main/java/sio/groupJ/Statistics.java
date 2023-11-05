package sio.groupJ;

import sio.tsp.TspData;
import sio.tsp.TspParsingException;

import java.io.FileNotFoundException;

public class Statistics {
    private long meanRuntime;
    private long meanDistance;
    private Main.Algorithm algo;

    public Statistics(Main.Algorithm algo, String filePath){
        this.algo = algo;
        try {
            long totalDistance = 0;
            long totalRuntime = 0;
            TspData data = TspData.fromFile(filePath);
            long nbCities = data.getNumberOfCities();

            for (int i = 0; i < nbCities; ++i) {
                switch (algo) {
                    case NN -> {
                        NearestNeighbor n = new NearestNeighbor();
                        long startTime = System.nanoTime();
                        totalDistance += n.computeTour(data, i).length();
                        long endTime = System.nanoTime();
                        totalRuntime += endTime - startTime;
                    }
                    case DENN -> {
                        DoubleEndsNearestNeighbor d = new DoubleEndsNearestNeighbor();
                        long startTime = System.nanoTime();
                        totalDistance += d.computeTour(data, i).length();
                        long endTime = System.nanoTime();
                        totalRuntime += endTime - startTime;
                    }
                }
            }

            meanDistance = totalDistance / nbCities;
            meanRuntime = totalRuntime / nbCities;

            System.out.println("Stats for " + nbCities + " starting cities for " + algo + " with " + filePath);
            System.out.println("Mean distance: " + meanDistance);
            System.out.println("Mean runtime(ns): " + meanRuntime);


        } catch (TspParsingException e) {
            System.out.println("Parsing error" + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
    }
    static public void computeBestAlgo(Statistics s1, Statistics s2) {
        System.out.println("The best distance was "
                + ((s1.meanDistance - s2.meanDistance) <= 0 ? ((s1.meanDistance - s2.meanDistance) == 0 ? "Equality" : s1.algo) : s2.algo));
        System.out.println("The best time was "
                + ((s1.meanRuntime - s2.meanRuntime) <= 0 ?  ((s1.meanRuntime - s2.meanRuntime) == 0 ? "Equality" : s1.algo) : s2.algo));
    }

    static public void compareToShortestRoute(double shortestRoute, Statistics s1, Statistics s2) {
        System.out.println("Ratio with optimal distance: " + s1.meanDistance/shortestRoute + " for " + s1.algo);
        System.out.println("Ratio with optimal distance: " + s2.meanDistance/shortestRoute + " for " + s2.algo + "\n");
    }
}
