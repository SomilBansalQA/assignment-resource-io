package com.qainfotech.tap.training.resourceio;

import java.io.IOException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
    
    public Object getOptionValue(String optionKey) throws IOException {
        //throw new UnsupportedOperationException("Not implemented.");

     FileReader reader=new FileReader("C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\test\\resources\\options.properties"); 

    	Properties p=new Properties(); 
    	p.load(reader); 

    	return p.getProperty(optionKey);}

    public void addOption(String optionKey, Object optionValue) throws IOException {
      //  throw new UnsupportedOperationException("Not implemented.");
    FileReader reader=new FileReader("C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\test\\resources\\options.properties"); 

    	Properties p=new Properties(); 
    	p.load(reader); 

    	p.setProperty(optionKey,(String) optionValue);

    	p.store(new FileWriter("C:\\Users\\somilbansal\\Desktop\\Eclipse_Workspace\\assignment-resource-io-master1\\src\\test\\resources\\options.properties"),"Written");

    }
}
