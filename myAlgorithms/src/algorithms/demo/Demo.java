package algorithms.demo;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.Heuristic;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattenDistance;
import algorithms.search.Searchable;
import algorithms.search.Solution;
import algorithms.search.State;
import algorithms.search.Heuristic;

public class Demo {
    public static void run(){ 
    	MyMaze3dGenerator mg = new MyMaze3dGenerator();
	    Maze3d maze  =  mg.generate(5, 100, 100);
	    maze.printMaze();
	    State<Position> goal = new State<Position>(maze.getGoalPosition());
	   // State<Position> start = new State<Position>(maze.getStartPos());
	    Searchable<Position> mts = new MazeToSearchable(maze);
	    Solution<Position> sol = new Solution<Position>();
	    BFS<Position> bfs = new BFS<Position>();
	    sol = bfs.search(mts);
	    //sol.printSol();
	   /* System.out.println("BFS:"+bfs.getNumberOfNodesEvaluated());
	    Heuristic<Position> hmmd = new MazeManhattenDistance<Position>(goal);
	    Heuristic<Position> hmad = new MazeAirDistance<Position>(goal);
	    Astar<Position> astarmanh = new Astar<Position>(hmmd);
	    Astar<Position> astarair = new Astar<Position>(hmad);
	    astarmanh.search(mts);
	    astarair.search(mts);
	    System.out.println("Astar manhattan:"+astarmanh.getNumberOfNodesEvaluated());
	    System.out.println("Astar air:"+astarair.getNumberOfNodesEvaluated());*/
	    System.out.println("change in demo");
	      
    }
	
	public static void main(String[] args) {
		run();
	}
}
