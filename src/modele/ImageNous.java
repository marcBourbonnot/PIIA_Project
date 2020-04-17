package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ImageNous extends Forme {

    private Image img;

    public ImageNous() {
        super();
    }

    public ImageNous(Image img) {
        super(10, 10, img.getWidth(), img.getHeight());

        this.img = img;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    @Override
    public boolean estDedans(double x, double y) {
        return x >= this.getX() && x <= this.getWidth() && y >= this.getY() && y <= this.getHeight();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.img, this.getX(), this.getY());
    }

    @Override
    public Point2D getCenter() {
        return new Point2D((this.getWidth() - this.getX()) / 2, (this.getHeight() - this.getY()) / 2);
    }

    @Override
    public String exportValuesFormes() {
        return "";
    }
}
