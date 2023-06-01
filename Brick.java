import java.awt.image.ImageObserver;
import java.util.Objects;
import javax.swing.ImageIcon;

public class Brick extends Sprite {
    String brickie;
    int level;
    boolean destroyed;


    public Brick(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;
        this.updateImage();
        this.destroyed = false;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void updateImage() {
        this.brickie = "pics/brick" + this.level + ".png";
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(this.brickie)));
        this.image = ii.getImage();
        this.width = this.image.getWidth((ImageObserver)null);
        this.heigth = this.image.getHeight((ImageObserver)null);
    }

    public void hit() {
        this.setDestroyed(true);
    }
}