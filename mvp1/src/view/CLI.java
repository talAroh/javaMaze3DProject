package view;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;

import presenter.Command;


/**
 * CLI = Command Line Interface
 * 
 * the command line of the user interface
 * @author Omer
 *
 */
public class CLI extends Observable implements View
{
	/**
	 * presented our input form the user,
	 * we gets strings from this buffered reader in
	 */
	private BufferedReader in;
	
	/**
	 * presented our output to a "file" called our 
	 */
	private PrintWriter out;
	
	/**
	 * CTOR
	 * @param in
	 * @param out
	 * 
	 */
	public CLI(BufferedReader in,PrintWriter out)//constarctor
	{
		//super();
		this.in = in;
		this.out = out;
	}
	
	/**
	 * From here out project is really working
	 * 
	 * this method is running a thread that gets a all the time
	 * String from the user input, until it gets the String "exit"
	 * 
	 * each string is running the right doCommand , that running the methods in the model 
	 */
	public void start()
	{	
		new Thread(new Runnable() {
			@Override
			public void run(){
				
				  String temp = new String();// contains the command that the user want to call
				  Scanner input = new Scanner(System.in);//input form the user
				  while(!(temp = input.nextLine()).equals("exit"))
				  {
						  try {
							  setChanged();
							  notifyObservers(temp);
							  //call the function that the user want
						  } catch (Exception e) {
							
							  e.printStackTrace();
						  }

				  }
				  exit();// close the buffer and the printer
				  input.close();
				  return;
			      }
		
		}).start();//start the thread
		
		return;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	
	/**
	 * this function actually exit from our project 
	 * in an ordered way and closes/exits any files or open threads
	 */
	public void exit()// close the buffer and the printer
	{
		System.out.println("Bye Bye");
		
		try {
			in.close();
		} catch (IOException e) {	
			e.printStackTrace();
		}		
		out.close();
	}
	
	 
	public void displaymessage(String str) {
		out.println(str);
		out.flush();
		
		System.out.println(str);
	}
}
