package ud8.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppPropertiesReader {
    private static final Properties properties = new Properties();

    static {
        loadProperties("application.properties"); // Carga las propiedades por defecto

        // Detectar el perfil y cargar las propiedades correspondientes
        String activeProfile = getProperty("app.profiles.active");
        if (activeProfile != null) {
            loadProperties("application-" + activeProfile + ".properties");
        }
    }

    private static void loadProperties(String filename) {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                System.out.println("No s'ha pogut trobar el fitxer de configuració: " + filename);
                return;
            }

            // Cargar las propiedades desde el archivo de configuración
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error al llegir el fitxer de configuració: " + filename);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
