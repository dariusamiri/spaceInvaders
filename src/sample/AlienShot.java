package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class AlienShot {
    private Pane currentRoot;
    public static int ARROW_WIDTH = 2;
    public static int ARROW_HEIGHT = 8;
    private int time = 0;
    private SpaceInvaders spaceInvaders;
    private final double dYShot = 3;
    private ALienArmy army;
    private ArrayList<Rectangle> arrows = new ArrayList<>();
    ArrayList<Alien> firstRow;
    ArrayList<Alien> secondRow;
    ArrayList<Alien> thirdRow;

    public AlienShot(Pane currentRoot, SpaceInvaders spaceInvaders) {
        this.currentRoot = currentRoot;
        this.spaceInvaders = spaceInvaders;
        army = spaceInvaders.getArmy();
        firstRow = army.firstRow;
        secondRow = army.secondRow;
        thirdRow = army.thirdRow;
        AnimationTimer alienShot = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (spaceInvaders.gameOver)
                    this.stop();
                time += 1;
                if (time > 98) {
                    shotByArmy();
                    time = 0;
                }
                moveShot();
            }
        };
        alienShot.start();
    }

    public void drawShot(double x, double y) {
        Rectangle arrow = new Rectangle();
        arrow.setHeight(ARROW_HEIGHT);
        arrow.setWidth(ARROW_WIDTH);
        arrow.setLayoutX(x);
        arrow.setLayoutY(y);
        arrow.setFill(Color.LIGHTGREEN);
        arrows.add(arrow);
        currentRoot.getChildren().add(arrow);
    }

    public void moveShot() {
        for (int i = 0; i < arrows.size(); i++) {
            if (checkShot(arrows.get(i))) {
                arrows.get(i).setLayoutY(arrows.get(i).getLayoutY() + dYShot);
                update(arrows.get(i));
            } else {
                arrows.remove(i);
                i--;
            }
        }
    }

    public boolean checkShot(Rectangle arrow) {
        if (spaceInvaders.getPlayer().isHit(arrow.getLayoutX(), arrow.getLayoutY())) {
            currentRoot.getChildren().remove(arrow);
            return false;
        } else if (arrow.getLayoutY() + dYShot >= Main.GAME_HEIGHT) {
            currentRoot.getChildren().remove(arrow);
            return false;
        }

        return true;
    }

    public void update(Rectangle arrow) {
        arrow.setHeight(ARROW_HEIGHT);
        arrow.setWidth(ARROW_WIDTH);
        arrow.setLayoutX(arrow.getLayoutX());
        arrow.setLayoutY(arrow.getLayoutY());
        arrow.setFill(Color.RED);
    }

    public void shotByArmy() {
        Random random = new Random();
        int iFirstRow = random.nextInt(ALienArmy.numberALiensInArrow);
        int iSecondRow = random.nextInt(ALienArmy.numberALiensInArrow);
        int iThirdRow = random.nextInt(ALienArmy.numberALiensInArrow);
        if (firstRow.get(iFirstRow).getIsAlive()) {
            drawShot(firstRow.get(iFirstRow).getALienXLayout() + Alien.ALIEN_WIDTH / 2, firstRow.get(iFirstRow).getALienYLayout());
        }
        if (secondRow.get(iSecondRow).getIsAlive()) {
            drawShot(secondRow.get(iSecondRow).getALienXLayout() + Alien.ALIEN_WIDTH / 2, secondRow.get(iSecondRow).getALienYLayout());
        }
        if (thirdRow.get(iThirdRow).getIsAlive()) {
            drawShot(thirdRow.get(iThirdRow).getALienXLayout() + Alien.ALIEN_WIDTH / 2, thirdRow.get(iThirdRow).getALienYLayout());
        }
    }
}
