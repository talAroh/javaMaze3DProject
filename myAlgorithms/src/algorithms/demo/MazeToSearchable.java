package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

public class MazeToSearchable implements Searchable<Position> {
    
	Maze3d maze;

	public MazeToSearchable(Maze3d mz) {
		super();
		this.maze = mz;
	}
	
	@Override
	public State<Position> getInitialState(){
		State<Position> p = new State<Position>();
		p.setState(maze.getStartPos());
		p.setCost(0);
		p.setCameFrom(null);
		
		return p;	
	}
	
	@Override
	public State<Position> getGoalState(){
		State<Position> g = new State<>();
		g.setState(maze.getGoalPosition());
		g.setCost(0);
		g.setCameFrom(null);
		
		return g;
	}
	
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s){
		
		String[] Pmoves =  maze.getPossibleMoves(s.getState());
		ArrayList<State<Position>> stMoves = new ArrayList<State<Position>>(); 
		for (String move : Pmoves) {
			
			if(move == "left"){
               Position p = new Position(s.getState().getY(), s.getState().getZ(), s.getState().getX()+1);
               s.setState(p);
               stMoves.add(s);
			}
			
			if(move == "right"){
	               Position p = new Position(s.getState().getY(), s.getState().getZ(), s.getState().getX()-1);
	               s.setState(p);
	               stMoves.add(s);
			}
			
			if(move == "up"){
	               Position p = new Position(s.getState().getY()+1, s.getState().getZ(), s.getState().getX());
	               s.setState(p);
	               stMoves.add(s);
			}
			
			if(move == "down"){
	               Position p = new Position(s.getState().getY()-1, s.getState().getZ(), s.getState().getX());
	               s.setState(p);
	               stMoves.add(s);
			}
			
			if(move == "forward"){
	               Position p = new Position(s.getState().getY(), s.getState().getZ()+1, s.getState().getX());
	               s.setState(p);
	               stMoves.add(s);
			}
			
			if(move == "backwards"){
	               Position p = new Position(s.getState().getY(), s.getState().getZ()-1, s.getState().getX()+1);
	               s.setState(p);
	               stMoves.add(s);
			}
		}
		return stMoves;
	}
}
