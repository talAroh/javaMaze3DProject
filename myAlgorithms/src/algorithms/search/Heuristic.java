package algorithms.search;

public interface Heuristic<T> {
	
    public double h(State<T> s);
}
