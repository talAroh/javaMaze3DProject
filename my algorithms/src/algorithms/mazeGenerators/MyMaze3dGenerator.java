package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3dGenerator extends AbstractMaze {

	@Override
	public Maze3d generate(int y, int z, int x) {
		
		Maze3d maze = new Maze3d(y, z, x);
		Random rd = new Random();
		
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < z; j++) {
				for (int k = 0; k < x; k++) {
					maze.setVal(i, j, k, 1);
				}
			}
		}
		
		ArrayList<Position> walls = new ArrayList<Position>();//position in the maze
		ArrayList<Position> neighbors = new ArrayList<Position>();
		
		Position start = maze.getStartPos();
		maze.setVal(start.getY(), start.getZ(), start.getX(), 0);
		
		
		if(start.getX()+2<x)//left
		{
		   if(maze.getVal((start).getY(), (start).getZ(), (start.Left().Left()).getX()) == 1){	
                   walls.add(start.Left());
                   neighbors.add(start.Left().Left());
		   }
		}
		
		if(start.getX()-2>0)//right
		{
		   if(maze.getVal((start).getY(), (start).getZ(), (start.Right().Right()).getX()) == 1){	
               walls.add(start.Right());
               neighbors.add(start.Right().Right());
		   }
		}
		
		if(start.getY()+1<y)//up
		{
		   if(maze.getVal((start.Up()).getY(), (start).getZ(), (start).getX()) == 1){	
               walls.add(start.Up());
               neighbors.add(start.Up());
		   }
		}
		
		if(start.getY()-1>0)//down
		{
		   if(maze.getVal((start.Down()).getY(), (start).getZ(), (start).getX()) == 1){	
               walls.add(start.Down());
               neighbors.add(start.Down());
		   }
		}
		
		if(start.getZ()+2<z)//forward
		{
		   if(maze.getVal((start).getY(), (start.Forward().Forward()).getZ(), (start).getX()) == 1){	
               walls.add(start.Forward());
               neighbors.add(start.Forward().Forward());
		   }
		}
		
		if(start.getZ()-2>0)//backwards
		{
		   if(maze.getVal((start).getY(), (start.Backwards().Backwards()).getZ(), (start).getX()) == 1){	
               walls.add(start.Backwards());
               neighbors.add(start.Backwards().Backwards());
		   }
		}
		
		
		while((!neighbors.isEmpty())&& (!walls.isEmpty()))////////////////////////////////////////////////////////////////////////////////////////////////////////
		{
			int randIndex = rd.nextInt(neighbors.size());
			Position p = neighbors.get(randIndex);
			//maze.setVal(p.getY(), p.getZ(), p.getX(), 0);
			//////////////////////////////////////////////////////////////////
			if(p.getX()+2<x)
			{
			   if(maze.getVal((p.getY()), (p.getZ()), (p.Left().Left()).getX()) == 1 && maze.getVal((p.getY()), (p.getZ()), (p.Left()).getX()) == 1){	
			     	neighbors.add(p.Left().Left());
			     	walls.add(p.Left());
			     	maze.setVal(p.getY(), p.getZ(), p.Left().Left().getX(), 0);
			     	maze.setVal(p.getY(), p.getZ(), p.Left().getX(), 0);
			   }
			}
			
			if(p.getX()-2>0)
			{
			   if(maze.getVal((p.getY()), (p.getZ()), (p.Right().Right()).getX()) == 1 && maze.getVal((p.getY()), (p.getZ()), (p.Right()).getX()) == 1){	
			     	neighbors.add(p.Right().Right());
			     	walls.add(p.Right());
			     	maze.setVal(p.getY(), p.getZ(), p.Right().Right().getX(), 0);
			     	maze.setVal(p.getY(), p.getZ(), p.Right().getX(), 0);
			   }
			}
			
			if(p.getY()+2<y)
			{
			   if(maze.getVal((p.Up()).getY(), (p).getZ(), (p).getX()) == 1){	
			     	neighbors.add(p.Up());
			     	walls.add(p.Up());
			     	maze.setVal(p.getY(), p.Up().Up().getZ(), p.getX(), 0);
			     	maze.setVal(p.getY(), p.Up().getZ(), p.getX(), 0);
			   }
			}
			
			if(p.getY()-2>0)
			{
			   if(maze.getVal((p.Down()).getY(), (p).getZ(), (p).getX()) == 1){	
			     	neighbors.add(p.Down());
			     	walls.add(p.Down());
			     	maze.setVal(p.getY(), p.getZ(), p.Down().Down().getX(), 0);
			     	maze.setVal(p.getY(), p.getZ(), p.Down().getX(), 0);
			   }
			}
			
			if(p.getZ()+2<z)
			{
			   if(maze.getVal((p).getY(), (p.Forward().Forward()).getZ(), (p).getX()) == 1 && maze.getVal((p).getY(), (p.Forward()).getZ(), (p).getX()) == 1){	
			     	neighbors.add(p.Forward().Forward());
			     	walls.add(p.Forward());
			     	maze.setVal(p.getY(), p.Forward().Forward().getZ(), p.getX(), 0);
			     	maze.setVal(p.getY(), p.Forward().getZ(), p.getX(), 0);
			   }
			}
			
			if(p.getZ()-2>0)
			{
			   if(maze.getVal((p).getY(), (p.Backwards().Backwards()).getZ(), (p).getX()) == 1 && maze.getVal((p).getY(), (p.Backwards()).getZ(), (p).getX()) == 1){	
			     	neighbors.add(p.Backwards().Backwards());
			     	walls.add(p.Backwards());
			     	maze.setVal(p.getY(), p.Backwards().Backwards().getZ(), p.getX(), 0);
			     	maze.setVal(p.getY(), p.Backwards().getZ(), p.getX(), 0);
			   }
			}
			neighbors.remove(randIndex);
			walls.remove(randIndex);
		}
		
		return maze;
	 }
		
}

