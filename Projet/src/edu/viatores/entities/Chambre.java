/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.viatores.entities;

/**
 *
 * @author Administrateur
 */
public class Chambre {
    
   private int id;
   private String type;
   private String nb_lits;
   private int etage;
   private int prix;
   private String vue;
    private Hotel hotel;

    public Chambre() {
    }

    public Chambre(int id, String type, String nb_lits, int etage, int prix, String vue, Hotel hotel) {
        this.id = id;
        this.type = type;
        this.nb_lits = nb_lits;
        this.etage = etage;
        this.prix = prix;
        this.vue = vue;
        this.hotel = hotel;
    }

    public Chambre(String type, String nb_lits, int etage, int prix, String vue, Hotel hotel) {
        this.type = type;
        this.nb_lits = nb_lits;
        this.etage = etage;
        this.prix = prix;
        this.vue = vue;
        this.hotel = hotel;
    }
    public Chambre(String type, String nb_lits, int etage, int prix, String vue, int hotel) {
        this.type = type;
        this.nb_lits = nb_lits;
        this.etage = etage;
        this.prix = prix;
        this.vue = vue;
      
    }
    public Chambre(int id, String type, String nb_lits, int etage, int prix, String vue) {
        this.id = id;
        this.type = type;
        this.nb_lits = nb_lits;
        this.etage = etage;
        this.prix = prix;
        this.vue = vue;
    }

    public Chambre(String type, String nb_lits, int etage, int prix, String vue) {
        this.type = type;
        this.nb_lits = nb_lits;
        this.etage = etage;
        this.prix = prix;
        this.vue = vue;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNb_lits() {
        return nb_lits;
    }

    public void setNb_lits(String nb_lits) {
        this.nb_lits = nb_lits;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getVue() {
        return vue;
    }

    public void setVue(String vue) {
        this.vue = vue;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "chambre{" + "id=" + id + ", type=" + type + ", nb_lits=" + nb_lits + ", etage=" + etage + ", prix=" + prix + ", vue=" + vue + ", hotel=" + hotel + '}';
    }
    
    
    
    
}
