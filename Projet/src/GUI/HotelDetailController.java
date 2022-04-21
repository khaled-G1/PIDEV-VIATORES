/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class HotelDetailController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label ville;
    @FXML
    private Label nb_chambre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            nom.setText(HotelFrontController.connectedHotel.getNom());
         
          nb_chambre.setText( Integer.toString(HotelFrontController.connectedHotel.getNb_chambre()));
                      
            ville.setText( HotelFrontController.connectedHotel.getVille());
         
        
        
        
        
        
        
    }    
    
}
