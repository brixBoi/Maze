package gameStart;

import java.awt.Color;
import java.awt.Graphics;

public class Gui {
	
	private boolean finish;
	
	public Gui() {
		finish = false;
	}

	public void draw(Graphics g, int moves, int x, int y) {
		String strMove = "Moves: " + moves;
		String strFinish = "Finished!";
		
		g.setColor(Color.white);
		g.fillRect(x, y-15, 90, 20);
		g.setColor(Color.blue);
		g.drawString(strMove, x, y);
		
		
		g.setColor(Color.white);
			g.fillRect(x, y+15, 55,20);
		if(finish) {
			g.setColor(Color.blue);
			g.drawString(strFinish, x, y+30);
		}
			
		
	}

	public void setFinish(boolean b) {
		finish = b;
	}

}
