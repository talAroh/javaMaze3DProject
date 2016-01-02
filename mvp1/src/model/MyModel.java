package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.SearchableAdapter;
import algorithms.maseGenerators.DFSMazeGanarator;
import algorithms.maseGenerators.Maze3d;
import algorithms.maseGenerators.Maze3dGenerator;
import algorithms.maseGenerators.Position;
import algorithms.maseGenerators.PrimMazeGenerator;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.Heuristic;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;
import algorithms.search.StateCostComperator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * a class that implements the interface Model and fulfills those functions 
 * @author Omer
 *
 */
public class MyModel extends Observable implements Model {
     
	
	/**
	 * HashMap between the name of the maze to the 
	 * maze itself.
	 * 
	 * (HashMap <String,Maze3d>).
	 */
	HashMap<String, Maze3d> hm;
	
	int size;
	
	/**
	 * HashMap between the name of the maze (String)
	 * to its solution.
	 * HashMap<String,Solution<Position>>
	 * 
	 * after we solved a current maze in a current solution algorithm,
	 * this hash map will be updated and the solution of the name of the right
	 * maze will be updated.
	 */
	HashMap<String, Solution<Position>> algohashmap;
	
	//HashMap<String,File> filehashmap;
	
	/**
	 * ctor
	 * creates a new 2 hash maps
	 * @param c
	 */
	HashMap<Maze3d, Solution<Position>> mazeSolution;
	
	
	
	ExecutorService exe;
	
	public MyModel(int numOfTreads)
	{
		super();
		hm = new HashMap<String,Maze3d>();//initialize the hash map
		algohashmap = new HashMap<String,Solution<Position>>();
		mazeSolution = new HashMap<Maze3d,Solution<Position>>();
		exe  = Executors.newFixedThreadPool(numOfTreads);
		
	}
	
	@Override
	public Maze3d generate3dmaze(String name, int y, int z, int x,String algoname) throws Exception
	{ 
		Callable<Maze3d> a = new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				
				Maze3dGenerator mg = null;
				Maze3d maze = null;
								
				try {//set the algorithm
					if(algoname.equals("Prim"))
					{
						mg = new PrimMazeGenerator();	
					}
					if(algoname.equals("DFS"))
						mg = new DFSMazeGanarator();
					maze = mg.generate(y, z, x);
				} catch (NullPointerException e) {
					System.out.println("invalid algorithm name");
					e.printStackTrace();
				}
				
			   	hm.put(name, maze);//add to the mazes;
			    return hm.get(name);
			}
		
		 };
		 /*Future<Maze3d> f =*/exe.submit(a);
		 setChanged();
		 notifyObservers("the maze "+name+" is ready");
		 
		 return hm.get(name);
	}

	@Override
	public Solution<Position> solve(String name, String algorithm)  throws Exception{
		if(mazeSolution.containsKey(name))///////////////////////
		{
			return algohashmap.get(name);
		}
		
       Callable<Solution<Position>> a = new Callable<Solution<Position>>() {

			@Override
			public Solution<Position> call() throws Exception {
				Searchable<Position> serchable  = new SearchableAdapter(hm.get(name));
				State<Position> goal_state = new State<Position>(hm.get(name).getGoalPos());
				Comparator<State<Position>> comparator = new StateCostComperator<Position>();
				Searcher<Position> a = null;
				switch(algorithm){//set the algorithm
				case "bfs"://set the algorithm to bfs
				    a = new BFS<Position>(comparator);
					break;
				case "a star air distance"://set the algorithm to a star air distance
					Heuristic<Position> heuristic1 = new MazeAirDistance<Position>(goal_state);
					a = new Astar<Position>(heuristic1,comparator);
					break;
				case "a star manhattan distance"://set the algorithm to a star manhattan distance
					Heuristic<Position> heuristic2 = new MazeManhattanDistance<Position>(goal_state);
					a = new Astar<Position>(heuristic2,comparator);
					break;
				}
				Solution<Position> solution = new Solution<Position>();//the solution of the maze
				try {
					solution = a.search(serchable);
					
				} catch (NullPointerException e) {
					
					e.printStackTrace();
					System.out.println("wrong algorithm name in solve in model");
				}
				algohashmap.put(name, solution);
				mazeSolution.put(hm.get(name),solution);
				return algohashmap.get(name);
			}
        	
		};
		/*Future<solution<position>> f = */exe.submit(a);
		setChanged();
		notifyObservers("The solution is ready");
		
        return algohashmap.get(name);
	}

	@Override
	public String display(String name) throws Exception {
	    if(!hm.containsKey(name))
	    	throw new Exception("maze name does not exist");
	    
		return hm.get(name).getMaze();
	}

	@Override
	public long fileSize(String name) throws NullPointerException
	{
		File f = new File(name +".maz");
		
	
		return f.length();
	}

	@Override
	public int[][] getCrossByX(int index, String name) 
	{
		return hm.get(name).getCrossSectionByX(index);
	}

	@Override
	public int[][] getCrossByY(int index, String name) 
	{
		return hm.get(name).getCrossSectionByY(index);
	}

	@Override
	public int[][] getCrossByZ(int index, String name) 
	{
		return hm.get(name).getCrossSectionByZ(index);	
	}

	@Override
	public int mazeSize(String name) throws Exception
	{
	    if(!hm.containsKey(name))
	    	throw new Exception("maze name does not exist");
	   
		return (((hm.get(name).getA()*hm.get(name).getB()*hm.get(name).getC())+9)*4);  //the real size of the maze in bytes
	}

	@Override
	public String dir(String path) throws NullPointerException
	{
         File f = new File(path);
         String []str = f.list();
         String listpath = "";
         
         for(int i=0 ; i<str.length ; i++)
        	 listpath+=str[i]+'\n'; //getting one line down in order it will be more  
         							//comfortable for the user/client to read.
         return listpath;
         
	}

	@Override
	public void saveMaze(String name, String fileName) 
	{ 
		OutputStream out=null;
		File f = new File(fileName+".maz"); //saving in "maz" file type.
		
		try {
			out = new MyCompressorOutputStream(new FileOutputStream(f));
		} catch (FileNotFoundException e4) {
			
			e4.printStackTrace();
		}
		
		try {
			out.write(hm.get(name).toByteArray());
		} catch (IOException e3) {
			
			e3.printStackTrace();
		}
		try {
			out.flush();
		} catch (IOException e2) {
			
			e2.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}	
		
		setChanged();
		notifyObservers("The maze saved successfully");
	}

	@Override
	public void loadMaze(String fileName, String name) 
	{
		InputStream in = null;
		File f = new File(fileName+".maz");
		int []arr = new int[6];
		int x = 0,y = 0,z =0;
		try {
			in = new MyDecompressorInputStream(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		for(int i=0 ; i<6 ; i++)
			try {
				arr[i]=in.read();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
		try {
			y = in.read();
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try {
			z = in.read();
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try {
			x = in.read();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		byte[] b = new byte[(x*y*z)+9];
		
		for(int j=0 ; j<6 ; j++)
			b[j] = (byte)arr[j];
		
		b[6] = (byte)y;	
		b[7] = (byte)z;	
		b[8] = (byte)x;	
		
		try {
			in.read(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		Maze3d loaded=null;
		try {
			loaded = new Maze3d(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hm.put(name, loaded);
		
		setChanged();
		notifyObservers("The maze loaded successfully");
		
	}

	@Override
	public Solution<Position> displaySolution(String name) throws Exception
	{
		if(!algohashmap.containsKey(name))
			throw new Exception("algorithm name does not exist");
		return algohashmap.get(name);
	}
    public void saveToZipFile(String path){
    	try {
			byte[] b = serialize(mazeSolution);
	    	GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(path));
	    	out.write(b);///////////////////////////////////////////////////////////////////////////////////////add flush
	    	out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }
    	
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
    
   /* public HashMap<Maze3d, Solution<Position>> readFromZip(String path)
    {
    	byte [] byteArr = new byte[mazeSolution.size()];
    	
    	GZIPInputStream in=null;
		try {
			in = new GZIPInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 try {
			in.read(byteArr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }*/
    
}
