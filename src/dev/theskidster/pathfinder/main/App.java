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
    
    private final int WIDTH  = 640;
    private final int HEIGHT = 480;
    private final int scale;
    private final int maxLength;
    private final int xOffset;
    
    private final JFrame jFrame = new JFrame("Java Pathfinder Example.");
    private final Canvas canvas = new Canvas();
    private final JPanel jPanel = (JPanel) jFrame.getContentPane();
    
    private final BufferStrategy bs;
    private Graphics2D g;
    
    private PathFinder pf = new PathFinder();
    
    App() {
        jPanel.add(canvas);
        
        maxLength = (pf.map[0].length > pf.map.length) ? pf.map[0].length : pf.map.length;
        scale     = (int) (Math.floor(HEIGHT / maxLength) * 0.9f);
        xOffset   = (maxLength * scale) / 4;
        
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        jFrame.setResizable(true);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        
        canvas.setIgnoreRepaint(true);
        canvas.setBounds(0, 0, pf.map[0].length * scale, pf.map.length * scale);
        canvas.setBackground(Color.GRAY);
        canvas.createBufferStrategy(3);
        bs = canvas.getBufferStrategy();
        
        canvas.requestFocus();
    }
    
    void loop() {
        while(Thread.currentThread().isAlive()) {
            g = (Graphics2D) bs.getDrawGraphics();
            g.clearRect(0, 0, pf.map[0].length * scale, pf.map.length * scale);
            
            for(int x = 0; x < maxLength; x++) {
                for(int y = 0; y < maxLength; y++) {
                    try {
                        int tileID = pf.map[x][y];

                        g.setColor((tileID == 0) ? Color.WHITE : Color.BLACK);
                        g.fillRect((y * scale) + xOffset, x * scale, scale, scale);
                    } catch(ArrayIndexOutOfBoundsException e) {}
                }
            }
            
            g.dispose();
            bs.show();
        }
    }

}