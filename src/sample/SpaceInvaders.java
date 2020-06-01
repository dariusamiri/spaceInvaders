package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceInvaders implements Runnable {
    Stage primaryStage;
    Scene previousScene;
    Player player = null;
    ALienArmy army = null;
    Graphics offScreen_high;
    BufferedImage offScreen;
    Pane currentRoot = new Pane();
    ImageView imageView;

    public SpaceInvaders(Stage primaryStage, Scene previousScene) throws FileNotFoundException {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;

//        offScreen = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
//        offScreen_high = offScreen.createGraphics();
        Canvas canvas = new Canvas(Main.GAME_WIDTH, Main.GAME_HEIGHT);

        Scene scene = new Scene(currentRoot, Main.GAME_WIDTH, Main.GAME_HEIGHT);
        currentRoot.getChildren().add(canvas);
        GraphicsContext gc = new Canvas().getGraphicsContext2D();
        Image image = new Image("file:back3.jpg");
        ImageView imageView = new ImageView(image);
        currentRoot.getChildren().add(imageView);
        primaryStage.setScene(scene);

        player = new Player(primaryStage, currentRoot, this);
        player.drawShip();
        army = new ALienArmy(currentRoot);
        paint();
        Thread thread = new Thread(this);
        thread.start();
    }

    public ALienArmy getArmy() {
        return army;
    }

    public Player getPlayer() {
        return player;
    }

    public static void gameOver() {
        System.exit(0);
    }

    public void paint() {
        army.drawArmy();
    }


    public void update() {
        army.drawArmy();
        if (player.getArrow() != null) {
            player.getShot().drawShot();
        }
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {

            }
            if (count > 10) {
                army.moveArmy();
                count = 0;
            }
            update();
            count++;
        }
    }
}
