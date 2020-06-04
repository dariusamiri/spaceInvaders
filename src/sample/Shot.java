package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Shot {
    private double xPos;
    private double yPos;
    private Pane currentRoot;
    public static int ARROW_WIDTH = 2;
    public static int ARROW_HEIGHT = 16;
    private final int SHOT_SPEED = 10;
    private SpaceInvaders spaceInvaders;
    private Rectangle arrow = new Rectangle();
    private boolean isAlive;
    private final double dYShot = 20;
    private final int plusScore = 1;
    Thread thread;

    public Shot(double xPos, double yPos, Pane currentRoot, SpaceInvaders spaceInvaders) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentRoot = currentRoot;
        this.spaceInvaders = spaceInvaders;
        isAlive = true;
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                drawShot();
                moveShot();
                if (!checkShot())
                    this.stop();
            }
        };
        currentRoot.getChildren().add(arrow);
        timer.start();

    }

    public Rectangle getArrow() {
        return arrow;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public void drawShot() {
        if (isAlive) {
            arrow.setHeight(ARROW_HEIGHT);
            arrow.setWidth(ARROW_WIDTH);
            arrow.setLayoutX(xPos);
            arrow.setLayoutY(yPos);
            arrow.setFill(Color.RED);
        }
    }

    public void moveShot() {
        this.setYPos(yPos - dYShot);
    }

    public boolean checkShot() {
        if (spaceInvaders.getArmy().checkIsHit(arrow.getLayoutX(), arrow.getLayoutY())) {
            spaceInvaders.getPlayer().setScore(spaceInvaders.getPlayer().getScore() + plusScore);
            spaceInvaders.getPlayer().setShotState(true);
            currentRoot.getChildren().remove(arrow);
            isAlive = false;
            return false;
        } else if (arrow.getLayoutY() - dYShot <= 0) {
            isAlive = false;
            spaceInvaders.getPlayer().setShotState(true);
            currentRoot.getChildren().remove(arrow);
            return false;
        }
        return true;
    }
}
