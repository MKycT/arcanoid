import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Objects;
import javax.swing.ImageIcon;

public class Paddle extends Sprite implements Parameters {
    String paddle = "pics/paddle.png";
    int dx;

    public Paddle() {
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(this.paddle)));
        this.image = ii.getImage();
        this.width = this.image.getWidth((ImageObserver)null);
        this.heigth = this.image.getHeight((ImageObserver)null);
        this.resetState();
    }

    public void move() {
        this.x += this.dx;
        if (this.x <= 2) {
            this.x = 2;
        }

        if (this.x >= 660) {
            this.x = 660;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 37) {
            this.dx = -5;
        }

        if (key == 39) {
            this.dx = 5;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 37) {
            this.dx = 0;
        }

        if (key == 39) {
            this.dx = 0;
        }

    }

    public void resetState() {
        this.x = 300;
        this.y = 360;
    }
}