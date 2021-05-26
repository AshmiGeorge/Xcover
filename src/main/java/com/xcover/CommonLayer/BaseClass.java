package com.cba.CommonLayer;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;



public class BaseClass {
	




public String readProperty(String property) {
	 String userDir = System.getProperty("user.dir");
    Properties prop = new Properties();
    try (InputStream input = new FileInputStream(userDir + "//src//test//resources/config.properties")) {
        prop.load(input);
        prop.getProperty(property);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return prop.getProperty(property);
}


}
