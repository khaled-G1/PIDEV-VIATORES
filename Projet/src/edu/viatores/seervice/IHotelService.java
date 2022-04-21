/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.viatores.seervice;

import edu.viatores.entities.Hotel;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public interface IHotelService {
    public void ajouterHotel(Hotel e) throws SQLException; 
    
     public List<Hotel> AfficherAllhotel() throws SQLException;
    public List<Hotel> AfficherAllHotelsByVille() throws SQLException;
    public List<Hotel> AfficherAllhotelByNom() throws SQLException;
     public List<Hotel> Recherchhotel(String Nom) throws SQLException;
     public void Supprimerhotel(Hotel e) throws SQLException;
     public void supp2(Hotel m) throws SQLException;
    public void modifierhotel(Hotel e,Hotel e2) throws SQLException, NoSuchAlgorithmException;
    
}
