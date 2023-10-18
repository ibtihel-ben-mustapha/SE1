/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.GUI;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tn.esprit.entity.Activite;
import tn.esprit.entity.Objectif;
import tn.esprit.entity.Planning;
import tn.esprit.entity.TypeActivite;
import tn.esprit.services.ServiceActivite;
import tn.esprit.services.ServicePlanning;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class PIController implements Initializable {

    @FXML
    private Button btnMenuActivite;
    @FXML
    private Button btnMenuPlanning;
    @FXML
    private TableColumn<Planning, Integer> col_id_pl;
    @FXML
    private TableColumn<Planning, String> col_nom_pl;
    @FXML
    private TableColumn<Planning,Integer> col_duree_pl;
    @FXML
    private Pane pnPlanning;
    @FXML
    private TableView<Planning> tvPlanning;
    @FXML
    private Pane pnActivite;
    @FXML
    private TableView<Activite> tvActivite;
    @FXML
    private Pane pnFormPlanning;
    @FXML
    private TextField tfnompl;
    @FXML
    private TextField tfdurepl;
    @FXML
    private Button btnconfAoutPL;
    @FXML
    private Label lbTitleAjoutPlanning;
    @FXML
    private Label lbTitleModifierPlanning;
    @FXML
    private Label lbidpl;
    @FXML
    private Button btnAjouterPlanning1;
    @FXML
    private Button bntModifierPlanning1;
    @FXML
    private Button bntSupprimerPlanning1;
    @FXML
    private TableColumn<Activite, Integer> col_id_AC;
    @FXML
    private TableColumn<Activite, String> col_nom_AC;
    @FXML
    private TableColumn<Activite, Float> col_duree_AC;
    @FXML
    private TableColumn<Activite, Integer> col_REP_AC;
    @FXML
    private TableColumn<Activite, String> col_DESC_AC;
    @FXML
    private TableColumn<Activite, String> col_OBJ_AC;
    @FXML
    private TableColumn<Activite,String > col_TYPE_AC;
    @FXML
    private Button btnAjouterActivite;
    @FXML
    private Button bntModifierActivite;
    @FXML
    private Button bntSupprimerActivite;
    @FXML
    private Label lbidac;
    @FXML
    private TextField tfnomac;
    @FXML
    private TextField tfdureac;
    @FXML
    private TextField tfrepac;
    @FXML
    private ChoiceBox<Objectif> ch_ob_ac;
    @FXML
    private ChoiceBox<TypeActivite> ch_ty_ac;
    @FXML
    private TextField tfdescac;
    @FXML
    private Button btnconfAoutAC;
    @FXML
    private Label lbTitleAjoutACTIVITE;
    @FXML
    private Label lbTitleModifierACTIVITE;
    @FXML
    private Pane pnFormActivite;
    @FXML
    private ChoiceBox<TypeActivite> chR_typ_AC;
    @FXML
    private Button generer_ac;
    @FXML
    private ChoiceBox<Objectif> chR_obj_AC;
    @FXML
    private Button reset_ac;
    @FXML
    private Button ActivitePlanning;
    @FXML
    private Pane pnPlanning_Activite;
    @FXML
    private TableView<Activite> tvPlanningACtivite;
    @FXML
    private TableColumn<Activite, Integer> col_idP_plAC;
    @FXML
    private TableColumn<Activite, Integer> col_idA_plAC;
    @FXML
    private TableColumn<?, ?> col_duree_pl1;
    @FXML
    private Label lbidpl1;
    @FXML
    private Button bntSupprimerPlanningActivite;
   
@FXML
private ChoiceBox<Planning> chnom_pl; // Gardez ChoiceBox<Planning> comme type
    @FXML
    private ImageView imd_act;

/**
 * Initializes the controller class.
 */
@Override
public void initialize(URL url, ResourceBundle rb) {
    pnActivite.toFront();
    fnPlanningShow();
    fnActiviteShow();

    ServicePlanning sr = new ServicePlanning();
    ch_ob_ac.setItems(FXCollections.observableArrayList(Objectif.values()));
    ch_ty_ac.setItems(FXCollections.observableArrayList(TypeActivite.values()));
    chR_obj_AC.setItems(FXCollections.observableArrayList(Objectif.values()));
    chR_typ_AC.setItems(FXCollections.observableArrayList(TypeActivite.values()));

    List<Planning> planningList = sr.affihcer();

    // Créez une ObservableList de Planning
    ObservableList<Planning> planningObservableList = FXCollections.observableArrayList(planningList);

    // Configurez un StringConverter pour afficher les noms de planning dans la ChoiceBox
    chnom_pl.setConverter(new StringConverter<Planning>() {
        @Override
        public String toString(Planning planning) {
            return planning.getNom_pl();
        }

        @Override
        public Planning fromString(String string) {
            // Cette méthode est nécessaire en raison des exigences de l'interface StringConverter
            return null;
        }
    });

    // Remplissez la ChoiceBox avec les objets Planning
    chnom_pl.setItems(planningObservableList);
}

    
    
    
    
    //////////////////////////Planning//////////////////////////////////////////////////
    
    
     public void fnPlanningShow(){
          ServicePlanning sr=new ServicePlanning();
         ObservableList<Planning> list = FXCollections.observableArrayList(sr.affihcer());;
    
     
        col_id_pl.setCellValueFactory(new PropertyValueFactory<>("id_pl")); 
        col_nom_pl.setCellValueFactory(new PropertyValueFactory<>("nom_pl"));       
        col_duree_pl.setCellValueFactory(new PropertyValueFactory<>("dure_pl"));        
        

            tvPlanning.setItems(list);
     }
     
   
     @FXML
    private void fnMenuPlanning(ActionEvent event) {
        pnPlanning.toFront();
    }
   
 
    @FXML
    private void fnSelectedPlanning(MouseEvent event) {
    Planning rec = tvPlanning.getSelectionModel().getSelectedItem();
    if (rec != null) {
        lbidpl.setText(Integer.toString(rec.getId_pl()));
    }
}


    @FXML
    private void fnAjoutPlanning(ActionEvent event) {
        pnFormPlanning.toFront();
        lbTitleAjoutPlanning.setVisible(true);
        lbTitleModifierPlanning.setVisible(false);
    }

    @FXML
    private void fnModifyPlanning(ActionEvent event) {
         pnFormPlanning.toFront();
        lbTitleAjoutPlanning.setVisible(false);
        lbTitleModifierPlanning.setVisible(true);
        ServicePlanning PL =new ServicePlanning();
        Planning P=PL.getById(Integer.parseInt(lbidpl.getText()));
        tfnompl.setText(P.getNom_pl());
        tfdurepl.setText(String.valueOf(P.getDure_pl()));;
     
    }

    @FXML
    private void fnSupprimerPlanning(ActionEvent event) {
        // Récupérez le texte de la Label lbidpl
        String idPlanningText = lbidpl.getText();

        // Assurez-vous que le texte n'est pas vide
        if (!idPlanningText.isEmpty()) {
            try {
                // Convertissez le texte en un entier (ID du planning)
                int planningId = Integer.parseInt(idPlanningText);

                ServicePlanning PL = new ServicePlanning();
                PL.supprimer(planningId);
                fnPlanningShow(); // Met à jour la liste des plannings après suppression
            } catch (NumberFormatException e) {
                // Gérez l'exception si la conversion échoue (par exemple, affichez un message d'erreur)
                System.err.println("Erreur de conversion de l'ID du planning : " + e.getMessage());
            }
        } else {
            // Gérez le cas où la Label est vide (par exemple, affichez un message d'erreur)
            System.err.println("L'ID du planning est vide.");
        }
        }
    
 

    
    
       @FXML
    private void fnConfPL(ActionEvent event) {
        ServicePlanning PL =new ServicePlanning();
        System.out.println(lbTitleAjoutPlanning.isVisible());
        System.out.println(lbidpl.getText());
        if(lbTitleAjoutPlanning.isVisible()){
            Planning P =new Planning();
            
            
            
            String ERROR_MSG="";
            if("".equals(tfnompl.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
            }
            if("".equals(tfdurepl.getText())){
                ERROR_MSG="Le champs duree de dois pas pas étre null";
            }
         
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormPlanning.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormPlanning.toFront();
        }}else{
            P.setNom_pl(tfnompl.getText());
            P.setDure_pl(Integer.parseInt(tfdurepl.getText()));
            
               PL.ajouter(P); 
             tfnompl.setText("");
            tfdurepl.setText("");
            
            pnPlanning.toFront();
            
             fnPlanningShow();
            }
            
            
            
            
        }else{
            Planning P =new Planning();
           
            String ERROR_MSG="";
            if("".equals(tfnompl.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
            }
            if("".equals(tfdurepl.getText())){
                ERROR_MSG="Le champs description de dois pas pas étre null";
            }
            
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormPlanning.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnPlanning.toFront();
        }}else{
               
           P.setNom_pl(tfnompl.getText());
           P.setDure_pl(Integer.parseInt(tfdurepl.getText()));
           P.setId_pl(Integer.parseInt(lbidpl.getText()));
               PL.modifier(P); 
              
               tfnompl.setText("");
               tfdurepl.setText("");
            
            pnPlanning.toFront();
            
        fnPlanningShow();
            }
            
        }
    }
    ////////////////////////////////ACTIVITE////////////////////////////////////////////
     public void fnActiviteShow() {
    ServiceActivite sr = new ServiceActivite();
    ObservableList<Activite> list = FXCollections.observableArrayList(sr.affihcer());

    col_id_AC.setCellValueFactory(new PropertyValueFactory<>("id_ac")); 
    col_nom_AC.setCellValueFactory(new PropertyValueFactory<>("nom_ac"));       
    col_duree_AC.setCellValueFactory(new PropertyValueFactory<>("duree_ac"));        
    col_REP_AC.setCellValueFactory(new PropertyValueFactory<>("repetition_ac"));   
    col_OBJ_AC.setCellValueFactory(new PropertyValueFactory<>("objectif_ac"));  
    col_TYPE_AC.setCellValueFactory(new PropertyValueFactory<>("type_ac"));
    col_DESC_AC.setCellValueFactory(new PropertyValueFactory<>("description_ac"));       

    tvActivite.setItems(list);
}

 @FXML
    private void fnMenuActivite(ActionEvent event) {
        pnActivite.toFront();
    }
    @FXML
    private void fnSelectedActivite(MouseEvent event) {
    Activite rec = tvActivite.getSelectionModel().getSelectedItem();
    if (rec != null) {
        lbidac.setText(Integer.toString(rec.getId_ac()));
    }
}
   



    @FXML
    private void fnAjoutActivite(ActionEvent event) {
        pnFormActivite.toFront();
        lbTitleAjoutACTIVITE.setVisible(true);
        lbTitleModifierACTIVITE.setVisible(false);
    }

    @FXML
    private void fnModifyActivite(ActionEvent event) {
         pnFormActivite.toFront();
        lbTitleAjoutACTIVITE.setVisible(false);
        lbTitleModifierACTIVITE.setVisible(true);
        ServiceActivite A1 =new ServiceActivite();
        Activite A=A1.getById(Integer.parseInt(lbidac.getText()));
        tfnomac.setText(A.getNom_ac());
        tfdureac.setText(String.valueOf(A.getDuree_ac()));;
        tfrepac.setText(String.valueOf(A.getRepetition_ac()));;
        tfdescac.setText(A.getDescription_ac());
          // Récupérer la valeur sélectionnée dans ch_ob_ac (Objectif)
        Objectif selectedObjectif = ch_ob_ac.getValue();
        // Récupérer la valeur sélectionnée dans ch_ty_ac (TypeActivite)
        TypeActivite selectedTypeActivite = ch_ty_ac.getValue();

        // Maintenant, vous pouvez utiliser ces valeurs pour configurer votre objet Activite
        A.setObj_ac(selectedObjectif);
        A.setType_ac(selectedTypeActivite);
     
    }
 

    @FXML
    private void fnSupprimerActivite(ActionEvent event) {
        // Récupérez le texte de la Label lbidpl
        String idActiviteText = lbidac.getText();

        // Assurez-vous que le texte n'est pas vide
        if (!idActiviteText.isEmpty()) {
            try {
                // Convertissez le texte en un entier (ID du planning)
                int AC_Id = Integer.parseInt(idActiviteText);

                ServiceActivite A = new ServiceActivite();
                A.supprimer(AC_Id);
                fnActiviteShow(); // Met à jour la liste des plannings après suppression
            } catch (NumberFormatException e) {
                // Gérez l'exception si la conversion échoue (par exemple, affichez un message d'erreur)
                System.err.println("Erreur de conversion de l'ID du ACTIVITE : " + e.getMessage());
            }
        } else {
            // Gérez le cas où la Label est vide (par exemple, affichez un message d'erreur)
            System.err.println("L'ID du ACTIVITE est vide.");
        }
        }
    
    
 @FXML
private void fnConfAC(ActionEvent event) {
    ServiceActivite activiteService = new ServiceActivite();
    System.out.println(lbTitleAjoutACTIVITE.isVisible());
    System.out.println(lbidac.getText());
    
    if (lbTitleAjoutACTIVITE.isVisible()) {
          
        Activite A = new Activite();

        String ERROR_MSG = "";
        
        if ("".equals(tfnomac.getText())) {
            ERROR_MSG = "Le champ nom ne doit pas être vide.";
        }
        
        if (tfdureac.getText().isEmpty()) {
            ERROR_MSG = "Le champ durée ne doit pas être vide.";
        }
        
        if ("".equals(tfrepac.getText())) {
            ERROR_MSG = "Le champ répétition ne doit pas être vide.";
        }
        
        if ("".equals(tfdescac.getText())) {
            ERROR_MSG = "Le champ description ne doit pas être vide.";
        }

        if (ch_ty_ac.getValue() == null) {
            ERROR_MSG = "Veuillez sélectionner un type d'activité.";
        }

        if (ch_ob_ac.getValue() == null) {
            ERROR_MSG = "Veuillez sélectionner un objectif.";
        }
        
        if (!"".equals(ERROR_MSG)) {
            Stage window = (Stage) pnFormActivite.getScene().getWindow();
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);

            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                pnActivite.toFront();
            }
        } else {
            A.setNom_ac(tfnomac.getText());
            A.setDuree_ac(Float.parseFloat(tfdureac.getText()));
            A.setRepetition_ac(Integer.parseInt(tfrepac.getText()));
            A.setDescription_ac(tfdescac.getText());
            A.setType_ac(ch_ty_ac.getValue()); // Utilisation de la valeur sélectionnée dans ch_ty_ac
            A.setObj_ac(ch_ob_ac.getValue()); // Utilisation de la valeur sélectionnée dans ch_ob_ac

            activiteService.ajouter(A);

            tfnomac.setText("");
            tfdureac.setText("");
            tfrepac.setText("");
            tfdescac.setText("");

            pnActivite.toFront();

            // Appeler la méthode pour afficher les activités si nécessaire
        }
    } else {
        // Logique pour modifier une activité existante
        Activite A = activiteService.getById(Integer.parseInt(lbidac.getText())); // Récupérer l'activité existante

        String ERROR_MSG = "";

        if ("".equals(tfnomac.getText())) {
            ERROR_MSG = "Le champ nom ne doit pas être vide.";
        }

        if ("".equals(tfdureac.getText())) {
            ERROR_MSG = "Le champ durée ne doit pas être vide.";
        }

        if ("".equals(tfrepac.getText())) {
            ERROR_MSG = "Le champ répétition ne doit pas être vide.";
        }

        if ("".equals(tfdescac.getText())) {
            ERROR_MSG = "Le champ description ne doit pas être vide.";
        }

        if (ch_ty_ac.getValue() == null) {
            ERROR_MSG = "Veuillez sélectionner un type d'activité.";
        }

        if (ch_ob_ac.getValue() == null) {
            ERROR_MSG = "Veuillez sélectionner un objectif.";
        }

        if (!"".equals(ERROR_MSG)) {
            Stage window = (Stage) pnFormActivite.getScene().getWindow();
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);

            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                pnFormActivite.toFront();
            }
        } else {
            A.setNom_ac(tfnomac.getText());
            A.setDuree_ac(Float.parseFloat(tfdureac.getText()));
            A.setRepetition_ac(Integer.parseInt(tfrepac.getText()));
            A.setDescription_ac(tfdescac.getText());
            A.setType_ac(ch_ty_ac.getValue());
            A.setObj_ac(ch_ob_ac.getValue());
            A.setId_ac(Integer.parseInt(lbidac.getText()));

            activiteService.modifier(A); // Mettre à jour l'activité existante

            tfnomac.setText("");
            tfdureac.setText("");
            tfrepac.setText("");
            tfdescac.setText("");

            pnActivite.toFront();
            fnActiviteShow();

            // Appeler la méthode pour afficher les activités si nécessaire
        }
    }
}

    @FXML
    public void rechercherActivites() {
    ServiceActivite activiteService = new ServiceActivite();
    List<Activite> activites = new ArrayList<>();
    
    TypeActivite typeSelectionne = chR_typ_AC.getValue();
    Objectif objectifSelectionne = chR_obj_AC.getValue();
    
    if (typeSelectionne != null && objectifSelectionne != null) {
        // Recherche par type et objectif
        activites = activiteService.afficherParTypeEtObjectif(typeSelectionne, objectifSelectionne);
    } else if (typeSelectionne != null) {
        // Recherche par type seulement
        activites = activiteService.afficherParType(typeSelectionne);
   
    } else {
        // Afficher un message d'erreur si rien n'est sélectionné
        System.out.println("Veuillez sélectionner un type ou un objectif pour la recherche.");
        // Vous pouvez également afficher un message d'erreur à l'utilisateur dans l'interface utilisateur.
    }

    // Mettez à jour votre interface utilisateur avec les activités trouvées
    afficherActivites(activites);
}

  public void afficherActivites(List<Activite> activites) {
    ObservableList<Activite> list = FXCollections.observableArrayList(activites);

    col_id_AC.setCellValueFactory(new PropertyValueFactory<>("id_ac")); 
    col_nom_AC.setCellValueFactory(new PropertyValueFactory<>("nom_ac"));       
    col_duree_AC.setCellValueFactory(new PropertyValueFactory<>("duree_ac"));        
    col_REP_AC.setCellValueFactory(new PropertyValueFactory<>("repetition_ac"));   
    col_OBJ_AC.setCellValueFactory(new PropertyValueFactory<>("objectif_ac"));  
    col_TYPE_AC.setCellValueFactory(new PropertyValueFactory<>("type_ac"));
    col_DESC_AC.setCellValueFactory(new PropertyValueFactory<>("description_ac"));       

    tvActivite.setItems(list);
}


@FXML
private void resetAffichage(ActionEvent event) {
    // Effacez tout texte ou valeurs des champs de texte ou des ChoiceBox
    tfnomac.clear();
    tfdureac.clear();
    tfrepac.clear();
    tfdescac.clear();
    ch_ty_ac.setValue(null); // Réinitialisez la valeur de ChoiceBox
    ch_ob_ac.setValue(null); // Réinitialisez la valeur de ChoiceBox
    // Réinitialisez d'autres composants de votre interface utilisateur au besoin

    // Appelez la méthode pour afficher les activités par défaut (ou affichez toutes les activités)
    fnActiviteShow();
}
 
   ////////////////////plannongActivite /////////////////////////////////////////:
  
   
public void fnPlanningActiviteShow() {
    // 1. Assurez-vous que le TableView et les colonnes sont correctement définis dans votre FXML

    // 2. Modifiez la méthode pour obtenir la liste d'activités liées au planning (à adapter en fonction de votre modèle de données)
    Planning selectedPlanning = chnom_pl.getValue(); // Récupérez le planning sélectionné

    if (selectedPlanning != null) {
        int planningId = selectedPlanning.getId_pl(); // Récupérez l'ID du planning

        // Créez une instance de ServicePlanning
        ServicePlanning servicePlanning = new ServicePlanning();

        // Récupérez la liste des activités liées au planning
        List<Activite> activites = servicePlanning.getActivitesPourPlanning(planningId);

        // 3. Créez une ObservableList d'Activite
        ObservableList<Activite> activitesList = FXCollections.observableArrayList(activites);

        // 4. Liez les colonnes aux propriétés de l'objet Activite (ID de planning et ID d'activité)
        col_idP_plAC.setCellValueFactory(new PropertyValueFactory<>("id_planning"));
        col_idA_plAC.setCellValueFactory(new PropertyValueFactory<>("id_activite"));

        // 5. Ajoutez les données à votre TableView
        tvPlanningACtivite.setItems(activitesList);
    } else {
        // Si aucun planning n'est sélectionné, effacez la TableView
        tvPlanningACtivite.getItems().clear();
    }
}



  @FXML
private void fnAffecterActiviteAuPlanning(ActionEvent event) {
    
    // Récupérez l'ID de l'activité à partir du label (c'est ce que vous faites déjà)
    int activiteId = Integer.parseInt(lbidac.getText());

    // Récupérez le planning sélectionné par l'utilisateur
    Planning selectedPlanning = chnom_pl.getValue();

    if (selectedPlanning != null) {
        // Récupérez l'ID du planning
        int planningId = selectedPlanning.getId_pl();

        // Ensuite, ajoutez l'activité au planning en utilisant votre service
        ServicePlanning servicePlanning = new ServicePlanning();
        servicePlanning.ajouterActiviteAPlanning(planningId, activiteId);

        // Rafraîchissez votre TableView pour afficher les activités mises à jour
        pnPlanning_Activite.toFront();
        fnPlanningActiviteShow();

        // Vous pouvez également afficher un message de succès ou effectuer d'autres actions ici
    } else {
        System.out.println("erreeeeeuuur");// Affichez un message d'erreur si l'utilisateur n'a pas sélectionné un planning
    }
}
}


