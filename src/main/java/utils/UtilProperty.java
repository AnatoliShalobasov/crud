package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilProperty {
    public static String getPropertyValue(String propertyName) {
        String propertyValue = "";
        Properties prop = new Properties();

        try (InputStream inputStream = UtilProperty.class.getClassLoader().getResourceAsStream("application.properties");) {
            prop.load(inputStream);
            propertyValue = prop.getProperty("DAO");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }
}