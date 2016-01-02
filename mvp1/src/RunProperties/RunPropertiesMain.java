package RunProperties;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;

public class RunPropertiesMain {

	public static void main(String[] args) throws FileNotFoundException {
		Properties p =new Properties();
		p.setGenerateAlgorithm("Prim");
		p.setSolveAlgorithm("bfs");
		p.setThreadsRunning(2);
		XMLEncoder xml = new XMLEncoder(new FileOutputStream("properties.xml"));
		
		xml.writeObject(p);
		xml.close();
		
	}

}
