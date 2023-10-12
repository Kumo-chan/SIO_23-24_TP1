package sio.groupJ;

import sio.tsp.TspData;
import sio.tsp.TspConstructiveHeuristic;
import sio.tsp.TspTour;

import java.util.ArrayList;
import java.util.Set;

public final class NearestNeighbor implements TspConstructiveHeuristic {
  @Override
  public TspTour computeTour(TspData data, int startCityIndex) {
    ArrayList<Integer> arrayList = new ArrayList<>(data.getNumberOfCities());

    arrayList.add(startCityIndex);
    int distance = 0;
    // TODO
    return new TspTour(data, new int[0], distance);
  }
  public int nearestCity(TspData data, int cityS, Set<Integer> listUsed) {
    int distance = Integer.MAX_VALUE;
    int index = cityS;
    for( int i = 0; i < data.getNumberOfCities(); ++i) {
      if(i == cityS || listUsed.contains(i) ) {
        continue;
      }
      int distanceToI = data.getDistance(cityS, i);
      if(distanceToI < distance) {
          distance = distanceToI;
          index = i;
      }
    }
    return index;
  }

}

