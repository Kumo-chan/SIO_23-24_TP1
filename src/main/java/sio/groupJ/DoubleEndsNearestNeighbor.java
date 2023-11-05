package sio.groupJ;

import sio.tsp.TspData;
import sio.tsp.TspConstructiveHeuristic;
import sio.tsp.TspTour;
/**
 * @author Patrick Furrer, Sarah Jallon
 */
public final class DoubleEndsNearestNeighbor implements TspConstructiveHeuristic {

    /**
     * Computes a tour using the Double Ends Nearest Neighbor (DENN) algorithm.
     *
     * @param data           The TSP data containing information about cities and distances.
     * @param startCityIndex The index of the starting city for the tour.
     * @return A TspTour representing the calculated tour.
     */
    @Override
    public TspTour computeTour(TspData data, int startCityIndex) {
        int distance = 0; // Initialize the current distance to zero
        int totalDistance; // Initialize the total distance variable
        int indexNearest = startCityIndex; // Initialize the index of the nearest city to the starting city

        // Get the first nearest neighbor (city)
        for (int i = 0; i < data.getNumberOfCities(); ++i) {
            if (i == startCityIndex) {
                continue; // Skip the starting city
            }
            if (indexNearest == startCityIndex || distance > data.getDistance(i, startCityIndex)) {
                // If this is the first iteration or a nearer city is found,
                // update the distance and index of the nearest city
                distance = data.getDistance(i, startCityIndex);
                indexNearest = i;
            }
        }

        int[] tour = new int[data.getNumberOfCities()]; // Initialize the tour array
        tour[0] = startCityIndex; // Set the first city in the tour as the starting city
        tour[1] = indexNearest; // Set the second city in the tour as the nearest city to the starting city

        boolean[] isCityUsed = new boolean[data.getNumberOfCities()]; // Initialize an array to keep track of used cities
        isCityUsed[startCityIndex] = true; // Mark the starting city as used
        isCityUsed[indexNearest] = true; // Mark the nearest city as used
        totalDistance = distance; // Initialize the total distance with the distance to the nearest city

        int frontCityIndex = indexNearest;
        int backCityIndex = startCityIndex;
        int frontCounter = 1;
        int backCounter = data.getNumberOfCities();

        // Iterate for each city except the first 2
        for (int j = 2; j < data.getNumberOfCities(); ++j) {

            boolean frontIsBetter = true;
            int distanceMin = totalDistance;

            // Search for the nearest city
            for (int k = 0; k < data.getNumberOfCities(); ++k) {
                if (isCityUsed[k]) {
                    continue; // Skip already used cities
                }

                // Evaluate if the front or back is a better choice
                if (distanceMin > data.getDistance(k, frontCityIndex)) {
                    distanceMin = data.getDistance(k, frontCityIndex);
                    indexNearest = k;
                    frontIsBetter = true;
                }
                if (distanceMin > data.getDistance(k, backCityIndex)) {
                    distanceMin = data.getDistance(k, backCityIndex);
                    indexNearest = k;
                    frontIsBetter = false;
                }
            }

            // Update the tour based on the chosen neighbor
            if (frontIsBetter) {
                tour[++frontCounter] = indexNearest;
                frontCityIndex = indexNearest;
            } else {
                tour[--backCounter] = indexNearest;
                backCityIndex = indexNearest;
            }

            totalDistance += distanceMin;
            isCityUsed[indexNearest] = true;
        }

        // Add the distance from the last city to the starting city to complete the tour
        totalDistance += data.getDistance(tour[frontCounter], tour[backCounter]);

        return new TspTour(data, tour, totalDistance); // Return the TspTour representing the calculated tour
    }

}
