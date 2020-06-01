package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ALienArmy {
    private Pane root;
    private final double distanceFromSide = 150;
    private final double distanceFromUp = 100;
    private final int numberALiensInArrow = 13;
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
        double y = distanceFromUp;
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
            firstRow.get(i).drawAlien(Color.AQUA);
        }
        for (int i = 0; i < secondRow.size(); i++) {
            secondRow.get(i).drawAlien(Color.DARKVIOLET);
        }
        for (int i = 0; i < thirdRow.size(); i++) {
            thirdRow.get(i).drawAlien(Color.LIGHTPINK);
        }
    }

    public double getMostLeftAlienX() {
        for (int i = 0; i < numberALiensInArrow; i++) {
            if (firstRow.get(i).getIsAlive())
                return firstRow.get(i).getALienXLayout();
            else if (secondRow.get(i).getIsAlive())
                return secondRow.get(i).getALienXLayout();
            else if (thirdRow.get(i).getIsAlive())
                return thirdRow.get(i).getALienXLayout();
        }
        return 0;
    }

    public double getMostRightALien() {
        for (int i = numberALiensInArrow - 1; i >= 0; i--) {
            if (firstRow.get(i).getIsAlive())
                return firstRow.get(i).getALienXLayout();
            else if (secondRow.get(i).getIsAlive())
                return secondRow.get(i).getALienXLayout();
            else if (thirdRow.get(i).getIsAlive())
                return thirdRow.get(i).getALienXLayout();
        }
        return 0;
    }

    public double getMostDownAlienY() {
        if (isRowArmyHasAlive(thirdRow)) {
            for (Alien alien : thirdRow) {
                if (alien.getIsAlive())
                    return alien.getALienYLayout();
            }
        } else if (isRowArmyHasAlive(secondRow)) {
            for (Alien alien : secondRow) {
                if (alien.getIsAlive())
                    return alien.getALienYLayout();
            }
        } else if (isRowArmyHasAlive(firstRow)) {
            for (Alien alien : firstRow) {
                if (alien.getIsAlive())
                    return alien.getALienYLayout();
            }
        }
        return 0;
    }


    public boolean isRowArmyHasAlive(ArrayList<Alien> row) {
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i).getIsAlive())
                return true;
        }
        return false;
    }

    public void moveRight() {
        if (moveRight && getMostRightALien() + dX + Alien.ALIEN_WIDTH <= Main.GAME_WIDTH) {
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
        if (getMostDownAlienY() + dY >= Main.GAME_HEIGHT)
            SpaceInvaders.gameOver();
        else {
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
    }

    public void moveLeft() {
        if (getMostLeftAlienX() - dX >= 0 && !moveRight) {
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
        for (int i = 0; i < numberALiensInArrow; i++) {
            if (thirdRow.get(i).getIsAlive() && xShot >= thirdRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= thirdRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                if (yShot - Shot.ARROW_HEIGHT <= thirdRow.get(i).getALienYLayout() && yShot >= thirdRow.get(i).getALienYLayout() - Alien.ALIEN_HEIGHT) {
                    thirdRow.get(i).setIsAlive(false);
                    return true;
                }
            }
            if (secondRow.get(i).getIsAlive() && xShot >= secondRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= secondRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                if (yShot - Shot.ARROW_HEIGHT <= secondRow.get(i).getALienYLayout() && yShot >= secondRow.get(i).getALienYLayout() - Alien.ALIEN_HEIGHT) {
                    secondRow.get(i).setIsAlive(false);
                    return true;
                }
            }
            if (firstRow.get(i).getIsAlive() && xShot >= firstRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= firstRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
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
