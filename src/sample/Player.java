package sample;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Player {
    private Stage primaryStage;
    private Pane currentRoot;
    private Rectangle ship = new Rectangle();
    private boolean shotState = true;
    private SpaceInvaders spaceInvaders;
    private Shot shot;
    private final int dX = 10;
    private final int SHIP_HEIGHT = 19;
    private final int SHIP_WIDTH = 57;
    private final double yPos = Main.GAME_HEIGHT - SHIP_HEIGHT;
    private double xPos = (Main.GAME_WIDTH - SHIP_WIDTH) / 2;


    public Player(Stage primaryStage, Pane currentRoot, SpaceInvaders spaceInvaders) {
        this.currentRoot = currentRoot;
        this.primaryStage = primaryStage;
        this.spaceInvaders = spaceInvaders;
        currentRoot.getChildren().add(ship);
        primaryStage.getScene().setOnKeyPressed(e -> this.keyPressed(e.getCode()));
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
        if ((e == KeyCode.RIGHT) && ((ship.getLayoutX() + SHIP_WIDTH + dX) < Main.GAME_WIDTH)) {
            ship.setLayoutX(ship.getLayoutX() + dX);
        } else if (e == KeyCode.LEFT && ((ship.getLayoutX() - dX) >= 0)) {
            ship.setLayoutX(ship.getLayoutX() - dX);
        } else if (e == KeyCode.SPACE) {
            shot();
        } else if (e == KeyCode.ESCAPE) {
            Main.showGameMenu(primaryStage, primaryStage.getScene());
            spaceInvaders.gameOver = true;
            spaceInvaders.show = false;
            spaceInvaders.gameOver();
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
