package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    private final Pane currentRoot;
    private Rectangle ship = new Rectangle();
    private boolean shotState = true;
    private SpaceInvaders spaceInvaders;
    private final int dX = 20;
    private final int SHIP_HEIGHT = 19;
    private final int SHIP_WIDTH = 57;
    private final double yPos = Main.GAME_HEIGHT - SHIP_HEIGHT;
    private double xPos = (Main.GAME_WIDTH - SHIP_WIDTH) / 2;


    public Player(Pane currentRoot, SpaceInvaders spaceInvaders) {
        this.currentRoot = currentRoot;
        this.spaceInvaders = spaceInvaders;
        currentRoot.getChildren().add(ship);
        spaceInvaders.getPrimaryStage().getScene().setOnKeyPressed(e -> this.keyPressed(e.getCode()));
    }

    public void setShotState(boolean shotState) {
        this.shotState = shotState;
    }

    public void drawShip() {
        ship.setHeight(SHIP_HEIGHT);
        ship.setWidth(SHIP_WIDTH);
        ship.setLayoutX(xPos);
        ship.setLayoutY(yPos);
        ship.setFill(Color.DARKGRAY);
    }

    public void keyPressed(KeyCode e) {
        if ((e == KeyCode.RIGHT) && ((ship.getLayoutX() + SHIP_WIDTH + dX) < Main.GAME_WIDTH)) {
            ship.setLayoutX(ship.getLayoutX() + dX);
        } else if (e == KeyCode.LEFT && ((ship.getLayoutX() - dX) >= 0)) {
            ship.setLayoutX(ship.getLayoutX() - dX);
        } else if (e == KeyCode.SPACE) {
            shot();
        } else if (e == KeyCode.ESCAPE) {
            spaceInvaders.getPrimaryStage().setScene(spaceInvaders.getPreviousScene());
            spaceInvaders.gameOver = true;
            spaceInvaders.show = false;
            spaceInvaders.gameOver();
        }
    }

    public void shot() {
        if (shotState) {
            Shot shot = new Shot(ship.getLayoutX() + SHIP_WIDTH / 2 - Shot.ARROW_WIDTH / 2, ship.getLayoutY(), currentRoot, spaceInvaders);
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
