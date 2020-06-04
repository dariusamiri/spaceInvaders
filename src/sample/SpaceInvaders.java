package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;


public class SpaceInvaders {
    private Stage primaryStage;
    private Scene previousScene;
    private Player player = null;
    private ALienArmy army = null;
    private Pane currentRoot = new Pane();
    private Text point;
    public static boolean gameOver;
    String name = "spaceinvaders.mp3";
    Media media = new Media(new File(name).toURI().toString());
    MediaPlayer backGroundSound = new MediaPlayer(media);
    int time = 0;
    public static boolean show = true;

    public SpaceInvaders(Stage primaryStage, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
        Image image = new Image("file:ClearSpace.jpg");
        ImageView imageView = new ImageView(image);
        currentRoot.getChildren().add(imageView);
        point = new Text("score  :  " + 0);
        point.setFill(Color.WHITE);
        point.setFont(Font.font(38));
        point.setLayoutX(50);
        point.setLayoutY(50);
        currentRoot.getChildren().add(point);
        Scene scene = new Scene(currentRoot, Main.GAME_WIDTH, Main.GAME_HEIGHT);
        primaryStage.setScene(scene);
        paint();
        gameOver = false;
        run();
    }

    public ALienArmy getArmy() {
        return army;
    }

    public Player getPlayer() {
        return player;
    }

    public Scene getPreviousScene() {
        return previousScene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setUserBestScore() {
        if (Main.currentUser.getScore() > Main.currentUser.getBestScore()) {
            Main.currentUser.setBestScore(Main.currentUser.getScore());
        }
        Main.currentUser.setScore(0);
    }

    public void runMusic() {
        if (Main.currentUser.isBackGroundSound()) {
            backGroundSound.setAutoPlay(true);
            backGroundSound.setCycleCount(MediaPlayer.INDEFINITE);
            backGroundSound.setVolume(0.6);
        }
    }

    public void win(AnimationTimer spaceInvaders) {
        if (!army.isRowArmyHasAlive(army.getFirstRow()) && !army.isRowArmyHasAlive(army.getSecondRow()) && !army.isRowArmyHasAlive(army.getThirdRow()) && !army.isRowArmyHasAlive(army.getForthRow()) && !army.isRowArmyHasAlive(army.getFifthRow())) {
            show = false;
            gameOver = false;
            spaceInvaders.stop();
            gameOver();
            new AlertBox("Won!", "You won!!!!  thank you for using our Game");
        }
    }

    public void run() {
        AnimationTimer spaceInvaders = new AnimationTimer() {
            @Override
            public void handle(long l) {
                runMusic();
                if (gameOver) {
                    gameOver();
                    this.stop();
                }
                if (time > 1) {
                    army.moveArmy();
                    time = 0;
                }
                win(this);
                update();
                time++;
            }
        };
        spaceInvaders.start();
    }

    public void gameOver() {
        setUserBestScore();
        primaryStage.setScene(previousScene);
        if (show) {
            new AlertBox("Game Over!", "Unfortunately you lose! May you win later");
        }
        backGroundSound.stop();
    }

    public void paint() {
        player = new Player(currentRoot, this);
        player.drawShip();
        army = new ALienArmy(currentRoot, this);
        army.drawArmy();
        new AlienShot(currentRoot, this);
    }

    public void update() {
        army.drawArmy();
        drawScoreBoard(Main.currentUser.getScore());
    }

    public void drawScoreBoard(int score) {
        point.setText("score  :  " + score);
    }
}
