package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.maseGenerators.Maze3d;
import algorithms.maseGenerators.Position;


// this is (1) the common type, and (2) a type of widget
// (1) we can switch among different MazeDisplayers
// (2) other programmers can use it naturally

/**
 * This class is the Maze Displayer class.
 * This class is a common and type, the structure of this class
 * help us to creat a different type of widgets, without changing this class!
 * 
 *  We can switch among different Maze Displayers.
 *  
 *  Other programmers can use this class natrually.
 *  The class extends Canvas Class, on order we can use many "Window" functions, like(paint etc...)
 *  
 *  The class hold the name of the current thing/game it displays and an array of integers, presented this game.
 * 
 * @author Omer
 */
public abstract class MazeDisplayer extends Canvas{
	
	// just as a stub...
	/*int[][] mazeData;
	
	public String mazeName;*/
	
	String name;
	
    int[][][] maze;
    
    int characterX;
    int characterY;
    int flourCharacter;
	
	
	 int exitX;
	 int exitY;
	 int flourExit;

	
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
		
		
	}

	/*public void setMazeData(int[][] mazeData){
		this.mazeData=mazeData;
	}*/
	
	public int[][] CrossSectionByY(int floor){
		int[][] ans = new int[maze[0].length][maze[0][0].length];
        for (int i = 0; i < maze[0].length; i++) {
			for (int j = 0; j < maze[0][0].length; j++) {
				ans[i][j] = maze[floor][i][j];
			}
		}	
        return ans;
	}
	
	public abstract  void setCharacterPosition(int row,int col);

	public int[][][] getMaze() {
		return maze;
	}

	public void setMaze(int[][][] maze) {
		this.maze = maze;
	}
	
	public void setVal(int y,int z,int x, int value) {
		this.maze[y][z][x] = value;
	}

	public abstract void moveUp();

	public abstract  void moveDown();

	public abstract  void moveLeft();

	public  abstract void moveRight();
	
	public abstract void moveForwards();
	
	public abstract void moveBackwards();

	/*public Position getStartPos() {
		return startPos;
	}*/

	public void setStartPos(Position startPos) {
		this.characterX = startPos.getX_pos();
		this.characterY = startPos.getZ_pos();
		this.flourCharacter = startPos.getY_pos();
	}

	/*public Position getGoalPos() {
		return goalPos;
	}*/

	public void setGoalPos(Position goalPos) {
		this.exitX = goalPos.getX_pos();
		this.exitY = goalPos.getZ_pos();
		this.flourExit = goalPos.getY_pos();
	}
}