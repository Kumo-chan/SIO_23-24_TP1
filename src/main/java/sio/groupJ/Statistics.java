package sio.groupJ;

import sio.tsp.TspData;
import sio.tsp.TspParsingException;

import java.io.FileNotFoundException;

public class Statistics {
    private long meanRuntime;
    private long meanDistance;
    private long nbCities;
    private String filePath;
    private TspData data;
    private final Main.Algorithm algo;

    public Statistics(Main.Algorithm algo, String filePath){
        this.algo = algo;

        try {
            long totalDistance = 0;
            long totalRuntime = 0;
            this.filePath = filePath;
            this.data = TspData.fromFile(filePath);
            nbCities = data.getNumberOfCities();

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

        } catch (TspParsingException e) {
            System.out.println("Parsing error" + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
    }
    @Override
    public String toString() {
        return ("Stats for " + nbCities + " starting cities for " + algo + " with " + filePath + "\n"
        + "Mean distance: " + meanDistance + "\n"
        + "Mean runtime(ns): " + meanRuntime + "\n");
    }

    static public String computeStats(double shortestRoute, Statistics s1, Statistics s2) {
        return  s1.toString() + s2.toString() +
                "The best distance was "
                + ((s1.meanDistance - s2.meanDistance) <= 0 ?
                ((s1.meanDistance - s2.meanDistance) == 0 ? "Equality" : s1.algo) : s2.algo) + "\n"
                + "The best time was "
                + ((s1.meanRuntime - s2.meanRuntime) <= 0 ?
                ((s1.meanRuntime - s2.meanRuntime) == 0 ? "Equality" : s1.algo) : s2.algo) + "\n"
                + ("Ratio compared to optimal distance: \n"
                + String.format("%.3f", s1.meanDistance / shortestRoute) + " for " + s1.algo + "\n"
                + String.format("%.3f", s2.meanDistance / shortestRoute) + " for " + s2.algo + "\n");
    }

    static public double generalMeanDistanceRatio( Statistics[] stats, double[] optimalDistances){
        assert(stats.length == optimalDistances.length);
        double meanRatio = 0;
        for(int i = 0; i < stats.length; ++i){
            meanRatio += stats[i].meanDistance/optimalDistances[i];
        }
        return meanRatio/stats.length;
    }
}
