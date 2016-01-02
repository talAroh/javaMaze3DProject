package algorithms.search;

import java.util.ArrayList;

public class Solution<T> {
   ArrayList<State<T>> sol;
   
   public Solution(){
	   sol = new ArrayList<State<T>>();
   }
   
   public void addState(State<T> s){
	   sol.add(s);
   }
   
   public void printSol(){
	   for (State<T> state : sol) {
		System.out.println(" "+state.getState().toString()+"\n");
	}
   }
	
}
