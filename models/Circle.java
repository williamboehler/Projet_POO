package models;

public class Circle extends Forms {

    private double cx; //centre du cercle
    private double cy;
    private double rayon;
    private double rotate = -10;

    public Circle(double cx, double cy, double rayon) {
        this.cx = cx;
        this.cy = cy;
        this.rayon = rayon;
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

    public double getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public double getLeftXPoint(){
        return getCx() - getRayon();
    }

    public double getLeftYPoint(){
        return getCy() - getRayon();
    }

    public double getRightXPoint(){
        return getCx() + getRayon();
    }

    public double getRightYPoint(){
        return getCy() + getRayon();
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
        res = "<circle cx=\"" + getCx() + "\" cy=\"" + getCy() + "\" r=\"" + getRayon() +"\" />\n";
        /*if(getRotate()!=0){
            res += "</g>\n";
        }*/
        return res;
    }

}
