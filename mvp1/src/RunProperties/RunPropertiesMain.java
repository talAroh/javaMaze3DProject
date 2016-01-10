package RunProperties;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;

/**
 * in this class we just save the properties in a 
 * xml file
 * 
 * (running an another main function
 * @author Omer
 *
 */
public class RunPropertiesMain {

	public static void main(String[] args) throws FileNotFoundException {
		Properties p =new Properties();
		p.setGenerateAlgorithm("Prim");
		p.setSolveAlgorithm("bfs");
		p.setThreadsRunning(2);
		XMLEncoder xml = new XMLEncoder(new FileOutputStream("properties.xml")); //the name of the 
																				 //xml file is "properties"
		xml.writeObject(p);
		xml.close();
		
	}

}
