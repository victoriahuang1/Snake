import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	private final SnakeNode head;
	private SnakeNode tail;
	private int size;
	public static final int WIDTH = 20;

	public Snake() {
		head = new SnakeNode();
		tail = head;
		size = 0; // head doesn't count
	}
	
	public void move(Direction d) {
		if (! isHit()) {
			SnakeNode curr = tail;
			while (curr != head) {
				curr.shift(curr.prev.getDir());
				curr = curr.prev;
			}
			head.shift(d);
		}
		
	}
	
	public void draw(Graphics g){
		SnakeNode curr = head;
		curr.draw(g);
		while (curr != tail) {
			curr = curr.next;
			curr.draw(g);
		}
	}
	
	public int getX() {
		return head.pos_x;
	}
	
	public int getY() {
		return head.pos_y;
	}
	
	public void addNew(Food f) {
		SnakeNode newNode = new SnakeNode(tail, f);
		tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	public boolean isHit() {
		SnakeNode curr = tail;
		int EPSILON = 2;
		while (curr != head) {
			if ((head.pos_x >= curr.pos_x && head.pos_x <= curr.pos_x + EPSILON 
				&& head.pos_y >= curr.pos_y && head.pos_y <= curr.pos_y + EPSILON)) {
				return true;
			}
			curr = curr.prev;
		}
		return head.pos_x < 0 || head.pos_x > GameCourt.COURT_LENGTH - WIDTH + 5 
			|| head.pos_y < 0 || head.pos_y > GameCourt.COURT_LENGTH - WIDTH + 5;
	}
	
	public int getSize() {
		return size;
	}
	
	private class SnakeNode {		
		private int pos_x;
		private int pos_y;
		private int vel_x;
		private int vel_y;
		private final Color c;
		private final SnakeNode prev;
		private SnakeNode next;
		
		public SnakeNode() {
			prev = null;
			vel_x = 0;
			vel_y = -5;
			pos_x = GameCourt.COURT_LENGTH / 2;
			pos_y = GameCourt.COURT_LENGTH / 2;
			c = Color.YELLOW;
		}
		
		public SnakeNode(SnakeNode last, Food f) {
			prev = last;
			vel_x = last.vel_x;
			vel_y = last.vel_y;
			this.c = f.getColor();
			if (last.getDir() == Direction.DOWN) {
				pos_x = last.pos_x;
				pos_y = last.pos_y - WIDTH;
			} else if (last.getDir() == Direction.LEFT) {
				pos_x = last.pos_x + WIDTH;
				pos_y = last.pos_y;
			} else if (last.getDir() == Direction.RIGHT) {
				pos_x = last.pos_x - WIDTH;
				pos_y = last.pos_y;
			} else {
				pos_x = last.pos_x;
				pos_y = last.pos_y + WIDTH;
			}
			
		}
		
		public void shift(Direction d) {
			if (prev != null) {
				vel_x = prev.vel_x;
				vel_y = prev.vel_y;
				pos_x = prev.pos_x;
				pos_y = prev.pos_y;
			} else {
				if (d != null) {
					changeDir(d);
				}
				pos_x += vel_x;
				pos_y += vel_y;
			}
		}
		
		Direction getDir() {
			if (vel_y == 5) {
				return Direction.DOWN;
			} else if (vel_y == -5) {
				return Direction.UP;
			} else if (vel_x == 5) {
				return Direction.RIGHT;
			} else {
				return Direction.LEFT;
			}
		}
		
		void changeDir(Direction d) {
			if (d == Direction.DOWN && vel_y != -5) {
				vel_x = 0;
				vel_y = 5;
			} else if (d == Direction.LEFT && vel_x != 5) {
				vel_x = -5;
				vel_y = 0;
			} else if (d == Direction.RIGHT && vel_x != -5) {
				vel_x = 5;
				vel_y = 0;
			} else if (d == Direction.UP && vel_y != 5) {
				vel_x = 0;
				vel_y = -5;
			}
		}
		
		public void draw(Graphics g){
			g.setColor(c);
			g.fillOval(pos_x, pos_y, WIDTH, WIDTH);
		}
	}
}
