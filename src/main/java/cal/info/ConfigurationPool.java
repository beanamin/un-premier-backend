package cal.info;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class ConfigurationPool {

    private static HikariDataSource dataSource;

    /**
     * Initialise le pool de connexion (à appeler UNE SEULE FOIS au démarrage)
     */
    public static void initialiser() {
        HikariConfig config = new HikariConfig();

        // Configuration de la base de données H2
        config.setJdbcUrl("jdbc:h2:./data/maBaseDeDonnees");
        config.setUsername("sa");
        config.setPassword("");
        config.setDriverClassName("org.h2.Driver");

        // Configuration du pool
        config.setMaximumPoolSize(10);        // Maximum 10 connexions
        config.setMinimumIdle(2);             // Minimum 2 connexions en attente
        config.setConnectionTimeout(30000);    // Timeout de 30 secondes
        config.setIdleTimeout(600000);         // Fermer après 10 minutes d'inactivité
        config.setMaxLifetime(1800000);        // Durée de vie maximale : 30 minutes

        // Nom du pool (pour le debugging)
        config.setPoolName("PoolH2Principal");

        // Requête de test de connexion
        config.setConnectionTestQuery("SELECT 1");

        // Auto-commit désactivé par défaut (bonne pratique)
        config.setAutoCommit(true);

        // Créer le pool
        dataSource = new HikariDataSource(config);

        System.out.println("✓ Pool de connexion initialisé");
    }

    /**
     * Obtenir le DataSource (pool de connexion)
     */
    public static DataSource obtenirDataSource() {
        if (dataSource == null) {
            throw new IllegalStateException(
                    "Le pool n'est pas initialisé. Appelez initialiser() d'abord."
            );
        }
        return dataSource;
    }

    /**
     * Fermer le pool (à appeler à la fin de l'application)
     */
    public static void fermer() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("✓ Pool de connexion fermé");
        }
    }
}
