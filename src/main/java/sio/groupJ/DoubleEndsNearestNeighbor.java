package sio.groupJ;

import sio.tsp.TspData;
import sio.tsp.TspConstructiveHeuristic;
import sio.tsp.TspTour;

public final class DoubleEndsNearestNeighbor implements TspConstructiveHeuristic {
  @Override
  public TspTour computeTour(TspData data, int startCityIndex) {
    int distance = 0, totalDistance;
    int indexNearest = startCityIndex;

    //Get first neighbour
    for(int i = 0; i < data.getNumberOfCities(); ++i) {
      if( i == startCityIndex) {
        continue;
      }
      if(indexNearest == startCityIndex || distance > data.getDistance(i, startCityIndex)) {
        distance = data.getDistance(i, startCityIndex);
        indexNearest = i;
      }
    }

    int[] tour = new int[data.getNumberOfCities()];
    tour[0] = startCityIndex;
    tour[1] = indexNearest;

    boolean[] isCityUsed = new boolean[data.getNumberOfCities()];
    isCityUsed[startCityIndex] = true;
    isCityUsed[indexNearest] = true;
    totalDistance = distance;
    int frontCityIndex = indexNearest;
    int backCityIndex = startCityIndex;
    int frontCounter = 1;
    int backCounter = data.getNumberOfCities();
    //Repeat for each city except the first 2
    for(int j = 2; j < data.getNumberOfCities(); ++j) {

      boolean frontIsBetter = true;
      int distanceMin = totalDistance;
      //Search for nearest city
      for (int k = 0; k < data.getNumberOfCities(); ++k) {
        if(isCityUsed[k]) {
          continue;
        }

        //Front
        if(distanceMin > data.getDistance(k, frontCityIndex)) {
          distanceMin = data.getDistance(k, frontCityIndex);
          indexNearest = k;
          frontIsBetter = true;
        }
        //Back
        if(distanceMin > data.getDistance(k, backCityIndex)) {
          distanceMin = data.getDistance(k, backCityIndex);
          indexNearest = k;
          frontIsBetter = false;
        }
      }

      if(frontIsBetter) {
        tour[++frontCounter] = indexNearest;
        frontCityIndex = indexNearest;
      } else {
        tour[--backCounter] = indexNearest;
        backCityIndex = indexNearest;
      }

      totalDistance += distanceMin;
      isCityUsed[indexNearest] = true;

    }

    return new TspTour(data, tour, totalDistance);
  }
}
