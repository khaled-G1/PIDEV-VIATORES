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
public class Chambre {
    
   private int id;
   private int hotel_id;
   private String type;
   private String nb_lits;
   private int etage;
   private int prix;
   private String vue;

    public Chambre() {
    }

    public Chambre(int id, int hotel_id, String type, String nb_lits, int etage, int prix, String vue) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.type = type;
        this.nb_lits = nb_lits;
        this.etage = etage;
        this.prix = prix;
        this.vue = vue;
    }

    public Chambre(int hotel_id, String type, String nb_lits, int etage, int prix, String vue) {
        this.hotel_id = hotel_id;
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

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
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


  

 
    
    
    
    
}
