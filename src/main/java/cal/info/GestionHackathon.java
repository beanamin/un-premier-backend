package cal.info;

import java.util.ArrayList;
import java.util.List;

public class GestionHackathon {
    private List<Hackathon> listeHackathon;

    public GestionHackathon(){
        listeHackathon = new ArrayList<Hackathon>();
    }
    public void ajouterHackathon(Hackathon h){
        listeHackathon.add(h);
    }
    public void modifierHackathon(Hackathon h){
        System.out.println("Modification en construction");
    }
    public void supprimerHackathon(String nomHackathon){
        for (Hackathon i : listeHackathon){
            if (i.obtenirNom().equals(nomHackathon)){
                listeHackathon.remove(i);
                return;
            }
        }
    }
    public void afficherHackathons(Hackathon h){
        System.out.println(listeHackathon);
    }
    public void creerEtudiants(List<Etudiant> etudiants){
        System.out.println("Creation d'etudiants en construction");
    }
    public Equipe formerEquipe(List<Etudiant> etudiants, String nomEquipe){
        Equipe equipe = new Equipe(nomEquipe);
        for (Etudiant e : etudiants){
            equipe.ajouterMembres(e);
        }
        return equipe;
    }
}
