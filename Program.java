import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class Program {

    private static Boolean goodFile(String pathname){
        return Pattern.matches(".*\\.svg", pathname);
    }

    public static void main(String[] args) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        Test jc;
        int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();

                //si le fichier se termine par .svg
                if(goodFile(selectedFile.getAbsolutePath())) {
                    jc = new Test(selectedFile.getAbsolutePath());
                    jc.setBackground(Color.WHITE);
                    jc.setPreferredSize(new Dimension(1000, 1000));
                    GUIHelper.showOnFrame(jc, "Test");
                }
                //sinon message d'erreur
                else {
                    Frame frame = new Frame();
                    JOptionPane.showMessageDialog(frame,
                            "Vous devez sélectionner un fichier SVG.",
                            "Fichier inaproprié",
                            JOptionPane.ERROR_MESSAGE);
                    exit(0);
                }
            }
    }
}
