package com.bdd.automation.framework.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdd.automation.framework.core.BaseStep;

public class FrameworkProperties {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrameworkProperties.class);
	
	private static final String DEFAULT_PROPS = "/framework.properties,/elements.properties";
	
	private static final String FILE_SEPARATION = ",";
	
	private static Properties prop = new Properties();
	
	static{
		String propertyFiles = System.getProperty(DEFAULT_PROPS);
		String[] files = propertyFiles.split(FILE_SEPARATION);
		for(String file:files) {
			loadProperty(file,true);
		}
	}
	
	private static void loadProperty(String file, boolean failOnError) {
		try {
		loadProperty(file);
		}catch(Exception e) {
			LOGGER.error("Error Loading Properties" +e);
			
			if(failOnError){
				new RuntimeException(String.format("Unable to locate properties file %s",file));
				}
			}
	}
	
	private static void loadProperty(String file) throws IOException {
		prop.load(BaseStep.class.getResourceAsStream(file));
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
	

	}