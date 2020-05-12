package mazeGen;

import java.awt.Color;
import java.awt.Graphics;

import mazeGen.node.MazeGenerator;
import mazeGen.node.Node;
import mazeGen.node.NodeReference;

public class MazeObj {

	int displace = 2;
	
	Node finishNode;

	int cellSize;

	MazeGenerator mazeGen;

	public MazeObj(int x, int y, int cs, int d) {
		mazeGen = new MazeGenerator(x, y);
		cellSize = cs;
		this.displace = d;
		
		finishNode = new Node(mazeGen.getNodeArray().length-1, mazeGen.getNodeArray()[0].length-1);
	}

	public boolean boundaryCheck(Node n, char c, int x, int y) {

		Node[][] nodeArrayCheck = mazeGen.getNodeArray();

		switch (c) {
		case 'N':
			if (linkedNodesCheck(x, y, x, y - 1, 1)) {
				return true;
			}
			break;

		case 'E':
			if (linkedNodesCheck(x, y, x + 1, y, 0)) {
				return true;
			}
			break;

		case 'S':
			if (linkedNodesCheck(x, y, x, y + 1, 1)) {
				return true;
			}

			break;

		case 'W':
			if (linkedNodesCheck(x, y, x - 1, y, 0)) {
				return true;
			}
			break;

		}

		return false;
	}

	public boolean linkedNodesCheck(int x, int y, int xa, int ya, int z) {

		Node[][] nodeArrayCheck = mazeGen.getNodeArray();
		NodeReference node = new NodeReference(xa, ya);

		for (int i = 0; i < nodeArrayCheck[x][y].getLinkedNodes().size(); i++) {
//			System.out.println("bing");

//			System.out.println(nodeArrayCheck[x][y].getLinkedNodes().get(i).getxPos() + ", "
//					+ nodeArrayCheck[x][y].getLinkedNodes().get(i).getyPos());

			if (z == 0)
				if (xa == nodeArrayCheck[x][y].getLinkedNodes().get(i).getxPos()) {

					return true;
				}
			if (z == 1)
				if (ya == nodeArrayCheck[x][y].getLinkedNodes().get(i).getyPos()) {

					return true;
				}

		}

		return false;
	}

	// private void reverseNodeCheck() {
	// for (int i = 0; i < nodeArrayCheck[x][y].getLinkedNodes().size(); i++)
	//
	// }

	public void paint(Graphics g) {

		Node[][] nodeArrayDraw = mazeGen.getNodeArray();

		// Render node links
		char wall;
		g.setColor(Color.white);
		for (int i = 0; i < nodeArrayDraw.length; i++) {
			for (int j = 0; j < nodeArrayDraw[i].length; j++) {

				if (nodeArrayDraw[i][j].getLinkedNodes().size() > 0) {

					for (int k = 0; k < nodeArrayDraw[i][j].getLinkedNodes().size(); k++) {

						wall = nodeOrientation((nodeArrayDraw[i][j]), k);

						int nTX = (nodeArrayDraw[i][j].getxPos() * cellSize + displace);

						int nTY = (nodeArrayDraw[i][j].getyPos() * cellSize + displace);

						int expansion = cellSize - (cellSize / 6);

						if (wall == 'N') {
							// System.out.println("N");
							g.fillRect(nTX, nTY - cellSize, expansion, cellSize + expansion);
						}

						if (wall == 'E') {
							// System.out.println("E");
							g.fillRect(nTX, nTY, cellSize + expansion, expansion);

						}

						if (wall == 'S') {
							// System.out.println("S");
							g.fillRect(nTX, nTY, expansion, cellSize + expansion);
						}

						if (wall == 'W') {
							// System.out.println("W");
							g.fillRect(nTX - cellSize, nTY, cellSize + expansion, expansion);
						}
					}

				}

			}
		}
		
		// Render finish node
		g.setColor(Color.green);
		g.fillRect(finishNode.getxPos()*cellSize + (cellSize/6), finishNode.getyPos()*cellSize + (cellSize/6), cellSize - (cellSize/6), cellSize - (cellSize/6));
		
		// Render nodes
//		g.setColor(Color.green);
//		for (int i = 0; i < nodeArrayDraw.length; i++) {
//			for (int j = 0; j < nodeArrayDraw[i].length; j++) {
//				g.fillRect((nodeArrayDraw[i][j].getxPos() * cellSize) + (cellSize / 2) - 1 + displace,
//						(nodeArrayDraw[i][j].getyPos() * cellSize) + (cellSize / 2) - 1 + displace, 2, 2);
//			}
//		}

	}

	public Node getFinishNode() {
		return finishNode;
	}
	
	
	public char nodeOrientation(Node n, int i) {

		// North Wall?
		if (n.getyPos() > n.getLinkedNodes().get(i).getyPos()) {
			return 'N';
		}

		// East Wall?
		if (n.getxPos() < n.getLinkedNodes().get(i).getxPos()) {
			return 'E';
		}

		// South Wall?
		if (n.getyPos() < n.getLinkedNodes().get(i).getyPos()) {
			return 'S';
		}

		// West Wall?
		if (n.getxPos() > n.getLinkedNodes().get(i).getxPos()) {
			return 'W';
		}

		n.getLinkedNodes().get(i);

		return 0;
	}

}
