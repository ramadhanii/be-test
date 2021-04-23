import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RoyalRumble {
  public List < String > getSortedList(List < String > names) {
    Map<String, String> newSortedList = new HashMap<String, String>();
    for(String name: names){
      String nm =  getName(name);
      String ordinalNumber = getOrdinalNumber(name);
      int number = ordinalToNumber(ordinalNumber);
      newSortedList.put(nm + String.valueOf(number), name);
    }
    Map<String, String> treeMap = new TreeMap<String, String>(newSortedList);
    List<String> newList = new ArrayList<String>();
    for(Map.Entry<String, String> name: treeMap.entrySet()){
      newList.add(name.getValue());
    }
    return newList;
  }

  private String getName(String name){
    return name.split(" ")[0];
  }

  private String getOrdinalNumber(String name){
    return name.split(" ")[1];
  }

  private int ordinalNumberValue(char r){
      if (r == 'I')
          return 1;
      if (r == 'V')
          return 5;
      if (r == 'X')
          return 10;
      if (r == 'L')
          return 50;
   
      return -1;
  }

  private int ordinalToNumber(String ordinal){
    int number = 0;
    int ordinalLength = ordinal.length();
    for(int i =0; i<ordinalLength; i++){
      int ordinalNumber     = ordinalNumberValue(ordinal.charAt(i));
      int nextOrdinalNumber = 0;
      if(i+1 < ordinalLength) nextOrdinalNumber = ordinalNumberValue(ordinal.charAt(i+1));

      if(ordinalNumber < nextOrdinalNumber){
        number = number + nextOrdinalNumber - ordinalNumber;
        i++;
      }
      else number += ordinalNumber;
    }

    return number;
  }

  // private List < String > getRawList() {
  //   List<String> rawList = new ArrayList<String>();
  //   try {
  //     File file = new File("input1.txt");
  //     FileReader fr = new FileReader(file); 
  //     BufferedReader br = new BufferedReader(fr);
  //     String line;
  //     while ((line = br.readLine()) != null) {
  //       rawList.add(line.trim());
  //     }
  //     fr.close();

  //   } catch (IOException e) {
  //     // e.printStackTrace();
  //     System.out.println("File is not exists.");
  //     System.exit(1);
  //   }
  //   return rawList;
  // }

  // private void writeSortedList(List<String> sortedNames){
  //   if(sortedNames.isEmpty()) System.exit(1);
  //   try {
  //     File file = new File("output1.txt");
      
  //     FileWriter fw = new FileWriter(file, false);
  //     for(String name: sortedNames){
  //       fw.write(name + "\n");
  //     }
  //     fw.close();

  //   } catch (IOException e) {
  //     e.printStackTrace();
  //   }
  // }
  // public static void main(String[] str){
  //   RoyalRumble rr = new RoyalRumble();

  //   List<String> rawList = rr.getRawList();
  //   List<String> sortedList = rr.getSortedList(rawList);
  //   rr.writeSortedList(sortedList);
  //   // System.out.println(sortedList);
  // }
}