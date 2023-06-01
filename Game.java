import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class Game extends JFrame {
    private Main parent;
    private Score scores;

    public Game(final Main parent, Score scores) {
        this.parent = parent;
        this.scores = scores;
        scores.setCurrentScore(0);
        this.add(new Board(scores));
        this.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
                parent.setVisible(true);
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });

        this.setTitle("Арканоид");
        this.setDefaultCloseOperation(2);
        this.setSize(800, 600);
        this.setLocationRelativeTo((Component)null);
        this.setIgnoreRepaint(true);
        this.setResizable(false);
        this.setVisible(true);

    }
}
