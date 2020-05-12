 package mazeGen.node;

import java.util.ArrayList;
import java.util.List;

public class Node {

	// Define variables
	boolean visited = false;
	List<NodeReference> linkedNodes = new ArrayList<NodeReference>();
	int xPos, yPos;

	public Node(int x, int y) {
		this.xPos = x;
		this.yPos = y;

	}

	// Getters

	public boolean isVisited() {
		return visited;
	}

	public List<NodeReference> getLinkedNodes() {
		return linkedNodes;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	// Setters

	public void linkNode(int x, int y) {
		linkedNodes.add(new NodeReference(x, y));
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setLinkedNodes(List<NodeReference> linkedNodes) {
		this.linkedNodes = linkedNodes;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

}
