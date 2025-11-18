package cal.info;

import cal.info.util.BaseDeDonneesUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionHackathonDAO {
    private static GestionHackathonDAO instance = null;
    public static GestionHackathonDAO getInstance(){
        if (instance == null) {
            instance = new GestionHackathonDAO();
        }
        return instance;
    }

    public GestionHackathonDAO(){

    }
    public void ajouterHackathon(Hackathon hackathon) throws SQLException {
        String insertSQL = "INSERT INTO Hackathon (nomHackathon, dateHackathon, lieuHackathon, urlHackathon) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConfigurationPool.obtenirDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, hackathon.getNomHackathon());
            pstmt.setString(2, hackathon.getDateHackathon());
            pstmt.setString(3, hackathon.getLieuHackathon());
            pstmt.setString(4, hackathon.getUrlHackathon());
            pstmt.executeUpdate();
        }
    }
    public void modifierHackathon(Hackathon h){
        System.out.println("Modification en construction");
    }
    public void supprimerHackathon(String nomHackathon) throws SQLException{ //CHANGER POUR LE SQL
        String deleteSQL = "DELETE FROM Hackathon WHERE nomHackathon=?";
        try(Connection conn = ConfigurationPool.obtenirDataSource().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, nomHackathon);
            pstmt.executeUpdate();
        }
    }
    // Récupérer tous les hackathons
    public List<Hackathon> obtenirHackathons() throws SQLException {
        List<Hackathon> hackathons = new ArrayList<>();
        String selectSQL = "SELECT * FROM Hackathon";
        try (Connection conn = ConfigurationPool.obtenirDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                Hackathon hackathon = new Hackathon();
                hackathon.setId(rs.getInt("id"));
                hackathon.setNomHackathon(rs.getString("nomHackathon"));
                hackathon.setDateHackathon(rs.getString("dateHackathon"));
                hackathon.setLieuHackathon(rs.getString("lieuHackathon"));
                hackathon.setUrlHackathon(rs.getString("urlHackathon"));
                hackathons.add(hackathon);
            }
        }
        return hackathons;
    }
    public List<Hackathon> obtenirHackathons(String nomHackathon) throws SQLException {
        List<Hackathon> hackathons = new ArrayList<>();
        String selectSQL = "SELECT * FROM Hackathon WHERE nomHackathon=?";
        try (Connection conn = ConfigurationPool.obtenirDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, nomHackathon);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Hackathon hackathon = new Hackathon();
                hackathon.setId(rs.getInt("id"));
                hackathon.setNomHackathon(rs.getString("nomHackathon"));
                hackathon.setDateHackathon(rs.getString("dateHackathon"));
                hackathon.setLieuHackathon(rs.getString("lieuHackathon"));
                hackathon.setUrlHackathon(rs.getString("urlHackathon"));
                hackathons.add(hackathon);
            }
        }
        return hackathons;
    }
}
