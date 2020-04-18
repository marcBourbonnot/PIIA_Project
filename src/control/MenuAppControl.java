package control;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import modele.*;
import vue.MenuApp;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class MenuAppControl {
    private Control ctrl;

    private MenuApp mapp;
    private boolean dessinMode;
    private boolean allowModif;
    private Model save;

    public MenuAppControl(Control c) {
        this.ctrl = c;

        this.mapp = new MenuApp();
        this.dessinMode = false;
        this.allowModif = false;
    }

    public boolean isDessinMode() {
        return dessinMode;
    }

    public void setDessinMode(boolean dessinMode) {
        this.dessinMode = dessinMode;
    }

    public void addActions() {
        //Menu fichier
        ObservableList<MenuItem> fichier = mapp.getMenuBar().getMenus().get(0).getItems();
        fichier.get(0).setOnAction(e -> {
            this.ctrl.comfirmNew(e);
        });
        fichier.get(1).setOnAction(e -> {
            System.out.println("Ouvrir");
            FileChooser fc = new FileChooser();
            fc.setTitle("Ouvrir");

            FileChooser.ExtensionFilter filterDF = new FileChooser.ExtensionFilter("DF files (*.df)", "*df");

            fc.getExtensionFilters().add(filterDF);

            File openedFile = fc.showOpenDialog(fichier.get(1).getParentPopup().getOwnerWindow());

            if (openedFile != null) {
                try {
                    InputStream fileIn = new FileInputStream(openedFile.getPath());
                    InputStreamReader fileInRead = new InputStreamReader(fileIn);
                    BufferedReader buff = new BufferedReader(fileInRead);

                    String line;
                    ArrayList<Forme> open = new ArrayList<>();

                    while ((line = buff.readLine()) != null){
                        String[] parts = line.split(" ");

                        double x = Double.valueOf(parts[1]);
                        double y = Double.valueOf(parts[2]);

                        double w = Double.valueOf(parts[3]);
                        double h = Double.valueOf(parts[4]);

                        String text = parts[5].replace("\"", "").replace("/*/", " ");
                        Color clr = Color.GRAY;

                        boolean drawable = Boolean.valueOf(parts[7]);

                        double epaisseurBord = Double.valueOf(parts[8]);
                        Color clrBord = Color.BLACK;

                        switch (parts[0]) {
                            case "Rectangle":
                                open.add(new Rectangle(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                                break;
                            case "Ligne":
                                open.add(new Ligne(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                                break;
                            case "TriangleIsocele":
                                open.add(new TriangleIsocele(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                                break;
                            case "TriangleRectangle":
                                open.add(new TriangleRectangle(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                                break;
                            case "ZoneTexte":
                                open.add(new ZoneTexte(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                                break;
                            case "Ellipse":
                                open.add(new Ellipse(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                                break;
                        }
                    }
                    buff.close();

                    this.ctrl.getMdl().setFormes(open);
                    this.ctrl.getCvsCtrl().draw();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        fichier.get(2).setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Sauvegarder");
            fc.setInitialFileName("Dessin");

            FileChooser.ExtensionFilter filterDF = new FileChooser.ExtensionFilter("DF files (*.df)", "*.df");

            fc.getExtensionFilters().add(filterDF);

            File savedFile = fc.showSaveDialog(fichier.get(1).getParentPopup().getOwnerWindow());

            if(savedFile != null){
                try {
                    FileWriter fileWrite = new FileWriter(savedFile);
                    this.ctrl.getMdl().getFormes().forEach(str -> {
                        try {
                            fileWrite.write(str.getClass().getSimpleName() + " " + str.exportValuesFormes() + "\n");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    fileWrite.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Enregistrer");

        });
        fichier.get(3).setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Export image");
            fc.setInitialFileName("monDessinEnImage");

            FileChooser.ExtensionFilter extFilterPng = new FileChooser.ExtensionFilter("png files : (*.png)", ".png");
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.jpg","*.JPEG", "*.jpeg");

            fc.getExtensionFilters().addAll(extFilterPng, extFilterJPG);

            File file = fc.showSaveDialog(fichier.get(1).getParentPopup().getOwnerWindow());

            if (file != null) {
                try {
                    WritableImage wImg = new WritableImage((int) this.ctrl.getCvsCtrl().getZoneDessin().getDrawArea().getWidth(), (int) this.ctrl.getCvsCtrl().getZoneDessin().getDrawArea().getHeight());
                    this.ctrl.getCvsCtrl().getZoneDessin().getDrawArea().snapshot(null, wImg);
                    RenderedImage rImg = SwingFXUtils.fromFXImage(wImg, null);

                    ImageIO.write(rImg, "png", file);

                    Alert alertDone = new Alert(Alert.AlertType.INFORMATION, "Export effectué avec succès !", ButtonType.YES);
                    alertDone.setTitle("Export");
                    alertDone.showAndWait();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Alert alertFail = new Alert(Alert.AlertType.ERROR, "Une erreur est survenue lors de l'export !", ButtonType.YES);
                    alertFail.setTitle("Export");
                    alertFail.showAndWait();
                }
            }


        });
        fichier.get(4).setOnAction(e -> this.ctrl.quit(e));

        //Menu edition
        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(0).setOnAction(e -> this.undo());
        edition.get(1).setOnAction(e -> this.ctrl.getMdl().copier());
        edition.get(2).setOnAction(e -> this.ctrl.getMdl().coller());

        //Menu insertion
        ObservableList<MenuItem> insertion = mapp.getMenuBar().getMenus().get(2).getItems();
        ((Menu)insertion.get(0)).getItems().get(0).setOnAction(e -> {
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.LIGNE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu)insertion.get(0)).getItems().get(1).setOnAction(e -> {
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.RECTANGLE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(0).setOnAction(e ->{
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_ISOCELE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(1).setOnAction(e ->{
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_RECTANGLE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu)insertion.get(0)).getItems().get(3).setOnAction(e -> {
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.ELLIPSE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        insertion.get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().setTypeSelected(Formes.IMAGE);
            this.ctrl.getMdl().newForme();
        });
        insertion.get(2).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().setTypeSelected(Formes.TEXT);
            this.ctrl.getMdl().newForme();
        });

        //Menu modification
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        modification.get(0).setOnAction(e -> {
            this.setDessinMode(false);
        });
        ((Menu)modification.get(1)).getItems().get(0).setOnAction(e -> {
            saveModel();
            System.out.println("ContourCouleur");
        });
        ((Menu) ((Menu)modification.get(1)).getItems().get(1)).getItems().get(0).setOnAction(e -> {
            saveModel();
            System.out.println("ContourEpaisseur +10");
            this.ctrl.getMdl().augCtr(10);
        });
        ((Menu) ((Menu)modification.get(1)).getItems().get(1)).getItems().get(1).setOnAction(e -> {
            saveModel();
            System.out.println("ContourEpaisseur +1");
            this.ctrl.getMdl().augCtr(1);
        });
        ((Menu) ((Menu)modification.get(1)).getItems().get(1)).getItems().get(2).setOnAction(e -> {
            saveModel();
            System.out.println("ContourEpaisseur -1");
            this.ctrl.getMdl().dimCtr(1);
        });
        ((Menu) ((Menu)modification.get(1)).getItems().get(1)).getItems().get(3).setOnAction(e -> {
            saveModel();
            System.out.println("ContourEpaisseur - 10");
            this.ctrl.getMdl().dimCtr(10);
        });
        modification.get(2).setOnAction(e -> {
            saveModel();
            System.out.println("Couleur de remplissage");

            ColorPicker pickRemp = new ColorPicker();

            pickRemp.show();

        });
        ((Menu)modification.get(3)).getItems().get(0).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().rotateForme(90);
        });
        ((Menu)modification.get(3)).getItems().get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().rotateForme(-90);
        });
        ((Menu)modification.get(4)).getItems().get(0).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().movePremPlan();
        });
        ((Menu)modification.get(4)).getItems().get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().moveArrPlan();
        });
        modification.get(5).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().removeForme();
        });

        this.lockSelection();
    }

    public MenuApp getMapp() {
        return mapp;
    }

    public void lockSelection() {
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        modification.get(1).setVisible(false);
        modification.get(2).setVisible(false);
        modification.get(3).setVisible(false);
        modification.get(4).setVisible(false);
        modification.get(5).setVisible(false);

        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(2).setVisible(false);
        edition.get(3).setVisible(false);
    }

    public void unlockSelection() {
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        modification.get(1).setVisible(true);
        modification.get(2).setVisible(true);
        modification.get(3).setVisible(true);
        modification.get(4).setVisible(true);
        modification.get(5).setVisible(true);

        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(2).setVisible(true);
        edition.get(3).setVisible(true);
    }

    public void saveModel() {
        this.save = this.ctrl.getMdl();
        System.out.println(save);
    }

    public void undo() {
        if (this.save == null) return;

        this.ctrl.setMdl(this.save);
        this.ctrl.getView().setMdl(this.save);

        this.ctrl.getCvsCtrl().draw();

        this.save = null;
    }
}
