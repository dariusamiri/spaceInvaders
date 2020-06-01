package sample;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Shot implements Runnable {
    private double xPos;
    private double yPos;
    private Pane currentRoot;
    public static int ARROW_WIDTH = 2;
    public static int ARROW_HEIGHT = 16;
    private int ARROW_SPEED = 2;
    private SpaceInvaders spaceInvaders;
    private Rectangle arrow = null;
    private boolean isAlive = true;
    private final double dYShot = 15;
    private final int plusScore = 1;

    public Shot(double xPos, double yPos, Pane currentRoot, SpaceInvaders spaceInvaders) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentRoot = currentRoot;
        this.spaceInvaders = spaceInvaders;
        drawShot();
        currentRoot.getChildren().add(arrow);
        Thread thread = new Thread(this);
        thread.start();

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
            arrow.setFill(Color.RED);
        } else {
            if (isAlive) {
                arrow.setHeight(ARROW_HEIGHT);
                arrow.setWidth(ARROW_WIDTH);
                arrow.setLayoutX(xPos);
                arrow.setLayoutY(yPos);
                arrow.setFill(Color.RED);
            } else {
                Platform.runLater(() -> currentRoot.getChildren().remove(arrow));
            }
        }
    }


    public void moveShot() {
        if (checkShot() && isAlive) {
            double dY = 2;
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
            spaceInvaders.getPlayer().setScore(spaceInvaders.getPlayer().getScore() + plusScore);
            isAlive = false;
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(ARROW_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveShot();
            if (!isAlive)
                break;
        }
    }
}
