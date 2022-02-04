import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Screen s = new Screen();
        s.setContentPane(s.panelMain);
        s.setTitle("7 inch Screen Tester");
        s.setSize(800, 700);
        s.setVisible(true);
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
