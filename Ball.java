import java.awt.image.ImageObserver;
import java.util.Objects;
import javax.swing.ImageIcon;

public class Ball extends Sprite implements Parameters {
    private int xdir = 3;
    private int ydir = -3;
    protected String ball = "pics/ball.png";

    public Ball() {
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(this.ball)));
        this.image = ii.getImage();
        this.width = this.image.getWidth((ImageObserver)null);
        this.heigth = this.image.getHeight((ImageObserver)null);
        this.resetState();
    }

    public void move() {
        this.x += this.xdir;
        this.y += this.ydir;
        if (this.x <= 0) {
            this.setXDir(3);
        }

        if (this.x >= 750) {
            this.setXDir(-3);
        }

        if (this.y <= 0) {
            this.setYDir(3);
        }

    }

    public void resetState() {
        this.x = 420;
        this.y = 340;
    }

    public void setXDir(int x) {
        this.xdir = x;
    }

    public void setYDir(int y) {
        this.ydir = y;
    }

    public int getYDir() {
        return this.ydir;
    }
}
