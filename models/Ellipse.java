package models;

public class Ellipse extends Forms {

    private double cx; //centre de l'Ellipse
    private double cy;
    private double rayonX; //rayon x du cercle
    private double rayonY; //rayon y du cercle

    public Ellipse(double cx, double cy, double rayonX, double rayonY) {
        this.cx = cx;
        this.cy = cy;
        this.rayonX = rayonX;
        this.rayonY = rayonY;
    }

    public double getCx() {
        return cx;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public double getRayonX() {
        return rayonX;
    }

    public void setRayonX(double rayonX) {
        this.rayonX = rayonX;
    }

    public double getRayonY() {
        return rayonY;
    }

    public void setRayonY(double rayonY) {
        this.rayonY = rayonY;
    }

    public double getLeftPoint(){
        return getCx() - getRayonX();
    }
}
