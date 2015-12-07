package algorithms.mazeGenerators;

public class Position {
  int x;
  int y;
  int z;
  
public Position(int x, int y, int z) {
	this.x = x;
	this.y = y;
	this.z = z;
}
public int getX() {
	return this.x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return this.y;
}
public void setY(int y) {
	this.y = y;
}
public int getZ() {
	return this.z;
}
public void setZ(int z) {
	this.z = z;
}

public boolean equals(Position p){
	if(this.x == p.getX()&& this.y == p.getY()&&this.z == p.getZ())
	{
		return true;
	}
	return false;
}

public Position Left(){
	return new Position(this.getX()+1,this.getY(),this.getZ());
}

public Position Right(){
	return new Position(this.getX()-1,this.getY(),this.getZ());
}

public Position Up(){
	return new Position(this.getX(),this.getY()+1,this.getZ());
}

public Position Down(){
	return new Position(this.getX(),this.getY()-1,this.getZ());
}

public Position Forward(){
	return new Position(this.getX(),this.getY(),this.getZ()+1);
}

public Position Backwards(){
	return new Position(this.getX(),this.getY(),this.getZ()-1);
}


}
