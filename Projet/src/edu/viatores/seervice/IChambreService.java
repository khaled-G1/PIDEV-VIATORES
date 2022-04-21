/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.viatores.seervice;

import edu.viatores.entities.Chambre;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public interface IChambreService {
  public void ajouter(Chambre C);
  public List<Chambre> AfficherAllChambres() throws SQLException ;
   public void modifierChambre(Chambre e,Chambre e2) throws SQLException, NoSuchAlgorithmException;
    public void supprimerChambre(Chambre e) throws SQLException;
     public List<Chambre> AfficherAllChambresByHotel() throws SQLException;
    
    
    
    
    
    
    
}
