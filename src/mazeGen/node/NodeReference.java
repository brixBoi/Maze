package mazeGen.node;

public class NodeReference {

	int xPos;
	int yPos;
	
	public NodeReference(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

}
