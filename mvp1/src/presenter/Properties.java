package presenter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

public class Properties implements Serializable
{
	private String generateAlgorithm;
    private String solveAlgorithm;
    private int threadsRunning;
    
     public String getGenerateAlgorithm() {
		return generateAlgorithm;
	}


	public void setGenerateAlgorithm(String generateAlgorithm) {
		this.generateAlgorithm = generateAlgorithm;
	}


	public String getSolveAlgorithm() {
		return solveAlgorithm;
	}


	public void setSolveAlgorithm(String solveAlgorithm) {
		this.solveAlgorithm = solveAlgorithm;
	}


	public int getThreadsRunning() {
		return threadsRunning;
	}


	public void setThreadsRunning(int threadsRunning) {
		this.threadsRunning = threadsRunning;
	}
          
    public Properties() {
	}
     
    
}
