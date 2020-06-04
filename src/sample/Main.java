package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;

public class Main extends Application {
    public static int GAME_WIDTH = 1000;
    public static int GAME_HEIGHT = 600;
    public static Users currentUser = null;
    private Stage primaryStage;
    private final int MENU_WIDTH = 500;
    private final int MENU_HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Space Invaders Game");
        showSignInMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setPositionNode(Node node, Pane root, int x, int y) {
        root.getChildren().add(node);
        node.setLayoutX(x);
        node.setLayoutY(y);
    }

    public void showSignInMenu() {
        Text userName = new Text("username : ");
        Text password = new Text("password : ");
        TextField userNameField = new TextField("");
        PasswordField passwordField = new PasswordField();
        Button signUp = new Button("Create new Account");
        Button logIn = new Button("Log in");
        Pane signInRoot = setPositionSignIn(userName, password, userNameField, passwordField, logIn, signUp);
        Scene signIn = new Scene(signInRoot, MENU_WIDTH, MENU_HEIGHT);
        setActionSignIn(signUp, logIn, userNameField, passwordField, signIn);
        primaryStage.setScene(signIn);
        primaryStage.show();
    }

    public void setActionSignIn(Button signUp, Button logIn, TextField userNameField, TextField passwordField, Scene signIn) {
        signUp.setOnAction(e -> showSignUpMenu(signIn));
        logIn.setOnAction(e -> {
            if (!userNameField.getText().isEmpty() && !passwordField.getText().isEmpty() && Users.isPasswordCorrect(userNameField.getText(), passwordField.getText())) {
                currentUser = Users.getUserByUserName(userNameField.getText());
                showGameMenu(signIn);
            } else {
                if (!userNameField.getText().isEmpty() && !passwordField.getText().isEmpty())
                    new AlertBox("Error!", "This username or this this password is wrong");
            }
        });
    }

    public Pane setPositionSignIn(Text userName, Text password, TextField userNameField, TextField passwordField, Button logIn, Button signUp) {
        Pane signInRoot = new Pane();
        setPositionNode(userName, signInRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2 - 90);
        userName.setFont(Font.font(18));
        setPositionNode(userNameField, signInRoot, MENU_WIDTH / 2 - 15, MENU_HEIGHT / 2 - 110);
        setPositionNode(password, signInRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2 - 45);
        password.setFont(Font.font(18));
        setPositionNode(passwordField, signInRoot, MENU_WIDTH / 2 - 15, MENU_HEIGHT / 2 - 65);
        setPositionNode(logIn, signInRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2);
        setPositionNode(signUp, signInRoot, MENU_WIDTH / 2, MENU_HEIGHT / 2);
        signInRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        return signInRoot;
    }

    public void showSignUpMenu(Scene previousScene) {
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
        Pane signUpRoot = setPositionSignUp(name, surname, userName, password, nameField, surNameField, userNameField, passwordField, createNewAccount, back);
        setActionSignUp(createNewAccount, back, nameField, surNameField, userNameField, passwordField, previousScene);
        primaryStage.setScene(new Scene(signUpRoot, MENU_WIDTH, MENU_HEIGHT));
    }

    public void setActionSignUp(Button createNewAccount, Button back, TextField nameField, TextField surNameField, TextField userNameField, TextField passwordField, Scene previousScene) {
        createNewAccount.setOnAction(e -> {
            try {
                if (!nameField.getText().isEmpty() && !surNameField.getText().isEmpty() && !userNameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    new Users(nameField.getText(), surNameField.getText(), userNameField.getText(), passwordField.getText());
                    showSignInMenu();
                    new AlertBox("Success", "Your new Account successfully created.");
                }
            } catch (Exception exception) {
                new AlertBox("error!", " this username has been already used.\n\nplease choose another one.");
            }
        });
        back.setOnAction(e -> primaryStage.setScene(previousScene));
    }

    public Pane setPositionSignUp(Text name, Text surname, Text userName, Text password, TextField nameField, TextField surNameField, TextField userNameField, PasswordField passwordField, Button createNewAccount, Button back) {
        Pane signUpRoot = new Pane();
        setPositionNode(name, signUpRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2 - 120);
        name.setFont(Font.font(17));
        setPositionNode(nameField, signUpRoot, MENU_WIDTH / 2 - 15, MENU_HEIGHT / 2 - 140);
        setPositionNode(surname, signUpRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2 - 75);
        surname.setFont(Font.font(17));
        setPositionNode(surNameField, signUpRoot, MENU_WIDTH / 2 - 15, MENU_HEIGHT / 2 - 95);
        setPositionNode(userName, signUpRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2 - 30);
        userName.setFont(Font.font(17));
        setPositionNode(userNameField, signUpRoot, MENU_WIDTH / 2 - 15, MENU_HEIGHT / 2 - 50);
        setPositionNode(password, signUpRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2 + 15);
        password.setFont(Font.font(17));
        setPositionNode(passwordField, signUpRoot, MENU_WIDTH / 2 - 15, MENU_HEIGHT / 2 - 5);
        setPositionNode(back, signUpRoot, MENU_WIDTH / 2 + 25, MENU_HEIGHT / 2 + 70);
        setPositionNode(createNewAccount, signUpRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2 + 70);
        signUpRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        return signUpRoot;
    }

    public void showGameMenu(Scene previousScene) {
        Button newGame = new Button("New Game");
        Button changeUserName = new Button("Change Username");
        Button logOut = new Button("Log Out");
        Button scoreBoard = new Button("Score Board");
        Button setting = new Button("Setting");
        Pane gameMenuRoot = setPositionGameMenu(newGame, changeUserName, logOut, scoreBoard, setting);
        Scene gameMenu = new Scene(gameMenuRoot, MENU_WIDTH, MENU_HEIGHT);
        newGame.setOnAction(e -> new SpaceInvaders(primaryStage, gameMenu));
        changeUserName.setOnAction(e -> showChangeUserNameMenu(gameMenu));
        scoreBoard.setOnAction(e -> showScoreBoards(gameMenu));
        setting.setOnAction(e -> showSetting(primaryStage.getScene()));
        logOut.setOnAction(e -> {
            currentUser = null;
            primaryStage.setScene(previousScene);
        });
        primaryStage.setScene(gameMenu);
    }

    public Pane setPositionGameMenu(Button newGame, Button changeUserName, Button logOut, Button scoreBoard, Button setting) {
        Pane gameMenuRoot = new Pane();
        setPositionNode(newGame, gameMenuRoot, MENU_WIDTH / 2 - 60, MENU_HEIGHT / 2 - 180);
        newGame.setFont(Font.font(20));
        setPositionNode(changeUserName, gameMenuRoot, MENU_WIDTH / 2 - 90, MENU_HEIGHT / 2 - 110);
        changeUserName.setFont(Font.font(20));
        setPositionNode(scoreBoard, gameMenuRoot, MENU_WIDTH / 2 - 65, MENU_HEIGHT / 2 - 40);
        scoreBoard.setFont(Font.font(20));
        setPositionNode(setting, gameMenuRoot, MENU_WIDTH / 2 - 45, MENU_HEIGHT / 2 + 30);
        setting.setFont(Font.font(20));
        setPositionNode(logOut, gameMenuRoot, MENU_WIDTH / 2 - 45, MENU_HEIGHT / 2 + 100);
        logOut.setFont(Font.font(20));
        gameMenuRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        return gameMenuRoot;
    }

    public void showChangeUserNameMenu(Scene previousScene) {
        Text newUserName = new Text("New username  :  ");
        TextField newUserNameField = new TextField("");
        Button ok = new Button("Ok");
        Button back = new Button("Back");
        ok.setOnAction(e -> {
            if (!Users.isThisUserNameExist(newUserNameField.getText()) && !newUserNameField.getText().isEmpty()) {
                currentUser.setUserName(newUserNameField.getText());
                new AlertBox("Success", "The username changed successfully.");
            } else {
                if (!newUserNameField.getText().isEmpty())
                    new AlertBox("Error", "This username has been already used.\n\nPlease choose another one.");
            }
        });
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        Pane changeUserNameRoot = setPositionChangeUser(newUserName, newUserNameField, back, ok);
        primaryStage.setScene(new Scene(changeUserNameRoot, MENU_WIDTH, MENU_HEIGHT));
    }

    public Pane setPositionChangeUser(Text newUserName, TextField newUserNameField, Button back, Button ok) {
        Pane changeUserNameRoot = new Pane();
        setPositionNode(newUserName, changeUserNameRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2 - 100);
        newUserName.setFont(Font.font(17));
        setPositionNode(newUserNameField, changeUserNameRoot, MENU_WIDTH / 2 + 20, MENU_HEIGHT / 2 - 120);
        setPositionNode(back, changeUserNameRoot, MENU_WIDTH / 2 - 155, MENU_HEIGHT / 2 - 60);
        back.setFont(Font.font(15));
        setPositionNode(ok, changeUserNameRoot, MENU_WIDTH / 2 - 80, MENU_HEIGHT / 2 - 60);
        ok.setFont(Font.font(15));
        changeUserNameRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        return changeUserNameRoot;
    }

    class CompareByScore implements Comparator<Users> {

        @Override
        public int compare(Users o1, Users o2) {
            return o1.getScore() - o2.getScore();
        }
    }

    public void showScoreBoards(Scene previousScene) {
        int x = MENU_WIDTH / 2 - 155;
        int y = 25;
        Pane scoreBoardRoot = new Pane();
        manageShowScore(scoreBoardRoot, x, y);
        Button back = new Button("Back");
        setPositionNode(back, scoreBoardRoot, 0, 0);
        back.setFont(Font.font(16));
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        scoreBoardRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, null, null)));
        Scene scoreBoard = new Scene(scoreBoardRoot, MENU_WIDTH, MENU_HEIGHT);
        primaryStage.setScene(scoreBoard);
    }

    public void manageShowScore(Pane scoreBoardRoot, int x, int y) {
        if (Users.allUsers.size() > 0) {
            ArrayList<Users> all = Users.allUsers;
            all.sort(new CompareByScore());
            int i = 1;
            for (Users users : all) {
                Text user = new Text(i++ + "  " + "User     :        " + users.getUserName());
                Text score = new Text("Score   :        " + users.getBestScore());
                setPositionNode(user, scoreBoardRoot, x, y);
                user.setFont(Font.font(16));
                setPositionNode(score, scoreBoardRoot, x + 260, y);
                score.setFont(Font.font(16));
                y += 20;
            }
        }
    }

    public void showSetting(Scene previousScene) {
        Pane settingRoot = new Pane();
        Button backGroundSound = new Button("Background Music");
        backGroundSound.setFont(Font.font(20));
        Button gameSound = new Button("Game Sound");
        gameSound.setFont(Font.font(20));
        Button back = new Button("Back");
        back.setFont(Font.font(20));
        setPositionNode(backGroundSound, settingRoot, MENU_WIDTH / 2 - 90, MENU_HEIGHT / 2 - 110);
        setPositionNode(gameSound, settingRoot, MENU_WIDTH / 2 - 65, MENU_HEIGHT / 2 - 30);
        setPositionNode(back, settingRoot, MENU_WIDTH / 2 - 30, MENU_HEIGHT / 2 + 50);
        backGroundSound.setOnAction(e -> showSoundOptionMenu(primaryStage.getScene(), true));
        gameSound.setOnAction(e -> showSoundOptionMenu(primaryStage.getScene(), false));
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        settingRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        Scene scoreBoard = new Scene(settingRoot, MENU_WIDTH, MENU_HEIGHT);
        primaryStage.setScene(scoreBoard);
    }

    public void showSoundOptionMenu(Scene previousScene, boolean backGround) {
        final int SOUND_WIDTH = 250;
        final int SOUND_HEIGHT = 250;
        Pane soundRoot = new Pane();
        Button on = new Button("ON");
        on.setFont(Font.font(20));
        Button off = new Button("OFF");
        off.setFont(Font.font(20));
        Button back = new Button("Back");
        back.setFont(Font.font(20));
        setActionAndPositionSoundOption(on, off, back, soundRoot, SOUND_WIDTH, SOUND_HEIGHT, previousScene, backGround);
        soundRoot.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGRAY, null, null)));
        primaryStage.setScene(new Scene(soundRoot, SOUND_WIDTH, SOUND_HEIGHT));
    }

    public void setActionAndPositionSoundOption(Button on, Button off, Button back, Pane soundRoot, int width, int height, Scene previousScene, boolean backGround) {
        setPositionNode(on, soundRoot, width / 2 - 80, height / 2 - 60);
        setPositionNode(off, soundRoot, width / 2 + 20, height / 2 - 60);
        setPositionNode(back, soundRoot, width / 2 - 80, height / 2 + 10);
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        if (backGround) {
            on.setOnAction(e -> currentUser.setBackGroundSound(true));
            off.setOnAction(e -> currentUser.setBackGroundSound(false));
        } else {
            on.setOnAction(e -> currentUser.setGameSound(true));
            off.setOnAction(e -> currentUser.setGameSound(false));
        }
    }
}
