/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

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
        for (Record rec : data) {
            if (rec.equals(id)) {
                cRec = rec;
                break;
            }

        }
        return cRec;
    }

    public int lowest() {
        int max = 0;

        for (int i = 0; i < data.length; i++) {
            max = data[i].getMap();

            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > max) {
                    max = a[j];
                }

            }
        }
        return 0;
    }

    public int highest() {
        return 0;
    }

    public int median() {
        return 0;
    }

    public Record[] find(int map1, int map2) {
        // no records found
        Record[] r = new Record[0];
        return r;
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
