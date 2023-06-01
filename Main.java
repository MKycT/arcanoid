import java.awt.Component;
import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;

public class Main extends JFrame  {
    Main() {
        Score scores = new Score();
        this.add(new MenuPanel(this, scores));
        this.setTitle("Главное меню");
        this.setDefaultCloseOperation(3);
        this.setSize(800, 600);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> (new Main()).setVisible(true));
    }
}