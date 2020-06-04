package sample;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Alien {
    public static final double ALIEN_HEIGHT = 15;
    public static final double ALIEN_WIDTH = 15;
    private double xLayout;
    private double yLayout;
    private Rectangle enemy = new Rectangle();
    public static ArrayList<Alien> allAliens = new ArrayList<>();
    private boolean isAlive;
    private Pane currentRoot;

    public Alien(Pane currentRoot) {
        this.currentRoot = currentRoot;
        isAlive = true;
        allAliens.add(this);
        Platform.runLater(() -> currentRoot.getChildren().add(enemy));
    }

    public void setALienXLayout(double xLayout) {
        this.xLayout = xLayout;
    }

    public void setALienYLayout(double yLayout) {
        this.yLayout = yLayout;
    }

    public void setIsAlive(boolean alive) {
        isAlive = false;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public double getALienXLayout() {
        return xLayout;
    }

    public double getALienYLayout() {
        return yLayout;
    }

    public void drawAlien(Color color) {
        if (this.getIsAlive()) {
            enemy.setHeight(ALIEN_HEIGHT);
            enemy.setWidth(ALIEN_WIDTH);
            enemy.setLayoutX(xLayout);
            enemy.setLayoutY(yLayout);
            enemy.setFill(color);
        } else {
//            Platform.runLater(() -> currentRoot.getChildren().remove(enemy));
            Platform.runLater(() -> enemy.setFill(null));
        }
    }
}
