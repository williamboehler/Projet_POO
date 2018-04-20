import models.Circle;
import models.Forms;
import models.Rectangle;
import models.Ellipse;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Test extends JPanel {

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
            //System.out.println(line);
        }
        br.close();
        return listForms;
    }

    //TODO trouver l'alogirthme de placement des formes
    public ArrayList<Forms> translation(ArrayList<Forms> listForms){

        for(int i = 0; i< listForms.size(); i++){
            if(listForms.get(i) instanceof Circle){
                Circle c = (Circle)listForms.get(i);
            }
            else if(listForms.get(i) instanceof Rectangle){
                Rectangle r = (Rectangle)listForms.get(i);
                r.setX(0);
                r.setY(0);
                listForms.set(i, r);
            }
            else if(listForms.get(i) instanceof Ellipse){
                Ellipse e = (Ellipse)listForms.get(i);
            }
        }
        return listForms;
    }

    /*affichage de la fenêtre*/
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, 500, 1000, 500);
        g.drawLine(500, 0, 500, 1000);


        ArrayList<Forms> listForms = null;
        try {
            listForms = readSVG("D:/Images/image.svg");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
