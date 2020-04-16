package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class TriangleIsocele extends Forme {
    //Constructeurs
    public TriangleIsocele(){
        super();
    }

    public TriangleIsocele(double x, double y, double width, double height, String text) {
        super(x, y, width, height, text);
    }

    public TriangleIsocele(double x, double y) {
        super(x, y);
    }

    public TriangleIsocele(double x, double y, double width, double height) {
        super(x, y, width, height);
    }


    //Methodes
    @Override
    public boolean estDedans(double x, double y) {

        double abc = this.calculAire(this.getX(), this.getY(), this.getWidth(), this.getHeight(),this.getX() - (this.getWidth() - this.getX()), this.getHeight() );
        double axb = this.calculAire(this.getX(), this.getY(), x, y, this.getWidth(), this.getHeight());
        double axc = this.calculAire(this.getX(), this.getY(), x, y, this.getX() - (this.getWidth() - this.getX()), this.getHeight());
        double bxc = this.calculAire(this.getWidth(), this.getHeight(), x, y, this.getX() - (this.getWidth() - this.getX()), this.getHeight());

        System.out.println("abc "+ abc);
        System.out.println("axb "+axb);
        System.out.println(" axc "+axc);
        System.out.println(" bxc "+bxc);
        System.out.println(" somme des trois "+(axb+axc+bxc));

        return abc == (axb+axc+bxc);
    }

    public double calculAire(double xA, double yA, double xB, double yB, double xC, double yC){
        return Math.abs((xB-xA)*(yC-yA)-(xC-xA)*(yB-yA));
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());

        gc.fillPolygon(new double[]{this.getX(), this.getWidth(), this.getX() - (this.getWidth() - this.getX())}, new double[]{this.getY(), this.getHeight(), this.getHeight()},3);
        gc.strokePolygon(new double[]{this.getX(), this.getWidth(), this.getX() - (this.getWidth() - this.getX())}, new double[]{this.getY(), this.getHeight(), this.getHeight()},3);

        System.out.println(this.getX());
        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return null;
    }


}
