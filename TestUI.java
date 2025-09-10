import javax.swing.JFrame;
public class TestUI {
    public static void main(String[] args) {
       JFrame windows = new JFrame("My First windows");
       windows.setSize(500,700);
       windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       windows.setVisible(true);
    }
}
