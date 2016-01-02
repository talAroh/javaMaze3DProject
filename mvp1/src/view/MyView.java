package view;



/**
 * this class is actually fulfills all of our methods from the 
 * View Interface 
 * @author Omer
 *
 */
public class MyView  implements View
{
	/**
	 * Ctor, gets a cli(Command Line Interface)
	 * @param cli
	 */
	public MyView(CLI cli) 
	{	
		super();
		this.cli = cli;
	}
	public MyView(){
		
	}
	
	CLI cli;




	public void start() 
	{
		cli.start();
	}

	@Override
	public void displaymessage(String str)
	{
	    if(str.equals(null))
	    	throw new NullPointerException("invalid input");
		try {
			cli.displaymessage(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*@Override
	public int getUserCommand()
	{
        return 0;
	}*/	
}
