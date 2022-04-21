/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.viatores.seervice;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.viatores.entities.Hotel;
import edu.viatores.tools.Maconnexion;

/**
 *
 * @author Administrateur
 */
public class HotelService implements IHotelService{
  Connection connexion;   
  public HotelService() {
        connexion = Maconnexion.getInstance().getConnection();
    }
 

  @Override
  public void ajouterHotel(Hotel e) throws SQLException {
        String req = "INSERT INTO `hotel` (`ville`,`nom`,`nb_chambre`,`imghotel`) "
                + "VALUES (?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, e.getVille());
        ps.setString(2, e.getNom());
       ps.setInt(3, e.getNb_chambre());
        ps.setString(4, e.getImghotel());
             

        ps.executeUpdate();
    }
  
  @Override
     public List<Hotel> AfficherAllhotel() throws SQLException {

        List<Hotel> hotels = new ArrayList<>();
        String req = "select * from hotel ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Hotel e = new Hotel(rst.getInt("id")
                    , rst.getString("ville")
                    , rst.getString("nom")
                    , rst.getInt("nb_chambre")
                    , rst.getString("imghotel"));
            hotels.add(e);
        }
        return hotels;
    }
     
     
             
             
    @Override         
   public List<Hotel> AfficherAllHotelsByVille() throws SQLException {

        List<Hotel> hotels = new ArrayList<>();
        String req = "select * from hotel order by ville ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Hotel e = new Hotel(rst.getInt("id")
                 , rst.getString("ville")
                    , rst.getString("nom")
                    , rst.getInt("nb_chambre")
                    , rst.getString("imghotel"));
            hotels.add(e);
        }
        return hotels;
    }
   @Override         
   public List<Hotel> AfficherAllhotelByNom() throws SQLException {

        List<Hotel> hotels = new ArrayList<>();
        String req = "select * from hotel order by nom ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Hotel e = new Hotel(rst.getInt("id")
                   , rst.getString("ville")
                    , rst.getString("nom")
                    , rst.getInt("nb_chambre")
                    , rst.getString("imghotel"));
            hotels.add(e);
        }
        return hotels;
    }
   
 /* @Override
   public List<Hotel> Recherchhotel(String Nom) throws SQLException {
   List<Hotel> hotels = new ArrayList<>();
            String req = "select  * from  hotel c  where c.nom LIKE'" + Nom + "'";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                  Hotel e = new Hotel(rst.getInt("id")
                       , rst.getString("ville")
                    , rst.getString("nom")
                    , rst.getInt("nb_chambre")
                    , rst.getString("imghotel"));
            hotels.add(e);
               
            }
     return hotels;
   
    }
   
  */ 
   public List<Hotel> Recherchhotel(String rech) {
        List<Hotel> myList = new ArrayList<>();
        String req="select * from hotel where nom like '%"+rech+"%' ";
        //String req = "select * from platter where NbPlatter like '%" + 5 + "%' ";
        // String req="select * from platter where NbPlatter like CONCAT('%',?,'%')";

        try {
            Statement stm = connexion.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                //rs.next();
                Hotel h = new Hotel();
                h.setId(rs.getInt("id")); // Remplir Platter
                h.setVille(rs.getString("ville"));
                h.setNom(rs.getString("nom"));
                h.setNb_chambre(rs.getInt("nb_chambre"));
                h.setImghotel(rs.getString("imghotel"));
             
                myList.add(h);//insert platter in My list

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
   

 @Override
     public void Supprimerhotel(Hotel e) throws SQLException {

        String req = "DELETE FROM hotel WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     
     
     @Override
   public void supp2(Hotel m) throws SQLException {

        String req = "DELETE FROM hotel WHERE id ="+m.getId()+"";
     
         PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
     
    }
   
   
  @Override
      public void modifierhotel(Hotel e,Hotel e2) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE hotel SET "
                + " ville='"+e.getVille()+"'"
                + ", nom='"+e.getNom()+"'"
                + ", nb_chambre  ='"+ e.getNb_chambre()+"'"
                + ", imghotel='"+e.getImghotel()+"' where id  = "+e2.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }  

   
      
      
      
      
      
}
