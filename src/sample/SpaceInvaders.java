package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button exit;
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

        AnimationTimer spaceInvaders = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (Main.currentUser.isBackGroundSound()) {
                    backGroundSound.setAutoPlay(true);
                    backGroundSound.setCycleCount(MediaPlayer.INDEFINITE);
                    backGroundSound.setVolume(0.6);
                }
                if (gameOver) {
                    gameOver();
                    this.stop();
                }
                if (time > 5) {
                    army.moveArmy();
                    time = 0;
                }
                update();
                time++;
            }
        };
        spaceInvaders.start();
    }

    public ALienArmy getArmy() {
        return army;
    }

    public Player getPlayer() {
        return player;
    }

    public void setUserBestScore() {
        if (Main.currentUser.getScore() > Main.currentUser.getBestScore()) {
            Main.currentUser.setBestScore(Main.currentUser.getScore());
        }
        Main.currentUser.setScore(0);
    }

    public void gameOver() {
        setUserBestScore();
        primaryStage.setScene(previousScene);
        if (show) {
            AlertBox.displayAlert("Game Over!", "\nYou Lose!");
        }
        backGroundSound.stop();
    }

    public void paint() {
        player = new Player(primaryStage, currentRoot, this);
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
