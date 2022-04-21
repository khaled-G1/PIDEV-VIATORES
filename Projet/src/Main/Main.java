///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Main;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import DB.MyDB;
//import Entities.Chambre;
//
//import Entities.Hotel;
//import Services.ChambreService;
//import Services.HotelService;
//import java.security.NoSuchAlgorithmException;
//import java.sql.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.ZoneId;
//
//
///**
// *
// * @author bhk
// */
//public class Main {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws NoSuchAlgorithmException, ParseException {
//       
//       
//        
//        ChambreService chambreService = new ChambreService();
//        HotelService hotelService = new HotelService();
//        
//                Hotel h1 = new Hotel("Sousse","Mouradi",500,"image");
//
//       
//        
//        ////////////// AFFICHER ALL HOTELS
//    /*    try {
//            System.out.println(hotelService.AfficherAllhotel());
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//*/
//////////////////////////Afficher All Hotels By Ville
///*
// try {
//            System.out.println(hotelService.AfficherAllHotelsByVille());
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//*/
//////////////////////////Afficher All Hotels By Nom
//
///*
// try {
//          
//            System.out.println(hotelService.AfficherAllhotelByNom());
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//*/
//////////////////////////Recherche Hotel
//
//
//
///*
// try {
//            System.out.println(hotelService.Recherchhotel("test"));
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//*/
////////////////////////////// Ajouter Hotel
//
///*
//           try {
//          
//         hotelService.ajouterHotel(h1);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          
//          
//          */
//          
//   //////////////////// Supprimer Hotel       
//       /*   
//           Hotel h2 = new Hotel(19,"Sousse","Mouradi",500,"image");
//           try {
//          
//         hotelService.Supprimerhotel(h1);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           */
//           
//      ////////////////Modifier Hotel
//            Hotel h3 = new Hotel("test","test",500,"test");
//               Hotel h2 = new Hotel(19,"Sousse","Mouradi",500,"image");
//           try {
//          
//         hotelService.modifierhotel(h3,h2);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           
//           
//           
//       /////////////////////Ajouter Chambre
//     /*  Chambre c1 = new Chambre("DOUBLE","500",2,50,"sans vue");
//       chambreService.ajouter(c1);
//           
//           */
//      
//            /////////////Afficher tous les chambres
//            
//       /*       try {
//            System.out.println(chambreService.AfficherAllChambres());
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           */
//            /////////////Afficher tous les chambres par hotel
//            
//                     try {
//            System.out.println(chambreService.AfficherAllChambresByHotel());
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
//            
//           ///////////////Supprimer Chambre 
//       /*     Chambre c1 = new Chambre(27,"DOUBLE","500",2,50,"sans vue");
//        try {
//            chambreService.supprimerChambre(c1);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           */ 
//            
//                 ///////////////Modifier Chambre 
//                 Chambre c11 = new Chambre(24,"DOUBLE","500",2,50,"sans vue");
//            Chambre c3 = new Chambre("test","test",2,50,"test");
//        try {
//            chambreService.modifierChambre(c3,c11);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }  
//            
//            
//    }
//}