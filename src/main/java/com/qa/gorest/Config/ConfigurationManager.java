package com.qa.gorest.Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkException.APIFrameworkException;

public class ConfigurationManager {

	
	
	private Properties prop;
	///Properties initialization
	public Properties init_Prop() {
		prop = new Properties();
		String path = null;
		String env = null;
		
		try {
			env = System.getProperty("env");
			System.out.println("Running on Environment: ---->" + env);
			if(env == null){
				path = "./src/test/resources/config/config.properties";
			}
			else{
				switch (env.toLowerCase()) {
				case "qa":
					System.out.println("Running on Environment: ---->" + env);
					path = "./src/test/resources/config/config.qa.properties";
					break;
				case "dev":
					System.out.println("Running on Environment: ---->" + env);
					path = "./src/test/resources/config/config.dev.properties";
					break;
				case "prod":
					System.out.println("Running on Environment: ---->" + env);
					path = "./src/test/resources/config/config.properties";
					break;
				default:
					System.out.println("Please pass the correct env value...." + env);
					throw new APIFrameworkException("Invalid env setup");
					
				}
			}
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
