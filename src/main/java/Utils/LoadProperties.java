package Utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.System.getProperty;

public class LoadProperties {


    private static final Logger logger = LoggerFactory.getLogger(LoadProperties.class);
    static String defaultPropertiesFile = "config.properties";
    private static Properties confProperties = new Properties();


    private static void loadPropFromResource(String fileName) {
        try {
            InputStream inputStream = LoadProperties.class.getClassLoader().getResourceAsStream(fileName);
            FileReader reader = new FileReader(fileName);
            InputStream inputStream1 = new FileInputStream(fileName);
            // Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            confProperties.load(inputStream1);
            // logger.info("Loading prop file - from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed  to load prop files -- " + e);
        }
    }


    /**
     * Read property from given properties file
     *
     * @param Key value to get
     * @param filename properties file from root
     * @return
     */
    public static String readProperty(String Key, String filename) {
        File file = new File(filename);
        Properties prop = new Properties();
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("Unable to find : " + filename + "---" + e.getMessage());
        }

        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to load property file : " + filename + "---" + e.getMessage());
        }
        // System.out.println(prop.getProperty(Key));
        logger.info("Successfully read property file: " + filename);
        return prop.getProperty(Key);
    }

    /**
     * Read key value from config.properties file
     *
     * @param key to get
     * @return string value
     */
    public static String readConfigProperty(String key) {
        String value = null;
        try {
            loadPropFromResource(defaultPropertiesFile);
            value = confProperties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unable to find property - " + key + " check the properties file" + "---" + e);
        }

        return value;
    }

    public static HashMap<String, String> loadValuesToMap(String filePath) {
        HashMap<String, String> propertyValues = new HashMap<String, String>();
        try {
            File file = new File(filePath);
            Properties props = new Properties();
            FileInputStream fileInput = null;
            try {
                fileInput = new FileInputStream(file);
                props.load(new InputStreamReader(fileInput, Charset.forName("UTF-8")));
            } catch (FileNotFoundException e) {
                logger.error("Unable to find : " + filePath + "---" + e.getMessage());
            }
            // load values to map
            for (final Map.Entry<Object, Object> entry : props.entrySet()) {
                propertyValues.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Failed to load values to hash map" + e.getMessage());
        }
        return propertyValues;
    }



    public static String getAppUrl() throws Exception {
        String appUrl = null;
        appUrl = getPropertyValue("appUrl");
        return appUrl;
    }


    public static String getBrowserType() throws Exception {
        String browserType = null;
        browserType = getPropertyValue("browserType");
        return browserType;
    }





    public static String getPropertyValue(String propertyKey) throws Exception {
        String propertyValue;
        if (isNullOrEmpty(getProperty(propertyKey))) {
            propertyValue = getProperty(propertyKey);
        } else {
            propertyValue = readConfigProperty(propertyKey);
        }
        if (propertyValue == null) {
            throw new Exception(
                    "property "
                            + propertyKey
                            + " is missing, please add it as system property or in config.properties files");
        }
        return propertyValue;
    }


    public static String getAdminUrl() throws Exception {
        return getPropertyValue("AdminUrl");
    }


















    public static int getElementWaitDuration()  {
        String configValue = readConfigProperty("elementWaitInSeconds").trim();
        return Integer.parseInt(configValue);
    }






    public static String getAPIHost() throws Exception {
        return getPropertyValue("APIHost");
    }


    public static String getAPIBuildUrl() throws Exception {
        return getPropertyValue("APIBuildUrl");
    }

    public static Boolean isNullOrEmpty(String value) {
        boolean isNullOrEmpty = false;
        if (value != null && !value.isEmpty()) {
            isNullOrEmpty = true;
        }
        return isNullOrEmpty;
    }




    public static Boolean isPropertyKeyPresent(String keyName) {
        boolean isKeyPresent = false;
        if (confProperties.containsKey(keyName)) {
            isKeyPresent = true;
        }
        return isKeyPresent;
    }
}
