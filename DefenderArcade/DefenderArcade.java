import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jdi.Value;

public class DefenderArcade {
  private int totalArcadesNeeded = 0;

  public int countArcades(List<String> times) {
    mapTime(times);
    return totalArcadesNeeded;
  }

  public void mapTime(List<String> times){
    Map<Integer, Integer> timeList = new HashMap<Integer, Integer>();
    for(String time: times){
      int startTime = Integer.parseInt(time.split(" ")[0]);
      int endTime = Integer.parseInt(time.split(" ")[1]);
      timeList.put(startTime, endTime);
    }
    countArcadeNeeded(timeList);
    // debug(timeList.toString());
  }

  private void countArcadeNeeded(Map<Integer, Integer> timeMapped){
    for(Map.Entry<Integer, Integer> entry: timeMapped.entrySet()){
      int startTime = entry.getKey();
      int endTime   = entry.getValue();
      int overlapCounter = 1;
      for(Map.Entry<Integer, Integer> entry1: timeMapped.entrySet()){
        int startTime1 = entry1.getKey();
        int endTime1   = entry1.getValue();
        if(startTime == startTime1 && endTime == endTime1) continue;

        boolean isOverlap = isOverlap(startTime, endTime, startTime1, endTime1);
        // debug(String.valueOf(startTime) + " "+ String.valueOf(endTime) + " "+ String.valueOf(startTime1) + " "+ String.valueOf(endTime1) + " " +String.valueOf(isOverlap));
        if(isOverlap) overlapCounter++;
      }
      // debug(" ================ ");
      if(overlapCounter > totalArcadesNeeded) totalArcadesNeeded = overlapCounter;
    }
  }

  private boolean isOverlap(int startTime, int endTime, int startTime1, int endTime1){
    if((startTime >= startTime1 && startTime < endTime1) || (startTime1 >= startTime && startTime1 < endTime)) return true;

    return false;
  }

  private void debug(String x ){
    System.out.println(x);
  }
}
