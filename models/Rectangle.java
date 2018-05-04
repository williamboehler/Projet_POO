package models;

public class Rectangle extends Forms {
    private double x;
    private double y;
    private double width;
    private double height;
    private double rotate = -10;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRotate() {
        return rotate;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public String rotate(){
            return "<g transform = \"" + "rotate("+getRotate()+" "+ getWidth()/2 + " " + getHeight()/2 +")\">\n";
    }

    @Override
    public String toString(){
        String res = "";
        /*if(getRotate()!=0){
            res += rotate();
        }*/
        res += "<rect x=\"" + getX() + "\" y=\"" + getY() + "\" width=\"" + getWidth() + "\" height=\"" + getHeight() +"\" />\n";
        /*if(getRotate()!=0){
            res += "</g>\n";
        }*/
        return res;
    }
}
