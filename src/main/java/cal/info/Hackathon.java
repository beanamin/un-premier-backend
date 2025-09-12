package cal.info;

import java.util.Date;
import java.util.List;

public class Hackathon {
    private String nomHackathon;
    private Date dateHackathon;
    private String lieuHackathon;
    private GestionHackathon gestionHackathon;

    public Hackathon(String nomEtudiant, Date ageEtudiant, String lieuHackathon){
        this.nomHackathon = nomHackathon;
        this.dateHackathon = dateHackathon;
        this.lieuHackathon = lieuHackathon;
    }

    public String obtenirNom(){
        return this.nomHackathon;
    }
    public Date obtenirDate(){
        return this.dateHackathon;
    }
    public String obtenirLieu(){
        return this.lieuHackathon;
    }
}
