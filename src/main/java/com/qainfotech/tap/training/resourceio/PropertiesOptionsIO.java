package com.qainfotech.tap.training.resourceio;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO {
	/**
	 * 
	 * @param optionKey
	 * @return
	 * @throws IOException
	 */
	public Object getOptionValue(String optionKey) throws IOException {
		// throw new UnsupportedOperationException("Not implemented.");

		// Reading Option.properties File from main resources
		FileReader reader = new FileReader(
				"C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\main\\resources\\options.properties");

		Properties p = new Properties();
		p.load(reader);

		return p.getProperty(optionKey);
	}

	/**
	 * 
	 * @param optionKey
	 * @param optionValue
	 * @throws IOException
	 */
	public void addOption(String optionKey, Object optionValue) throws IOException {
		// throw new UnsupportedOperationException("Not implemented.");

		FileReader reader = new FileReader(
				"C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\main\\resources\\options.properties");

		Properties p = new Properties();
		p.load(reader);

	
		p.setProperty(optionKey, (String) optionValue);

		// Writing in Option.properties File

		p.store(new FileWriter(
				"C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\main\\resources\\options.properties"),
				"");

	}
}
