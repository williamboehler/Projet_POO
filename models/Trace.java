package models;

import java.util.ArrayList;

public class Trace extends Forms{

    private ArrayList<Point> listPoints;

    public Trace(ArrayList<Point> listPoints) {
        this.listPoints = listPoints;
    }

    public ArrayList<Point> getListPoints() {
        return listPoints;
    }

    public void setListPoints(ArrayList<Point> listPoints) {
        this.listPoints = listPoints;
    }
}
