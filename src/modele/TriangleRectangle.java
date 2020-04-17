package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TriangleRectangle extends Forme {
    //Constructeurs
    public TriangleRectangle(){
        super();
    }

    public TriangleRectangle(double x, double y, double width, double height, String text) {
        super(x, y, width, height, text);
    }

    public TriangleRectangle(double x, double y) {
        super(x, y);
    }

    public TriangleRectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public TriangleRectangle(double x, double y, double w, double h, String text, Color clr, boolean drawable, double epaisseurBord, Color clrBord) {
        super(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord);
    }


    //Methodes
    @Override
    public boolean estDedans(double x, double y) {
        double abc = this.calculAire(this.getX(), this.getY(), this.getWidth(), this.getHeight(),this.getX() - (this.getWidth() - this.getX()), this.getHeight() );
        double axb = this.calculAire(this.getX(), this.getY(), x, y, this.getWidth(), this.getHeight());
        double axc = this.calculAire(this.getX(), this.getY(), x, y, this.getX() - (this.getWidth() - this.getX()), this.getHeight());
        double bxc = this.calculAire(this.getWidth(), this.getHeight(), x, y, this.getX() - (this.getWidth() - this.getX()), this.getHeight());

        if(this.getX()<= this.getWidth()){
            return x>=this.getX() && x <= this.getWidth() && (abc == (axb+axc+bxc));
        }
        return x <=this.getX() && x >= this.getWidth() &&abc == (axb+axc+bxc);
    }

    public double calculAire(double xA, double yA, double xB, double yB, double xC, double yC){
        return Math.abs((xB-xA)*(yC-yA)-(xC-xA)*(yB-yA));
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());
        gc.fillPolygon(new double[]{this.getX(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getHeight(), this.getHeight()},3);

        gc.setFill(this.getClrBord());
        gc.setLineWidth(this.getEpaisseurBord());
        gc.strokePolygon(new double[]{this.getX(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getHeight(), this.getHeight()},3);

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return null;
    }
}
