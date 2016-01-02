package boot;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;

import model.Model;
import model.MyModel;
import presenter.Command;
import presenter.Dir;
import presenter.Display;
import presenter.DisplayCrossSectionByX;
import presenter.DisplayCrossSectionByY;
import presenter.DisplayCrossSectionByZ;
import presenter.DisplaySolution;
import presenter.FileSize;
import presenter.Generate3dMaze;
import presenter.LoadMaze;
import presenter.MazeSize;
import presenter.Presenter;
import presenter.Properties;
import presenter.SaveMaze;
import presenter.Solve;
import view.CLI;
import view.MyView;
import view.View;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

public class Run {

	public static void main(String[] args) throws IOException 
	{
		PrintWriter out = null;
		
		try { 
			out = new PrintWriter(new FileWriter("2.maz"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("2.maz"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
        XMLDecoder xml = new XMLDecoder(new FileInputStream("properties.xml"));
        Properties properties = new Properties();
        properties = (Properties)xml.readObject();
		//m.saveToZipFile("mazeWithSolution.zip");/////////////////////
		
		MyModel m = new MyModel(properties.getThreadsRunning());
		Presenter p = new Presenter();
		
		
		CLI cli = new CLI(in,out);
		MyView v = new MyView(cli);
        p.setM(m);
        p.setV(v);
		m.addObserver(p);
		cli.addObserver(p);
		
		v.start();//start the project
	}

}
