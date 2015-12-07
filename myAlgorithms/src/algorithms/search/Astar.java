package algorithms.search;

public class Astar<T> extends BFS<T> {
	
	Heuristic<T> h;

	public Astar(Heuristic<T> h) {
		super();
		this.h = h;
	}
	
	@Override
	public double costCalculator(State<T> s){
		return super.costCalculator(s) + h.h(s);
	}
}
