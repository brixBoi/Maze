package gameStart;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mazeGen.MazeObj;
import mazeGen.node.MazeGenerator;
import mazeGen.node.Node;
import player.Player;

// Runnable allows applet to run on own, key listener allows monitoring of keyboard presses
public class Start extends Applet implements Runnable, KeyListener {

	// Set variables
	// Double buffering prevention
	Graphics gfx;
	Image img;

	// Variables
	int time = 40;
	int mazeSizeX = 30;
	int mazeSizeY = 30;

	int playerMoves = 0;

	int cellSize = 20;

	int displace = 35;
	int inDisplace = cellSize / 6;

	// Objects
	Thread thread;
	Player player;
	MazeObj maze;
	Gui gui;
	boolean gameRun = true;

	// Spagett
	Node npCheck;

	public void init() {

		// Set the applet size
		this.resize(mazeSizeX * cellSize + 250, mazeSizeY * cellSize + 70);
		time = 40;

		// Initialize objects
		player = new Player(cellSize, cellSize, inDisplace);
		maze = new MazeObj(mazeSizeX, mazeSizeY, cellSize, inDisplace);
		gui = new Gui();
		npCheck = new Node(player.getxPos(), player.getyPos());

		img = createImage(mazeSizeX * cellSize + inDisplace, mazeSizeY * cellSize + inDisplace);
		gfx = img.getGraphics();
		this.addKeyListener(this);

		// Thread is used for timing repainting
		thread = new Thread(this);
		thread.start();

	}

	public void paint(Graphics g) {
		// Set the background color
		gfx.setColor(Color.black);
		// Set the size to match the applet
		gfx.fillRect(0, 0, mazeSizeX * cellSize + inDisplace, mazeSizeY * cellSize + inDisplace);

		maze.paint(gfx);
		player.draw(gfx);
		gui.draw(g, playerMoves, (mazeSizeX * cellSize) + 100, (mazeSizeY * cellSize) / 2);

		// Double buffering thing
		g.drawImage(img, 0 + displace, 0 + displace, null);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void repaint(Graphics g) {
		paint(g);

	}

	// Continuously updates applet
	@Override
	public void run() {
		for (;;) {
			// System.out.println(player.getpCurrentNode());
			if (npCheck.getxPos() == maze.getFinishNode().getxPos()
					&& npCheck.getyPos() == maze.getFinishNode().getyPos()) {
				gui.setFinish(true);
				gameRun = false;
			}

			this.repaint();
			try {
				// Determines update speed
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	// Triggered on keypress
	@Override
	public void keyPressed(KeyEvent e) {

		if (gameRun) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				if (maze.boundaryCheck(player.getpCurrentNode(), 'N', player.getxPos(), player.getyPos())) {
					player.setyPos(player.getyPos() - 1);
					npCheck = new Node(player.getxPos(), player.getyPos());
					playerMoves++;
				}

			}

			if (e.getKeyCode() == KeyEvent.VK_D) {
				if (maze.boundaryCheck(player.getpCurrentNode(), 'E', player.getxPos(), player.getyPos())) {
					player.setxPos(player.getxPos() + 1);
					npCheck = new Node(player.getxPos(), player.getyPos());
					playerMoves++;
				}

			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				if (maze.boundaryCheck(player.getpCurrentNode(), 'S', player.getxPos(), player.getyPos())) {
					player.setyPos(player.getyPos() + 1);
					npCheck = new Node(player.getxPos(), player.getyPos());
					playerMoves++;
				}

			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				if (maze.boundaryCheck(player.getpCurrentNode(), 'W', player.getxPos(), player.getyPos())) {
					player.setxPos(player.getxPos() - 1);
					npCheck = new Node(player.getxPos(), player.getyPos());
					playerMoves++;
				}

			}

		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			reset();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void reset() {
		maze = new MazeObj(mazeSizeX, mazeSizeY, cellSize, inDisplace);
		playerMoves = 0;
		player = new Player(cellSize, cellSize, inDisplace);
		gui.setFinish(false);
		gameRun = true;
		npCheck = new Node(player.getxPos(), player.getyPos());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
