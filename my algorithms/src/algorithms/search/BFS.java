package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
//import java.util.PriorityQueue;

import algorithms.mazeGenerators.Position;


public class BFS<T> extends CommonSearcher<T> {
    
	
	@Override
	public Solution<T> search(Searchable<T> s) {
    
		addOpenList(s.getInitialState());
		State<T> start = s.getInitialState();
		State<T> goal = s.getGoalState();
		Solution<T> sol = new Solution<T>();
	    HashSet<State<T>> closed = new HashSet<>();
		
		closed.add(start);
		
		while(!openList.isEmpty())
		{
			State<T> n = popOpenList();//1
			closed.add(n);//2
			if(n.equals(goal))//3
			{
                 return back(n, s.getInitialState());
			}
		    ArrayList<State<T>> successors = s.getAllPossibleStates(n); //4
			
		     	for(State<T> st : successors){ //5
				 if(!closed.contains(st) && !openList.contains(st))
				 {
				 	 st.setCameFrom(n);
				     addOpenList(st);
				 }
				 else{
				 if(costCalculator(n)<st.getCost())
				 	 if(!openList.contains(st)){
						 st.setCameFrom(n);
						 addOpenList(st);
					 }
					 else{
						 popOpenList();
						 st.setCameFrom(n);
						 st.setCost(costCalculator(n));
						 addOpenList(st);
					 }
				 }
			 }
		  }
		 return sol;	
	}	

	@Override
	public double costCalculator(State<T> s)
	{
		if(s.getCameFrom() == null)
			return 0;
		return s.getCost()+1;
	}
}
