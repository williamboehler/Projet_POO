import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.NumberFormat;
import java.util.regex.Pattern;

public class GUI extends JFrame {

    private static Boolean goodFile(String pathname){
        return Pattern.matches(".*\\.svg", pathname);
    }


    public GUI(){
        this.setTitle("Découpe de formes");
        this.setSize(325, 175);

        JPanel jp0 = new JPanel();

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();

        JTextField jt = new JTextField();
        jt.setEditable(false);

        JButton jbChoose = new JButton("...");

        jp3.setLayout(new FlowLayout());


        /* pour vérifier que l'user ne rentre que des chiffres */
        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setValueClass(Long.class);
        numberFormatter.setAllowsInvalid(false);


        JLabel jlDim = new JLabel("Dimension des plaques (x/y): ");
        JFormattedTextField jtDimX = new JFormattedTextField(numberFormatter);
        jtDimX.setText("500");
        JFormattedTextField jtDimY = new JFormattedTextField(numberFormatter);
        jtDimY.setText("500");

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
                if(jt.getText().length()!=0 && jtDimX.getText().length()!=0 && jtDimY.getText().length()!=0) {
                    Test jc = new Test(jt.getText(), Double.parseDouble(jtDimX.getText()), Double.parseDouble(jtDimY.getText()));
                    jc.algo();
                }
            }
        });

        jp1.add(jL1);
        jp1.add(jt);
        jp1.add(jbChoose);
        jp2.add(jbValide);
        jp3.add(jlDim);
        jp3.add(jtDimX);
        jp3.add(jtDimY);
        jp0.add(jp1, BorderLayout.NORTH);
        jp0.add(jp3, BorderLayout.CENTER);
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
        this.setResizable(false);
    }
}
