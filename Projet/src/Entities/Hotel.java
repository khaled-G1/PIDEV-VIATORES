/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Administrateur
 */
public class Hotel {
   
    private int id;
    private String ville;
    private String nom;
    private int nb_chambre;
    private String imghotel;

    public Hotel() {
    }

    public Hotel(int id, String ville, String nom, int nb_chambre, String imghotel) {
        this.id = id;
        this.ville = ville;
        this.nom = nom;
        this.nb_chambre = nb_chambre;
        this.imghotel = imghotel;
    }

    public Hotel(String ville, String nom, int nb_chambre, String imghotel) {
        this.ville = ville;
        this.nom = nom;
        this.nb_chambre = nb_chambre;
        this.imghotel = imghotel;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "hotel{" + "id=" + id + ", ville=" + ville + ", nom=" + nom + ", nb_chambre=" + nb_chambre + ", imghotel=" + imghotel + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNb_chambre() {
        return nb_chambre;
    }

    public void setNb_chambre(int nb_chambre) {
        this.nb_chambre = nb_chambre;
    }

    public String getImghotel() {
        return imghotel;
    }

    public void setImghotel(String imghotel) {
        this.imghotel = imghotel;
    }
    
    
    
    
}
