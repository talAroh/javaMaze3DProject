package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeAirDistance<T> extends CommonHeuristic<T> {

	public MazeAirDistance(State<Position> g) {
		super(g);
	}

	@Override
	public double h(State<Position> s) {
		double x = getGoal().getState().getX()-s.getState().getX();
		double y = getGoal().getState().getY()-s.getState().getY();
		double z = getGoal().getState().getZ()-s.getState().getZ();
		double distance = Math.pow(x, 2) + Math.pow(y, 2) +Math.pow(z, 2);
		double airD = Math.sqrt(distance);
		return airD;
				
	}

}
