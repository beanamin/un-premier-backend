package cal.info;

import java.util.Date;
import java.util.List;

public class Hackathon {
    private String nomHackathon;
    private String dateHackathon;
    private String lieuHackathon;
    private String urlHackathon;
    private int id;

    public Hackathon(String nomHackathon, String dateHackathon, String lieuHackathon, String urlHackathon){
        this.nomHackathon = nomHackathon;
        this.dateHackathon = dateHackathon;
        this.lieuHackathon = lieuHackathon;
        this.urlHackathon = urlHackathon;
    }

    public Hackathon(){}

    public String getNomHackathon(){
        return this.nomHackathon;
    }
    public String getDateHackathon(){
        return this.dateHackathon;
    }
    public String getLieuHackathon(){
        return this.lieuHackathon;
    }

    public String getUrlHackathon() {
        return urlHackathon;
    }

    public int getId() {
        return id;
    }

    public void setDateHackathon(String dateHackathon) {
        this.dateHackathon = dateHackathon;
    }

    public void setLieuHackathon(String lieuHackathon) {
        this.lieuHackathon = lieuHackathon;
    }

    public void setNomHackathon(String nomHackathon) {
        this.nomHackathon = nomHackathon;
    }

    public void setUrlHackathon(String urlHackathon) {
        this.urlHackathon = urlHackathon;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"nomHackathon\": \"" + nomHackathon + "\"" +
                ", \"dateHackathon\": \"" + dateHackathon + "\"" +
                ", \"lieuHackathon\": \"" + lieuHackathon + "\"" +
                ", \"urlHackathon\": \"" + urlHackathon + "\"" +
                '}';
    }
}
