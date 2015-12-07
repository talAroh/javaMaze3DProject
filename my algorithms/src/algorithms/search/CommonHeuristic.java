package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract class CommonHeuristic<T> implements Heuristic<Position> {
   
	State<Position> goal;
	
	@Override
	public abstract double h(State<Position> s);

	public CommonHeuristic(State<Position> g) {
		this.goal = g;
	}

	public void setGoal(State<Position> goal) {
		this.goal = goal;
	}
	public State<Position> getGoal(){
		return goal;
	}
	
	
}
