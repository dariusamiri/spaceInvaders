package sample;

import java.util.ArrayList;

public class Users {
    private String name;
    private String surName;
    private String userName;
    private String password;
    private int Score;
    private int bestScore;
    public static ArrayList<Users> allUsers = new ArrayList<>();
    private boolean gameSound = true;
    private boolean backGroundSound = true;

    public Users(String name, String surName, String userName, String password) throws Exception {
        if (!isThisUserNameExist(userName)) {
            this.name = name;
            this.surName = surName;
            this.userName = userName;
            this.password = password;
            this.setScore(0);
            allUsers.add(this);
        } else {
            throw new Exception();
        }
    }

    public boolean isGameSound() {
        return gameSound;
    }

    public boolean isBackGroundSound() {
        return backGroundSound;
    }

    public int getScore() {
        return Score;
    }

    public int getBestScore() {
        return bestScore;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGameSound(boolean gameSound) {
        this.gameSound = gameSound;
    }

    public void setBackGroundSound(boolean backGroundSound) {
        this.backGroundSound = backGroundSound;
    }

    public static boolean isThisUserNameExist(String userName) {
        for (Users allUser : allUsers) {
            if (allUser.getUserName().equals(userName))
                return true;
        }

        return false;
    }

    public static Users getUserByUserName(String userName) {
        for (Users allUser : allUsers) {
            if (allUser.getUserName().equals(userName))
                return allUser;
        }
        return null;
    }

    public static boolean isPasswordCorrect(String userName, String password) {
        if (isThisUserNameExist(userName) && getUserByUserName(userName).getPassword().equals(password))
            return true;
        return false;
    }


}
