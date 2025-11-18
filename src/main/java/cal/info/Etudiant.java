package cal.info;

public class Etudiant {
    private int id;
    private String nomEtudiant;
    private int ageEtudiant;
    private int noteEtudiant;

    private String programmeEtudiant;

    public Etudiant(){}
    public Etudiant(String nomEtudiant, int ageEtudiant, int noteEtudiant, String programmeEtudiant){
        this.nomEtudiant = nomEtudiant;
        this.ageEtudiant = ageEtudiant;
        this.noteEtudiant = noteEtudiant;
        this.programmeEtudiant = programmeEtudiant;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "nomEtudiant='" + nomEtudiant + '\'' +
                ", ageEtudiant=" + ageEtudiant +
                ", noteEtudiant=" + noteEtudiant +
                ", programmeEtudiant=" + programmeEtudiant +
                '}';
    }

    public int getId() {
        return id;
    }
    public String getNomEtudiant(){
        return this.nomEtudiant;
    }
    public int getAgeEtudiant(){
        return this.ageEtudiant;
    }
    public int getNoteEtudiant(){
        return this.noteEtudiant;
    }

    public String getProgrammeEtudiant() {
        return programmeEtudiant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProgrammeEtudiant(String programmeEtudiant) {
        this.programmeEtudiant = programmeEtudiant;
    }

    public void setAgeEtudiant(int ageEtudiant) {
        this.ageEtudiant = ageEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public void setNoteEtudiant(int noteEtudiant) {
        this.noteEtudiant = noteEtudiant;
    }




    @Override
    public boolean equals(Object obj) {
        // si l'objet est comparé à lui-même
        if (this == obj) return true;

        // si l'objet comparé est null ou de type différent
        if (obj == null || getClass() != obj.getClass()) return false;

        // Alors, on peut convertir l'objet en Etudiant
        Etudiant etudiant = (Etudiant) obj;

        // retourner vrai si le matricule et le nom de l'etudiant sont les mêmes
        return ageEtudiant == etudiant.getAgeEtudiant() && nomEtudiant.equals(etudiant.getNomEtudiant()) && noteEtudiant == etudiant.getNoteEtudiant() && programmeEtudiant.equals(etudiant.getProgrammeEtudiant());
    }
}

