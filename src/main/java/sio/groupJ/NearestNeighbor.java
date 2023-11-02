package sio.groupJ;

import sio.tsp.TspData;
import sio.tsp.TspConstructiveHeuristic;
import sio.tsp.TspTour;

public final class NearestNeighbor implements TspConstructiveHeuristic {
  @Override
  public TspTour computeTour(TspData data, int startCityIndex) {

    int distance = 0, totalDistance;
    int indexNearest = startCityIndex;

    //Get first tour
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
    for(int j = 2; j < data.getNumberOfCities(); ++j) {
      int lastCityIndex = indexNearest;
      for (int k = 0; k < data.getNumberOfCities(); ++k) {
        if(isCityUsed[k]) {
          continue;
        }
        if(lastCityIndex == indexNearest || distance > data.getDistance(k, lastCityIndex)) {
          distance = data.getDistance(k, lastCityIndex);
          indexNearest = k;
        }
      }
      tour[j] = indexNearest;
      totalDistance += distance;
      isCityUsed[indexNearest] = true;
    }

    totalDistance += data.getDistance(tour[data.getNumberOfCities() -1], tour[0]);
    return new TspTour(data, tour, totalDistance);
  }

}

