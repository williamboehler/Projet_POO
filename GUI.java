import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.regex.Pattern;

public class GUI extends JFrame {

    private static Boolean goodFile(String pathname){
        return Pattern.matches(".*\\.svg", pathname);
    }


    public GUI(){
        this.setTitle("Découpe de formes");
        this.setSize(300, 200);

        String path = "";
        JPanel jp0 = new JPanel();
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JTextField jt = new JTextField();
        jt.setEditable(false);
        JFileChooser jfc;
        JButton jbChoose = new JButton("...");
        JButton jbValide = new JButton("valider");

        jt.setColumns(13);
        jp1.setLayout(new FlowLayout());
        JLabel jL1 = new JLabel("Fichier SVG : ", JLabel.LEFT);

        jbChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ok");
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();

                    //si le fichier se termine par .svg
                    if(goodFile(selectedFile.getAbsolutePath())) {
                        jt.setText(selectedFile.getAbsolutePath());
                    }
                    //sinon message d'erreur
                    else {
                        Frame frame = new Frame();
                        JOptionPane.showMessageDialog(frame,
                                "Vous devez sélectionner un fichier SVG.",
                                "Fichier inaproprié",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        jbValide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jt.getText().length()!=0) {
                    Test jc = new Test(jt.getText());
                    jc.algo();
                }
            }
        });

        jp1.add(jL1);
        jp1.add(jt);
        jp1.add(jbChoose);
        jp2.add(jbValide);
        jp0.add(jp1, BorderLayout.NORTH);
        jp0.add(jp2, BorderLayout.SOUTH);
        this.setContentPane(jp0);
        this.setVisible(true);


        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        this.addWindowListener(wa);
        this.setLocationRelativeTo(null);
    }
}
