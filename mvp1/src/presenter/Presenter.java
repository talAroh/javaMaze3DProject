package presenter;




import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.CLI;
import view.View;

/**
 * in this class we write the methods declared in the super classes
 * and fulfill the interface Observer that contains the method "update"
 * @author Omer
 *
 */
public class Presenter implements Observer
{
	/**
	 * our model
	 */
	private Model m;
	
	/**
	 * our view
	 */
	private View v;
	
	/**
	 * hashMap mapping between String to Command
	 */
	HashMap<String, Command> hashMap;
	
	/**
	 * CTOR
	 * Initialize the hash map 
	 */
	public Presenter() {
		super();

		hashMap = new HashMap<String,Command>();
		
		
		//initialize the hash map with command
		hashMap.put("1", new Dir(this)); //file name
		hashMap.put("2", new Display(this)); //maze name
		hashMap.put("3", new DisplayCrossSectionByX(this)); //index,maze name
		hashMap.put("4", new DisplayCrossSectionByY(this)); //index,maze name
		hashMap.put("5", new DisplayCrossSectionByZ(this)); //index,maze name
		hashMap.put("6", new DisplaySolution(this)); //maze name
		hashMap.put("7", new FileSize(this)); //file name
		hashMap.put("8", new Generate3dMaze(this)); //maze name,y size,z size,x size,algorithm generator name
		hashMap.put("9", new LoadMaze(this)); //file name,new maze name
		hashMap.put("10", new MazeSize(this)); //maze name
		hashMap.put("11", new SaveMaze(this)); //maze name,new file name
		hashMap.put("12", new Solve(this)); //maze name,solution algorithm name
	}
	
	public Model getM() {
		return m;
	}
	
	public void setM(Model m) {
		this.m = m;
	}
	
	public View getV() {
		return v;
	}
	
	public void setV(View v) {
		this.v = v;
	}


	@Override
	public void update(Observable o, Object arg)
	{
		
		
		if(o == m)
		{	
			try {
				v.displaymessage(arg.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		 }
			
		 if(o instanceof View)
		 {
			 
		    String temp = (String)arg;
		    String[] temp1 = temp.split(" ");
			String choise = temp1[0];
			int choiseInt = Integer.parseInt(choise);
			
			if(choiseInt < 1 || choiseInt > 12)
				try {
					throw new Exception("inavlid number type");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			String var = temp1[1];
			
			try {
				hashMap.get(choise).doCommand(var);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
}
