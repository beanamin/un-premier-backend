package cal.info;

import cal.info.util.BaseDeDonneesUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;

public  class GestionHackathon {
    private static List<Hackathon> listeHackathon;
    private static Map<String, Set<Etudiant>> etudiantsParProg = new HashMap<>();

    private GestionHackathonDAO gestionHackathonDAO;

    public GestionHackathon(GestionHackathonDAO gestionHackathonDAO) {
        this.gestionHackathonDAO = gestionHackathonDAO;
    }

    public GestionHackathon(){
        listeHackathon = new ArrayList<Hackathon>();
    }
    private static GestionHackathon instance = null;
    public static GestionHackathon getInstance(){
        if (instance == null) {
            instance = new GestionHackathon();
        }
        return instance;
    }
    public void ajouterHackathon(Hackathon hackathon) throws SQLException {
        gestionHackathonDAO.ajouterHackathon(hackathon);
    }
    public void modifierHackathon(Hackathon h){
        System.out.println("Modification en construction");
    }
    public void supprimerHackathon(String nomHackathon) throws SQLException{ //CHANGER POUR LE SQL
        gestionHackathonDAO.supprimerHackathon(nomHackathon);
    }
    // Récupérer tous les hackathons
    public List<Hackathon> obtenirHackathons() throws SQLException {
        return gestionHackathonDAO.obtenirHackathons();
    }

    public List<Hackathon> obtenirHackathons(String nomHackathon) throws SQLException {
        return gestionHackathonDAO.obtenirHackathons(nomHackathon);
    }
    public void ajouterEtudiants(List<Etudiant> etudiants){
        etudiants.stream().forEach(nv -> {
            etudiantsParProg.putIfAbsent(nv.getProgrammeEtudiant(), new HashSet<>());
            etudiantsParProg.get(nv.getProgrammeEtudiant()).add(nv);

        });
    }

    public List<String> getvalues(){
        List<String> strings = new ArrayList<>();
        etudiantsParProg.entrySet().stream().sorted().forEach(k -> {
                strings.add(k.getKey());
        });
        return strings;



    }

    public Map<String, Set<Etudiant>> getEtudiantsParProg() {
        return etudiantsParProg;
    }

    public Equipe formerEquipe(List<Etudiant> etudiants, String nomEquipe){
        Equipe equipe = new Equipe(nomEquipe);
        for (Etudiant e : etudiants){
            equipe.ajouterMembres(e);
        }
        return equipe;
    }
}
