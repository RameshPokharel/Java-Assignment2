/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ramesh
 */
public class MAPAnalyser {

    String identifiers[] = new String[]{"Ramesh", "Arjun", "Krishna", "Harry", "Admand", "Nick", "Mahesh","Hari","Gokarna"};
    int sbp[] = new int[]{20, 50, 200, 45, 63, 45, 67,90,45};
    int dbp[] = new int[]{123, 100, 56, 67, 55, 67, 99,134,98};

    private int nrecords;
    private Record data[];

    public MAPAnalyser() {
        nrecords = identifiers.length;
        data = new Record[nrecords];
        loadFromTables();
        sortById();
    }

    /**
     *
     * @param id is identifier of Record
     * @return object of Record
     */
    public Record find(String id) {
        Record cRec = null;

        int first = 0;
        int last = data.length;

        while (first < last) {
            int mid = (first + last) / 2;
            if (id.compareToIgnoreCase(data[mid].getId()) < 0) {
                last = mid;
            } else if (id.compareToIgnoreCase(data[mid].getId()) > 0) {
                first = mid + 1;
            } else {
                return data[mid];
            }
        }
        return cRec;
    }

    /**
     * perform search operation to find Records within specified range
     *
     * @param minMap minimum MAP value
     * @param maxMap maximum MAP value
     * @return array of Record
     */
    public Record[] find(int minMap, int maxMap) {
        ArrayList<Record> mylist = new ArrayList<Record>();
        for (int i = 0; i < data.length; i++) {
            {
                if (data[i].getMap() >= minMap && data[i].getMap() <= maxMap) {
                    mylist.add(data[i]);
                }
            }
        }
        return mylist.toArray(new Record[mylist.size()]);
    }

    /**
     * Highest MAP value
     *
     * @return highest MAP value from Records
     */
    public int highest() {

        int max = data[0].getMap();
        for (int i = 0; i < data.length; i++) {

            for (int j = i + 1; j < data.length; j++) {
                if (data[j].getMap() > max) {
                    max = data[j].getMap();
                }
            }
        }
        return max;
    }

    /**
     * Lowest MAP value
     *
     * @return lowest MAP value from Records
     */
    public int lowest() {
        int min = data[0].getMap();;

        for (int i = 0; i < data.length; i++) {

            for (int j = i + 1; j < data.length; j++) {
                if (data[j].getMap() < min) {
                    min = data[j].getMap();
                }
            }
        }
        return min;
    }

    /**
     * Median MAP value
     *
     * @return median MAP value from Records
     */
    public int median() {

        int median = 0;
        ArrayList<Integer> mapList = new ArrayList();

        for (int i = 0; i < data.length; i++) {
            mapList.add(data[i].getMap());
        }
        Arrays.sort(mapList.toArray());

        int n = mapList.size();
        if (n % 2 == 0) {
            median = (mapList.get(n / 2) + mapList.get((n / 2) - 1)) / 2;
        } else {
            median = mapList.get(n / 2);
        }

        return median;
    }

    /**
     *
     * Perform selection sort algorithm to sort Records in array
     */
    private void sortById() {

        int n = data.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j].getId().compareTo(data[min_idx].getId()) < 0) {
                    min_idx = j;
                }

            }

            // Swap the found minimum element with the first element
            Record temp = data[min_idx];
            data[min_idx] = data[i];
            data[i] = temp;

        }
    }

    /**
     * construct Record objects and add them to the data array.
     */
    private void loadFromTables() {

        int cPos = 0;
        for (String id : identifiers) {
            int cSbp = sbp[cPos];
            int cDbp = dbp[cPos];
            int cMap = calculateMap(cSbp, cDbp);
            String cCategory = classify(cMap);
            Record cRecord = new Record(id, cSbp, cDbp, cMap, cCategory);
            data[cPos] = cRecord;
            cPos++;
        }
        sortById();

    }

    /**
     * classify MAP value 
     * @param map MAP value
     * @return category 
     */
    private String classify(int map) {
        if (map < 70) {
            return "low";
        } else if (map > 100) {
            return "high";
        } else {
            return "normal";
        }

    }

    /**
     * calculate Mean Arterial Pressure
     *
     * @param sbp systolic blood pressure
     * @param dbp diastolic blood pressure
     * @return MAP (Mean Arterial Pressure )
     */
    private int calculateMap(int sbp, int dbp) {
        return (int) (1.0 / 3.0 * sbp + 2.0 / 3.0 * dbp);
    }
}
