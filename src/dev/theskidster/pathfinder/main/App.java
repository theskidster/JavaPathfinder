package dev.theskidster.pathfinder.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
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
    
    private JFrame jFrame = new JFrame("Java Pathfinder Example.");
    private Canvas canvas = new Canvas();
    private JPanel jPanel = (JPanel) jFrame.getContentPane();
    
    private BufferStrategy bs;
    
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
        canvas.setBackground(new Color(249, 249, 249));
        canvas.createBufferStrategy(3);
        bs = canvas.getBufferStrategy();
        
        canvas.requestFocus();
    }
    
    void loop() {
        
        
        
    }

}