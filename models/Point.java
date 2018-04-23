package models;

public class Point{
    /*si commande de tracé en majuscule: coordonnees absolues sinon coordonnees relatives*/
    private Boolean M = false;
    private Boolean m = false;
    private Boolean H = false;
    private Boolean h = false;
    private Boolean V = false;
    private Boolean v = false;
    private Boolean Z = false;
    private Boolean z = false;
    private Boolean L = false;
    private Boolean l = false;
    private double x;
    private double y;

    public Point(){ }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Boolean getM() {
        return M;
    }

    public void setM(Boolean m) {
        M = m;
    }

    public Boolean getH() {
        return H;
    }

    public void setH(Boolean h) {
        H = h;
    }

    public Boolean getV() {
        return V;
    }

    public void setV(Boolean v) {
        V = v;
    }

    public Boolean getZ() {
        return Z;
    }

    public void setZ(Boolean z) {
        Z = z;
    }

    public Boolean getL() {
        return L;
    }

    public void setL(Boolean l) {
        L = l;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    //***/
    public Boolean getm() {
        return m;
    }

    public void setm(Boolean m) {
        m = m;
    }

    public Boolean geth() {
        return h;
    }

    public void seth(Boolean h) {
        h = h;
    }

    public Boolean getv() {
        return v;
    }

    public void setv(Boolean v) {
        v = v;
    }

    public Boolean getz() {
        return z;
    }

    public void setz(Boolean z) {
        z = z;
    }

    public Boolean getl() {
        return L;
    }

    public void setl(Boolean l) {
        l = l;
    }
}
