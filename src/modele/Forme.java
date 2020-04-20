package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Forme {

    //Attributs
    private double x = -1;
    private double y = -1;

    private double width = -1;
    private double height = -1;

    private String text = "";
    private Color clr = Color.GRAY;

    private boolean drawable = false;

    private double epaisseurBord = 1;
    private Color clrBord = Color.BLACK;


    //Constantes


    //Constructeur

    /**
     * Constructeur de la forme sans params
     */
    public Forme() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.text = "";
        this.drawable = false;
    }

    /**
     * Constructeur avec tous les parmetres
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     * @param clr
     * @param drawable
     * @param epaisseurBord
     * @param clrBord
     */
    public Forme(double x, double y, double width, double height, String text, Color clr, boolean drawable, double epaisseurBord, Color clrBord) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.clr = clr;
        this.drawable = drawable;
        this.epaisseurBord = epaisseurBord;
        this.clrBord = clrBord;
    }

    /**
     * Constructeur de la forme
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     */
    public Forme(double x, double y, double width, double height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.drawable = true;
    }

    /**
     * Constructeur de la forme avec la position
     *
     * @param x
     * @param y
     */
    public Forme(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
        this.text = "";
        this.drawable = true;
    }

    /**
     * Constructeur de la forme avec la position et la taille
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Forme(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "";
        this.drawable = true;
    }

    //Getters et Setters

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getClr() {
        return clr;
    }

    public void setClr(Color clr) {
        this.clr = clr;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }

    public double getEpaisseurBord() {
        return epaisseurBord;
    }

    public void setEpaisseurBord(double epaisseurBord) {
        this.epaisseurBord = epaisseurBord;
    }

    public Color getClrBord() {
        return clrBord;
    }

    public void setClrBord(Color clrBord) {
        this.clrBord = clrBord;
    }


    //Methodes

    /**
     * Verifie qu'un point est dans la forme
     *
     * @param x abscisse d'un point
     * @param y ordonnee d'un point
     * @return vrai si le point est dans la forme
     */
    public abstract boolean estDedans(double x, double y);

    /**
     * Dessine la forme
     *
     * @param gc GraphicsContext du canvas de la vue
     */
    public abstract void draw(GraphicsContext gc);

    public abstract Point2D getCenter();

    public String coordsToString() {
        return this.getClass().getName() + " x: " + this.x + " y: " + this.y + " w: " + this.width + " h: " + this.height;
    }

    public String exportValuesFormes() {
        return this.getX() + " " + this.getY() + " " + this.getWidth() + " " + this.getHeight() + " \"" + this.getText().replace(" ", "/*/") + "\" " + this.getClr() + " " + this.drawable + " " + this.getEpaisseurBord() + " " + this.getClrBord();
    }

    public void diminuerTailleBord(double value) {
        if (this.getEpaisseurBord() - value >= 0) {
            this.setEpaisseurBord(this.getEpaisseurBord() - value);
        }
    }

    public void augmenterTailleBord(double value) {
        this.setEpaisseurBord(this.getEpaisseurBord() + value);
    }
}
