package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeManhattenDistance<T> extends CommonHeuristic<T> {

	public MazeManhattenDistance(State<Position> g) {
		super(g);
	}

	@Override
	public double h(State<Position> s) {
	  
      double distance  = (getGoal().getState().getX()-s.getState().getX()) + (getGoal().getState().getY()-s.getState().getY()) + (getGoal().getState().getZ()-s.getState().getZ());
      return distance;
	}

}
