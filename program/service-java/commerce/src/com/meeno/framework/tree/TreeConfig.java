package com.meeno.framework.tree;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class TreeConfig {
	private static final Logger LOGGER = Logger.getLogger(TreeConfig.class);
	private  int maxLevel;
	public int getMaxLevel() {
		return maxLevel;
	}
	private String subStringMethod;
	public String getSubStringMethod() {
		return subStringMethod;
	}
	private TreeConfig() {
		Properties prop = new Properties();
		try {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("TreeConfig init");
			}
			prop.load(this.getClass().getResourceAsStream("tree_config.properties"));
			maxLevel = Integer.parseInt(prop.getProperty("maxLevel","6"));

			subStringMethod = prop.getProperty("substringMethod","subString");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
	private static final TreeConfig INSTANCE = new TreeConfig();
	public static TreeConfig getInstance() {
		return INSTANCE;
	}

}
