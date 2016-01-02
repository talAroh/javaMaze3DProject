package algorithms.mazeGenerators;

public interface Maze3dGenerator {
  public Maze3d generate(int y, int z ,int x);
  public String measureAlgorithmTime(int y, int z ,int x);
}
