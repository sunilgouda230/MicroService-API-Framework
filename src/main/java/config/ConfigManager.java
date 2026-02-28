package config;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try(FileInputStream is = new FileInputStream("config.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String get(String key) {
        return System.getenv(key.toUpperCase().replace(".", "_"));
    }

}
