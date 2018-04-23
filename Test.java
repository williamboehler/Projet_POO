import models.*;
import models.Point;
import models.Rectangle;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Test extends JPanel {

    private String pathnamefile;

    public Test(String file) {
        this.pathnamefile = file;
    }

    public String getPathnamefile() {
        return pathnamefile;
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

    public ArrayList<Forms> listeTriee(ArrayList<Forms> listForms){
        ArrayList<Forms> newListForms = new ArrayList<Forms>();
        double minValue = 100000;
        int index = 0;
        int nbRemove = 0;
        listForms.toString();

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
                    if (e.getLeftPoint() < minValue) {
                        index = i;
                        minValue = e.getLeftPoint();
                    }
                }
            }
            newListForms.add(listForms.get(index));
            listForms.remove(index);
            nbRemove++;
            minValue = 100000;
        }

    return newListForms;
    }

    //TODO trouver l'alogirthme de placement des formes
    public ArrayList<Forms> translation(ArrayList<Forms> listForms){

        ArrayList<Forms> newList = new ArrayList<>();
        ArrayList<Double> listFormerX = new ArrayList<>();

        for (int i = 0; i < listForms.size(); i++) {
            if (listForms.get(i) instanceof Circle) {
                Circle c = (Circle) listForms.get(i);
                listFormerX.add(c.getCx());
            } else if (listForms.get(i) instanceof Rectangle) {
                Rectangle r = (Rectangle) listForms.get(i);
                listFormerX.add(r.getX());

            } else if (listForms.get(i) instanceof Ellipse) {
                Ellipse e = (Ellipse) listForms.get(i);
                listFormerX.add(e.getCx());
            }
        }

        for(int i = 0; i< listForms.size(); i++){
            if(listForms.get(i) instanceof Circle){
                Circle c = (Circle)listForms.get(i);
                if(i==0){
                    c.setCx(0);
                    newList.add(c);
                }
                else{
                    if(listForms.get(i-1) instanceof Circle){
                        Circle cNew = (Circle)newList.get(i-1);
                        double diff = c.getCx()-listFormerX.get(i-1);
                        c.setCx(cNew.getCx()+diff);
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
                        c.setCx(eNew.getCx()+diff);
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
                        r.setX(cNew.getCx()+diff);
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
                        r.setX(eNew.getCx()+diff);
                        newList.add(r);
                    }
                }
            }
            else if(listForms.get(i) instanceof Ellipse){
                Ellipse e = (Ellipse) listForms.get(i);
                if(i==0){
                    e.setCx(0);
                    newList.add(e);
                }
                else {
                    if(listForms.get(i-1) instanceof Circle){
                        Circle cNew = (Circle)newList.get(i-1);
                        double diff = e.getCx()-listFormerX.get(i-1);
                        e.setCx(cNew.getCx()+diff);
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
                        e.setCx(eNew.getCx()+diff);
                        newList.add(e);
                    }
                }
            }
        }
        return newList;
    }

    /*affichage de la fenêtre*/
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, 500, 1000, 500);
        g.drawLine(500, 0, 500, 1000);


        ArrayList<Forms> listForms = null;
        try {
            listForms = readSVG(getPathnamefile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //on trie la liste en fonction du point le plus a gauche
        listForms = listeTriee(listForms);
        //ligne qui s'occupe de déplacer la forme. A retirer pour voir la forme de base !
        listForms = translation(listForms);

        for(int i = 0; i< listForms.size(); i++){
            if(listForms.get(i) instanceof Circle){
                Circle c = (Circle)listForms.get(i);
                g.fillOval((int)c.getCx(), (int)c.getCy(), (int)c.getRayon(), (int)c.getRayon());
            }
            else if(listForms.get(i) instanceof Rectangle){
                Rectangle r = (Rectangle)listForms.get(i);
                g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
            }
            else if(listForms.get(i) instanceof Ellipse){
                Ellipse e = (Ellipse)listForms.get(i);
                g.fillOval((int)e.getCx(), (int)e.getCy(), (int)e.getRayonX(), (int)e.getRayonY());
            }
            else if(listForms.get(i) instanceof Trace){
                Trace t = (Trace)listForms.get(i);
                ArrayList<Point> listPoints = t.getListPoints();
                for(int j = 0 ; j<listPoints.size(); j++)
                    g.drawLine((int)listPoints.get(i).getX(), (int)listPoints.get(i).getY(), (int)listPoints.get(j++).getX(), (int)listPoints.get(j++).getY());
            }
        }
    }

    // recopie le fichier (on ne travaille pas sur le fichier d'origine pour la réécriture après optimisation)
    public static String copyFile(File source){ //Methode permettant la copie d'un fichier
        File destination = new File("copyFile.svg");

        // Declaration des flux
        java.io.FileInputStream sourceFile = null;
        java.io.FileOutputStream destinationFile = null;
        try {
            // Création du fichier :
            destination.createNewFile();
            // Ouverture des flux
            sourceFile = new java.io.FileInputStream(source);
            destinationFile = new java.io.FileOutputStream(destination);
            // Lecture par segment de 0.5Mo
            byte buffer[]=new byte[512*1024];
            int nbLecture;
            while( (nbLecture = sourceFile.read(buffer)) != -1 ) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch( java.io.FileNotFoundException f ) {
        } catch( java.io.IOException e ) {
        } finally {
            try {
                sourceFile.close();
            } catch(Exception e) { }
            try {
                destinationFile.close();
            } catch(Exception e) { }
        }
        return destination.getPath();
    }

    //TODO faire une fonction qui modifie la coipie du fichier svg avec les nouvelles données après optimisation

    public static void main(String[] args) { }
}
