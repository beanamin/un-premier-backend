package cal.info;

import cal.info.util.BaseDeDonneesUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionEtudiant {
    private List<Etudiant> listeEtudiant;

    public GestionEtudiant(){
        listeEtudiant = new ArrayList<Etudiant>();
    }
    public void ajouterEtudiant(Etudiant etudiant) throws SQLException {
        String insertSQL = "INSERT INTO Etudiant (NomEtudiant, ageEtudiant, noteEtudiant) VALUES (?, ?, ?)";
        try (Connection conn = BaseDeDonneesUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, etudiant.getNomEtudiant());
            pstmt.setInt(2, etudiant.getAgeEtudiant());
            pstmt.setInt(3, etudiant.getNoteEtudiant());
            pstmt.executeUpdate();
        }
        GestionHackathon gh = GestionHackathon.getInstance();

    }
    public void modifierEtudiant(Hackathon h){
        System.out.println("Modification en construction");
    }
    public void supprimerEtudiant(String nomEtudiant){ //CHANGER POUR LE SQL
        for (Etudiant i : listeEtudiant){
            if (i.getNomEtudiant().equals(nomEtudiant)){
                listeEtudiant.remove(i);
                return;
            }
        }
    }
    // Récupérer tous les hackathons
    public List<Etudiant> obtenirEtudiants() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String selectSQL = "SELECT * FROM Etudiant";
        try (Connection conn = BaseDeDonneesUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setNomEtudiant(rs.getString("nomEtudiant"));
                etudiant.setAgeEtudiant(rs.getInt("ageEtudiant"));
                etudiant.setNoteEtudiant(rs.getInt("noteEtudiant"));
                etudiants.add(etudiant);
            }
        }
        return etudiants;
    }
}
