/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.viatores.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bhk
 */
public class Maconnexion {

    final String url = "jdbc:mysql://localhost/test3";
    final String login = "root";
    final String password = "";
    Connection connexion;
    static Maconnexion instance;

    private Maconnexion() {
        try {
            connexion
                    = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion à la base de données");
        }
    }
    
    public static  Maconnexion getInstance(){
        if(instance == null)
            instance = new Maconnexion();
        return instance;
    }
    
    public Connection getConnection(){
        return connexion;
    }
}
