package algorithms.search;

public class State<T> {
	private T state;
	private double cost;
	private State<T> cameFrom;
	
	public State(T state){
		this.state = state;
	}
	
	public State(){
		
	}
	public boolean equals(State<T> s){
		return state.equals(s.state);
	}

	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
   	

}
