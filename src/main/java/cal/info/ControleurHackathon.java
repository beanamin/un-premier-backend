package cal.info;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ControleurHackathon implements HttpHandler {

    private GestionHackathon gestionHackathon;

    public ControleurHackathon(GestionHackathon gh){
        this.gestionHackathon = gh;
    }

    @Override
    public void handle(HttpExchange echange) throws IOException {

        String response = "Bienvenue au controle des hackathons!";
        String typeRequete = echange.getRequestMethod();
        System.out.println("Requete de type : " + typeRequete);
        System.out.println("Query = " + echange.getRequestURI().getQuery());
        String query = echange.getRequestURI().getQuery();

        switch (typeRequete){
            case "GET":
                if (query != null && query.contains("nom=")){
                    try {
                        rechercheHackathons(echange, query.split("nom=")[1]);
                    }catch (SQLException e){
                        System.out.println(e);
                    }
                }else {
                    try{
                    listeHackathons(echange);
                    }
                    catch (SQLException e){
                        System.out.println(e);
                    }
                }break;
            case "POST":
                try{
                    if (query != null && query.contains("etudiant")){
                        ajouterEtudiant(echange);
                    }else {
                        ajouterHackathon(echange);
                    }
                }
                catch (SQLException e){
                    System.out.println(e);
                }
                break;
            case "PUT":
                modifierHackathon(null);
                break;
            case "DELETE":
                try {
                    supprimerHackathon(echange);
                }catch (SQLException e){
                    System.out.println(e);
                }
                break;
            default:
                echange.sendResponseHeaders(405,-1);
                System.out.println("Error type request");
                break;


        }

        echange.sendResponseHeaders(200, response.length());
        OutputStream os = echange.getResponseBody();
        os.write(response.getBytes());

        os.close();
    }


    private void listeHackathons(HttpExchange echange) throws SQLException, IOException {

        List<Hackathon> hackathons = gestionHackathon.obtenirHackathons();

        ObjectMapper mapper = new ObjectMapper();
        String reponse;
        reponse = mapper.writeValueAsString(hackathons);

        byte[] reponseEncode = reponse.getBytes(StandardCharsets.UTF_8);

        echange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        echange.sendResponseHeaders(200, reponseEncode.length);

        OutputStream os = echange.getResponseBody();
        os.write(reponseEncode);
        os.close();

    }
    public void rechercheHackathons(HttpExchange echange ,String nomHackathon) throws SQLException, IOException{
        List<Hackathon> hackathons = gestionHackathon.obtenirHackathons(nomHackathon);

        ObjectMapper mapper = new ObjectMapper();
        String reponse;
        reponse = mapper.writeValueAsString(hackathons);

        byte[] reponseEncode = reponse.getBytes(StandardCharsets.UTF_8);

        echange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        echange.sendResponseHeaders(200, reponseEncode.length);

        OutputStream os = echange.getResponseBody();
        os.write(reponseEncode);
        os.close();
    }
    private void ajouterHackathon(HttpExchange echange) throws SQLException, IOException {

        InputStream corpRequete = echange.getRequestBody();
        InputStreamReader lecteur = new InputStreamReader(corpRequete, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        Hackathon hackathon = mapper.readValue(lecteur, Hackathon.class);

        gestionHackathon.ajouterHackathon(hackathon);

        String reponse = "Le Hackathon a bien été ajouté";
        byte[] reponseEncodee = reponse.getBytes(StandardCharsets.UTF_8);

        echange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        echange.sendResponseHeaders(201, reponseEncodee.length);

        OutputStream os = echange.getResponseBody();
        os.write(reponseEncodee);
        os.close();
    }
    private void ajouterEtudiant(HttpExchange echange) throws SQLException, IOException {

        InputStream corpRequete = echange.getRequestBody();
        InputStreamReader lecteur = new InputStreamReader(corpRequete, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<Etudiant> etudiants = mapper.readValue(lecteur, ArrayList.class);

        gestionHackathon.ajouterEtudiants(etudiants);


        Map<String, Set<Etudiant>> etudiantList = gestionHackathon.getEtudiantsParProg();

        String reponse;
        reponse = mapper.writeValueAsString(etudiantList);
        byte[] reponseEncodee = reponse.getBytes(StandardCharsets.UTF_8);

        echange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        echange.sendResponseHeaders(201, reponseEncodee.length);

        OutputStream os = echange.getResponseBody();
        os.write(reponseEncodee);
        os.close();
    }
    public void modifierHackathon(Hackathon hackathon){
        System.out.println("modifierHackathon");
    }
    public void supprimerHackathon(HttpExchange echange) throws IOException, SQLException{
        final JsonNode node = new ObjectMapper().readTree(echange.getRequestBody().toString());
        if (node.has("nomHackathon")){

        gestionHackathon.supprimerHackathon(node.get("nomHackathon").asText());

        String reponse = "Le Hackathon a bien été supprimé";
        byte[] reponseEncodee = reponse.getBytes(StandardCharsets.UTF_8);

        echange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        echange.sendResponseHeaders(201, reponseEncodee.length);

        OutputStream os = echange.getResponseBody();
        os.write(reponseEncodee);
        os.close();
        }
    }


}
