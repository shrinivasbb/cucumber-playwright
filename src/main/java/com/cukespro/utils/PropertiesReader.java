package com.cukespro.utils;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * @author Shrinivas 
 *
 */
public class PropertiesReader {

	FileReader fileReader;
	Properties properties;

	public PropertiesReader() throws Exception {
		this.fileReader = new FileReader(new File(System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "main" + File.separator + "resources" + File.separator + "env.properties"));
	}

	public Properties readProperties() throws Exception {
		properties = new Properties();
		properties.load(fileReader);
		return properties;
	}
}
