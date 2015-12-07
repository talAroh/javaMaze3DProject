package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMaze3dGenerator extends AbstractMaze {

	@Override
	public Maze3d generate(int y,int z,int x) {
		Maze3d maze = new Maze3d(y, z, x);
		Random rand = new Random();
		int val;
		
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < z; j++) {
				for (int j2 = 0; j2 < x; j2++) {
				val = rand.nextInt(2);
				maze.setVal(i, j, j2, val);
				}
			}
		}
		
		Position Start = maze.getStartPos();
		Position End = maze.getGoalPosition();
		
	    while(Start.equals(End))
	    {
	    	val = rand.nextInt(6);
	    	
	    	if(val == 0&& Start.getX()>0)
	    		Start.setX(Start.getX()-1);
	    	if(val == 1 && Start.getX()<x)
	    		Start.setX(Start.getX()+1);
	    	
	    	if(val == 2 && Start.getY()>0)
	    		Start.setY(Start.getY()-1);
	    	if(val == 3 && Start.getY()<y)
	    		Start.setY(Start.getY()+1);
	    	
	    	if(val == 4 && Start.getZ()>0)
	    		Start.setZ(Start.getZ()-1);
	    	if(val == 5 && Start.getZ()<z)
	    		Start.setZ(Start.getZ()+1);	    
	    }
		
		return maze;
		
	}

}
