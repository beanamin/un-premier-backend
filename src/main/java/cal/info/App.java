package cal.info;

import cal.info.util.BaseDeDonneesUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws IOException {
        // Création du serveur HTTP qui écoutera sur le port 8000
        HttpServer serveur = HttpServer.create(new InetSocketAddress(8000), 0);
        ConfigurationPool.initialiser();

            String creationTableSQL = "CREATE TABLE IF NOT EXISTS Hackathon ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nomHackathon VARCHAR(255) NOT NULL,"
                    + "dateHackathon VARCHAR(50) NOT NULL,"
                    + "lieuHackathon VARCHAR(255) NOT NULL,"
                    + "urlHackathon VARCHAR(255) NOT NULL"
                    + ");";

            try (Connection conn = ConfigurationPool.obtenirDataSource().getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.execute(creationTableSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        // Première route "/accueil" :
        serveur.createContext("/", echange -> {
            String response = "Bienvenue sur la page d'accueil !";
            echange.sendResponseHeaders(200, response.length());
            OutputStream os = echange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        GestionHackathonDAO dao = new GestionHackathonDAO();
        GestionHackathon gestionHackathon = new GestionHackathon(dao);
        ControleurHackathon controleurHackathon = new ControleurHackathon(gestionHackathon);

        serveur.createContext("/hackathons", controleurHackathon);
        serveur.createContext("/etudiants", new ControleurEtudiant());

        // Démarrer le serveur
        serveur.setExecutor(null); // Créer un exécuteur par défaut
        serveur.start();

        System.out.println("Serveur démarré et en écoute sur le port 8000");
    }
}
