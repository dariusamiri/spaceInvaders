package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ALienArmy {
    private Pane root;
    private final double distanceFromSide = 85;
    private final int numberALiensInArrow = 10;
    ArrayList<Alien> firstRow = new ArrayList<>(numberALiensInArrow);
    ArrayList<Alien> secondRow = new ArrayList<>(numberALiensInArrow);
    ArrayList<Alien> thirdRow = new ArrayList<>(numberALiensInArrow);
    private boolean moveRight = true;
    private boolean moveDown = true;
    private final double dX = 5;
    private final double dY = 30;

    public ALienArmy(Pane root) {
        this.root = root;
        createArmy();
        setStartingPosition();
    }

    public void createArmy() {
        for (int i = 0; i < numberALiensInArrow; i++) {
            firstRow.add(new Alien(root));
            secondRow.add(new Alien(root));
            thirdRow.add(new Alien(root));
        }
    }

    public void setStartingPosition() {
        double x = distanceFromSide;
        double y = dY;
        for (int i = 0; i < numberALiensInArrow; i++) {
            firstRow.get(i).setALienXLayout(x);
            firstRow.get(i).setALienYLayout(y);
            x += 40;
        }
        x = distanceFromSide;
        y += dY;
        for (int i = 0; i < numberALiensInArrow; i++) {
            secondRow.get(i).setALienXLayout(x);
            secondRow.get(i).setALienYLayout(y);
            x += 40;
        }
        x = distanceFromSide;
        y += dY;
        for (int i = 0; i < numberALiensInArrow; i++) {
            thirdRow.get(i).setALienXLayout(x);
            thirdRow.get(i).setALienYLayout(y);
            x += 40;
        }
    }

    public void drawArmy() {
        for (int i = 0; i < firstRow.size(); i++) {
            firstRow.get(i).drawAlien();
        }
        for (int i = 0; i < secondRow.size(); i++) {
            secondRow.get(i).drawAlien();
        }
        for (int i = 0; i < thirdRow.size(); i++) {
            thirdRow.get(i).drawAlien();
        }
    }

    public void moveRight() {
        if (moveRight && firstRow.get(numberALiensInArrow - 1).getALienXLayout() + dX + Alien.ALIEN_WIDTH <= Main.GAME_WIDTH) {
            for (Alien alien : firstRow) {
                alien.setALienXLayout(alien.getALienXLayout() + dX);
            }
            for (Alien alien : secondRow) {
                alien.setALienXLayout(alien.getALienXLayout() + dX);
            }
            for (Alien alien : thirdRow) {
                alien.setALienXLayout(alien.getALienXLayout() + dX);
            }
            moveDown = true;
        } else {
            moveRight = false;
            moveDown();
        }
    }

    public void moveDown() {
        if (thirdRow.size() > 0 && thirdRow.get(0).getALienYLayout() + dY >= Main.GAME_HEIGHT)
            SpaceInvaders.gameOver();
        else if (secondRow.size() > 0 && secondRow.get(0).getALienYLayout() + dY >= Main.GAME_HEIGHT)
            SpaceInvaders.gameOver();
        else if (firstRow.size() > 0 && firstRow.get(0).getALienYLayout() + dY >= Main.GAME_HEIGHT)
            SpaceInvaders.gameOver();
        if (moveDown) {
            for (Alien alien : firstRow) {
                alien.setALienYLayout(alien.getALienYLayout() + dY);
            }
            for (Alien alien : secondRow) {
                alien.setALienYLayout(alien.getALienYLayout() + dY);
            }
            for (Alien alien : thirdRow) {
                alien.setALienYLayout(alien.getALienYLayout() + dY);
            }
            moveDown = false;
        } else {
            if (moveRight)
                moveRight();
            else moveLeft();
        }

    }

    public void moveLeft() {
        if (firstRow.get(0).getALienXLayout() - dX >= 0 && !moveRight) {
            for (Alien alien : firstRow) {
                alien.setALienXLayout(alien.getALienXLayout() - dX);
            }
            for (Alien alien : secondRow) {
                alien.setALienXLayout(alien.getALienXLayout() - dX);
            }
            for (Alien alien : thirdRow) {
                alien.setALienXLayout(alien.getALienXLayout() - dX);
            }
        } else {
            moveRight = true;
            moveDown = true;
            moveDown();
        }
    }

    public boolean checkIsHit(double xShot, double yShot) {
        for (int i = 0; i < thirdRow.size(); i++) {
            if (thirdRow.get(i).getIsAlive() && xShot >= thirdRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= thirdRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                System.out.println("third");
                if (yShot - Shot.ARROW_HEIGHT <= thirdRow.get(i).getALienYLayout() && yShot >= thirdRow.get(i).getALienYLayout() - Alien.ALIEN_HEIGHT) {
                    thirdRow.get(i).setIsAlive(false);
                    return true;
                }
            }
        }
        for (int i = 0; i < secondRow.size(); i++) {
            if (secondRow.get(i).getIsAlive() && xShot >= secondRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= secondRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                System.out.println("second");
                if (yShot - Shot.ARROW_HEIGHT <= secondRow.get(i).getALienYLayout() && yShot >= secondRow.get(i).getALienYLayout() - Alien.ALIEN_HEIGHT) {
                    secondRow.get(i).setIsAlive(false);
                    return true;
                }
            }
        }
        for (int i = 0; i < firstRow.size(); i++) {
            if (firstRow.get(i).getIsAlive() && xShot >= firstRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= firstRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                System.out.println("first");
                if (yShot - Shot.ARROW_HEIGHT <= firstRow.get(i).getALienYLayout() && yShot >= firstRow.get(i).getALienYLayout() - Alien.ALIEN_HEIGHT) {
                    firstRow.get(i).setIsAlive(false);
                    return true;
                }
            }
        }
        return false;
    }

    public void moveArmy() {
        moveRight();
    }
}
