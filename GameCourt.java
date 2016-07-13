import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameCourt extends JPanel{

    public static final int COURT_LENGTH = 700;
    public static final int INTERVAL = 20;
    private Snake snake;
    private Food food;
    private Color[] colors;
    private Random rand;
    
    public GameCourt(){
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // make background white
        setBackground(Color.WHITE);
        
        rand = new Random();
        colors = new Color[] {Color.RED, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE};
        reset();
        
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (! hasLost()) {
                    tick();
            	}
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!
        
        setFocusable(true);
        
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_DOWN){
            		snake.move(Direction.DOWN);
            	} else if (e.getKeyCode() == KeyEvent.VK_UP){
            		snake.move(Direction.UP);
            	} else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            		snake.move(Direction.LEFT);
            	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            		snake.move(Direction.RIGHT);
            	}
            }
        });
    }
    
    void tick(){
        // TODO
    	snake.move(null);
    	repaint();
    	if(hasEaten()) {
    		snake.addNew(food);
    		newFood();
    	}
    	if (hasLost()) {
    		JOptionPane.showMessageDialog(null, "GAME OVER!");
    	}
    }
    
    public void reset(){
        // TODO
    	snake = new Snake();
        newFood();
        repaint();

        requestFocusInWindow();
    }
    
    void newFood() {
    	food = new Food(rand.nextInt(COURT_LENGTH - Food.WIDTH), rand.nextInt(COURT_LENGTH - Food.WIDTH), colors[rand.nextInt(colors.length)]);
    }
    
    public int getScore(){
        return snake.getSize();
    }
    
    public boolean hasLost() {
    	return snake.isHit();
    }
    
    boolean hasEaten() {
    	return (food.getX() >= snake.getX() && food.getX() <= snake.getX() + Snake.WIDTH 
			&& food.getY() >= snake.getY() && food.getY() <= snake.getY() + Snake.WIDTH)
			|| (snake.getX() >= food.getX() && snake.getX() <= food.getX() + Food.WIDTH 
				&& snake.getY() >= food.getY() && snake.getY() <= food.getY() + Food.WIDTH);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        snake.draw(g);
        food.draw(g);
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(COURT_LENGTH, COURT_LENGTH);
    }
    
}
