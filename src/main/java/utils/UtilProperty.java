package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilProperty {
    private static Properties prop;
    static{
         prop = new Properties();
        try (InputStream inputStream = UtilProperty.class.getClassLoader().getResourceAsStream("application.properties")) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyValue(String propertyName) {
        String propertyValue = prop.getProperty(propertyName);
        return propertyValue;
    }
}