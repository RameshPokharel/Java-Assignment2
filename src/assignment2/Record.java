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
public class Record {

    String id;
    int sbp;
    int dbp;
    int map;
    String category;

    /**
     *
     * @param id identifier of Record
     * @param sbp
     * @param dbp
     * @param map
     * @param category
     */
    public Record(String id,
            int sbp,
            int dbp,
            int map,
            String category) {
        this.id = id;
        this.sbp = sbp;
        this.dbp = dbp;
        this.map = map;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSbp() {
        return sbp;
    }

    public void setSbp(int sbp) {
        this.sbp = sbp;
    }

    public int getDbp() {
        return dbp;
    }

    public void setDbp(int dbp) {
        this.dbp = dbp;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if ("".getClass() != obj.getClass()) {
            return false;
        }
        String other = (String) obj;
        if (this.id.equalsIgnoreCase(other)) {
            return true;
        } else {
            return false;

        }
    }

    @Override
    public String toString() {
        return String.format("%10s %15s %15s %15d",getId(), getSbp(),getDbp(), getMap());
        //return "Record{" + "id=" + id + ", sbp=" + sbp + ", dbp=" + dbp + ", map=" + map + ", category=" + category + '}';
    }

}
