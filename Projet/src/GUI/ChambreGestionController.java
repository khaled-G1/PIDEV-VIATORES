/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.MyDB;
import Entities.Chambre;
import Entities.Hotel;
import Services.ChambreService;
import Services.HotelService;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class ChambreGestionController implements Initializable {
 Connection connexion;   
    @FXML
    private Hyperlink gcategorie;
  public ChambreGestionController() {
        connexion = MyDB.getInstance().getConnection();
    }
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
    @FXML
    private TableView<Chambre> tableview;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> nb_lits;
    @FXML
    private TableColumn<?, ?> etage;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> vue;
    private TextField inputville;
    @FXML
    private Button Confirmer;
    @FXML
    private Label labelid;
    @FXML
    private TextField inputtype;
    @FXML
    private TextField inputnb_lits;
    @FXML
    private ComboBox<Integer> inputetage;
    @FXML
    private ComboBox<String> inputvue;
    @FXML
    private ComboBox<Integer> inputhotel;
    ChambreService cs = new ChambreService();
     public ObservableList<Chambre> list;
    @FXML
    private TextField inputprix;
    @FXML
    private TableColumn<?, ?> hoteliNom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   try {
            String req = "select * from hotel";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                inputhotel.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        inputetage.getItems().add(1);
        inputetage.getItems().add(2);
        inputetage.getItems().add(3);
        inputetage.getItems().add(4);
        inputetage.getItems().add(5);
        
        inputvue.getItems().add("Vus sur Mer");
        inputvue.getItems().add("Vus sur Piscine");
        inputvue.getItems().add("Pas de Vue");
        ChambreService pss = new ChambreService();
        ArrayList<Chambre> c = new ArrayList<>();
        try {
            c = (ArrayList<Chambre>) pss.AfficherAllChambresByHotel();
        } catch (SQLException ex) {
        }
        
        
        ObservableList<Chambre> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
           
        
         
 hoteliNom.setCellValueFactory(new PropertyValueFactory<>("hotel_id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        nb_lits.setCellValueFactory(new PropertyValueFactory<>("nb_lits"));
  etage.setCellValueFactory(new PropertyValueFactory<>("etage"));
    prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
      vue.setCellValueFactory(new PropertyValueFactory<>("vue"));
  
   
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllChambresByHotel()
            );        
        
        
   FilteredList<Chambre> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Chambre>) Chambres -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Chambres.getType().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Chambre> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
  public void resetTableData() throws SQLDataException, SQLException {
      ChambreService cs = new ChambreService();
      List<Chambre> listevents = new ArrayList<>();
        listevents = cs.AfficherAllChambresByHotel();
        ObservableList<Chambre> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }   
   
    @FXML
    private void supp(ActionEvent event) throws SQLException {
           if (event.getSource() == supp) {
            Chambre e = new Chambre();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
          ChambreService cs = new ChambreService();
            cs.supprimerChambre(e);
            resetTableData();  
        
        }
        
        
    }

    @FXML
    private void Modif(ActionEvent event) {
        ChambreService ps = new ChambreService();
          
 //in String disponibilite, String type_action, String imgproduit) {

      
                
               
        inputhotel.setValue(tableview.getSelectionModel().getSelectedItem().getHotel_id());
           
            labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));
         
            inputtype.setText(tableview.getSelectionModel().getSelectedItem().getType());
            inputnb_lits.setText(  tableview.getSelectionModel().getSelectedItem().getNb_lits());
 inputetage.setValue(tableview.getSelectionModel().getSelectedItem().getEtage());
 inputprix.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getPrix()));
 inputvue.setValue(tableview.getSelectionModel().getSelectedItem().getVue());
 
 
 
           Confirmer.setVisible(true);   
        
        
        
    }

    @FXML
    private void Ajouter(ActionEvent event) {
          ChambreService productService = new ChambreService();
  
        if (inputtype.getText().equals("")
                || inputnb_lits.getText().equals("") || inputprix.getText().equals("")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (  inputtype.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))
                {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
                             

// int etage, int prix, String vue)
            Chambre c = new Chambre(inputhotel.getValue(),inputtype.getText(),inputnb_lits.getText(),
            inputetage.getValue(),Integer.parseInt(inputprix.getText()),inputvue.getValue()
            
            );
        try {
            productService.ajouter(c);
             resetTableData();
        } catch (SQLException ex) {
           
        }
        
        
    }

    @FXML
    private void Confirmer(ActionEvent event) throws NoSuchAlgorithmException {
           ChambreService productService = new ChambreService();
  
        if (inputtype.getText().equals("")
                || inputnb_lits.getText().equals("") || inputprix.getText().equals("")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (  inputtype.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))
                {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
                             

// int etage, int prix, String vue)
            Chambre c = new Chambre(Integer.parseInt(labelid.getText()),inputhotel.getValue(),inputtype.getText(),inputnb_lits.getText(),
            inputetage.getValue(),Integer.parseInt(inputprix.getText()),inputvue.getValue()
            
            );
        try {
            productService.modifierChambre(c);
             resetTableData();
        } catch (SQLException ex) {
           
        }  
        
        
        
    }

    @FXML
    private void gcategorie(ActionEvent event) throws IOException {
            Parent page1 = FXMLLoader.load(getClass().getResource("HotelGestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
        
    }

    
}
