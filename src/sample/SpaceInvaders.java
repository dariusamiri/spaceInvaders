package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    Text point;

    public SpaceInvaders(Stage primaryStage, Scene previousScene) throws FileNotFoundException {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
        Scene scene = new Scene(currentRoot, Main.GAME_WIDTH, Main.GAME_HEIGHT);
        Image image = new Image("file:ClearSpace.jpg");
        ImageView imageView = new ImageView(image);
        currentRoot.getChildren().add(imageView);
        point = new Text("score  :  " + 0);
        point.setFill(Color.WHITE);
        point.setFont(Font.font(38));
        point.setLayoutX(50);
        point.setLayoutY(50);
        currentRoot.getChildren().add(point);
        primaryStage.setScene(scene);
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
        player = new Player(primaryStage, currentRoot, this);
        player.drawShip();
        army = new ALienArmy(currentRoot);
        army.drawArmy();
    }


    public void update() {
        army.drawArmy();
        if (player.getShot() != null) {
            player.getShot().drawShot();
        }
        drawScoreBoard(player.getScore());
    }

    public void drawScoreBoard(int score) {
        point.setText("score  :  " + score);
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {

            }
            if (count > 200) {
                army.moveArmy();
                count = 0;
            }
            update();
            count++;
        }
    }
}
