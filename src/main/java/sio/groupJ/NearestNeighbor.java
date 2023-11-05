package sio.groupJ;

import sio.tsp.TspData;
import sio.tsp.TspConstructiveHeuristic;
import sio.tsp.TspTour;

public final class NearestNeighbor implements TspConstructiveHeuristic {
    /**
     * A method that calculates a tour using the Nearest Neighbor algorithm.
     *
     * @param startCityIndex The index of the starting city.
     * @return A TspTour representing the calculated tour.
     */
    @Override
    public TspTour computeTour(TspData data, int startCityIndex) {

        int distance = 0; // Initialize the current distance to zero
        int totalDistance; // Initialize the total distance variable
        int indexNearest = startCityIndex; // Initialize the index of the nearest city to the starting city

        // Get the first nearest city
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

        // Find the remaining cities to complete the tour
        for (int j = 2; j < data.getNumberOfCities(); ++j) {
            int lastCityIndex = indexNearest; // Store the index of the last city added to the tour
            for (int k = 0; k < data.getNumberOfCities(); ++k) {
                if (isCityUsed[k]) {
                    continue; // Skip already used cities
                }
                if (lastCityIndex == indexNearest || distance > data.getDistance(k, lastCityIndex)) {
                    // If this is the first iteration or a nearer city is found,
                    // update the distance and index of the nearest city
                    distance = data.getDistance(k, lastCityIndex);
                    indexNearest = k;
                }
            }
            tour[j] = indexNearest; // Add the nearest city to the tour
            totalDistance += distance; // Update the total distance
            isCityUsed[indexNearest] = true; // Mark the nearest city as used
        }

        totalDistance += data.getDistance(tour[data.getNumberOfCities() - 1], tour[0]); // Add the distance from the last city to the starting city
        return new TspTour(data, tour, totalDistance); // Return the TspTour representing the calculated tour
    }
}


