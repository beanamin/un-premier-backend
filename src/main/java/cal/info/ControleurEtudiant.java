package cal.info;

import com.fasterxml.jackson.core.JsonProcessingException;
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

public class ControleurEtudiant implements HttpHandler{
    GestionEtudiant gestionEtudiant = new GestionEtudiant();
    @Override
    public void handle(HttpExchange echange) throws IOException {
        String response = "Bienvenue au controle des etudiants!";
        String typeRequete = echange.getRequestMethod();
        System.out.println("Requete de type : " + typeRequete);
        String query = echange.getRequestURI().getQuery();

        switch (typeRequete){
            case "GET":
                try{
                    afficherEtudiants(echange);
                }
                catch (SQLException e){
                    System.out.println(e);
                }
                break;
            case "POST":
                try{
                    ajouterEtudiant(echange);
                }
                catch (SQLException e){
                    System.out.println(e);
                }
                break;
            case "PUT":
                modifierEtudiant();
                break;
            case "DELETE":

                supprimerEtudiant(echange);

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
    public void afficherEtudiants(HttpExchange exchange) throws IOException, SQLException{
        List<Etudiant> etudiantList = gestionEtudiant.obtenirEtudiants();
        String response = null;
        ObjectMapper mapper = new ObjectMapper();
        List <Etudiant> etudiants = etudiantList;
        try {
            response = mapper.writeValueAsString(etudiants);

            byte[] reponseEncode = response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, reponseEncode.length);

            OutputStream os = exchange.getResponseBody();
            os.write(reponseEncode);
            os.close();
        }catch (IOException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    public void ajouterEtudiant(HttpExchange exchange) throws IOException, SQLException{
        InputStream corpRequete = exchange.getRequestBody();
        InputStreamReader lecteur = new InputStreamReader(corpRequete, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        Etudiant etudiant = mapper.readValue(lecteur, Etudiant.class);

        gestionEtudiant.ajouterEtudiant(etudiant);

        String reponse = "L'Etudiant a bien été ajouté";
        byte[] reponseEncodee = reponse.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(201, reponseEncodee.length);

        OutputStream os = exchange.getResponseBody();
        os.write(reponseEncodee);
        os.close();
    }
    public void modifierEtudiant(){

    }
    public void supprimerEtudiant(HttpExchange exchange){

    }

}
