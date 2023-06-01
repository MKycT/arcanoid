import java.awt.*;

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int heigth;
    protected Image image;

    public Sprite() {
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.heigth;
    }

    Image getImage() {
        return this.image;
    }

    Rectangle getRect() {
        return new Rectangle(this.x, this.y, this.image.getWidth(null), this.image.getHeight(null));
    }
}
