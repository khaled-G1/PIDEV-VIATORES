/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.MyDB;
import Entities.Chambre;
import Entities.Hotel;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class ChambreService {

    
    
      Connection connexion;   
  public ChambreService() {
        connexion = MyDB.getInstance().getConnection();
    }
    
    
        public void ajouter(Chambre C) { 
       
       String req = "INSERT INTO  `chambre` (`hotel_id`,`type`,`nb_lits`, `etage`,`prix`,`vue`) VALUE (?,?,?,?,?,?)";
    try {
                 PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, C.getHotel_id());
            ps.setString(2, C.getType());
            ps.setString(3,C.getNb_lits());
            ps.setInt(4,C.getEtage());
            ps.setInt(5,C.getPrix());
            ps.setString(6,C.getVue());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChambreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public List<Chambre> AfficherAllChambres() throws SQLException {

        List<Chambre> Chambres = new ArrayList<>();
        String req = "select * from chambre ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Chambre e = new Chambre(
                    rst.getInt("id"),
                    rst.getInt("hotel_id"),
                    
                    rst.getString("type")
                    , rst.getString("nb_lits")
                    , rst.getInt("etage")
                    , rst.getInt("prix")
                   
                    , rst.getString("vue"));
             
            Chambres.add(e);
        }
        return Chambres;
    }
    
       public List<Chambre> AfficherAllChambresByHotel() throws SQLException {

        List<Chambre> Chambres = new ArrayList<>();
        String req = "select * from chambre order by hotel_id ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Chambre e = new Chambre( 
                    rst.getInt("id"),
                    rst.getInt("hotel_id")
                    ,rst.getString("type")
                    , rst.getString("nb_lits")
                    , rst.getInt("etage")
                    , rst.getInt("prix")
                                       , rst.getString("vue"));
             
            Chambres.add(e);
        }
        return Chambres;
    } 
       
       
        public void supprimerChambre(Chambre e) throws SQLException {

        String req = "DELETE FROM chambre WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    
     } 
    
        
        
           public void modifierChambre(Chambre e) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE chambre SET "
                + " hotel_id='"+e.getHotel_id()+"'"
                + ", type='"+e.getType()+"'"
                + ", nb_lits='"+e.getNb_lits()+"'"
                + ", etage  ='"+ e.getEtage()+"'"
                + ", prix  ='"+ e.getPrix()+"'"
                + ", vue='"+e.getVue()+"' where id  = "+e.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }  
        
        
        
        
        
        
        
        
        
}
