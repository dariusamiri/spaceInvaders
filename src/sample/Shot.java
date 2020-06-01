package sample;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Shot {
    private double xPos;
    private double yPos;
    private Pane currentRoot;
    public static int ARROW_WIDTH = 7;
    public static int ARROW_HEIGHT = 7;
    private int ARROW_SPEED = 15;
    private SpaceInvaders spaceInvaders;
    private Rectangle arrow = null;
    private boolean isAlive = true;
    private final double dYShot = 5;

    public Shot(double xPos, double yPos, Pane currentRoot, SpaceInvaders spaceInvaders) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentRoot = currentRoot;
        this.spaceInvaders = spaceInvaders;
        drawShot();
        currentRoot.getChildren().add(arrow);
    }

    public Rectangle getArrow() {
        return arrow;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public void drawShot() {
        if (arrow == null) {
            arrow = new Rectangle();
            arrow.setHeight(ARROW_HEIGHT);
            arrow.setWidth(ARROW_WIDTH);
            arrow.setLayoutX(xPos);
            arrow.setLayoutY(yPos);
            arrow.setFill(Color.WHITE);
        } else {
            if (isAlive) {
                moveShot();
                arrow.setHeight(ARROW_HEIGHT);
                arrow.setWidth(ARROW_WIDTH);
                arrow.setLayoutX(xPos);
                arrow.setLayoutY(yPos);
                arrow.setFill(Color.WHITE);
            } else {
                Platform.runLater(() -> currentRoot.getChildren().remove(arrow));
            }
        }
    }


    public void moveShot() {
        if (checkShot() && isAlive) {
            double dY = 5;
            this.setYPos(yPos - dY);
        } else {
            spaceInvaders.getPlayer().setShotState(true);
        }
    }

    public boolean checkShot() {
        if (arrow.getLayoutY() - dYShot <= 0) {
            isAlive = false;
            return false;
        }
        if (spaceInvaders.getArmy().checkIsHit(arrow.getLayoutX(), arrow.getLayoutY())) {
            isAlive = false;
            return false;
        }
        return true;
    }
}
