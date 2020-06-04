package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class Shot {
    private double xPos;
    private double yPos;
    private Pane currentRoot;
    public static int ARROW_WIDTH = 2;
    public static int ARROW_HEIGHT = 16;
    private SpaceInvaders spaceInvaders;
    private Rectangle arrow = new Rectangle();
    private boolean isAlive;
    private final double dYShot = 20;
    private final int plusScore = 1;
    private String soundName = "shotship.mp3";
    private Media media = new Media(new File(soundName).toURI().toString());
    private MediaPlayer shotSound = new MediaPlayer(media);

    public Shot(double xPos, double yPos, Pane currentRoot, SpaceInvaders spaceInvaders) {
        if (Main.currentUser.isGameSound()) {
            shotSound.play();
            shotSound.setCycleCount(2);
            shotSound.setVolume(0.7);
        }
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentRoot = currentRoot;
        this.spaceInvaders = spaceInvaders;
        isAlive = true;
        run();
    }

    public void run() {
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

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public void drawShot() {
        if (isAlive) {
            arrow.setHeight(ARROW_HEIGHT);
            arrow.setWidth(ARROW_WIDTH);
            arrow.setLayoutX(xPos);
            arrow.setLayoutY(yPos);
            arrow.setFill(Color.MEDIUMSPRINGGREEN);
        }
    }

    public void moveShot() {
        this.setYPos(yPos - dYShot);
    }

    public boolean checkShot() {
        if (spaceInvaders.getArmy().checkIsHit(arrow.getLayoutX(), arrow.getLayoutY())) {
            Main.currentUser.setScore(Main.currentUser.getScore() + plusScore);
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
