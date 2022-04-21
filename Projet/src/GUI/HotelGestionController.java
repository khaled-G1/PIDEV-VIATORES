/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Hotel;
import Services.HotelService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javax.mail.Authenticator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class HotelGestionController implements Initializable {
    HotelService cs = new HotelService();
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
    @FXML
    private TableView<Hotel> tableview;
    @FXML
    private TableColumn<?, ?> ville;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> nb_chambre;
    @FXML
    private TextField inputville;
    @FXML
    private TextField inputnom;
    @FXML
    private TextField inputnb_chambre;
    @FXML
    private Button Confirmer;
    @FXML
    private Label labelid;
 public ObservableList<Hotel> list;
    @FXML
    private Button Timage;
    @FXML
    private ImageView imgajoutincours;
    @FXML
    private Label imgpathttt;
    @FXML
    private Hyperlink gcategorie;
    @FXML
    private Hyperlink Front;
    @FXML
    private Button pdf2;
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
        
        
   FilteredList<Hotel> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Hotel>) Hotels -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Hotels.getNom().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Hotel> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
  public void resetTableData() throws SQLDataException, SQLException {
      HotelService cs = new HotelService();
      List<Hotel> listevents = new ArrayList<>();
        listevents = cs.AfficherAllhotelByNom();
        ObservableList<Hotel> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }   
   
    @FXML
    private void supp(ActionEvent event) throws SQLException {
           if (event.getSource() == supp) {
            Hotel e = new Hotel();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
          HotelService cs = new HotelService();
            cs.supp2(e);
            resetTableData();  
         TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Supprimé un HOTEL");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }
        
        
    }

    @FXML
    private void Modif(ActionEvent event) {
         HotelService ps = new HotelService();
          
 //in String disponibilite, String type_action, String imgproduit) {

        Hotel c = new Hotel(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getVille(),
               tableview.getSelectionModel().getSelectedItem().getNom(),
                 tableview.getSelectionModel().getSelectedItem().getNb_chambre(),
                tableview.getSelectionModel().getSelectedItem().getImghotel()
                );
                
                
               
        
           
            labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));
         
            inputville.setText(tableview.getSelectionModel().getSelectedItem().getVille());
            inputnom.setText(tableview.getSelectionModel().getSelectedItem().getNom());
            inputnb_chambre.setText( Integer.toString( tableview.getSelectionModel().getSelectedItem().getNb_chambre()));
 
  Timage.setText(tableview.getSelectionModel().getSelectedItem().getImghotel());
 
           Confirmer.setVisible(true);   
        
        
    }

    @FXML
    private void Ajouter(ActionEvent event) {
         HotelService productService = new HotelService();
  
        if (inputville.getText().equals("")
                || inputnom.getText().equals("") || inputnb_chambre.getText().equals("")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (  inputnb_chambre.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))
                {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
                             


            Hotel c = new Hotel(inputville.getText(),inputnom.getText(),Integer.parseInt(inputnb_chambre.getText()),
                    Timage.getText()                  );
        try {
            productService.ajouterHotel(c);
             resetTableData();
                     TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Ajouté un nouveau Hotel");
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
                     sendMail("khaled.ghouili@esprit.tn");
        } catch (SQLException ex) {
           
        } catch (MessagingException ex) {
            Logger.getLogger(HotelGestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void Confirmer(ActionEvent event) throws NoSuchAlgorithmException {
            HotelService productService = new HotelService();
  
        if (inputville.getText().equals("")
                || inputnom.getText().equals("") || inputnb_chambre.getText().equals("")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (  inputnb_chambre.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))
                {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
       
                             


            Hotel c = new Hotel(Integer.parseInt(labelid.getText()),inputville.getText(),inputnom.getText(),Integer.parseInt(inputnb_chambre.getText()),
                    Timage.getText()                  );
        try {
            productService.modifierhotel(c);
             resetTableData();
             
      
        } catch (SQLException ex) {
           
        }
        
        
        
        
    }

 @FXML
    private void addimgcours(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpathttt.setText(file.getAbsolutePath());
    }

    @FXML
    private void gcategorie(ActionEvent event) throws IOException {
            Parent page1 = FXMLLoader.load(getClass().getResource("ChambreGestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }

    @FXML
    private void Front(ActionEvent event) throws IOException {
          Parent page1 = FXMLLoader.load(getClass().getResource("HotelFront.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
        
        
    }
    
     private void printPDF() throws FileNotFoundException, DocumentException, IOException {
        ;
        Document d = new Document();
        PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\Msi\\Desktop\\ListHotels.pdf"));
        d.open();
        d.add(new Paragraph("Liste des Hotel"));
        
        PdfPTable pTable = new PdfPTable(1);
       
     //   pTable.addCell("NomEvent");
     
        
        tableview.getItems().forEach((t) -> {
            pTable.addCell(String.valueOf(t.getNom()));
            //pTable.addCell(t.getNomEvent());
          //  pTable.addCell(t.getDescriptionEvent());
            
            try {
                d.add(pTable);
            } catch (DocumentException ex) {
                System.out.println(ex);
            }
        });
        
        
        d.close();
        Desktop.getDesktop().open(new File("C:\\Users\\Msi\\Desktop\\ListHotels.pdf"));

    } 
    
    
    
    
    
    @FXML
    private void pdf2(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
         if (event.getSource() == pdf2) {
            
             printPDF();
            

        }
        
        
        
    }
    
    
    public static void sendMail(String recipient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "viatores10@gmail.com";
        String password = "viatores5683@";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }  
   
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Vous Avez Ajouté un nouveau Hotel");
            message.setText("Vous Avez Ajouté un nouveau evenement");
            return message;
        } catch (MessagingException ex) {
        }
        return null;
    }   
    
}
