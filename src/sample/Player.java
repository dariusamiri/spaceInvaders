package sample;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {
    private Pane currentRoot;
    private Rectangle ship = new Rectangle();
    private boolean shotState = true;
    private SpaceInvaders spaceInvaders;
    private Shot shot;
    private int score = 0;
    private final int dX = 10;
    private final int SHIP_HEIGHT = 15;
    private final int SHIP_WIDTH = 55;
    private final double yPos = Main.GAME_HEIGHT - SHIP_HEIGHT;
    private double xPos = (Main.GAME_WIDTH - SHIP_WIDTH)/2 ;

    public Player(Stage primaryStage, Pane currentRoot, SpaceInvaders spaceInvaders) {
        this.currentRoot = currentRoot;
        this.spaceInvaders = spaceInvaders;
        currentRoot.getChildren().add(ship);
        primaryStage.getScene().setOnKeyPressed(e -> this.keyPressed(e.getCode()));
    }

    public int getScore() {
        return score;
    }

    public void setShotState(boolean shotState) {
        this.shotState = shotState;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void drawShip() {
        ship.setHeight(SHIP_HEIGHT);
        ship.setWidth(SHIP_WIDTH);
        ship.setLayoutX(xPos);
        ship.setLayoutY(yPos);
        ship.setFill(Color.DARKGREEN);
    }

    public void keyPressed(KeyCode e) {
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
            shot = new Shot(ship.getLayoutX() + SHIP_WIDTH / 2 - Shot.ARROW_WIDTH / 2, ship.getLayoutY(), currentRoot, spaceInvaders);
            shotState = false;
        }
    }

    public boolean isHit(double xShot, double yShot) {
        if (ship.getLayoutX() <= xShot && xShot + AlienShot.ARROW_WIDTH <= ship.getLayoutX() + SHIP_WIDTH) {
            if (ship.getLayoutY() - SHIP_HEIGHT <= yShot && yShot - AlienShot.ARROW_HEIGHT <= ship.getLayoutY()) {
                SpaceInvaders.gameOver = true;
            }
        }
        return false;
    }
}
