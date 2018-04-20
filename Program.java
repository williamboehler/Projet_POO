import java.awt.*;

public class Program {
    public static void main(String[] args) {
        Test jc = new Test();
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(1000,1000));
        GUIHelper.showOnFrame(jc,"Test");
    }
}
