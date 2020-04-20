package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ImageNous extends Forme {

    private Image img;

    public ImageNous() {
        super();
    }

    public ImageNous(Image img) {
        super(10, 10, img.getWidth(), img.getHeight());

        this.img = img;
    }

    public ImageNous(Image img, double x, double y, double w, double h, String text, Color clr, boolean drawable, double epaisseurBord, Color clrBord) {
        super(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord);
        this.img = img;
    }

    /**
     * Renvoie l'image
     *
     * @return
     */
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    /**
     * Teste si la souris est sur l'image
     *
     * @param x abscisse d'un point
     * @param y ordonnee d'un point
     * @return
     */
    @Override
    public boolean estDedans(double x, double y) {
        return x >= this.getX() && x <= this.getWidth() && y >= this.getY() && y <= this.getHeight();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.img, this.getX(), this.getY());

        gc.setStroke(this.getClrBord());
        gc.setLineWidth(this.getEpaisseurBord());
        gc.strokePolygon(new double[]{this.getX(), this.getX() + this.img.getWidth(), this.getX() + this.img.getWidth(), this.getX()}, new double[]{this.getY(), this.getY(), this.getY() + this.img.getHeight(), this.getY() + this.img.getHeight()}, 4);
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
