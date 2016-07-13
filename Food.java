import java.awt.Color;
import java.awt.Graphics;

public class Food {
	public final static int WIDTH = 20;
	
	private final int pos_x;
	private final int pos_y;
	private final Color c;
	
	public Food(int x, int y, Color c) {
		pos_x = x;
		pos_y = y;
		this.c = c;
	}
	
	public int getX() {
		return pos_x;
	}
	
	public int getY() {
		return pos_y;
	}
	
	public Color getColor() {
		return c;
	}
	
	public void draw(Graphics g){
		g.setColor(c);
		g.fillOval(pos_x, pos_y, WIDTH, WIDTH);
	}
}
