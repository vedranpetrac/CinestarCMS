/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.cinestar.model;

/**
 *
 * @author Vedran
 */
public final class User {


    private static String username;
    private static int level;

    private User() {
        username = null;
        level = 0;
    }

    public static void setLoin(String username_login, int level_login){
        username = username_login;
        level = level_login;
    }

    public static String getUsername() {
        return username;
    }

    public static int getLevel() {
        return level;
    }

}
