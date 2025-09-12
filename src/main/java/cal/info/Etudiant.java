package cal.info;

import sun.jvm.hotspot.ui.tree.CStringTreeNodeAdapter;

public class Etudiant {
    private String nomEtudiant;
    private int ageEtudiant;
    private double noteEtudiant;

    public Etudiant(String nomEtudiant, int ageEtudiant, double noteEtudiant){
        this.nomEtudiant = nomEtudiant;
        this.ageEtudiant = ageEtudiant;
        this.noteEtudiant = noteEtudiant;
    }

    public String obtenirNom(){
        return this.nomEtudiant;
    }
    public int obtenirAge(){
        return this.ageEtudiant;
    }
    public double obtenirNote(){
        return this.noteEtudiant;
    }





}
