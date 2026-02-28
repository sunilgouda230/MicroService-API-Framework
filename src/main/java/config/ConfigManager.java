package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input =
                    ConfigManager.class.getClassLoader()
                            .getResourceAsStream("config.properties");

            if (input != null) {
                properties.load(input);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String get(String key) {

        // 1️⃣ First check environment variable
        String envValue = System.getenv(key.toUpperCase());

        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        // 2️⃣ Fallback to properties file
        return properties.getProperty(key);
    }
}