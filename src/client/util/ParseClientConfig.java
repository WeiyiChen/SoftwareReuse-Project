package client.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.Serializer;

public class ParseClientConfig {
	private static ClientConfig clientConfig;
	
	public static void format(OutputStream os, Document doc){
		try {
			Serializer serializer = new Serializer(os, "ISO-8859-1");
			serializer.setIndent(4);
			serializer.setMaxLength(60);
			serializer.write(doc);
			serializer.flush();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeClientConfigFile(){
		Element clientConfig = new Element("clientConfig");
		Element ip = new Element("ip");
		Element port = new Element("port");
		ip.appendChild("10.60.41.1");
		port.appendChild("12345");
		clientConfig.appendChild(ip);
		clientConfig.appendChild(port);
		Document doc = new Document(clientConfig);
		format(System.out, doc);
		try {
			format(new BufferedOutputStream(
					new FileOutputStream("ClientConfig.xml")), doc);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object rebuildConfig(String fileName){
		try {
			Document doc = new Builder().build(new BufferedInputStream(
					new FileInputStream("ClientConfig.xml")));
			Element root = doc.getRootElement();
			String ip = root.getFirstChildElement("ip").getValue();
			String port = root.getFirstChildElement("port").getValue();
			clientConfig = new ClientConfig(ip, port);
			return clientConfig;
		} catch (ParsingException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println("failed to open the config file");
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getConfig(String fileName) {
		if(clientConfig == null){
			return rebuildConfig(fileName);
		}
		else{
			return clientConfig;
		}
	}
}
