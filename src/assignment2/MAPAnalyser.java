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

    String identifiers[] = new String[]{"Ramesh", "Arjun"};
    int sbp[] = new int[]{80, 90};
    int dbp[] = new int[]{123, 100};
    int nrecords = identifiers.length;
    Record data[];

    public MAPAnalyser() {
        data = new Record[nrecords];
        loadFromTables();
        sortById();
    }

    public Record find(String id) {
        Record cRec = null;

        int first = 0;
        int last = data.length;

        while (first < last) {
            int mid = (first + last) / 2;
            if (id.compareTo(data[mid].getId()) < 0) {
                last = mid;
            } else if (id.compareTo(data[mid].getId()) > 0) {
                first = mid + 1;
            } else {
                return data[mid];
            }
        }
        return cRec;
    }

    public int highest() {
        int max = 0;

        for (int i = 0; i < data.length; i++) {
            max = data[i].getMap();

            for (int j = i + 1; j < data.length; j++) {
                if (data[j].getMap() > max) {
                    max = data[j].getMap();
                }
            }
        }
        return max;
    }

    public int lowest() {
        int min = 0;

        for (int i = 0; i < data.length; i++) {
            min = data[i].getMap();

            for (int j = i + 1; j < data.length; j++) {
                if (data[j].getMap() < min) {
                    min = data[j].getMap();
                }
            }
        }
        return min;
    }

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

    public Record[] find(int map1, int map2) {
        // no records found
        ArrayList<Record> mylist = new ArrayList<Record>();
        for (int i = 0; i < data.length; i++) {
            {
                if (data[i].getMap() >= map1 && data[i].getMap() <= map1) {
                    mylist.add(data[i]);
                }
            }
        }
        return mylist.toArray(new Record[mylist.size()]);
    }

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
     * calculate Mean Arterial Pressure
     *
     * @param sbp systolic blood pressure
     * @param dbp diastolic blood pressure
     * @return MAP (Mean Arterial Pressure )
     */
    public int calculateMap(int sbp, int dbp) {
        return 1 / 3 * sbp + 2 / 3 * dbp;
    }

    private String classify(int map) {
        if (map < 70) {
            return "low";
        } else if (map > 100) {
            return "high";
        } else {
            return "normal";
        }

    }

}
