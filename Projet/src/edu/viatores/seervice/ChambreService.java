/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.viatores.seervice;

import edu.viatores.tools.Maconnexion;
import edu.viatores.entities.Chambre;
import edu.viatores.entities.Hotel;
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
public class ChambreService implements IChambreService{

    
    
      Connection connexion;   
  public ChambreService() {
        connexion = Maconnexion.getInstance().getConnection();
    }
    
    @Override
        public void ajouter(Chambre C) {
       
       String req = "INSERT INTO  `chambre` (`type`,`nb_lits`, `etage`,`prix`,`vue`) VALUE (?,?,?,?,?)";
    try {
                 PreparedStatement ps = connexion.prepareStatement(req);
           
            ps.setString(1, C.getType());
            ps.setString(2,C.getNb_lits());
            ps.setInt(3,C.getEtage());
            ps.setInt(4,C.getPrix());
            ps.setString(5,C.getVue());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChambreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @Override
        public List<Chambre> AfficherAllChambres() throws SQLException {

        List<Chambre> Chambres = new ArrayList<>();
        String req = "select * from chambre ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Chambre e = new Chambre(rst.getString("type")
                    , rst.getString("nb_lits")
                    , rst.getInt("etage")
                    , rst.getInt("prix")
                   
                    , rst.getString("vue"));
             
            Chambres.add(e);
        }
        return Chambres;
    }
      @Override
       public List<Chambre> AfficherAllChambresByHotel() throws SQLException {

        List<Chambre> Chambres = new ArrayList<>();
        String req = "select * from chambre order by hotel_id ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Chambre e = new Chambre(rst.getString("type")
                    , rst.getString("nb_lits")
                    , rst.getInt("etage")
                    , rst.getInt("prix")
                                       , rst.getString("vue"));
             
            Chambres.add(e);
        }
        return Chambres;
    } 
       
         @Override
        public void supprimerChambre(Chambre e) throws SQLException {

        String req = "DELETE FROM chambre WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    
     } 
    
        
          @Override
           public void modifierChambre(Chambre e,Chambre e2) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE chambre SET "
                + " type='"+e.getType()+"'"
                + ", nb_lits='"+e.getNb_lits()+"'"
                + ", etage  ='"+ e.getEtage()+"'"
                + ", prix  ='"+ e.getPrix()+"'"
                + ", vue='"+e.getVue()+"' where id  = "+e2.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }  
        
        
        
        
        
        
        
        
        
}
