package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Forme {
    //Attributs
    private double x;
    private double y;

    private int width;
    private int height;

    private String text;
    Color clr;


    //Constantes



    //Constructeur
    /**
     * Constructeur de la forme avec tout les params
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     */
    public Forme(double x, double y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    /**
     * Constructeur de la forme avec la position
     * @param x
     * @param y
     */
    public Forme(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.text = "";
    }

    /**
     * Constructeur de la forme avec la position et la taille
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Forme(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "";
    }

    //Getters et Setters

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    //Methodes
    /**
     * Verifie qu'un point est dans la forme
     * @param x abscisse d'un point
     * @param y ordonnee d'un point
     * @return vrai si le point est dans la forme
     */
    public abstract boolean estDedans(double x, double y);

    /**
     * Dessine la forme
     * @param gc GraphicsContext du canvas de la vue
     */
    public abstract void draw(GraphicsContext gc);
}
