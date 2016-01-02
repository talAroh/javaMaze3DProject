package view;

import java.util.Observable;

public interface View 
{
	/**
	 * this start method calling to the start method the command line interface (CLI)
	 * and from there the Io loop started running
	 */
	public void start();
	
	   /**
	 * display the message to the the user
	 * 
	 * gets a string and print it to a file or to the screen
	 * @param str
	 * @throws Exception 
	 */
	public void displaymessage(String str) throws Exception ;
	
	//public int getUserCommand();
	
	
	
}
