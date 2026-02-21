//package config;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    private static Properties properties;
//
//    static {
//        try {
//            FileInputStream fis =
//                    new FileInputStream("src/test/resources/config.properties");
//            properties = new Properties();
//            properties.load(fis);
//        } catch (IOException e) {
//            throw new RuntimeException("Config file not found");
//        }
//    }
//
//    public static String get(String key) {
//        return properties.getProperty(key);
//    }
//}

package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream input = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("config/config.properties");

            if (input == null) {
                throw new RuntimeException("config/config.properties NOT found in classpath!");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}