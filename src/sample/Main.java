package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    public static int GAME_WIDTH = 1285;
    public static int GAME_HEIGHT = 685;
    Users currentUser = null;

    @Override

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Space Invaders Game");
        showSignInMenu(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

    Scene signIn, signUp;

    public void showSignInMenu(Stage primaryStage) {
        GridPane signInRoot = new GridPane();
        Text userName = new Text("username : ");
        Text password = new Text("password : ");
        TextField userNameField = new TextField("");
        PasswordField passwordField = new PasswordField();
        Button signUp = new Button("Create new Account");
        Button logIn = new Button("Log in");
        Button button = new Button("Button");

        signInRoot.setVgap(5);
        signInRoot.setHgap(5);
        signInRoot.add(userName, 2, 6);
        signInRoot.add(userNameField, 3, 6);
        signInRoot.add(password, 2, 8);
        signInRoot.add(passwordField, 3, 8);
        signInRoot.add(logIn, 2, 14);
        signInRoot.add(signUp, 2, 19);
        signInRoot.add(button, 2, 25);
        signInRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));
        signIn = new Scene(signInRoot, Main.GAME_WIDTH, Main.GAME_HEIGHT);
        button.setOnAction(e -> new SpaceInvaders(primaryStage, signIn));
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
        GridPane signUpRoot = new GridPane();
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
                    AlertBox.displayAlert("Success", "Your new Account successfully created.");
                    showSignInMenu(primaryStage);
                }
            } catch (Exception exception) {
                AlertBox.displayAlert("error!", " this username has been already used.\n\nplease choose another one.");
            }
        });
        back.setOnAction(e -> primaryStage.setScene(previousScene));
        signUpRoot.setVgap(5);
        signUpRoot.setHgap(5);
        signUpRoot.add(name, 2, 4);
        signUpRoot.add(nameField, 3, 4);
        signUpRoot.add(surname, 2, 6);
        signUpRoot.add(surNameField, 3, 6);
        signUpRoot.add(userName, 2, 8);
        signUpRoot.add(userNameField, 3, 8);
        signUpRoot.add(password, 2, 10);
        signUpRoot.add(passwordField, 3, 10);
        signUpRoot.add(createNewAccount, 2, 14);
        signUpRoot.add(back, 3, 14);
        signUpRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));
        signUp = new Scene(signUpRoot, Main.GAME_WIDTH, Main.GAME_HEIGHT);
        primaryStage.setScene(signUp);
    }

    public void showGameMenu(Stage primaryStage, Scene previousScene) {
        Scene gameMenu;
        Button newGame = new Button("New Game");
        Button changeUserName = new Button("Change Username");
        Button logOut = new Button("Log Out");
        GridPane gameMenuRoot = new GridPane();
        gameMenuRoot.setHgap(5);
        gameMenuRoot.setVgap(5);
        gameMenuRoot.add(newGame, 2, 4);
        gameMenuRoot.add(changeUserName, 2, 7);
        gameMenuRoot.add(logOut, 2, 10);
        gameMenuRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));
        gameMenu = new Scene(gameMenuRoot, GAME_WIDTH, GAME_HEIGHT);
        newGame.setOnAction(e -> new SpaceInvaders(primaryStage, gameMenu));
        changeUserName.setOnAction(e -> showChangeUserNameMenu(primaryStage, gameMenu));
        logOut.setOnAction(e -> {
            currentUser = null;
            primaryStage.setScene(previousScene);
        });
        primaryStage.setScene(gameMenu);
    }

    public void showChangeUserNameMenu(Stage primaryStage, Scene previousScene) {
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
        GridPane root = new GridPane();
        root.setVgap(5);
        root.setHgap(5);
        root.add(newUserName, 2, 3);
        root.add(newUserNameField, 3, 3);
        root.add(back, 2, 6);
        root.add(ok, 3, 6);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));
        Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
        primaryStage.setScene(scene);
    }
}
