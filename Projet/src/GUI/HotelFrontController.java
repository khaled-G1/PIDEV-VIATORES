/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Hotel;
import Services.HotelService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class HotelFrontController implements Initializable {
public ObservableList<Hotel> list;
public static Hotel connectedHotel;
    @FXML
    private TableView<Hotel> tableview;
    @FXML
    private TableColumn<?, ?> ville;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> nb_chambre;
    @FXML
    private Button Afficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HotelService pss = new HotelService();
        ArrayList<Hotel> c = new ArrayList<>();
        try {
            c = (ArrayList<Hotel>) pss.AfficherAllHotelsByVille();
        } catch (SQLException ex) {
        }
        
        ObservableList<Hotel> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
           
        
         
 ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nb_chambre.setCellValueFactory(new PropertyValueFactory<>("nb_chambre"));

  
   
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllHotelsByVille()
            );        
        
        
  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    

    @FXML
    private void Afficher(ActionEvent event) throws IOException {
         HotelService ps = new HotelService();
      //  String ville, String nom, int nb_chambre, String imghotel;
         Hotel c = new Hotel(tableview.getSelectionModel().getSelectedItem().getId(),

                tableview.getSelectionModel().getSelectedItem().getVille(),
                 tableview.getSelectionModel().getSelectedItem().getNom(),
                tableview.getSelectionModel().getSelectedItem().getNb_chambre(),
                tableview.getSelectionModel().getSelectedItem().getImghotel()
           
                );
        
        HotelFrontController.connectedHotel = c;
        
             Parent page1 = FXMLLoader.load(getClass().getResource("HotelDetail.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();   
        
        
        
        
        
    }
    
}
