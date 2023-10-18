package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entity.Activite;
import tn.esprit.entity.Planning;
import tn.esprit.entity.TypeActivite;
import tn.esprit.entity.Objectif;
import tn.esprit.tools.MyBD;

public class ServicePlanning implements IPlanning<Planning> {
    Connection con;
    Statement ste;

    public ServicePlanning() {
        con = MyBD.getinstance().getCon();
    }

    @Override
    public void ajouter(Planning P) {
        try {
            String req = "INSERT INTO Planning (nom_pl, dure_pl) VALUES (?, ?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, P.getNom_pl());
            pre.setFloat(2, P.getDure_pl());
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimer(int idPlanning) {
        try {
            String req = "DELETE FROM Planning WHERE id_pl = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, idPlanning);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void modifier(Planning P) {
        try {
            String req = "UPDATE Planning SET nom_pl=?, dure_pl=? WHERE id_pl=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, P.getNom_pl());
            pre.setFloat(2, P.getDure_pl());
            pre.setInt(3, P.getId_pl());
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

   

    public void supprimerParNom(String nomPlanning) {
        try {
            String req = "DELETE FROM Planning WHERE nom_pl = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, nomPlanning);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Planning> afficherParType(TypeActivite typeActivite) {
        List<Planning> plannings = new ArrayList<>();
        String sql = "SELECT DISTINCT p.* FROM Planning p " +
                     "JOIN Planning_Activite pa ON p.id_pl = pa.planning_id " +
                     "JOIN Activite a ON pa.activite_id = a.id_ac WHERE a.type_ac = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, typeActivite.name());
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Planning P = new Planning(
                    rs.getInt("id_pl"),
                    rs.getString("nom_pl"),
                    rs.getFloat("dure_pl")
                );
                plannings.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return plannings;
    }

    public List<Planning> afficherParTypeEtObjectif(TypeActivite typeActivite, Objectif objectif) {
        List<Planning> plannings = new ArrayList<>();
        String sql = "SELECT DISTINCT p.* FROM Planning p " +
                     "JOIN Planning_Activite pa ON p.id_pl = pa.planning_id " +
                     "JOIN Activite a ON pa.activite_id = a.id_ac WHERE a.type_ac = ? AND a.objectif_ac = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, typeActivite.name());
            pre.setString(2, objectif.name());
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {                   
                Planning P = new Planning(
                    rs.getInt("id_pl"),
                    rs.getString("nom_pl"),
                    rs.getFloat("dure_pl")
                );
                plannings.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return plannings;
    }

    @Override
    public List<Planning> affihcer() {
             List<Planning> plannings = new ArrayList<>();
        String sql = "SELECT * FROM Planning";
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Planning P = new Planning(
                    rs.getInt("id_pl"),
                    rs.getString("nom_pl"),
                    rs.getFloat("dure_pl")
                );
                plannings.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return plannings;
    }
    ////////////////////////////////////////////////////requette////////////////////////////////////////////
   


    public void ajouterActiviteAPlanning(int planningId, int activiteId) {
        try {
            String req = "INSERT INTO Planning_Activite (planning_id, activite_id) VALUES (?, ?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, planningId);
            pre.setInt(2, activiteId);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void supprimerActiviteDePlanning(int planningId, int activiteId) {
        try {
            String req = "DELETE FROM Planning_Activite WHERE planning_id = ? AND activite_id = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, planningId);
            pre.setInt(2, activiteId);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Activite> getActivitesPourPlanning(int planningId) {
        List<Activite> activites = new ArrayList<>();
        String sql = "SELECT a.* FROM Activite a " +
                     "JOIN Planning_Activite pa ON a.id_ac = pa.activite_id " +
                     "WHERE pa.planning_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, planningId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Activite A = new Activite(
                    rs.getInt("id_ac"),
                    rs.getFloat("duree_ac"),
                    rs.getString("nom_ac"),
                    rs.getInt("repetition_ac"),
                    rs.getString("description_ac"),
                    TypeActivite.valueOf(rs.getString("type_ac")),
                    Objectif.valueOf(rs.getString("objectif_ac"))
                );
                activites.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return activites;
    }

    @Override
  
public Planning getById(int id) {
   Planning PL=new Planning();
        try{
        Statement st= con.createStatement();
        String query = "select * from Planning where id_pl="+id+"";
        
        ResultSet rs;
        rs = st.executeQuery(query);
        
        while (rs.next()) {
           PL = new Planning(rs.getInt("id_pl"),rs.getString("nom_pl"),rs.getFloat("dure_pl")); 
            

        }
         return PL;    
         }catch(SQLException ex){
                         System.out.println(ex.getMessage());

         }
        return PL;
    }

    
  public List<String> getPlanningNames() {
    List<String> planningNames = new ArrayList<>();
    try {
        String sql = "SELECT nom_pl FROM Planning";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String nom_pl = resultSet.getString("nom_pl");
            planningNames.add(nom_pl);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return planningNames;
}


}
