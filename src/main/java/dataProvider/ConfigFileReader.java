package dataProvider;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath= "configs//config.properties";

	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getBrowser(){
		String browser = properties.getProperty("browser");
		if(browser!= null) return browser;
		else throw new RuntimeException("browser not specified in the Config.properties file.");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Config.properties file.");		
	}
	
	public long getExplicitlyWait() {		
		String explicitlyWait = properties.getProperty("explicitlyWait");
		if(explicitlyWait != null) return Long.parseLong(explicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Config.properties file.");		
	}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Config.properties file.");
	}
	
}
