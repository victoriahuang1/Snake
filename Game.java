import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Victoria
 * Snake game
 *
 */

public class Game implements Runnable{

    JLabel status;
    
    public void run() {
        final JFrame frame = new JFrame("Snake");
        frame.setLocation(500, 125);
        
        // main playing area
        final GameCourt court = new GameCourt();
        frame.add(court, BorderLayout.CENTER);
        
        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
                status.setText("Score: " + court.getScore());
            }
        });
        control_panel.add(reset);
        
        // Score panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        status = new JLabel("Score: " + court.getScore());
        status_panel.add(status);
        
        Timer timer = new Timer(GameCourt.INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	status.setText("Score: " + court.getScore());
            }
        });
        timer.start();
        
        // Put the frame on the screen
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        court.reset();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
