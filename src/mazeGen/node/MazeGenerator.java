package mazeGen.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MazeGenerator {

	Node[][] nodeArray;

	public MazeGenerator(int x, int y) {

		nodeArray = nodeGen(x, y);
		nodeArray = pathGen(nodeArray);

	}

	private Node[][] nodeGen(int x, int y) {

		Node[][] na = new Node[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				na[i][j] = new Node(i, j);
				// System.out.println(na[i][j].getxPos() + " " + na[i][j].getyPos());
			}
		}

		return na;
	}

	private Node[][] pathGen(Node[][] in) {
		
		int visitedCells = 0;
		int tempX = 0;
		int tempY = 0;
		int rand;
		Node currentNode = in[tempX][tempY];
		Node nextNode;
		Node lastNode;
		Stack<Node> stack = new Stack<Node>();
		stack.push(currentNode);
		
		in[0][0].setVisited(true);

		List<Node> possibleMoves;

		while (visitCheck(in)) {
			
			possibleMoves = new ArrayList<Node>();
			
			in[currentNode.xPos][currentNode.yPos].setVisited(true);
			
			// Work out possible nodes
			int x = currentNode.xPos;
			int y = currentNode.yPos;

			// North check
			if (y - 1 >= 0 && !in[x][y - 1].visited) {
				possibleMoves.add(in[x][y - 1]);
			}

			// East check
			if (x + 1 < nodeArray.length && !in[x + 1][y].visited) {
				possibleMoves.add(in[x + 1][y]);
			}

			// South check
			if (y + 1 < nodeArray[x].length && !in[x][y + 1].visited) {
				possibleMoves.add(in[x][y + 1]);
			}

			// West check
			if (x - 1 >= 0 && !in[x - 1][y].visited) {
				possibleMoves.add(in[x - 1][y]);
			}
			
			
			// If a move can be made, randomly choose from options and make it the current cell
			if(possibleMoves.size() > 0) {
				
				rand = randomNum(possibleMoves.size()) - 1;
				nextNode = possibleMoves.get(rand);
				in[currentNode.xPos][currentNode.yPos].linkNode(nextNode.xPos, nextNode.yPos);
				lastNode = currentNode;
				currentNode = nextNode;
				in[currentNode.xPos][currentNode.yPos].linkNode(lastNode.xPos, lastNode.yPos);
				stack.push(currentNode);
			} else {
				stack.pop();
				currentNode = stack.peek();
			}

			 printList(possibleMoves);

			// System.out.println(visitedCells);
			visitedCells++;
		}
		
		return in;
	}

	public boolean visitCheck(Node[][] in) {
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[i].length; j++) {
				if (!in[i][j].visited) {
					return true;
				}
			}
		}

		return false;
	}

	public void printList(List<Node> in) {
		for (int i = 0; i < in.size(); i++) {
			System.out.println("       " + in.get(i).xPos + ", " + in.get(i).yPos);
		}
	}

	public void printStack(Stack<Node> in) {
		for (int i = 0; i < in.size(); i++)
			System.out.println(in.get(i).xPos + ", " + in.get(i).yPos);
	}

	public Node[][] getNodeArray() {
		return nodeArray;
	}

	private int randomNum(int i) {
		double randomDouble = Math.random();
		randomDouble = randomDouble * i + 1;
		int randomInt = (int) randomDouble;
		return randomInt;
	}
}
