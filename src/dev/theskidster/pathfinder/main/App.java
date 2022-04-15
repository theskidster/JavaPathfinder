package dev.theskidster.pathfinder.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Apr 15, 2022
 */

/**
 * @author J Hoffman
 * @since  
 */
class App {
    
    private final int WIDTH  = 500;
    private final int HEIGHT = 500;
    
    private final JFrame jFrame = new JFrame("Java Pathfinder Example.");
    private final Canvas canvas = new Canvas();
    private final JPanel jPanel = (JPanel) jFrame.getContentPane();
    
    private final BufferStrategy bs;
    private Graphics2D g;
    
    private PathFinder pf = new PathFinder();
    
    App() {
        jPanel.add(canvas);
        
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        
        canvas.setIgnoreRepaint(true);
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setBackground(Color.WHITE);
        canvas.createBufferStrategy(3);
        bs = canvas.getBufferStrategy();
        
        canvas.requestFocus();
    }
    
    void loop() {
        while(Thread.currentThread().isAlive()) {
            g = (Graphics2D) bs.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);
            
            for(int x = 0; x < pf.map.length; x++) {
                for(int y = 0; y < pf.map.length; y++) {
                    int tileID = pf.map[x][y];
                    
                    g.setColor((tileID == 0) ? Color.RED : Color.BLACK);
                    g.fillRect(y * 20, x * 20, 20, 20);
                }
            }
            
            g.dispose();
            bs.show();
        }
    }

}