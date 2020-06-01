package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {
    private Stage primaryStage;
    private Pane currentRoot;
    private Rectangle ship = new Rectangle();
    private boolean shotState = true;
    private SpaceInvaders spaceInvaders;
    private Rectangle arrow = null;
    private Shot shot;

    public Player(Stage primaryStage, Pane currentRoot, SpaceInvaders spaceInvaders) {
        this.primaryStage = primaryStage;
        this.currentRoot = currentRoot;
        this.spaceInvaders = spaceInvaders;
        currentRoot.getChildren().add(ship);
        primaryStage.getScene().setOnKeyPressed(e -> this.keyPressed(e.getCode()));
    }

    private final int SHIP_HEIGHT = 15;
    private final int SHIP_WIDTH = 55;
    private final double yPos = Main.GAME_HEIGHT - SHIP_HEIGHT;
    private double xPos = (Main.GAME_WIDTH - SHIP_WIDTH) / 2;

    public Rectangle getArrow() {
        return arrow;
    }

    public Shot getShot() {
        return shot;
    }

    public void setShotState(boolean shotState) {
        this.shotState = shotState;
    }

    public void drawShip() {
        ship.setHeight(SHIP_HEIGHT);
        ship.setWidth(SHIP_WIDTH);
        ship.setLayoutX(xPos);
        ship.setLayoutY(yPos);
        ship.setFill(Color.DARKGREEN);
    }

    public void keyPressed(KeyCode e) {
        int dX = 20;
        if ((e == KeyCode.RIGHT) && ((ship.getLayoutX() + SHIP_WIDTH + dX) < Main.GAME_WIDTH)) {
            ship.setLayoutX(ship.getLayoutX() + dX);
        } else if (e == KeyCode.LEFT && ((ship.getLayoutX() - dX) >= 0)) {
            ship.setLayoutX(ship.getLayoutX() - dX);
        } else if (e == KeyCode.SPACE) {
            shot();
        }
    }

    public void shot() {
        if (shotState) {
            shot = new Shot(ship.getLayoutX() + SHIP_WIDTH / 2, ship.getLayoutY(), currentRoot, spaceInvaders);
            arrow = shot.getArrow();
            shotState = false;
        }
    }
}
