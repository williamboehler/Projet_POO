package models;

public class Ellipse extends Forms {

    private double cx; //centre de l'Ellipse
    private double cy;
    private double rayonX; //rayon x du cercle
    private double rayonY; //rayon y du cercle
    private double rotate = -10;

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

    public double getLeftPointX(){
        return getCx() - getRayonX();
    }

    public double getLeftPointY(){
        return getCy() - getRayonY();
    }

    public double getRightXPoint(){
        return getCx()+getRayonX();
    }

    public double getRightYPoint(){
        return getCy() + getRayonY();
    }

    public double getRotate() {
        return rotate;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public String rotate(){
        return "<g transform = \"" + "rotate("+getRotate()+")\">\n";
    }

    @Override
    public String toString(){
        String res = "";
        /*if(getRotate()!=0){
            res += rotate();
        }*/
        res += "<ellipse cx=\"" + getCx() + "\" cy=\"" + getCy() + "\" rx=\"" + getRayonX() + "\" ry=\"" + getRayonY() + "\" />\n";
        /*if(getRotate()!=0){
            res += "</g>\n";
        }*/
        return res;
    }
}
