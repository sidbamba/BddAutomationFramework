package com.bdd.automation.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrameworkProperties {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrameworkProperties.class);
	
	private static final String DEFAULT_PROPS = "/framework.properties,/elements.properties";
	
	private static final String FILE_SEPARATION = ",";
	static{
		String propertyFiles = System.getProperty(DEFAULT_PROPS);
		String[] files = propertyFiles.split(FILE_SEPARATION);
		for(String file:files) {
			
		}
	}
	}