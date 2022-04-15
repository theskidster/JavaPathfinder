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
    
    Maze maze = new Maze("maze2.txt");
    
    Point start = new Point(0, 0, null);
    Point end   = new Point(5, 6, null);
    
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
        List<Point> path = pf.findPath(maze.cells, start, end);
        
        while(Thread.currentThread().isAlive()) {
            g = (Graphics2D) bs.getDrawGraphics();
            g.clearRect(0, 0, maze.cells[0].length * scale, maze.cells.length * scale);
            
            for(int y = 0; y < maxLength; y++) {
                for(int x = 0; x < maxLength; x++) {
                    try {
                        int tileID = maze.cells[x][y];
                        
                        if(x == start.x && y == start.y)  g.setColor(Color.GREEN);
                        else if(x == end.x && y == end.y) g.setColor(Color.RED);
                        else                              g.setColor((tileID == 0) ? Color.WHITE : Color.BLACK);
                        
                        g.fillRect((x * scale) + xOffset, y * scale, scale, scale);
                    } catch(ArrayIndexOutOfBoundsException e) {}
                }
            }
            
            if(path != null) {
                path.forEach(point -> {
                    if(!point.equals(start) && !point.equals(end)) {
                        g.setColor(Color.BLUE);
                        g.fillRect((point.x * scale) + xOffset, point.y * scale, scale, scale);
                    }
                });
            }
            
            g.dispose();
            bs.show();
        }
    }

}