package com.vtiger.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

    public String getDataFromProperties(String key) throws IOException {

        FileInputStream fis =
                new FileInputStream("./src/main/resources/CommonData.properties");

        Properties properties = new Properties();

        properties.load(fis);

        String value = properties.getProperty(key);

        fis.close();

        return value;
    }
}
