import java.awt.image.ImageObserver;
import java.util.Objects;
import javax.swing.ImageIcon;

public class Wall extends Sprite implements Parameters {
    String wall = "pics/wall.png";

    public Wall() {
        ImageIcon ii = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(this.wall)));
        this.image = ii.getImage();
        this.width = this.image.getWidth((ImageObserver)null);
        this.heigth = this.image.getHeight((ImageObserver)null);
        this.resetState();
    }

    public void resetState() {
        this.x = 790;
        this.y = 0;
    }
}