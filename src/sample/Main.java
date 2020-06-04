package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class Main extends Application {
    public static int GAME_WIDTH = 1000;
    public static int GAME_HEIGHT = 600;
    public static Users currentUser = null;

    @Override

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Space Invaders Game");
        showSignInMenu(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

    Scene signIn, signUp;

    public static void setPositionNode(Node node, Pane root, int x, int y) {
        root.getChildren().add(node);
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    public void showSignInMenu(Stage primaryStage) {
        final int SIGN_IN_WIDTH = 500;
        final int SIGN_IN_HEIGHT = 500;
        Pane signInRoot = new Pane();
        Text userName = new Text("username : ");
        Text password = new Text("password : ");
        TextField userNameField = new TextField("");
        PasswordField passwordField = new PasswordField();
        Button signUp = new Button("Create new Account");
        Button logIn = new Button("Log in");
        Button button = new Button("Button");
        setPositionNode(userName, signInRoot, SIGN_IN_WIDTH / 2 - 155, SIGN_IN_HEIGHT / 2 - 90);
        userName.setFont(Font.font(18));
        setPositionNode(userNameField, signInRoot, SIGN_IN_WIDTH / 2 - 15, SIGN_IN_HEIGHT / 2 - 110);
        setPositionNode(password, signInRoot, SIGN_IN_WIDTH / 2 - 155, SIGN_IN_HEIGHT / 2 - 45);
        password.setFont(Font.font(18));
        setPositionNode(passwordField, signInRoot, SIGN_IN_WIDTH / 2 - 15, SIGN_IN_HEIGHT / 2 - 65);
        setPositionNode(logIn, signInRoot, SIGN_IN_WIDTH / 2 - 155, SIGN_IN_HEIGHT / 2);
        setPositionNode(signUp, signInRoot, SIGN_IN_WIDTH / 2, SIGN_IN_HEIGHT / 2);
        signInRoot.getChildren().add(button);
        signInRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        signIn = new Scene(signInRoot, SIGN_IN_WIDTH, SIGN_IN_HEIGHT);
        button.setOnAction(e -> showGameMenu(primaryStage, signIn));
        signUp.setOnAction(e -> showSignUpMenu(primaryStage, signIn));
        logIn.setOnAction(e -> {
            if (!userNameField.getText().isEmpty() && !passwordField.getText().isEmpty() && Users.isPasswordCorrect(userNameField.getText(), passwordField.getText())) {
                currentUser = Users.getUserByUserName(userNameField.getText());
                showGameMenu(primaryStage, signIn);
            } else AlertBox.displayAlert("Error!", "This username or this this password is wrong");
        });
        primaryStage.setScene(signIn);
        primaryStage.show();
    }

    public void showSignUpMenu(Stage primaryStage, Scene previousScene) {
        final int SIGN_UP_WIDTH = 500;
        final int SIGN_UP_HEIGHT = 500;
        Pane signUpRoot = new Pane();
        Text name = new Text("name : ");
        Text surname = new Text("surname : ");
        Text userName = new Text("username : ");
        Text password = new Text("password : ");
        TextField nameField = new TextField("");
        TextField surNameField = new TextField("");
        TextField userNameField = new TextField("");
        PasswordField passwordField = new PasswordField();
        Button createNewAccount = new Button("Create New Account");
        Button back = new Button("Back");
        createNewAccount.setOnAction(e -> {
            try {
                if (!nameField.getText().isEmpty() && !surNameField.getText().isEmpty() && !userNameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    new Users(nameField.getText(), surNameField.getText(), userNameField.getText(), passwordField.getText());
                    showSignInMenu(primaryStage);
                    AlertBox.displayAlert("Success", "Your new Account successfully created.");
                }
            } catch (Exception exception) {
                AlertBox.displayAlert("error!", " this username has been already used.\n\nplease choose another one.");
            }
        });
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        setPositionNode(name, signUpRoot, SIGN_UP_WIDTH / 2 - 155, SIGN_UP_HEIGHT / 2 - 120);
        name.setFont(Font.font(17));
        setPositionNode(nameField, signUpRoot, SIGN_UP_WIDTH / 2 - 15, SIGN_UP_HEIGHT / 2 - 140);
        setPositionNode(surname, signUpRoot, SIGN_UP_WIDTH / 2 - 155, SIGN_UP_HEIGHT / 2 - 75);
        surname.setFont(Font.font(17));
        setPositionNode(surNameField, signUpRoot, SIGN_UP_WIDTH / 2 - 15, SIGN_UP_HEIGHT / 2 - 95);
        setPositionNode(userName, signUpRoot, SIGN_UP_WIDTH / 2 - 155, SIGN_UP_HEIGHT / 2 - 30);
        userName.setFont(Font.font(17));
        setPositionNode(userNameField, signUpRoot, SIGN_UP_WIDTH / 2 - 15, SIGN_UP_HEIGHT / 2 - 50);
        setPositionNode(password, signUpRoot, SIGN_UP_WIDTH / 2 - 155, SIGN_UP_HEIGHT / 2 + 15);
        password.setFont(Font.font(17));
        setPositionNode(passwordField, signUpRoot, SIGN_UP_WIDTH / 2 - 15, SIGN_UP_HEIGHT / 2 - 5);
        setPositionNode(back, signUpRoot, SIGN_UP_WIDTH / 2 + 25, SIGN_UP_HEIGHT / 2 + 70);
        setPositionNode(createNewAccount, signUpRoot, SIGN_UP_WIDTH / 2 - 155, SIGN_UP_HEIGHT / 2 + 70);
        signUpRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        signUp = new Scene(signUpRoot, SIGN_UP_WIDTH, SIGN_UP_HEIGHT);
        primaryStage.setScene(signUp);
    }

    public static void showGameMenu(Stage primaryStage, Scene previousScene) {
        final int GAME_MENU_WIDTH = 500;
        final int GAME_MENU_HEIGHT = 500;
        Scene gameMenu;
        Button newGame = new Button("New Game");
        Button changeUserName = new Button("Change Username");
        Button logOut = new Button("Log Out");
        Button scoreBoard = new Button("Score Board");
        Button setting = new Button("Setting");
        Pane gameMenuRoot = new Pane();
        setPositionNode(newGame, gameMenuRoot, GAME_MENU_WIDTH / 2 - 60, GAME_MENU_HEIGHT / 2 - 180);
        newGame.setFont(Font.font(20));
        setPositionNode(changeUserName, gameMenuRoot, GAME_MENU_WIDTH / 2 - 90, GAME_MENU_HEIGHT / 2 - 110);
        changeUserName.setFont(Font.font(20));
        setPositionNode(scoreBoard, gameMenuRoot, GAME_MENU_WIDTH / 2 - 65, GAME_MENU_HEIGHT / 2 - 40);
        scoreBoard.setFont(Font.font(20));
        setPositionNode(setting, gameMenuRoot, GAME_MENU_WIDTH / 2 - 45, GAME_MENU_HEIGHT / 2 + 30);
        setting.setFont(Font.font(20));
        setPositionNode(logOut, gameMenuRoot, GAME_MENU_WIDTH / 2 - 45, GAME_MENU_HEIGHT / 2 + 100);
        logOut.setFont(Font.font(20));
        gameMenuRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        gameMenu = new Scene(gameMenuRoot, GAME_MENU_WIDTH, GAME_MENU_HEIGHT);
        newGame.setOnAction(e -> new SpaceInvaders(primaryStage, gameMenu));
        changeUserName.setOnAction(e -> showChangeUserNameMenu(primaryStage, gameMenu));
        scoreBoard.setOnAction(e -> showScoreBoards(primaryStage, gameMenu));
        setting.setOnAction(e -> showSetting(primaryStage, primaryStage.getScene()));
        logOut.setOnAction(e -> {
            currentUser = null;
            primaryStage.setScene(previousScene);
        });
        primaryStage.setScene(gameMenu);
    }

    public static void showChangeUserNameMenu(Stage primaryStage, Scene previousScene) {
        final int CHANGE_USERNAME_MENU_WIDTH = 500;
        final int CHANGE_USERNAME_MENU_HEIGHT = 500;
        Text newUserName = new Text("New username  :  ");
        TextField newUserNameField = new TextField("");
        Button ok = new Button("Ok");
        Button back = new Button("Back");
        ok.setOnAction(e -> {
            if (!Users.isThisUserNameExist(newUserNameField.getText())) {
                currentUser.setUserName(newUserNameField.getText());
                AlertBox.displayAlert("Success", "The username changed successfully.");
            } else {
                AlertBox.displayAlert("Error", "This username has been already used.\n\nPlease choose another one.");
            }
        });
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        Pane changeUserNameRoot = new Pane();
        setPositionNode(newUserName, changeUserNameRoot, CHANGE_USERNAME_MENU_WIDTH / 2 - 155, CHANGE_USERNAME_MENU_HEIGHT / 2 - 100);
        newUserName.setFont(Font.font(17));
        setPositionNode(newUserNameField, changeUserNameRoot, CHANGE_USERNAME_MENU_WIDTH / 2 + 20, CHANGE_USERNAME_MENU_HEIGHT / 2 - 120);
        setPositionNode(back, changeUserNameRoot, CHANGE_USERNAME_MENU_WIDTH / 2 - 155, CHANGE_USERNAME_MENU_HEIGHT / 2 - 60);
        back.setFont(Font.font(15));
        setPositionNode(ok, changeUserNameRoot, CHANGE_USERNAME_MENU_WIDTH / 2 - 80, CHANGE_USERNAME_MENU_HEIGHT / 2 - 60);
        ok.setFont(Font.font(15));
        changeUserNameRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        Scene scene = new Scene(changeUserNameRoot, CHANGE_USERNAME_MENU_WIDTH, CHANGE_USERNAME_MENU_HEIGHT);
        primaryStage.setScene(scene);
    }

    static class CompareByScore implements Comparator<Users> {

        @Override
        public int compare(Users o1, Users o2) {
            return o1.getScore() - o2.getScore();
        }
    }

    public static void showScoreBoards(Stage primaryStage, Scene previousScene) {
        final int SHOW_SCORE_BOARD_WIDTH = 500;
        final int SHOW_SCORE_BOARD_HEIGHT = 500;
        int x = SHOW_SCORE_BOARD_WIDTH / 2 - 155;
        int y = 25;
        Pane scoreBoardRoot = new Pane();
        if (Users.allUsers.size() > 0) {
            ArrayList<Users> all = Users.allUsers;
            Collections.sort(all, new CompareByScore());
            int i = 1;
            for (Users users : all) {
                Text user = new Text(i++ + ".  " + "User     :        " + users.getUserName());
                Text score = new Text("Score   :        " + users.getBestScore());
                setPositionNode(user, scoreBoardRoot, x, y);
                user.setFont(Font.font(16));
                setPositionNode(score, scoreBoardRoot, x + 160, y);
                score.setFont(Font.font(16));
                y += 20;
            }
        }
        Button back = new Button("Back");
        setPositionNode(back, scoreBoardRoot, 0, 0);
        back.setFont(Font.font(16));
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        scoreBoardRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, null, null)));
        Scene scoreBoard = new Scene(scoreBoardRoot, SHOW_SCORE_BOARD_WIDTH, SHOW_SCORE_BOARD_HEIGHT);
        primaryStage.setScene(scoreBoard);
    }

    public static void showSetting(Stage primaryStage, Scene previousScene) {
        final int SETTING_WIDTH = 500;
        final int SETTING_HEIGHT = 500;
        Pane settingRoot = new Pane();
        Button backGroundSound = new Button("Background Music");
        backGroundSound.setFont(Font.font(20));
        Button gameSound = new Button("Game Sound");
        gameSound.setFont(Font.font(20));
        Button back = new Button("Back");
        back.setFont(Font.font(20));
        setPositionNode(backGroundSound, settingRoot, SETTING_WIDTH / 2 - 90, SETTING_HEIGHT / 2 - 110);
        setPositionNode(gameSound, settingRoot, SETTING_WIDTH / 2 - 65, SETTING_HEIGHT / 2 - 30);
        setPositionNode(back, settingRoot, SETTING_WIDTH / 2 - 30, SETTING_HEIGHT / 2 + 50);
        backGroundSound.setOnAction(e -> showSoundOptionMenu(primaryStage, primaryStage.getScene(), true));
        gameSound.setOnAction(e -> showSoundOptionMenu(primaryStage, primaryStage.getScene(), false));
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        settingRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        Scene scoreBoard = new Scene(settingRoot, SETTING_WIDTH, SETTING_HEIGHT);
        primaryStage.setScene(scoreBoard);
    }

    public static void showSoundOptionMenu(Stage primaryStage, Scene previousScene, boolean backGround) {
        final int SOUND_WIDTH = 250;
        final int SOUND_HEIGHT = 250;
        Pane soundRoot = new Pane();
        Button on = new Button("ON");
        on.setFont(Font.font(20));
        Button off = new Button("OFF");
        off.setFont(Font.font(20));
        Button back = new Button("Back");
        back.setFont(Font.font(20));
        setPositionNode(on, soundRoot, SOUND_WIDTH / 2 - 80, SOUND_HEIGHT / 2 - 60);
        setPositionNode(off, soundRoot, SOUND_WIDTH / 2 + 20, SOUND_HEIGHT / 2 - 60);
        setPositionNode(back, soundRoot, SOUND_WIDTH / 2 - 80, SOUND_HEIGHT / 2 + 10);
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        if (backGround) {
            on.setOnAction(e -> currentUser.setBackGroundSound(true));
            off.setOnAction(e -> currentUser.setBackGroundSound(false));
        } else {
            on.setOnAction(e -> currentUser.setGameSound(true));
            off.setOnAction(e -> currentUser.setGameSound(false));
        }
        soundRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        Scene scoreBoard = new Scene(soundRoot, SOUND_WIDTH, SOUND_HEIGHT);
        primaryStage.setScene(scoreBoard);
    }
}
