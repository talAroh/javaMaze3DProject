package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class Maze3d 
{
	int [][][] maze3d;
	private int XSize;
	private int YSize;
	private int ZSize;
	
	public Maze3d(int ySize, int zSize, int xSize) {
		XSize = xSize;
		YSize = ySize;
		ZSize = zSize;
		
		this.maze3d = new int [ySize][zSize][xSize];
	}
	
	public int getXSize() {
		return XSize;
	}
	public void setXSize(int xSize) {
		XSize = xSize;
	}
	public int getYSize() {
		return YSize;
	}
	public void setYSize(int ySize) {
		YSize = ySize;
	}
	public int getZSize() {
		return ZSize;
	}
	public void setZSize(int zSize) {
		ZSize = zSize;
	}
	
	public Position getStartPos()
	{
		Random random = new Random();
	    int xStart = random.nextInt(XSize);
	    int yStart = random.nextInt(YSize);
	    int zStart = random.nextInt(ZSize);
	    
	    Position p = new Position(xStart, yStart, zStart);
	    return p;
	}
	

	
	public int getVal(int y,int z,int x){
		return this.maze3d[y][z][x];
	}
	public void setVal(int y,int z,int x,int val){
		this.maze3d[y][z][x] = val;
	}
	
	public String[] getPossibleMoves(Position p)
	{
		ArrayList<String> moves = new ArrayList<String>(); 
		
		try {
			if(this.maze3d[p.Left().getY()][p.Left().getZ()][p.Left().getX()] == 0)
			{
				moves.add("left");
			}
		} catch (Exception e) {
			
		}
		
		try {
			if(this.maze3d[p.Right().getY()][p.Right().getZ()][p.Right().getX()] == 0)
			{
				moves.add("right");
			}
		} catch (Exception e) {

		}
		
		try {
			if(this.maze3d[p.Up().getY()][p.Up().getZ()][p.Up().getX()] == 0)
			{
				moves.add("up");
			}
		} catch (Exception e) {

		}
		
		try {
			if(this.maze3d[p.Down().getY()][p.Down().getZ()][p.Down().getX()] == 0)
			{
				moves.add("down");
			}
		} catch (Exception e) {

		}
		
		try {
			if(this.maze3d[p.Forward().getY()][p.Forward().getZ()][p.Forward().getX()] == 0)
			{
				moves.add("forward");
			}
		} catch (Exception e) {

		}
		
		try {
			if(this.maze3d[p.Backwards().getY()][p.Backwards().getZ()][p.Backwards().getX()] == 0)
			{
				moves.add("backwards");
			}
		} catch (Exception e) {

		}
		
		String[] move = new String[moves.size()];
		moves.toArray(move);
		
		return move;
	}
	
	public Position getGoalPosition()
	{
		Random random = new Random();
	    int xEnd = random.nextInt(XSize);
	    int yEnd = random.nextInt(YSize);
	    int zEnd = random.nextInt(ZSize);
	    if(this.maze3d[yEnd][zEnd][xEnd] == 0)
	    {
	    Position p = new Position(xEnd, yEnd, zEnd);
	    return p;
	    }
	    else {return getGoalPosition();}
	}
	
	public int[][] getCrossSectionByX(int x)
	{
		int [][] crossByX = new int[YSize][ZSize];
		if(x>-1)
		{
			for (int i = 0; i < YSize; i++) {
				for (int j = 0; j < ZSize; j++) {
					crossByX[i][j] = maze3d[i][j][x];
				}
			}
		}
		else {return null;}
		
		return crossByX;
	}
	
	public int[][] getCrossSectionByY(int y)
	{
		int [][] crossByY = new int[YSize][ZSize];
		if(y>-1 && y<YSize)
		{
			for (int i = 0; i < ZSize; i++) {
				for (int j = 0; j < XSize; j++) {
					crossByY[i][j] = maze3d[y][i][j];
				}
			}
		}
		else {return null;}
		
		return crossByY;
	}
	
	public int[][] getCrossSectionByZ(int z)
	{
		
			int [][] crossByZ = new int[YSize][ZSize];
			if(z>-1 && z<ZSize)
			{
				for (int i = 0; i < YSize; i++) {
					for (int j = 0; j < XSize; j++) {
						crossByZ[i][j] = maze3d[i][z][j];
					}
				}
			}
			else {return null;}
		
			return crossByZ;		
	}
	
	public void printMaze()
	{
		for (int i = 0; i < YSize; i++) {
			for (int j = 0; j < ZSize; j++) {
				for (int j2 = 0; j2 < XSize; j2++) {
					System.out.print(this.maze3d[i][j][j2]);
				}
				System.out.println(" ");
			}
			System.out.println("************");
		}
	}
	
}
