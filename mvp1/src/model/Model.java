package model;


import java.io.FileNotFoundException;

import algorithms.maseGenerators.Maze3d;
import algorithms.maseGenerators.Position;

import algorithms.search.Solution;

/**
 * an interface contains all of the functions we wrote for the user
 * 
 * we get these functions from the pdf file of the project
 * @author Omer
 *
 */
public interface Model 
{
	/**
	 * generate a 3d  maze in the current name and gets its sizes 
	 * @param name
	 * @param y - the y size of the maze
	 * @param z - the z size of the maze
	 * @param x - the x size of the maze
	 * @param algoname - means the name of the algorithm we want to generate the maze
	 * @return - return a maze with start position , goal position and the right cells in a randomalic way
	 * @throws Exception 
	 */
	public Maze3d generate3dmaze(String name,int y,int z,int x,String algoname) throws Exception ;
	
	/**
	 * solving the maze in name "name" by the algorithm in name "algorithm" 
	 * and return a solution to the right maze in the right algorithm we wanted
	 * @param name
	 * @param algorithm
	 * @return
	 * @throws Exception 
	 */
	public Solution<Position> solve(String name, String algorithm) throws Exception;
	
	/**
	 * gets the name of the maze and return the maze in the right name (as string)
	 * @param name
	 * @return string presented the maze
	 * @throws Exception 
	 */
	public String display(String name) throws Exception;
	
	/**
	 * gets the name of the file and return the size of the file
	 * (return value is in long type)
	 * @param name
	 * @return 
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 */
	public long fileSize(String name) throws Exception;
	
	/**
	 * return a 2d array presented a cross section of the maze 
	 * (using the "getCrossBy" function in the Maze3d class
	 * @param index
	 * @param name
	 * @return
	 */
	public int[][] getCrossByX(int index,String name);
	
	/**
	 * return a 2d array presented a cross section of the maze 
	 * (using the "getCrossBy" function in the Maze3d class
	 * @param index
	 * @param name
	 * @return
	 */
	public int[][] getCrossByY(int index,String name);
	
	/**
	 * return a 2d array presented a cross section of the maze 
	 * (using the "getCrossBy" function in the Maze3d class
	 * @param index
	 * @param name
	 * @return
	 */
	public int[][] getCrossByZ(int index,String name);
	
	/**
	 * gets the name of the maze and return the size of the maze in the current name in int
	 * format : 9+y_size * z_size * x_size
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public int mazeSize(String name) throws Exception;
	
	/**
	 * return a string presented the files and folders 
	 * that in the right path
	 * @param path
	 * @return
	 */
	public String dir(String path);
	
	/**
	 * saving the maze called "name" in the file called "fileName"
	 * @param name
	 * @param fileName
	 */
	public void saveMaze(String name,String fileName);
	
	/**
	 * load from the file called "fileName" a new maze that will be saved
	 * under the name "name"
	 * @param fileName
	 * @param name
	 */
	public void loadMaze(String fileName,String name);
	
	/**
	 * display the solution of the maze called "name"
	 * returns a Solution<Position> presented the solution of the current maze
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public Solution<Position> displaySolution(String name) throws Exception;
	
	public void saveToZipFile(String path);

}
