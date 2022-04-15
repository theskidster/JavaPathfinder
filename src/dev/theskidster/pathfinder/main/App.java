package dev.theskidster.pathfinder.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.List;
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
    
    Maze maze = new Maze("maze3.txt");
    
    App() {
        jPanel.add(canvas);
        
        maxLength = (maze.cells[0].length > maze.cells.length) ? maze.cells[0].length : maze.cells.length;
        scale     = (int) (Math.floor(HEIGHT / maxLength) * 0.9f);
        xOffset   = (maxLength * scale) / 4;
        
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        jFrame.setResizable(true);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        
        canvas.setIgnoreRepaint(true);
        canvas.setBounds(0, 0, maze.cells[0].length * scale, maze.cells.length * scale);
        canvas.setBackground(Color.GRAY);
        canvas.createBufferStrategy(3);
        bs = canvas.getBufferStrategy();
        
        canvas.requestFocus();
    }
    
    void loop() {
        boolean ticked;
        final double TARGET_DELTA = 1 / 30.0;
        double prevTime = System.currentTimeMillis();
        double currTime;
        double delta = 0;
        int count = 0;
        
        List<Point> path = null;
        
        while(Thread.currentThread().isAlive()) {
            currTime = System.currentTimeMillis() * 0.01f;
            
            delta += currTime - prevTime;
            if(delta < TARGET_DELTA) delta = TARGET_DELTA;
            prevTime = currTime;
            ticked   = false;
            
            while(delta >= TARGET_DELTA) {
                delta -= TARGET_DELTA;
                ticked = true;
                
                count++;
                
                if(count == 60) { //only update on map change?
                    count = 0;
                    path  = pf.findPath(maze);
                }
            }
            
            //TODO: add dynamic maze editing w/ mouse cursor
            
            g = (Graphics2D) bs.getDrawGraphics();
            g.clearRect(0, 0, maze.cells[0].length * scale, maze.cells.length * scale);
            
            for(int y = 0; y < maxLength; y++) {
                for(int x = 0; x < maxLength; x++) {
                    try {
                        int tileID = maze.cells[x][y];
                        
                        switch(tileID) {
                            case 0 -> { g.setColor(Color.WHITE); }
                            case 1 -> { g.setColor(Color.BLACK); }
                            case 2 -> { g.setColor(Color.GREEN); }
                            case 3 -> { g.setColor(Color.RED); }
                        }
                        
                        g.fillRect((x * scale) + xOffset, y * scale, scale, scale);
                    } catch(ArrayIndexOutOfBoundsException e) {}
                }
            }
            
            if(path != null) {
                path.forEach(point -> {
                    if(!point.equals(maze.start) && !point.equals(maze.end)) {
                        g.setColor(Color.BLUE);
                        g.fillRect((point.x * scale) + xOffset, point.y * scale, scale, scale);
                    }
                });
            }
            
            g.dispose();
            bs.show();
            
            if(!ticked) {
                try {
                    Thread.sleep(1);
                } catch(InterruptedException e) {}
            }
        }
    }

}