package algorithms.search;

import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {

  protected PriorityQueue<State<T>> openList;
  private int evaluatedNodes;
  
  public CommonSearcher(){
	  openList = new PriorityQueue<State<T>>();
	  evaluatedNodes = 0;
  }
  
  protected State<T> popOpenList(){
	  evaluatedNodes++;
	  return openList.poll();
  }
  
  public abstract double costCalculator(State<T> s);
  
  public void addOpenList(State<T> s){
	  s.setCost(costCalculator(s));
	  openList.add(s);
  }
  
  @Override
  public abstract Solution<T> search(Searchable<T> s);
  
  @Override
  public int getNumberOfNodesEvaluated(){
	  return evaluatedNodes;
  }
  
  public Solution<T> back(State<T> s,State<T> start)
  {
	  Solution<T> sol = new Solution<T>();
	  while(!s.equals(start))
	  {
		  sol.addState(s);
		  s = s.getCameFrom();
	  }
	  return sol;
  }
  
}
