package sample;

import java.util.ArrayList;

public class Users {
    private String name;
    private String surName;
    private String userName;
    private String password;
    private int Score;
    public static ArrayList<Users> allUsers = new ArrayList<>();

    public Users(String name, String surName, String userName, String password) throws Exception {
        if (!isThisUserNameExist(userName)) {
            this.name = name;
            this.surName = surName;
            this.userName = userName;
            this.password = password;
            allUsers.add(this);
        } else {
            throw new Exception();
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        if (getUserByUserName(userName).getPassword().equals(password))
            return true;
        return false;
    }


}
