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
import vue.View;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;

public class MenuAppControl {
    //Attributs
    private Control ctrl;

    private MenuApp mapp;
    private boolean dessinMode;
    private boolean allowModif;
    private Model save;

    /**
     * Constructeur du menu (control et vue)
     * @param c
     */
    public MenuAppControl(Control c) {
        this.ctrl = c;

        this.mapp = new MenuApp();
        this.dessinMode = false;
        this.allowModif = false;
    }

    /**
     * Getter du mode dessin
     * @return
     */
    public boolean isDessinMode() {
        return dessinMode;
    }

    /**
     * setter du mode dessin
     * @param dessinMode
     */
    public void setDessinMode(boolean dessinMode) {
        this.dessinMode = dessinMode;
    }

    /**
     * Methode qui permet d'ajouter les actions à la vue du menu
     */
    public void addActions() {
        //Menu fichier
        ObservableList<MenuItem> fichier = mapp.getMenuBar().getMenus().get(0).getItems();

        //Nouveau
        fichier.get(0).setOnAction(e -> {
            this.ctrl.comfirmNew(e);
            this.ctrl = new Control(this.ctrl.getMdl(), this.ctrl.getView());
        });

        //Ouvrir
        fichier.get(1).setOnAction(e -> {
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
                        Color clr = Color.web(parts[6]);

                        boolean drawable = Boolean.valueOf(parts[7]);

                        double epaisseurBord = Double.valueOf(parts[8]);
                        Color clrBord = Color.web(parts[9]);

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

        //Sauvegardr
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
        });

        //Export
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

        //Quitter
        fichier.get(4).setOnAction(e -> this.ctrl.quit(e));







        //Menu edition
        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();

        //undo
        edition.get(0).setOnAction(e -> this.undo());

        //copier
        edition.get(1).setOnAction(e -> this.ctrl.getMdl().copier());

        //coller
        edition.get(2).setOnAction(e -> this.ctrl.getMdl().coller());

        //Menu insertion
        ObservableList<MenuItem> insertion = mapp.getMenuBar().getMenus().get(2).getItems();

        //Ligne
        ((Menu)insertion.get(0)).getItems().get(0).setOnAction(e -> {
            saveModel();
            ((RadioMenuItem) ((Menu)insertion.get(0)).getItems().get(0)).setSelected(true);
            ((ToggleButton) this.ctrl.getbOutilsCtrl().getBarreOutils().getOutils().getItems().get(7)).setSelected(true);
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.LIGNE);
            this.ctrl.getMdl().newForme();
        });

        //Rectangle
        ((Menu)insertion.get(0)).getItems().get(1).setOnAction(e -> {
            saveModel();
            ((RadioMenuItem) ((Menu)insertion.get(0)).getItems().get(1)).setSelected(true);
            ((ToggleButton) this.ctrl.getbOutilsCtrl().getBarreOutils().getOutils().getItems().get(8)).setSelected(true);
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.RECTANGLE);
            this.ctrl.getMdl().newForme();
        });

        //Trgl Isocele
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(0).setOnAction(e ->{
            saveModel();
            ((RadioMenuItem) ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(0)).setSelected(true);
            ((ToggleButton) this.ctrl.getbOutilsCtrl().getBarreOutils().getOutils().getItems().get(9)).setSelected(true);
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_ISOCELE);
            this.ctrl.getMdl().newForme();
        });

        //Trgl Rectangle
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(1).setOnAction(e ->{
            saveModel();
            ((RadioMenuItem) ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(1)).setSelected(true);
            ((ToggleButton) this.ctrl.getbOutilsCtrl().getBarreOutils().getOutils().getItems().get(10)).setSelected(true);
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_RECTANGLE);
            this.ctrl.getMdl().newForme();
        });

        //Ellipse
        ((Menu)insertion.get(0)).getItems().get(3).setOnAction(e -> {
            saveModel();
            ((RadioMenuItem) ((Menu)insertion.get(0)).getItems().get(3)).setSelected(true);
            ((ToggleButton) this.ctrl.getbOutilsCtrl().getBarreOutils().getOutils().getItems().get(11)).setSelected(true);
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.ELLIPSE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });

        //Image
        insertion.get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().setTypeSelected(Formes.IMAGE);
            this.ctrl.getMdl().newForme();
        });

        //Texte
        insertion.get(2).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().setTypeSelected(Formes.TEXT);
            this.ctrl.getMdl().newForme();
        });






        //Menu modification
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();

        //Selection
        modification.get(0).setOnAction(e -> {
            ((RadioMenuItem) modification.get(0)).setSelected(true);
            ((ToggleButton) this.ctrl.getbOutilsCtrl().getBarreOutils().getOutils().getItems().get(2)).setSelected(true);
            this.setDessinMode(false);
        });

        //redimension
        modification.get(1).setOnAction(e -> {
             this.setDessinMode(false);
             if ((((CheckMenuItem) modification.get(1)).isSelected() || ((ToggleButton) this.ctrl.getbOutilsCtrl().getBarreOutils().getOutils().getItems().get(4)).isSelected()) && !this.ctrl.getMdl().isRedimMode()) {
                 ((CheckMenuItem) modification.get(1)).setSelected(true);
                 ((ToggleButton) this.ctrl.getbOutilsCtrl().getBarreOutils().getOutils().getItems().get(4)).setSelected(true);
                this.ctrl.getMdl().setRedimMode(true);
             } else {
                 ((CheckMenuItem) modification.get(1)).setSelected(false);
                 ((ToggleButton) this.ctrl.getbOutilsCtrl().getBarreOutils().getOutils().getItems().get(4)).setSelected(false);
                this.ctrl.getMdl().setRedimMode(false);
             }

        });

        //Couleur Contour
        Menu couleurContour = ((Menu) ((Menu)modification.get(2)).getItems().get(0));
        couleurContour.getItems().get(0).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.BLACK);
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.WHITE);
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(2).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.GRAY);
            System.out.println(this.ctrl.getMdl().getSelectedForme().getClrBord().toString());
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(3).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.BLUE);
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(4).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.CYAN);
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(5).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.GREEN);
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(6).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.YELLOW);
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(7).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.ORANGE);
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(8).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.RED);
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(9).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClrBord(Color.PINK);
            this.ctrl.getCvsCtrl().draw();
        });
        couleurContour.getItems().get(10).setOnAction(e -> {
            saveModel();
            TextInputDialog in = new TextInputDialog("#FFFFFF");

            in.setTitle("Couleur Contour");
            in.setContentText("Couleur Hexadéciamal :");

            Optional<String> txtIn = in.showAndWait();
            if (txtIn.isPresent()) {
                this.ctrl.getMdl().getSelectedForme().setClrBord(Color.web(txtIn.get()));
            }

            this.ctrl.getCvsCtrl().draw();
        });
        //Epaisseur Contour
        ((Menu) ((Menu)modification.get(2)).getItems().get(1)).getItems().get(0).setOnAction(e -> {
            saveModel();
            System.out.println("ContourEpaisseur +10");
            this.ctrl.getMdl().augCtr(10);
        });
        ((Menu) ((Menu)modification.get(2)).getItems().get(1)).getItems().get(1).setOnAction(e -> {
            saveModel();
            System.out.println("ContourEpaisseur +1");
            this.ctrl.getMdl().augCtr(1);
        });
        ((Menu) ((Menu)modification.get(2)).getItems().get(1)).getItems().get(2).setOnAction(e -> {
            saveModel();
            System.out.println("ContourEpaisseur -1");
            this.ctrl.getMdl().dimCtr(1);
        });
        ((Menu) ((Menu)modification.get(2)).getItems().get(1)).getItems().get(3).setOnAction(e -> {
            saveModel();
            System.out.println("ContourEpaisseur - 10");
            this.ctrl.getMdl().dimCtr(10);
        });

        //Couleur Remplissage
        Menu couleur = (Menu) modification.get(3);
        couleur.getItems().get(0).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.BLACK);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.WHITE);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(2).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.GRAY);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(3).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.BLUE);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(4).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.CYAN);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(5).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.GREEN);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(6).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.YELLOW);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(7).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.ORANGE);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(8).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.RED);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(9).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().getSelectedForme().setClr(Color.PINK);
            this.ctrl.getCvsCtrl().draw();
        });
        couleur.getItems().get(10).setOnAction(e -> {
            saveModel();
            TextInputDialog in = new TextInputDialog("#FFFFFF");

            in.setTitle("Couleur Remplissage");
            in.setContentText("Couleur Hexadéciamal :");

            Optional<String> txtIn = in.showAndWait();
            if (txtIn.isPresent()) {
                this.ctrl.getMdl().getSelectedForme().setClr(Color.web(txtIn.get()));
            }

            this.ctrl.getCvsCtrl().draw();
        });

        //Roatation
        ((Menu)modification.get(4)).getItems().get(0).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().rotateForme(90);
        });
        ((Menu)modification.get(4)).getItems().get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().rotateForme(-90);
        });

        //Plan
        ((Menu)modification.get(5)).getItems().get(0).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().movePremPlan();
        });
        ((Menu)modification.get(5)).getItems().get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().moveArrPlan();
        });

        //Supprimer
        modification.get(6).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().removeForme();
        });


        this.lockSelection();
        mapp.getMenuBar().getMenus().get(3).getItems().get(0).fire();
    }

    /**
     * Methode qui rend la vue du menu
     * @return view menu
     */
    public MenuApp getMapp() {
        return mapp;
    }

    /**
     * Methode qui permet de bloquer les elements propres à la selection
     */
    public void lockSelection() {
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        modification.get(1).setVisible(false);
        modification.get(2).setVisible(false);
        modification.get(3).setVisible(false);
        modification.get(4).setVisible(false);
        modification.get(5).setVisible(false);
        modification.get(6).setVisible(false);

        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(1).setVisible(false);
        edition.get(2).setVisible(false);
    }


    /**
     * Methode qui permet de debloquer les elements propres a la selection
     */
    public void unlockSelection() {
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        modification.get(1).setVisible(true);
        modification.get(2).setVisible(true);
        modification.get(3).setVisible(true);
        modification.get(4).setVisible(true);
        modification.get(5).setVisible(true);
        modification.get(6).setVisible(true);

        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(1).setVisible(true);
        edition.get(2).setVisible(true);
    }

    /**
     * Methide qui permet de faire une saubegarde du modele
     */
    public void saveModel() {
        Model mdl = this.ctrl.getMdl();

        this.save = new Model(mdl.getView(), mdl.getCtrl(), mdl.getTypeSelected(), mdl.getNewForme(), mdl.getFormes(), mdl.isEnDeplacement(), mdl.getIndexSelected(), mdl.getX_souris(), mdl.getY_souris(), mdl.getCopiedForme());
    }

    /**
     * Methode à appeler pour faire le undo
     */
    public void undo() {
        if (this.save == null) return;

        System.out.println(this.save.getFormes());
        System.out.println(this.ctrl.getMdl().getFormes());
        this.ctrl.setMdl(this.save);
        this.ctrl.getCvsCtrl().draw();

        this.save = null;
    }
}
