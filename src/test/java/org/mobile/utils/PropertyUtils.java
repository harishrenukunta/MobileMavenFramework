package org.mobile.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class PropertyUtils {
    private static PropertyUtils INSTANCE;
    public Properties properties = new Properties();

    private PropertyUtils(){
        this.loadProperties("configuration.properties");
        this.properties.putAll(System.getProperties());
    }

    public static PropertyUtils getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new PropertyUtils();
        }
        return INSTANCE;
    }
    public static int getIntegerProperty(String propKey, int defaultValue) {
        final String propVal = getINSTANCE().properties.getProperty(propKey);
        if(propVal == null){
            return defaultValue;
        }
        return Integer.parseInt(propVal);
    }

    public static String getProperty(final String propKey){
        return getINSTANCE().properties.getProperty(propKey);
    }

    public static Properties getProps(){
        return getINSTANCE().properties;
    }

    private void loadProperties(final String filename){
        try {
            final InputStream propFile = ClassLoader.getSystemResourceAsStream("configuration.properties");
            this.properties.load(propFile);
            if(propFile != null) {
                propFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
