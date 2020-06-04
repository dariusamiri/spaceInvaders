package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ALienArmy {
    private ArrayList<Alien> firstRow = new ArrayList<>(numberALiensInArrow);
    private ArrayList<Alien> secondRow = new ArrayList<>(numberALiensInArrow);
    private ArrayList<Alien> thirdRow = new ArrayList<>(numberALiensInArrow);
    private ArrayList<Alien> forthRow = new ArrayList<>(numberALiensInArrow);
    private ArrayList<Alien> fifthRow = new ArrayList<>(numberALiensInArrow);
    private final double distanceFromSide = 150;
    private final double distanceFromUp = 98;
    private final double dX = 1;
    private final double dY = 20;
    public static final int numberALiensInArrow = 13;
    private Pane root;
    private boolean moveRight = true;
    private boolean moveDown = true;
    private SpaceInvaders spaceInvaders;

    public ALienArmy(Pane root, SpaceInvaders spaceInvaders) {
        this.root = root;
        this.spaceInvaders = spaceInvaders;
        createArmy();
        setStartingPosition();
    }

    public ArrayList<Alien> getFirstRow() {
        return firstRow;
    }

    public ArrayList<Alien> getSecondRow() {
        return secondRow;
    }

    public ArrayList<Alien> getThirdRow() {
        return thirdRow;
    }

    public ArrayList<Alien> getForthRow() {
        return forthRow;
    }

    public ArrayList<Alien> getFifthRow() {
        return fifthRow;
    }

    public void createArmy() {
        for (int i = 0; i < numberALiensInArrow; i++) {
            firstRow.add(new Alien(root));
            secondRow.add(new Alien(root));
            thirdRow.add(new Alien(root));
            forthRow.add(new Alien(root));
            fifthRow.add(new Alien(root));
        }
    }

    public void setStartingPosition() {
        double x = distanceFromSide;
        double y = distanceFromUp;
        for (int i = 0; i < numberALiensInArrow; i++) {
            firstRow.get(i).setALienXLayout(x);
            firstRow.get(i).setALienYLayout(y);
            secondRow.get(i).setALienXLayout(x);
            secondRow.get(i).setALienYLayout(y += 35);
            thirdRow.get(i).setALienXLayout(x);
            thirdRow.get(i).setALienYLayout(y += 35);
            forthRow.get(i).setALienXLayout(x);
            forthRow.get(i).setALienYLayout(y += 35);
            fifthRow.get(i).setALienXLayout(x);
            fifthRow.get(i).setALienYLayout(y += 35);
            y = distanceFromUp;
            x += 50;
        }
    }


    public void drawArmy() {
        for (Alien alien : firstRow)
            alien.drawAlien(Color.AQUA);
        for (Alien value : secondRow)
            value.drawAlien(Color.DARKVIOLET);
        for (Alien alien : thirdRow)
            alien.drawAlien(Color.LIGHTPINK);
        for (Alien alien : forthRow)
            alien.drawAlien(Color.DARKGREEN);
        for (Alien alien : fifthRow)
            alien.drawAlien(Color.ROYALBLUE);
    }

    public double getMostLineALienX(int i) {
        if (firstRow.get(i).getIsAlive())
            return firstRow.get(i).getALienXLayout();
        else if (secondRow.get(i).getIsAlive())
            return secondRow.get(i).getALienXLayout();
        else if (thirdRow.get(i).getIsAlive())
            return thirdRow.get(i).getALienXLayout();
        else if (forthRow.get(i).getIsAlive())
            return forthRow.get(i).getALienXLayout();
        else if (fifthRow.get(i).getIsAlive())
            return fifthRow.get(i).getALienXLayout();
        return 0;
    }

    public double getMostLeftAlienX() {
        for (int i = 0; i < numberALiensInArrow; i++) {
            if (getMostLineALienX(i) != 0)
                return getMostLineALienX(i);
        }
        return 0;
    }

    public double getMostRightALien() {
        for (int i = numberALiensInArrow - 1; i >= 0; i--) {
            if (getMostLineALienX(i) != 0)
                return getMostLineALienX(i);
        }
        return 0;
    }

    public double getMostDownALienYEach(ArrayList<Alien> row) {
        for (Alien alien : row)
            if (alien.getIsAlive())
                return alien.getALienYLayout();
        return 0;
    }

    public double getMostDownAlienY() {
        if (isRowArmyHasAlive(fifthRow)) {
            return getMostDownALienYEach(fifthRow);
        } else if (isRowArmyHasAlive(forthRow)) {
            return getMostDownALienYEach(forthRow);
        } else if (isRowArmyHasAlive(thirdRow)) {
            return getMostDownALienYEach(thirdRow);
        } else if (isRowArmyHasAlive(secondRow)) {
            return getMostDownALienYEach(secondRow);
        } else if (isRowArmyHasAlive(firstRow)) {
            return getMostDownALienYEach(firstRow);
        }
        return 0;
    }

    public boolean isRowArmyHasAlive(ArrayList<Alien> row) {
        for (Alien alien : row) {
            if (alien.getIsAlive())
                return true;
        }
        return false;
    }

    public void moveRightEach(ArrayList<Alien> row) {
        for (Alien alien : row) {
            alien.setALienXLayout(alien.getALienXLayout() + dX);
        }
    }

    public void moveRight() {
        if (moveRight && getMostRightALien() + dX + Alien.ALIEN_WIDTH <= Main.GAME_WIDTH) {
            moveRightEach(firstRow);
            moveRightEach(secondRow);
            moveRightEach(thirdRow);
            moveRightEach(forthRow);
            moveRightEach(fifthRow);
            moveDown = true;
        } else {
            moveRight = false;
            moveDown();
        }
    }

    public void moveDownEach(ArrayList<Alien> row) {
        for (Alien alien : row) {
            alien.setALienYLayout(alien.getALienYLayout() + dY);
        }
    }

    public void moveDown() {
        if (getMostDownAlienY() + dY + Alien.ALIEN_HEIGHT >= Main.GAME_HEIGHT)
            SpaceInvaders.gameOver = true;
        else {
            if (moveDown) {
                moveDownEach(firstRow);
                moveDownEach(secondRow);
                moveDownEach(thirdRow);
                moveDownEach(forthRow);
                moveDownEach(fifthRow);
                moveDown = false;
            } else {
                if (moveRight)
                    moveRight();
                else moveLeft();
            }
        }
    }

    public void moveLeftEach(ArrayList<Alien> row) {
        for (Alien alien : row) {
            alien.setALienXLayout(alien.getALienXLayout() - dX);
        }
    }

    public void moveLeft() {
        if (getMostLeftAlienX() - dX >= 0 && !moveRight) {
            moveLeftEach(firstRow);
            moveLeftEach(secondRow);
            moveLeftEach(thirdRow);
            moveLeftEach(forthRow);
            moveLeftEach(fifthRow);
        } else {
            moveRight = true;
            moveDown = true;
            moveDown();
        }
    }

    public boolean checkIsHitEach(double xShot, double yShot, ArrayList<Alien> row, int i) {
        if (yShot - Shot.ARROW_HEIGHT <= row.get(i).getALienYLayout() && yShot >= row.get(i).getALienYLayout() - Alien.ALIEN_HEIGHT) {
            row.get(i).setIsAlive(false);
            return true;
        }
        return false;
    }

    public boolean checkIsHit(double xShot, double yShot) {
        for (int i = 0; i < numberALiensInArrow; i++) {
            if (fifthRow.get(i).getIsAlive() && xShot >= fifthRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= fifthRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                if (checkIsHitEach(xShot, yShot, fifthRow, i)) return checkIsHitEach(xShot, yShot, fifthRow, i);
            } else if (forthRow.get(i).getIsAlive() && xShot >= forthRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= forthRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                if (checkIsHitEach(xShot, yShot, forthRow, i)) return checkIsHitEach(xShot, yShot, forthRow, i);
            } else if (thirdRow.get(i).getIsAlive() && xShot >= thirdRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= thirdRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                if (checkIsHitEach(xShot, yShot, thirdRow, i)) return checkIsHitEach(xShot, yShot, thirdRow, i);
            } else if (secondRow.get(i).getIsAlive() && xShot >= secondRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= secondRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                if (checkIsHitEach(xShot, yShot, secondRow, i)) return checkIsHitEach(xShot, yShot, secondRow, i);
            } else if (firstRow.get(i).getIsAlive() && xShot >= firstRow.get(i).getALienXLayout() && xShot + Shot.ARROW_WIDTH <= firstRow.get(i).getALienXLayout() + Alien.ALIEN_WIDTH) {
                if (checkIsHitEach(xShot, yShot, firstRow, i)) return checkIsHitEach(xShot, yShot, firstRow, i);
            }
        }
        return false;
    }

    public void moveArmy() {
        moveRight();
    }
}
