package player;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import mazeGen.node.Node;

public class Player {

	int xPos, yPos;
	int playerSize = 10;
	int cellSize;
	Node pCurrentNode;
	int displace;

	// Movement

	public Node getpCurrentNode() {
		return pCurrentNode;
	}

	public void setpCurrentNode(Node pCurrentNode) {
		this.pCurrentNode = pCurrentNode;
	}

	public Player() {
		xPos = 0;
		yPos = 0;
		Node pCurrentNode = new Node(xPos, yPos);
	}

	public Player(int s, int cS, int d) {
		xPos = 0;
		yPos = 0;
		Node pCurrentNode = new Node(xPos, yPos);
		this.cellSize = cS;
		this.displace = d;

		playerSize = s / 2;
	}

	public void draw(Graphics g) {

		g.setColor(Color.red);
		g.fillRect(xPos*cellSize+(cellSize/5)+displace, yPos*cellSize+(cellSize/5)+displace, playerSize, playerSize);
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

	public void updateNode() {
		pCurrentNode.setxPos(xPos);
		pCurrentNode.setxPos(yPos);
		
	}

}
