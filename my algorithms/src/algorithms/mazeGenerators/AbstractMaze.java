package algorithms.mazeGenerators;

public abstract class AbstractMaze implements Maze3dGenerator {

	@Override
	public abstract Maze3d generate(int y, int z, int x);

	@Override
	public String measureAlgorithmTime(int y,int z,int x) {
	 long startTime = System.currentTimeMillis();
	 generate(y,z,x);
	 long finishTIme = System.currentTimeMillis();
	 Long total = finishTIme - startTime;
	 
	 return "the algorithm time is: "+total.toString();

	}

}
