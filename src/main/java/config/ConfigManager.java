package config;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream is = new FileInputStream("config.properties")) {
            properties.load(is);
        } catch (IOException ignored) {
        }
    }

    public static String get(String key) {

        // 1️⃣ Check JVM system property (-Dbase.url=...)
        String value = System.getProperty(key);
        if (value != null) {
            return value;
        }

        // 2️⃣ Check environment variable (BASE_URL)
        String envKey = key.toUpperCase().replace(".", "_");
        value = System.getenv(envKey);
        if (value != null) {
            return value;
        }

        // 3️⃣ Fallback to config.properties
        return properties.getProperty(key);
    }
}
