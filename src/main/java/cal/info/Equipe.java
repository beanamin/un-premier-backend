package cal.info;

import java.util.List;

public class Equipe {
    private List<Etudiant> listeMembres;
    private String nomEquipe;
    private Hackathon hackathonInscrit;

    public Equipe(String nomEquipe){
        this.nomEquipe = nomEquipe;
    }

    public void ajouterMembres(Etudiant e){
        this.listeMembres.add(e);
    }
    public void afficherMembres(){
        System.out.println(listeMembres);
    }
    public void participerAHackathon(Hackathon h){
        hackathonInscrit = h;

    }
    public void afficherHackathonInscrit(){
        System.out.println(hackathonInscrit);
    }
}
