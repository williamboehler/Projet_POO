import models.*;
import models.Point;
import models.Rectangle;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Test /*extends JPanel*/ {

    private String pathnamefile;
    private double rotate = 0;
    private double transX = 0;
    private double transY = 0;
    private Point p1 = new Point(500, 0);
    private Point p2 = new Point(0, 500);

    public Test(String file) {
        this.pathnamefile = file;
    }

    public String getPathnamefile() {
        return pathnamefile;
    }

    public double getRotate() {
        return rotate;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public static ArrayList readSVG(String path) throws IOException {
        ArrayList<Forms> listForms = new ArrayList<>(); //List de Forms ou stockes les formes

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        //on lit le fichier ligne par ligne
        while ((line = br.readLine()) != null) {
            // Si la forme lue est un rectangle
            if(line.contains("<rec")) {
                boolean ok = false;
                double x = 0; // coordonnee x
                double y = 0; // coordonnee y
                double width = 0; // longueur
                double height = 0; //largeur
                while (!ok){
                    int indexDeb;
                    int indexEnd;

                    //si la ligne contient un "x=", on lit la valeur de x
                    if(line.contains("x=")) {
                        //x
                        indexDeb = line.indexOf('x') + 3; // on skip le '=' et le '"'
                        indexEnd = line.indexOf('p', indexDeb);
                        if(indexEnd == -1)
                            indexEnd = line.indexOf('"', indexDeb);

                        x = Double.parseDouble(line.substring(indexDeb, indexEnd)); // (X) on recupere la valeur du x="...px"
                    }

                    //si la ligne contient un "y=", on lit la valeur de y
                    if(line.contains("y=")) {
                        indexDeb = line.indexOf('y') + 3; // on skip le '=' et le '"'
                        indexEnd = line.indexOf('p', indexDeb);
                        if(indexEnd == -1)
                            indexEnd = line.indexOf('"', indexDeb);

                        y = Double.parseDouble(line.substring(indexDeb, indexEnd)); // (Y) on recupere la valeur du y="...py"
                    }

                    //si la ligne contient un "width=", on lit la valeur de width
                    if(line.contains("width=")) {
                        indexDeb = line.indexOf('h') + 3; // on skip le '=' et le '"'
                        indexEnd = line.indexOf('"', indexDeb);

                        width = Double.parseDouble(line.substring(indexDeb, indexEnd)); // (Y) on recupere la valeur du y="...py"
                    }

                    //si la ligne contient un "height=", on lit la valeur de height
                    if(line.contains("height=")) {
                        indexDeb = line.indexOf('g') + 5; // on skip le '=' et le '"'
                        indexEnd = line.indexOf('"', indexDeb);

                        height = Double.parseDouble(line.substring(indexDeb, indexEnd)); // (Y) on recupere la valeur du y="...py
                    }
                    //on est arrivee à la fin de la lecture de cette forme donc on sort de la boucle
                    if(line.contains("/>")){
                        ok = true;
                    }
                    // on passe à la ligne suivante
                    else{
                        line = br.readLine();
                    }
                }
                //on cree un rectangle avec les donnees lues et on l'ajoute dans la list de Forms
                Rectangle rec = new Rectangle(x, y, width, height);
                listForms.add(rec);// on ajoute le rectangle à la liste des forme

                System.out.println("x = " + x + " ; y = " + y + " ; width = " + width + " ; height = " + height);
            }
            // Si la forme lue est un cercle
            else if(line.contains("<circle")){
                int indexDeb;
                int indexEnd;
                double x = 0;
                double y = 0;
                double r = 0;
                boolean ok = false;
                while(!ok) {
                    //x
                    if (line.contains("cx=")) {
                        indexDeb = line.indexOf('x') + 3; // on skip le '=' et le '"'
                        indexEnd = line.indexOf('"', indexDeb);

                        x = Integer.parseInt(line.substring(indexDeb, indexEnd)); // (CX)on recupere la valeur du x="..."
                    }
                    //y
                    if (line.contains("cy=")) {
                        indexDeb = line.indexOf('y') + 3; // on skip le '=' et le '"'
                        indexEnd = line.indexOf('"', indexDeb);

                        y = Integer.parseInt(line.substring(indexDeb, indexEnd)); // (CX)on recupere la valeur du x="..."
                    }
                    //r
                    if (line.contains("r=")) {
                        if(line.contains("<circle")) {
                            indexDeb = line.indexOf('r', line.indexOf('r') + 1) + 3;
                            indexEnd = line.indexOf('"', indexDeb);
                        }
                        else {
                            indexDeb = line.indexOf('r') + 3;
                            indexEnd = line.indexOf('"', indexDeb);
                        }
                        r = Integer.parseInt(line.substring(indexDeb, indexEnd)); // (RAYON)on recupere la valeur du r="..."
                    }
                    //fin de la forme
                    if (line.contains("/>")) {
                        ok = true;
                    }
                    // on passe à la ligne suivante
                    else{
                        line = br.readLine();
                    }
                }
                Circle circle = new Circle(x, y,  r);
                listForms.add(circle);
                System.out.println("x = " + x + " ; y = " + y + " ; r = " + r);
            }
            // Si la forme lue est une ellipse
            else if(line.contains("<ellipse")){
                int indexDeb;
                int indexEnd;
                double cx = 0;
                double cy = 0;
                double rx = 0;
                double ry = 0;
                boolean ok = false;

                while(!ok) {
                    //cx
                    if (line.contains("cx=")) {
                        indexDeb = line.indexOf('x') + 3; // on skip le '=' et le '"'
                        indexEnd = line.indexOf('"', indexDeb);

                        cx = Integer.parseInt(line.substring(indexDeb, indexEnd)); // (CX)on recupere la valeur du x="..."
                    }

                    //cy
                    if (line.contains("cy=")) {
                        indexDeb = line.indexOf('y') + 3; // on skip le '=' et le '"'
                        indexEnd = line.indexOf('"', indexDeb);

                        cy = Integer.parseInt(line.substring(indexDeb, indexEnd)); // (CX)on recupere la valeur du x="..."
                    }

                    //rx
                    if (line.contains("rx=")) {
                        indexDeb = line.indexOf('r') + 4;
                        indexEnd = line.indexOf('"', indexDeb);

                        rx = Integer.parseInt(line.substring(indexDeb, indexEnd)); // (RAYON)on recupere la valeur du r="..."
                    }

                    //ry
                    if (line.contains("ry=")) {
                        if (line.contains("rx=")) {
                            indexDeb = line.indexOf('r', line.indexOf('r') + 1) + 4;
                            indexEnd = line.indexOf('"', indexDeb);
                        } else {
                            indexDeb = line.indexOf('r') + 4;
                            indexEnd = line.indexOf('"', indexDeb);
                        }

                        ry = Integer.parseInt(line.substring(indexDeb, indexEnd)); // (RAYON)on recupere la valeur du r="..."
                    }
                    //fin de la forme
                    if (line.contains("/>")) {
                        ok = true;
                    }
                    // on passe à la ligne suivante
                    else {
                        line = br.readLine();
                    }
                }
                Ellipse ellipse = new Ellipse(cx, cy, rx, ry);
                listForms.add(ellipse);
                System.out.println("cx = " + cx + " y = " + cy + " ; rx = " + rx + " ; ry = " + ry);
            }
            //si c'est un tracé
            else if(line.contains("<path")){
                int indexDeb;
                int indexEnd;
                boolean ok = false;

                while(!ok) {
                    //cx
                    if (line.contains("d=\"")) {
                        indexDeb = line.indexOf('"')+1;
                        indexEnd = line.indexOf('"', indexDeb);
                        //TODO if indexEnd = -1
                        ArrayList<Point> listPoints = new ArrayList<>();
                        while(indexDeb<indexEnd){
                            //si le point commence par M, point courant
                            if(line.charAt(indexDeb)=='m' || line.charAt(indexDeb)=='M'){
                                char temp = line.charAt(indexDeb);
                                indexDeb++;
                                String point1 = "";
                                String point2 = "";
                                while(line.charAt(indexDeb)!= ','){
                                    point1 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                indexDeb++; //pour sauter la virgule ','
                                while(line.charAt(indexDeb) != ' '){
                                    point2 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                Point p = new Point(Double.parseDouble(point1), Double.parseDouble(point2));
                                if(temp=='M')
                                    p.setM(true);
                                else
                                    p.setm(true);
                                listPoints.add(p);
                                indexDeb++;
                            }
                            if(line.charAt(indexDeb)=='l' || line.charAt(indexDeb)=='L'){
                                char temp = line.charAt(indexDeb);
                                indexDeb++;
                                if(line.charAt(indexDeb)==' ')
                                    indexDeb++;
                                String point1 = "";
                                String point2 = "";
                                while(line.charAt(indexDeb)!= ','){
                                    point1 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                indexDeb++; //pour sauter la virgule ','
                                while(line.charAt(indexDeb) != ' '){
                                    point2 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                Point p = new Point(Double.parseDouble(point1), Double.parseDouble(point2));
                                if(temp=='L')
                                    p.setL(true);
                                else
                                    p.setl(true);
                                listPoints.add(p);
                                indexDeb++;
                            }
                            if(line.charAt(indexDeb)=='h' || line.charAt(indexDeb)=='H'){
                                char temp = line.charAt(indexDeb);
                                indexDeb++;
                                if(line.charAt(indexDeb)==' ')
                                    indexDeb++;
                                String point1 = "";
                                String point2 = "";
                                while(line.charAt(indexDeb)!= ','){
                                    point1 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                indexDeb++; //pour sauter la virgule ','
                                while(line.charAt(indexDeb) != ' '){
                                    point2 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                Point p = new Point(Double.parseDouble(point1), Double.parseDouble(point2));
                                if(temp=='H')
                                    p.setH(true);
                                else
                                    p.seth(true);
                                listPoints.add(p);
                                indexDeb++;
                            }
                            if(line.charAt(indexDeb)=='v' || line.charAt(indexDeb)=='V'){
                                char temp = line.charAt(indexDeb);
                                indexDeb++;
                                if(line.charAt(indexDeb)==' ')
                                    indexDeb++;
                                String point1 = "";
                                String point2 = "";
                                while(line.charAt(indexDeb)!= ','){
                                    point1 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                indexDeb++; //pour sauter la virgule ','
                                while(line.charAt(indexDeb) != ' '){
                                    point2 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                Point p = new Point(Double.parseDouble(point1), Double.parseDouble(point2));
                                if(temp=='V')
                                    p.setV(true);
                                else
                                    p.setv(true);
                                listPoints.add(p);
                                indexDeb++;
                            }
                            if(line.charAt(indexDeb)=='z' || line.charAt(indexDeb)=='Z'){
                                Point p = new Point();
                                if(line.charAt(indexDeb)=='Z')
                                    p.setZ(true);
                                else
                                    p.setz(true);
                                listPoints.add(p);
                                indexDeb++;
                            }
                            //TODO Q et T pour les courbes
                            else{
                                String point1 = "";
                                String point2 = "";
                                while(line.charAt(indexDeb)!= ','){
                                    point1 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                indexDeb++; //pour sauter la virgule ','
                                while(line.charAt(indexDeb) != ' '){
                                    point2 += line.charAt(indexDeb);
                                    indexDeb++;
                                }
                                Point p = new Point(Double.parseDouble(point1), Double.parseDouble(point2));
                                listPoints.add(p);
                                indexDeb++;
                            }
                        }
                        ok = true;
                        listForms.add(new Trace(listPoints));
                    }
                    //fin de la forme
                    if (line.contains("/>")) {
                        ok = true;
                    }
                    // on passe à la ligne suivante
                    else {
                        line = br.readLine();
                    }
                }
                //TODO remplir l'objet path
            }
            //System.out.println(line);
        }
        br.close();
        return listForms;
    }

    //trie dans l'ordre croissant une liste de formes en fonction de la coordonnee X
    public ArrayList<Forms> listeTrieeX(ArrayList<Forms> listForms){
        ArrayList<Forms> newListForms = new ArrayList<Forms>();
        double minValue = 1000000000;
        int index = 0;
        int nbRemove = 0;

        while(newListForms.size()!=listForms.size()+nbRemove) {
            for (int i = 0; i < listForms.size(); i++) {
                if (listForms.get(i) instanceof Circle) {
                    Circle c = (Circle) listForms.get(i);
                    if (c.getLeftXPoint() < minValue) {
                        index = i;
                        minValue = c.getLeftXPoint();
                    }
                } else if (listForms.get(i) instanceof Rectangle) {
                    Rectangle r = (Rectangle) listForms.get(i);
                    if (r.getX() < minValue) {
                        index = i;
                        minValue = r.getX();
                    }
                } else if (listForms.get(i) instanceof Ellipse) {
                    Ellipse e = (Ellipse) listForms.get(i);
                    if (e.getLeftPointX() < minValue) {
                        index = i;
                        minValue = e.getLeftPointX();
                    }
                }
            }
            newListForms.add(listForms.get(index));
            listForms.remove(index);
            nbRemove++;
            minValue = 1000000000;
        }

    return newListForms;
    }

    //trie dans l'ordre croissant une liste de formes en fonction de la coordonnee X
    public ArrayList<Forms> listeTrieeY(ArrayList<Forms> listForms){
        ArrayList<Forms> newListForms = new ArrayList<Forms>();
        double minValue = 1000000000;
        int index = 0;
        int nbRemove = 0;

        while(newListForms.size()!=listForms.size()+nbRemove) {
            for (int i = 0; i < listForms.size(); i++) {
                if (listForms.get(i) instanceof Circle) {
                    Circle c = (Circle) listForms.get(i);
                    if (c.getLeftYPoint() < minValue) {
                        index = i;
                        minValue = c.getLeftYPoint();
                    }
                } else if (listForms.get(i) instanceof Rectangle) {
                    Rectangle r = (Rectangle) listForms.get(i);
                    if (r.getX() < minValue) {
                        index = i;
                        minValue = r.getX();
                    }
                } else if (listForms.get(i) instanceof Ellipse) {
                    Ellipse e = (Ellipse) listForms.get(i);
                    if (e.getLeftPointY() < minValue) {
                        index = i;
                        minValue = e.getLeftPointY();
                    }
                }
            }
            newListForms.add(listForms.get(index));
            listForms.remove(index);
            nbRemove++;
            minValue = 1000000000;
        }

        return newListForms;
    }

    public ArrayList<Forms> translationX(ArrayList<Forms> listForms){

        ArrayList<Forms> newList = new ArrayList<>();
        ArrayList<Double> listFormerX = new ArrayList<>();

        //on stocke les valeurs de la coordonnee X pour chaque forme avant modification
        for (int i = 0; i < listForms.size(); i++) {
            if (listForms.get(i) instanceof Circle) {
                Circle c = (Circle) listForms.get(i);
                listFormerX.add(c.getLeftXPoint());
            } else if (listForms.get(i) instanceof Rectangle) {
                Rectangle r = (Rectangle) listForms.get(i);
                listFormerX.add(r.getX());

            } else if (listForms.get(i) instanceof Ellipse) {
                Ellipse e = (Ellipse) listForms.get(i);
                listFormerX.add(e.getLeftPointX());
            }
        }

        //translation le plus à gauche
        for(int i = 0; i< listForms.size(); i++){
            if(listForms.get(i) instanceof Circle){
                Circle c = (Circle)listForms.get(i);
                if(i==0){
                    c.setCx(0+c.getRayon());
                    newList.add(c);
                }
                else{
                    if(listForms.get(i-1) instanceof Circle){
                        Circle cNew = (Circle)newList.get(i-1);
                        double diff = c.getCx()-listFormerX.get(i-1);
                        c.setCx(cNew.getLeftXPoint()+diff);
                        newList.add(c);
                    }
                    else if(listForms.get(i-1) instanceof Rectangle){
                        Rectangle rNew = (Rectangle) newList.get(i-1);
                        double diff = c.getCx()-listFormerX.get(i-1);
                        c.setCx(rNew.getX()+diff);
                        newList.add(c);
                    }
                    else if(listForms.get(i-1) instanceof Ellipse){
                        Ellipse eNew = (Ellipse) newList.get(i-1);
                        double diff = c.getCx()-listFormerX.get(i-1);
                        c.setCx(eNew.getLeftPointX()+diff);
                        newList.add(c);
                    }
                }
            }
            else if(listForms.get(i) instanceof Rectangle){
                Rectangle r = (Rectangle)listForms.get(i);
                if(i==0){
                    r.setX(0);
                    newList.add(r);
                }
                else{
                    if(listForms.get(i-1) instanceof Circle){
                        Circle cNew = (Circle)newList.get(i-1);
                        double diff = r.getX()-listFormerX.get(i-1);
                        r.setX(cNew.getLeftXPoint()+diff);
                        newList.add(r);
                    }
                    else if(listForms.get(i-1) instanceof Rectangle){
                        Rectangle rNew = (Rectangle) newList.get(i-1);
                        double diff = r.getX()-listFormerX.get(i-1);
                        r.setX(rNew.getX()+diff);
                        newList.add(r);
                    }
                    else if(listForms.get(i-1) instanceof Ellipse){
                        Ellipse eNew = (Ellipse) newList.get(i-1);
                        double diff = r.getX()-listFormerX.get(i-1);
                        r.setX(eNew.getLeftPointX()+diff);
                        newList.add(r);
                    }
                }
            }
            else if(listForms.get(i) instanceof Ellipse){
                Ellipse e = (Ellipse) listForms.get(i);
                if(i==0){
                    e.setCx(0+e.getRayonX());
                    newList.add(e);
                }
                else {
                    if(listForms.get(i-1) instanceof Circle){
                        Circle cNew = (Circle)newList.get(i-1);
                        double diff = e.getCx()-listFormerX.get(i-1);
                        e.setCx(cNew.getLeftXPoint()+diff);
                        newList.add(e);
                    }
                    else if(listForms.get(i-1) instanceof Rectangle){
                        Rectangle rNew = (Rectangle) newList.get(i-1);
                        double diff = e.getCx()-listFormerX.get(i-1);
                        e.setCx(rNew.getX()+diff);
                        newList.add(e);
                    }
                    else if(listForms.get(i-1) instanceof Ellipse){
                        Ellipse eNew = (Ellipse) newList.get(i-1);
                        double diff = e.getCx()-listFormerX.get(i-1);
                        e.setCx(eNew.getLeftPointX()+diff);
                        newList.add(e);
                    }
                }
            }
        }
        return newList;
    }

    public ArrayList<Forms> translationY(ArrayList<Forms> listForms){

        ArrayList<Forms> newList = new ArrayList<>();
        ArrayList<Double> listFormerY = new ArrayList<>();

        //on stocke les valeurs de la coordonnee X pour chaque forme avant modification
        for (int i = 0; i < listForms.size(); i++) {
            if (listForms.get(i) instanceof Circle) {
                Circle c = (Circle) listForms.get(i);
                listFormerY.add(c.getLeftYPoint());
            } else if (listForms.get(i) instanceof Rectangle) {
                Rectangle r = (Rectangle) listForms.get(i);
                listFormerY.add(r.getY());

            } else if (listForms.get(i) instanceof Ellipse) {
                Ellipse e = (Ellipse) listForms.get(i);
                listFormerY.add(e.getLeftPointY());
            }
        }

        //translation le plus à gauche
        for(int i = 0; i< listForms.size(); i++){
            if(listForms.get(i) instanceof Circle){
                Circle c = (Circle)listForms.get(i);
                if(i==0){
                    c.setCy(0+c.getRayon());
                    newList.add(c);
                }
                else{
                    if(listForms.get(i-1) instanceof Circle){
                        Circle cNew = (Circle)newList.get(i-1);
                        double diff = c.getCy()-listFormerY.get(i-1);
                        c.setCy(cNew.getLeftYPoint()+diff);
                        newList.add(c);
                    }
                    else if(listForms.get(i-1) instanceof Rectangle){
                        Rectangle rNew = (Rectangle) newList.get(i-1);
                        double diff = c.getCy()-listFormerY.get(i-1);
                        c.setCy(rNew.getY()+diff);
                        newList.add(c);
                    }
                    else if(listForms.get(i-1) instanceof Ellipse){
                        Ellipse eNew = (Ellipse) newList.get(i-1);
                        double diff = c.getCy()-listFormerY.get(i-1);
                        c.setCy(eNew.getLeftPointY()+diff);
                        newList.add(c);
                    }
                }
            }
            else if(listForms.get(i) instanceof Rectangle){
                Rectangle r = (Rectangle)listForms.get(i);
                if(i==0){
                    r.setY(0);
                    newList.add(r);
                }
                else{
                    if(listForms.get(i-1) instanceof Circle){
                        Circle cNew = (Circle)newList.get(i-1);
                        double diff = r.getY()-listFormerY.get(i-1);
                        r.setY(cNew.getLeftYPoint()+diff);
                        newList.add(r);
                    }
                    else if(listForms.get(i-1) instanceof Rectangle){
                        Rectangle rNew = (Rectangle) newList.get(i-1);
                        double diff = r.getY()-listFormerY.get(i-1);
                        r.setY(rNew.getY()+diff);
                        newList.add(r);
                    }
                    else if(listForms.get(i-1) instanceof Ellipse){
                        Ellipse eNew = (Ellipse) newList.get(i-1);
                        double diff = r.getY()-listFormerY.get(i-1);
                        r.setY(eNew.getLeftPointY()+diff);
                        newList.add(r);
                    }
                }
            }
            else if(listForms.get(i) instanceof Ellipse){
                Ellipse e = (Ellipse) listForms.get(i);
                if(i==0){
                    e.setCy(0+e.getRayonY());
                    newList.add(e);
                }
                else {
                    if(listForms.get(i-1) instanceof Circle){
                        Circle cNew = (Circle)newList.get(i-1);
                        double diff = e.getCy()-listFormerY.get(i-1);
                        e.setCy(cNew.getLeftYPoint()+diff);
                        newList.add(e);
                    }
                    else if(listForms.get(i-1) instanceof Rectangle){
                        Rectangle rNew = (Rectangle) newList.get(i-1);
                        double diff = e.getCy()-listFormerY.get(i-1);
                        e.setCy(rNew.getY()+diff);
                        newList.add(e);
                    }
                    else if(listForms.get(i-1) instanceof Ellipse){
                        Ellipse eNew = (Ellipse) newList.get(i-1);
                        double diff = e.getCy()-listFormerY.get(i-1);
                        e.setCy(eNew.getLeftPointY()+diff);
                        newList.add(e);
                    }
                }
            }
        }
        return newList;
    }

    public ArrayList<Forms> translation(ArrayList<Forms> listForms, double x, double y){
        ArrayList<Forms> newList = new ArrayList<>();
        for(int i=0; i<listForms.size(); i++){
            if(listForms.get(i) instanceof Circle){
                Circle c = (Circle)listForms.get(i);
                c.setCx(c.getCx()+x);
                c.setCy(c.getCy()+y);
                newList.add(c);
            }
            else if(listForms.get(i) instanceof Rectangle){
                Rectangle r = (Rectangle) listForms.get(i);
                r.setX(r.getX()+x);
                r.setY(r.getY()+y);
                newList.add(r);
            }
            else if(listForms.get(i) instanceof Ellipse){
                Ellipse e = (Ellipse) listForms.get(i);
                e.setCx(e.getCx()+x);
                e.setCy(e.getCy()+y);
                newList.add(e);
            }
        }
        return newList;
    }

    /* fait la rotation d'un point */
    public Point rotationPoint(double x, double y, double theta){
        double thetaRad = theta * Math.PI/180;
        double rx = x*Math.cos(thetaRad) - y * Math.sin(thetaRad);
        double ry = x * Math.sin(thetaRad) + y * Math.cos(thetaRad);
        return new Point(rx, ry);
    }

    /* fait la rotation des formes contenues dans une liste de Forms */
    public ArrayList<Forms> rotationForms(ArrayList<Forms> listForms, double theta){
        ArrayList<Forms> newList = new ArrayList<>();
        for(int i = 0; i<listForms.size(); i++){
            if(listForms.get(i) instanceof Circle){
                Circle c = (Circle)listForms.get(i);
                Point p = rotationPoint(c.getCx(), c.getCy(), theta);
                c.setCx(p.getX());
                c.setCy(p.getY());
                newList.add(c);
            }
            else if(listForms.get(i) instanceof Rectangle){
                Rectangle r = (Rectangle) listForms.get(i);
                Point p = rotationPoint(r.getX(), r.getY(), theta);
                r.setX(p.getX());
                r.setY(p.getY());
                newList.add(r);
            }
            else if(listForms.get(i) instanceof Ellipse){
                Ellipse e = (Ellipse) listForms.get(i);
                Point p = rotationPoint(e.getCx(), e.getCy(), theta);
                e.setCx(p.getX());
                e.setCy(p.getY());
                newList.add(e);
            }
        }
        return newList;
    }

    public ArrayList<Forms> copyListForms(ArrayList<Forms> list){
        ArrayList<Forms> copy = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            if(list.get(i) instanceof Circle){
                Circle c = (Circle)list.get(i);
                Circle copyC = new Circle(c.getCx(), c.getCy(), c.getRayon());
                copy.add(copyC);
            }
            else if(list.get(i) instanceof Rectangle){
                Rectangle r = (Rectangle) list.get(i);
                Rectangle copyR = new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight());
                copy.add(copyR);
            }
            else if(list.get(i) instanceof Ellipse){
                Ellipse e = (Ellipse) list.get(i);
                Ellipse copyE = new Ellipse(e.getCx(), e.getCy(), e.getRayonX(), e.getRayonY());
                copy.add(copyE);
            }
        }
        return copy;
    }

    //TODO
    public int nbPlaques(ArrayList<Forms> list){
        int res = 1; //nombre de plaques
        boolean plaque1 = true; //plaque en haut à gauche
        boolean plaque2 = false; //plaque en haut à droite
        boolean plaque3 = false; //plaque en bas à gauche
        boolean plaque4 = false; //plaque en bas à droite

        ArrayList<Forms> workingList = copyListForms(list);
        workingList = listeTrieeX(workingList);

        int indexRight = workingList.size()-1;
        //point le plus + droite
        if(workingList.get(indexRight) instanceof Circle){
            Circle c = (Circle)workingList.get(indexRight);
            if(c.getRightXPoint()>p1.getX() && c.getRightYPoint()<p2.getY())
                plaque2 = true;
            if(c.getRightXPoint()>p1.getX() && c.getRightYPoint()>p2.getY())
                plaque4 = true;
            if(c.getRightXPoint()<p1.getX() && c.getRightYPoint()>p2.getY())
                plaque3 = true;
        }
        else if(workingList.get(0) instanceof Rectangle){
            Rectangle r = (Rectangle) workingList.get(indexRight);
            if(r.getX()+r.getWidth()>p1.getX() && r.getY()+r.getHeight()<p2.getY())
                plaque2 = true;
            if(r.getX()+r.getWidth()>p1.getX() && r.getY()+r.getHeight()>p2.getY())
                plaque4 = true;
            if(r.getX()+r.getWidth()<p1.getX() && r.getY()+r.getHeight()>p2.getY())
                plaque3 = true;
        }
        else if(workingList.get(indexRight) instanceof Ellipse){
            Ellipse e = (Ellipse) workingList.get(indexRight);
            if(e.getRightXPoint()>p1.getX() && e.getRightYPoint()<p2.getY())
                plaque2 = true;
            if(e.getRightXPoint()>p1.getX() && e.getRightYPoint()>p2.getY())
                plaque4 = true;
            if(e.getRightXPoint()<p1.getX() && e.getRightYPoint()>p2.getY())
                plaque3 = true;
        }

        workingList = translationY(workingList);

        //point le plus bas
        if(workingList.get(indexRight) instanceof Circle){
            Circle c = (Circle)workingList.get(indexRight);
            if(c.getRightXPoint()>p1.getX() && c.getRightYPoint()<p2.getY())
                plaque2 = true;
            if(c.getRightXPoint()>p1.getX() && c.getRightYPoint()>p2.getY())
                plaque4 = true;
            if(c.getRightXPoint()<p1.getX() && c.getRightYPoint()>p2.getY())
                plaque3 = true;
        }
        else if(workingList.get(0) instanceof Rectangle){
            Rectangle r = (Rectangle) workingList.get(indexRight);
            if(r.getX()+r.getWidth()>p1.getX() && r.getY()+r.getHeight()<p2.getY())
                plaque2 = true;
            if(r.getX()+r.getWidth()>p1.getX() && r.getY()+r.getHeight()>p2.getY())
                plaque4 = true;
            if(r.getX()+r.getWidth()<p1.getX() && r.getY()+r.getHeight()>p2.getY())
                plaque3 = true;
        }
        else if(workingList.get(indexRight) instanceof Ellipse){
            Ellipse e = (Ellipse) workingList.get(indexRight);
            if(e.getRightXPoint()>p1.getX() && e.getRightYPoint()<p2.getY())
                plaque2 = true;
            if(e.getRightXPoint()>p1.getX() && e.getRightYPoint()>p2.getY())
                plaque4 = true;
            if(e.getRightXPoint()<p1.getX() && e.getRightYPoint()>p2.getY())
                plaque3 = true;
        }

        //on compte le nombre de plaques
        if(plaque2)
            res++;
        if(plaque3)
            res++;
        if(plaque4)
            res++;

        return res;
    }

    public ArrayList<Double> transXY(ArrayList<Forms> list){
        ArrayList<Double> res = new ArrayList<>();
        double x = 0; //translation X
        double y = 0; //translation Y

        ArrayList<Forms> workingList = copyListForms(list);
        workingList = listeTrieeX(workingList);

        //on cherche de combien on doit décaler en X
        if(workingList.get(0) instanceof Circle){
            Circle c = (Circle)workingList.get(0);
            if(c.getLeftXPoint()<0)
                x = -c.getLeftXPoint();
        }
        else if(workingList.get(0) instanceof Rectangle){
            Rectangle r = (Rectangle) workingList.get(0);
            if(r.getX()<0)
                x = -r.getX();
        }
        else if(workingList.get(0) instanceof Ellipse){
            Ellipse e = (Ellipse) workingList.get(0);
            if(e.getLeftPointX()<0)
                x = -e.getLeftPointX();
        }

        workingList = listeTrieeY(workingList);

        //on cherche de combien on doit décaler en Y
        if(workingList.get(0) instanceof Circle){
            Circle c = (Circle)workingList.get(0);
            if(c.getLeftYPoint()<0)
                y = -c.getLeftYPoint();
        }
        else if(workingList.get(0) instanceof Rectangle){
            Rectangle r = (Rectangle) workingList.get(0);
            if(r.getY()<0)
                y = -r.getY();
        }
        else if(workingList.get(0) instanceof Ellipse){
            Ellipse e = (Ellipse) workingList.get(0);
            if(e.getLeftPointY()<0)
                y = -e.getLeftPointY();
        }

        res.add(x);
        res.add(y);

        return res;
    }

    public void algo(){
        ArrayList<Forms> listForms = new ArrayList<>(); //liste contenant les données des formes du fichier svg
        ArrayList<Forms> workingList = new ArrayList<>(); //liste de travail
        ArrayList<Forms> solution = new ArrayList<>(); //la solution optimale courante

        /*on lit le SVG file*/
        try {
            listForms = readSVG(getPathnamefile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* --- on met la figure le plus en haut à gauche possible --- */
        listForms = listeTrieeX(listForms);//on trie la liste en fonction du point le plus a gauche
        listForms = translationX(listForms);//Translation le plus à gauche possible
        listForms = listeTrieeY(listForms);//on trie la liste en fonction du point le plus a gauche
        listForms = translationY(listForms);//Translation le plus haut possible

        solution = copyListForms(listForms);//on sauvegarde cette solution
        workingList = copyListForms(solution);

        int nbPlaques = nbPlaques(workingList);

        if(nbPlaques>1){
            workingList = copyListForms(solution);
            int i = 1; //degré de rotation
            boolean end = false;
            while(i<360 && !end){
                workingList  = rotationForms(workingList, 1);
                //TODO liste à retenir pour faire la translation sur le fichier directement?
                ArrayList<Double> trans = transXY(workingList);
                if(trans.get(0) != 0 || trans.get(1) != 0) {
                    workingList = translation(workingList, trans.get(0), trans.get(1));
                }
                if(nbPlaques(workingList)==1){
                    nbPlaques = nbPlaques(workingList);
                    solution = workingList;
                    listForms = translation(listForms, trans.get(0), trans.get(1));
                    this.rotate = i;
                    System.out.println("i1 = " + i);
                    end = true;
                }
                if(nbPlaques(workingList)<nbPlaques){
                    nbPlaques = nbPlaques(workingList);
                    this.rotate = i;
                    System.out.println("i2 = " + i);
                    solution = workingList;
                }
                i++;
            }
        }

        //TODO modifier la réécriture du fichier pour écrire la bonne translation et le bon degré de rotation grace aux resultats obtenus ci-dessus

        /* --- FIN TEST --- */
        //on écrit le nouveau SVG file
        try {
            WriteNewFile(listForms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*affichage de la fenêtre*/
    /*public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, 500, 1000, 500);
        g.drawLine(500, 0, 500, 1000);

        algo();
    }*/

    //TODO ecrire rotation
    public void WriteNewFile(ArrayList<Forms> listForms) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(pathnamefile));
        String line;

        File f = new File("C:\\Users\\admin\\Desktop\\newFile.svg");
        FileWriter fw = new FileWriter(f);
        int index = 0;
        //on lit le fichier ligne par ligne
        while ((line = br.readLine()) != null) {
            //si on on tombe sur une forme on recopie une forme
            if(line.contains("<rec") || line.contains("<circle") || line.contains("<ellipse")){
                if(index==0){
                    if(getRotate()!=0)
                        fw.write("<g transform = \"" + "rotate("+getRotate()+")\">\n");
                }
                fw.write(listForms.get(index++).toString());
                //si la forme est spécifiée sur le plusieurs lignes
                while(!line.contains("/>"))
                    line = br.readLine();
                if(index==listForms.size())
                    fw.write("</g>\n");
            }
            else
                fw.write(line+"\n"); //on recopie la ligne du fichier
        }
        fw.close();
        br.close();
    }

    public static void main(String[] args) { }
}
